package com.dist;

import java.io.File;
import java.io.IOException;

public class Test1 {

    public static void main(String[] args) throws IOException {
        String test="fhssjah.F";
        String p="^.+\\.(?i)f$";
        System.out.println(test.matches(p));


    }
}
