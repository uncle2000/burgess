package com.uncle2000.libutils;
/*
  @record ==>2015.4.30
 * 1.添加检查长度验证
 * 2.添加字符串验证规则
 */

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.mll.R;


public class Validator {

    /**
     * 判断电话号码是否符合规则
     * 电话号码
     *
     * @return boolean，true代表符号电话号码规则，false不符合电话规则
     */

    public static boolean validate(String reg, String data) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(data);
        return m.matches();
    }


//    /**
//     * 检查字符串是否符合规则
//     * @param text 需要检查的字符串
//     * @param reg  验证的规则
//     * @param msg  验证后提示的消息信息
//     * @return  true 验证成功，false 验证失败
//     */
//    public static boolean checkIsCorrect(Activity mContext,String text, String reg, String msg) {
//
//        if (Validator.validate(reg, text) ){
//            return true;
//        } else {
//            Toast.makeText(mContext, R.string.pleaseinputcorrect, Toast.LENGTH_LONG).show();
//        }
//        return false;
//    }

    public static boolean iscontain(int key, List<Map<Integer, String>> urllist) {
        boolean isc = false;
        //去重复
        for (int i = urllist.size() - 1; i >= 0; i--) {
            Map<Integer, String> map = urllist.get(i);
            if (map.containsKey(key)) {
                isc = true;
                break;
            }
        }

        return isc;

    }


}
