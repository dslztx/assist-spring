package aop;

import org.springframework.stereotype.Component;

@Component
public class TargetObject {

    public void beforeTest() {
        System.out.println("world");
    }

    public Object afterReturningTest() {
        return "has returned";
    }

    public void afterThrowingTest() {
        throw new RuntimeException("has thrown");
    }

    public Object afterTest() {
        if (System.currentTimeMillis() % 2 == 1) {
            return "hello world";
        } else {
            throw new RuntimeException("has thrown");
        }
    }

    public void aroundTest() throws InterruptedException {
        System.out.println("hello");

        Thread.sleep(10000L);

        System.out.println("world");
    }
}
