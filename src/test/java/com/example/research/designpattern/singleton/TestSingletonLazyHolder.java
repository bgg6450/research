package com.example.research.designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

class TestSingletonLazyHolder {

    private static final Logger logger = Logger.getLogger(TestSingletonLazyHolder.class.getName());

    @Test
    void given_singletonInstances_when_singleThread_then_success() {
        SingletonLazyHolder instance_1 = SingletonLazyHolder.getInstance();
        SingletonLazyHolder instance_2 = SingletonLazyHolder.getInstance();

        assertThat(instance_1).isEqualTo(instance_2);
    }

    @Test
    void given_singletonInstances_when_multiThread_then_success() throws InterruptedException {
        Set<SingletonLazyHolder> objects = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(100);
        multiThreadRequest(objects, latch);
        latch.await();

        assertThat(objects).hasSize(1);
    }

    private void multiThreadRequest(Set<SingletonLazyHolder> objects, CountDownLatch latch) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    SingletonLazyHolder instance = SingletonLazyHolder.getInstance();
                    synchronized (objects) {
                        objects.add(instance);
                    }
                } finally {
                    latch.countDown();
                }
            }).start();
        }
    }
}