package com.imooc.common.enums;

/**
 * Created by eru on 2020/2/5.
 */
public enum CommentLevel {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    public final Integer level;
    public final String value;

    CommentLevel(Integer level, String value) {
        this.level = level;
        this.value = value;
    }
}
