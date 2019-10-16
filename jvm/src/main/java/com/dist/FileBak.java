package com.dist;

import java.io.File;
import java.io.IOException;

public class FileBak {
    public static void main(String[] args) throws IOException {
        File file=new File("test");
        System.out.println(file.getCanonicalPath());
//        System.out.println( System.getProperty("user.dir"));
        System.out.println(new File("G:/test","..").getCanonicalPath());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        ClassLoader
    }
}
