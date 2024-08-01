package com.example.research.designpattern.singleton;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JVM에 따라 싱글턴이 보장되지 않음. 실제 테스트 결과도 잦은 실패가 발생
 */
class TestSingletonDoubleCheckedLocking {

    @Test
    void given_singletonInstances_when_singleThread_then_success() {
        SingletonDoubleCheckedLocking instance_1 = SingletonDoubleCheckedLocking.getInstance();
        SingletonDoubleCheckedLocking instance_2 = SingletonDoubleCheckedLocking.getInstance();

        assertThat(instance_1).isEqualTo(instance_2);
    }

    @Test
    void given_singletonInstances_when_multiThread_then_success() throws InterruptedException {
        Set<SingletonDoubleCheckedLocking> objects = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(100);
        multiThreadRequest(objects, latch);
        latch.await();

        assertThat(objects).hasSize(1);
    }

    private void multiThreadRequest(Set<SingletonDoubleCheckedLocking> objects, CountDownLatch latch) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    SingletonDoubleCheckedLocking instance = SingletonDoubleCheckedLocking.getInstance();
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