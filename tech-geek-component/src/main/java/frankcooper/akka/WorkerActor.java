package frankcooper.akka;

import akka.actor.AbstractActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 17:05
 * @description:
 */
@Component("workerActor")
@Scope("prototype")
public class WorkerActor extends AbstractActor {

    @Autowired
    private BusinessService businessService;

    final private CompletableFuture<Message> future;

    public WorkerActor(CompletableFuture<Message> future) {
        this.future = future;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Message.class, message -> {
                    businessService.perform(message);
                    future.complete(message);
                })
                .build();
    }

}