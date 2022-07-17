package com.pupilcc.pushbot.service;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 模板
 *
 * @author pupilcc
 * @since 2022-06-17
 */
public class TemplateContent {
    public static final Map<Integer, String> map = ImmutableMap.<Integer, String>builder()
            .put(1, getTemplate1())
            .build();

    /**
     * 模板1
     *
     * ******
     * 标题
     *
     * 文字内容
     * ******
     *
     * @return 模板内容
     */
    private static String getTemplate1() {
        return "%s" + "\n\n" + "%s";
    }
}
