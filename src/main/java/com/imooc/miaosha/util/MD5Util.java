package com.imooc.miaosha.util;
import org.apache.commons.codec.digest.DigestUtils;
import sun.applet.Main;

public class MD5Util {

    //md5在DigestUtils已经封装了
    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    //我们设置的固定的salt值
    private static final String salt = "1a2b3c4d";

    //第一次md5加密（用户端）
    public static String inputPassToFormPass(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    //第二次加密md5（存储到数据库中）salt:随机的salt
    public static String formPassToDBPass(String formPass, String salt) {
        String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //用户输入的明文密码转化成DB里面里的密码
    public static String inputPassToDbPass(String inputPass, String saltDB) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, saltDB);
        return dbPass;
    }

//    public static void main(String [] args){
//        String i = inputPassToFormPass("123456");
//        System.out.println(i);
//        System.out.println(formPassToDBPass(i,"1a2b3c4d"));
//    }
}
