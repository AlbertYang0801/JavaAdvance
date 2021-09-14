package com.albert.javase.io;

import com.albert.utils.jackson.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.*;

/**
 * 对象流的练习
 *
 * @author yangjunwei
 * @date 2021/9/13 10:47 上午
 */
public class ObjectStreamTest {

    /**
     * 测试对象流的写入
     */
    @Test
    public void testWrite() {
        String path = "src/main/java/com/albert/javase/io/file/data.bat";
        ObjectOutputStream objectOutputStream = null;
        try {
            Person person = new Person("小明", 10);
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            //序列化
            objectOutputStream.writeObject(person);
            objectOutputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 测试对象流的读取
     */
    @Test
    public void testRead() {
        String path = "src/main/java/com/albert/javase/io/file/data.bat";
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(path));
            //反序列化
            Person person = (Person) objectInputStream.readObject();
            System.out.println(JsonUtil.toString(person));
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 测试Externalizable序列化
     */
    @Test
    public void testExternalizableWrite() {
        String path = "src/main/java/com/albert/javase/io/file/data.bat";
        ObjectOutputStream objectOutputStream = null;
        try {
            OldMan oldMan = new OldMan("小明", 10);
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(oldMan);
            objectOutputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * 测试Externalizable反序列化
     */
    @Test
    public void testExternalizableRead() {
        String path = "src/main/java/com/albert/javase/io/file/data.bat";
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(path));
            OldMan person = (OldMan) objectInputStream.readObject();
            System.out.println(JsonUtil.toString(person));
        } catch (IOException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                objectInputStream.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


}


/**
 * 实现Serializable接口支持序列化
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Serializable {

    private static final long serialVersionUID = 475463534532L;

    transient private String name;
    private int age;

}


/**
 * 实现Externalizable接口支持序列化
 * 反序列化机制根据重写的两个方法实现
 * 若没有实现内容，则反序列化的对象属性都是默认值。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
class OldMan implements Externalizable {

    private String name;
    private int age;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.write(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();
        age = in.read();
    }
}

