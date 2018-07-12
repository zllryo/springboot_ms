package com.ryo.springboot_ms.redis;

public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
