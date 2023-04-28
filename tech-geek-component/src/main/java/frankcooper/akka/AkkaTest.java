package frankcooper.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
//import akka.remote.WireFormats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 17:09
 * @description:
 */
public class AkkaTest {

    @Component
    class Runner implements CommandLineRunner {


        @Autowired
        private ActorSystem actorSystem;

        @Autowired
        private SpringExtension springExtension;

        @Override
        public void run(String[] args) throws Exception {
            try {
                ActorRef workerActor = actorSystem.actorOf(springExtension.props("workerActor"), "worker-actor");

//                workerActor.tell(new WorkerActor.Request(), null);
//                workerActor.tell(new WorkerActor.Request(), null);
//                workerActor.tell(new WorkerActor.Request(), null);

//                FiniteDuration duration = FiniteDuration.create(1, String.valueOf(WireFormats.TimeUnit.SECONDS));
//                Future<Object> awaitable = Patterns.ask(workerActor, new WorkerActor.Response(), Timeout.durationToTimeout(duration));

//                logger.info("Response: " + Await.result(awaitable, duration));
            } finally {
                actorSystem.terminate();
                Await.result(actorSystem.whenTerminated(), Duration.Inf());
            }
        }
    }
}
