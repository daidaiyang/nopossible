package com.nopossible.utils;

import android.content.Context;

import java.text.DecimalFormat;

public class AppUtil {


    public static final String BASEIMGURL = "http://file.bukenengtech.cn/";

    //判断字符串中是否包含数字和字符
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i<str.length();i++){
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            }
            if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }

    String regex = "^[a-zA-Z0-9]+$";
    boolean isRight = isDigit && isLetter && str.matches(regex);
 return isRight;

}

    //安卓中像素px和dp的转换：
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density; //当前屏幕密度因子
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    /**
     * 获取两位小数
     *
     * @param d
     * @return
     */
    public static String get2xiaoshu(double d) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        String format = df.format(d);
        return format;
    }

    public static String get2xiaoshu(String s) {
        Double d = Double.parseDouble(s);
        DecimalFormat df = new DecimalFormat("#####0.00");
        String format = df.format(d);
        return format;
    }
}
