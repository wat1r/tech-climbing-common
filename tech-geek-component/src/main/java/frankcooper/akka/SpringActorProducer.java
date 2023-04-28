package frankcooper.akka;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 17:01
 * @description:
 */
import org.springframework.context.ApplicationContext;
import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

/*
 * jikuan.zjk
 */

public class SpringActorProducer implements IndirectActorProducer {

    final private ApplicationContext applicationContext;
    final private String actorBeanName;

    public SpringActorProducer(ApplicationContext applicationContext, String actorBeanName) {
        this.applicationContext = applicationContext;
        this.actorBeanName = actorBeanName;
    }

    @Override
    public Actor produce() {
        return (Actor) applicationContext.getBean(actorBeanName);
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(actorBeanName);
    }
}
