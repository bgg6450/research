package com.example.research.designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

class TestSingletonThreadUnsafe {

    @Test
    void given_singletonInstances_when_singleThread_then_equals() {
        SingletonThreadUnsafe instance_1 = SingletonThreadUnsafe.getInstance();
        SingletonThreadUnsafe instance_2 = SingletonThreadUnsafe.getInstance();

        assertThat(instance_1).isEqualTo(instance_2);
    }

    @Test
    void given_singletonInstances_when_multiThread_then_failRandomly() throws InterruptedException {
        Set<SingletonThreadUnsafe> objects = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(100);
        multiThreadRequest(objects, latch);
        latch.await();

        assertThat(objects).hasSizeGreaterThan(1);
    }

    private void multiThreadRequest(Set<SingletonThreadUnsafe> objects, CountDownLatch latch) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    SingletonThreadUnsafe instance = SingletonThreadUnsafe.getInstance();
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