package com.imooc.common.enums;

/**
 * Created by eru on 2020/2/5.
 */
public enum CommentLevel {
    good(1, "好评"),
    normal(2, "中评"),
    bad(3, "差评");

    public final Integer level;
    public final String value;

    CommentLevel(Integer level, String value) {
        this.level = level;
        this.value = value;
    }
}
