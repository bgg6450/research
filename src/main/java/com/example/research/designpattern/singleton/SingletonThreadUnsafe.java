package com.example.research.designpattern.singleton;

public class SingletonThreadUnsafe {
    private static SingletonThreadUnsafe singletonThreadUnsafe;

    private SingletonThreadUnsafe() {}

    public static SingletonThreadUnsafe getInstance() {
        if (singletonThreadUnsafe == null) {
            singletonThreadUnsafe = new SingletonThreadUnsafe();
        }
        return singletonThreadUnsafe;
    }
}
