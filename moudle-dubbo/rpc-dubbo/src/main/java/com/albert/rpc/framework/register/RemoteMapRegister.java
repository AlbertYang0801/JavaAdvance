package com.albert.rpc.framework.register;


import com.albert.rpc.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteMapRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void regist(String interfaceName, URL url){

        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();

        }
        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();
        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }

    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./dubbo/rpc-dubbo/tmp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./dubbo/rpc-dubbo//tmp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
