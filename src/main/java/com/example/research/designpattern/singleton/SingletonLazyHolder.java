package com.example.research.designpattern.singleton;

public class SingletonLazyHolder {
    private SingletonLazyHolder() {}

    public static SingletonLazyHolder getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final SingletonLazyHolder INSTANCE = new SingletonLazyHolder();

        private Holder() {}
    }
}
