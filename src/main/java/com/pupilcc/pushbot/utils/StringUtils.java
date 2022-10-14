package com.pupilcc.pushbot.utils;

/**
 * 字符串工具类
 *
 * @author pupilcc
 */
public class StringUtils {
    /**
     * 换行符
     */
    private final static String NEW_LINE = "%n";

    /**
     * 替换换行
     *
     * @param s 字符串
     * @return 替换好的字符串
     */
    public static StringBuilder replaceLine(String s) {
        StringBuilder text = new StringBuilder();
        for (String str : s.split(NEW_LINE)) {
            text.append(str).append("\n");
        }
        return text;
    }
}
