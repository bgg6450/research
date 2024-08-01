package com.example.research.designpattern.singleton;

public class SingletonDoubleCheckedLocking {
    private static volatile SingletonDoubleCheckedLocking naiveSingleton;

    private SingletonDoubleCheckedLocking() {}

    public static SingletonDoubleCheckedLocking getInstance() {
        if (naiveSingleton == null) {
            synchronized (SingletonDoubleCheckedLocking.class) {
                naiveSingleton = new SingletonDoubleCheckedLocking();
            }
        }
        return naiveSingleton;
    }
}
