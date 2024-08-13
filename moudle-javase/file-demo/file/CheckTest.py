# coding=utf-8

import sys

from MySqlTools import MySqlTools

if __name__ == '__main__':
    db_config={'host':'127.0.0.1', 'port':3306, 'user':'root', 'passwd':'123456', 'db':'bpmtest'}
    #pardict={'a':'123', 'b':11.22, 'c':'ert', 'd':'tre', 'e':'haha'}
    db = MySqlTools(db_config, 'dict', 1000)
    #
    sql = 'select * from test1 where name =%s'
    empty_dict = {}
    try:
        username=sys.argv[1]
        #print("参数----："+username)
        #
        results = db.select_all(sql,username)
        #results1 = db.get_insert_sql('sys_user',pardict)
        # 获取所有记录列表
        tmp=results[0]
        #print(tmp['password'])
        if tmp['password'] is None:
           empty_dict = {"status":"0","msg":"错误"}
        else:
           empty_dict = {"status":"1","msg":"正确"}
        print(empty_dict)
    except:
        empty_dict = {"status": "-1", "msg": "执行异常"}
        print(empty_dict)