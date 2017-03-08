package com.zjicm.common.sql;

public interface BatchHandler<E> {
    public void consume(E e);

    public void afterBatch(int offset);
}
