package com.zjicm.common.lang.sql;

public interface BatchHandler<E> {
    void consume(E e);

    void afterBatch(int offset);
}
