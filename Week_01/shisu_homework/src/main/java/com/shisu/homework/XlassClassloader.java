package com.shisu.homework;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class XlassClassloader extends ClassLoader {
    public static void main(String[] args) throws Exception {
        String appPath = "/Users/shihaolin/jiketask/Hello/Hello.xlass";
        InputStream inputStream = new FileInputStream(appPath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n = 0;
        while ((n = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        byte[] data2 = out.toByteArray();
        byte[] newbyte = new byte[data2.length];
        for (int i = 0; i < data2.length; i++) {
            newbyte[i] = (byte) ((byte) 255 - data2[i]);
        }
        Class class2 = new XlassClassloader().findClass(newbyte);
        Method method = class2.getMethod("hello");
        method.invoke(class2.newInstance());
    }

    public Class findClass(byte[] bt) {
        return defineClass("Hello", bt, 0, bt.length);
    }

}
