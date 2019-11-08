package com.dist;

import java.io.*;

public class FileBak {
    public static void main(String[] args) throws FileNotFoundException {
//        File file=new File("test.ddd");
//        System.out.println(file.getCanonicalPath());
////        System.out.println( System.getProperty("user.dir"));
//        System.out.println(new File("G:/test","..").getCanonicalPath());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getPath());
//
//        System.out.println(file.getName()+"aaa");

        //测试文件 修改
        /*File file1=new File("H://test");
           File file2= new File(file1.getParentFile(),"test3");
        file1.renameTo(file2);*/
        System.out.println(FileBak.class.getClassLoader().getResource(""));
        String oldpath="H:\\a//a b c/a.txt";
        String newpath="H:\\a//c/a.txt";
        copyFile(oldpath,newpath);

    }

    public static boolean copyFile(String oldPath, String newPath) {
        try ( InputStream inStream = new FileInputStream(oldPath);
              FileOutputStream fs = new FileOutputStream(newPath);){

            int byteread = 0;
            File oldfile = new File(oldPath);
            File newDir = new File(newPath.replace('\\', '/').substring(0,
                    newPath.replace('\\', '/').lastIndexOf('/')));
            if (!newDir.exists()) {
                newDir.mkdirs();
            }
            if (oldfile.exists()) { // 文件存在时
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                fs.flush();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println(1111);
            return false;
        }
    }
}
