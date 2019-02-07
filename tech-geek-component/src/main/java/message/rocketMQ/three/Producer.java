package message.rocketMQ.three;


import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class Producer {

    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        //nameserver服务,多个以;分开
        producer.setNamesrvAddr("192.168.200.131:9876");
        try {
            producer.start();
            for (int i = 0; i < 100; i++) {
                String key = String.valueOf(i);
                Message msg = new Message("PushTopic", "push", key, ("Just for test." + key).getBytes());
                SendResult result = producer.send(msg);
                System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

}
