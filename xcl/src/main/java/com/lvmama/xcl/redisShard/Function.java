package com.lvmama.xcl.redisShard;

public interface Function<E, T> {

    public T callBack(E e);

}