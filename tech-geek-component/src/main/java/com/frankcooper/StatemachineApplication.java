package com.frankcooper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;
import com.frankcooper.spring.stateMachine.turnstile.TurnstileEvents;
import com.frankcooper.spring.stateMachine.turnstile.TurnstileStates;

/**
 * https://www.jianshu.com/p/
 * https://www.jianshu.com/p/1a4947a099e9?from=singlemessage
 * https://blog.csdn.net/guo_xl/article/details/83716342
 * https://docs.spring.io/spring-statemachine/docs/2.0.3.BUILD-SNAPSHOT/reference/htmlsingle/#statemachine-examples-persist
 * https://www.jianshu.com/p/4b07b76ca90d
 */
@SpringBootApplication
public class StatemachineApplication implements CommandLineRunner {
    @Autowired
    private StateMachine<TurnstileStates, TurnstileEvents> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        System.out.println("--- coin ---");
        stateMachine.sendEvent(TurnstileEvents.COIN);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        System.out.println("--- push ---");
        stateMachine.sendEvent(TurnstileEvents.PUSH);
        stateMachine.stop();

    }
}
