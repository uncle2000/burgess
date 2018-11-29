//package com.uncle2000.libutils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.security.MessageDigest;
//
///**
// * Created by danger on 16/10/9.
// * 可计算File,String,byte[]的md5
// */
//public class MD5 {
//
//    private static char[] hexChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
//
//    public static String toHexString(byte[] b) {
//        StringBuilder sb = new StringBuilder(b.length * 2);
//
//        for (byte aB : b) {
//            sb.append(hexChar[(aB & 240) >>> 4]);
//            sb.append(hexChar[aB & 15]);
//        }
//
//        return sb.toString();
//    }
//
//    public static String md5(File f) {
//        try {
//            FileInputStream fis = new FileInputStream(f);
//            byte[] buffer = new byte[1024];
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            boolean numRead = false;
//
//            int numRead1;
//            while ((numRead1 = fis.read(buffer)) > 0) {
//                md5.update(buffer, 0, numRead1);
//            }
//
//            fis.close();
//            return toHexString(md5.digest());
//        } catch (Exception var5) {
//            return "";
//        }
//    }
//
//    /**
//     * encode By MD5
//     *
//     * @param str
//     * @return String
//     */
//    public static String md5(String str) {
//        return md5(str.getBytes());
//    }
//
//    public static String md5(byte[] str) {
//        if (str == null) {
//            return "";
//        }
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.update(str);
//            return toHexString(messageDigest.digest());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
