package message.rocketMQ.two;


import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.OrderConsumerBean;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.ons.api.order.MessageOrderListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @Auther: willow
 * @Date: 2018/9/14 11:06
 * @Description: see ProducerImpl
 */
@Configuration
@EnableConfigurationProperties(RabbitMqProperties.class)
public class RocketMQProducerConfiguration {


    @Autowired
    public RabbitMqProperties properties;


    /**
     * mq 生产者
     *
     * @param
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public ProducerBean producerBean() {
        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(properties);
        return producerBean;
    }


    /**
     * 消费者
     *
     * @return
     */
    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderConsumerBean getConsumer() {
        OrderConsumerBean consumerBean = new OrderConsumerBean();
        consumerBean.setProperties(properties);
        Subscription subscription = new Subscription();
        subscription.setTopic(properties.get("Topic").toString());
        subscription.setExpression(properties.get("Tag").toString());
        Map<Subscription, MessageOrderListener> map = new HashMap();
        map.put(subscription, new OrderMessageListener()); //添加监听器
        consumerBean.setSubscriptionTable(map);
        return consumerBean;
    }
}
