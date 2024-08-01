package com.example.research.designpattern.singleton;

public class SingletonSynchronized {
    private static SingletonSynchronized naiveSingleton;

    private SingletonSynchronized() {}

    public static synchronized SingletonSynchronized getInstance() {
        if (naiveSingleton == null) {
            naiveSingleton = new SingletonSynchronized();
        }
        return naiveSingleton;
    }
}
