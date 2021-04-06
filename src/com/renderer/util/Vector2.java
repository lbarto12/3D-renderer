package com.renderer.util;

public class Vector2<T extends  Comparable<T>> {
    public Vector2(T x, T y){
        this.x = x;
        this.y = y;
    }

    public T x, y;
}
