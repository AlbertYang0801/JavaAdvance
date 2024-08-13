# coding=utf-8

import pymysql


class MySqlTools():
    '''创建mysql实例, 并提供查询,创建语句等一系列方法'''

    def __init__(self, db_config, type, auto_commit=1000, *args):
        '''初始化,指定游标类型,自动提交数量'''
        self.db_config = db_config
        self.type = type
        self.auto_commit = auto_commit
        self.num = 0
        self._fd_dict = {}
        self._conn, self._curs = self._get_conn_curs(db_config=self.db_config, type=self.type)

    def _get_conn(self, dbconfig_dict):
        conn = pymysql.connect(**dbconfig_dict)
        return conn

    def _get_cursor(self, conn, type='stream'):
        if type == 'stream':
            return conn.cursor(pymysql.cursors.SSCursor)  # 返回流式游标,查询大量数据时不占用内存(返回数据形式是元组)
        elif type == 'dict':
            return conn.cursor(pymysql.cursors.DictCursor)  # 返回字典形式游标,查询出的数据以字典形式返回
        elif type == 'default':
            return conn.cursor()
        else:
            raise Exception("cursor type error")

    def _get_conn_curs(self, db_config, type='stream'):

        conn = self._get_conn(db_config)
        curs = self._get_cursor(conn, type=type)
        return conn, curs

    def select_all(self, sql, *args):
        '''查询全部数据'''
        try:
            self._curs.execute(sql,args)
        except Exception as e:
            print(sql)
            raise e
        data = self._curs.fetchall()
        return data

    def select_one(self, sql, *args):
        '''查询单条数据'''
        try:
            self._curs.execute(sql)
        except Exception as e:
            print(sql)
            raise e
        data = self._curs.fetchone()
        return data

    def select_limit(self, sql, start, step):
        '''limit查询'''
        pass

    def _get_fd(self, file, type='a'):
        '''获取文件操作符'''
        if file not in self._fd_dict:
            fd = open(file, type)
            self._fd_dict[file] = fd
        else:
            fd = self._fd_dict.get(file)
        return fd

    def _write(self, fd, sql):
        fd.write(sql)
        fd.write('\n')

    def execute_sql(self, sql, commit=False, to_file=False):
        '''执行sql语句'''
        try:
            self._curs.execute(sql)
            self.num += 1
            if to_file:
                fd = self._get_fd(to_file)
                self._write(fd, sql)
        except Exception as e:
            print(sql)
            raise e
        if commit:
            self._conn.commit()
        else:
            if self.num % self.auto_commit == 0:
                self.commit_sql()

    def commit_sql(self):
        self._conn.commit()
        print(u"提交缓存, 当前计数: {}".format(self.num))

    def get_insert_sql(self, table, dict):
        '''获取sql插入语句'''
        fields = '`' + '`,`'.join(dict.keys()) + '`'
        values = []
        for k in dict.keys():
            v = dict[k]
            if type(v) not in (int, float, str):
                if not v:
                    values.append('')
                else:
                    s = u'值错误, key: {}, value: {}, type: {}'.format(k, v, type(v))
                    raise Exception(s)
            if v == 'now()':
                values.append(v)
            elif type(v) is str:
                values.append("'{}'".format(v.replace('\\', '\\\\')))
                print(values)
            else:
                values.append('{}'.format(v))
        value_str = ','.join(values)
        return 'INSERT INTO `{}` ({}) VALUE ({})'.format(table, fields, value_str)