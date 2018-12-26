package com.example.liangweibin.Utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class MyUtil {
    public static final Map<Character, Integer> symLvMap = new HashMap<Character, Integer>();// 符号优先级map
    static {
        symLvMap.put('=', 0);
        symLvMap.put('-', 1);
        symLvMap.put('+', 1);
        symLvMap.put('*', 2);
        symLvMap.put('/', 2);
        symLvMap.put('(', 3);
        symLvMap.put(')', 1);
    }

    public static boolean checkFormat(String str){
        //检验是否以“=”结尾
        if ('='!=str.charAt(str.length()-1)){
            return  false;
        }
        //检验开头是否是“(”
        if (!(str.charAt(0)=='(')){
                return false;
        }
        char c;
        for (int i=1;i<str.length()-1;i++){
            c=str.charAt(i);
            if (!(isCharNum(c))){
                if (c=='.'){
                    if (!isCharNum(str.charAt(i - 1)) || !isCharNum(str.charAt(i + 1))) {// 校验“.”的前后是否位数字
                        return false;
                    }
                }
            }
        }
        return isBracketCouple(str);//检验括号
    }
    /**
     *
     * @Title: isCharNum
     * @Desc: 判断是否为数字
     *
     * @param c
     * @return
     *
     */
    public static boolean isCharNum(Character c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;

    }
    /**
     *  检验括号是否匹配
     * @param str
     * @return
     */
    public static boolean isBracketCouple(String str){
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : str.toCharArray()){
            if (c=='('){
                stack.add(c);
            }
            else if (c==')'){
                stack.removeLast();
            }

        }if (stack.isEmpty()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 加法
     * @param num1
     * @param num2
     * @return
     */
    public static double jia(double num1,double num2){
        return num1+num2;
    }
    /**
     * 减法
     * @param num1
     * @param num2
     * @return
     */
    public static double jian(double num1,double num2){
        return num1-num2;
    }
    /**
     * 乘法
     * @param num1
     * @param num2
     * @return
     */
    public static double cheng(double num1,double num2){
        return num1*num2;
    }
    /**
     * 除法
     * @param num1
     * @param num2
     * @return
     */
    public static double chu(double num1,double num2){
        return num1/num2;
    }
}
