package com.elyr1c.el1batis.mapping;

/**
 * @ClassName SqlCommandType
 * @Description TODO
 * @Author Elyr1c
 * @Date 2025/6/26 23:00
 */
public enum SqlCommandType {

    /**
     * 未知
     */
    UNKNOWN,
    /**
     * 插入
     */
    INSERT,
    /**
     * 更新
     */
    UPDATE,
    /**
     * 删除
     */
    DELETE,
    /**
     * 查找
     */
    SELECT;
}
