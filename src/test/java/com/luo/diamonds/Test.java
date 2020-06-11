package com.luo.diamonds;

import com.luo.diamonds.utils.MD5Utils;

public class Test {
    public static void main(String[] args) {
        System.out.println(MD5Utils.getPassword("Lsqlsq123","salt"));
    }
}
