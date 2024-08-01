package com.example.research.designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class TestSingletonSynchronized {

    @Test
    void given_singletonInstances_when_singleThread_then_success() {
        SingletonSynchronized instance_1 = SingletonSynchronized.getInstance();
        SingletonSynchronized instance_2 = SingletonSynchronized.getInstance();

        assertThat(instance_1).isEqualTo(instance_2);
    }

    @Test
    void given_singletonInstances_when_multiThread_then_success() throws InterruptedException {
        Set<SingletonSynchronized> objects = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(100);
        multiThreadRequest(objects, latch);
        latch.await();

        assertThat(objects).hasSize(1);
    }

    private void multiThreadRequest(Set<SingletonSynchronized> objects, CountDownLatch latch) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    SingletonSynchronized instance = SingletonSynchronized.getInstance();
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