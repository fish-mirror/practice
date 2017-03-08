package com.zjicm.common.sql;

public interface BatchHandler<E> {
    void consume(E e);

    void afterBatch(int offset);
}
