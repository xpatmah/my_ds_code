package com.my.ds.code.heaps.domain;

public class HeapNodeTS<T> {
    private T value;
    public HeapNodeTS(T value) { this.value = value; }
    public T getValue() { return value; }
    public void setValue(T v) { this.value = v; }
}

