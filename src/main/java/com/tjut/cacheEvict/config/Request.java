package com.tjut.cacheEvict.config;

/**
 * @author tb
 * @date 7/2/20-7:30 PM
 */
public class Request {
    long req;
    int size;
    int type;

    public long getReq() {
        return req;
    }

    public void setReq(long req) {
        this.req = req;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
