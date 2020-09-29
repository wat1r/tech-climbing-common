//package message.rocketMQ.two;
//
//
//import com.aliyun.openservices.ons.api.Message;
//import com.aliyun.openservices.ons.api.order.ConsumeOrderContext;
//import com.aliyun.openservices.ons.api.order.MessageOrderListener;
//import com.aliyun.openservices.ons.api.order.OrderAction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//
///**
// * mq 订单消费者
// */
//@Component
//public class OrderMessageListener implements MessageOrderListener {
//
//    private static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);
//
//
//    @Override
//    public OrderAction consume(Message message, ConsumeOrderContext context) {
//        String data = null;
//        try {
//            byte[] body = message.getBody();
//            data = new String(body, "UTF-8");
//            logger.info("####消费成功" + data);
//            return OrderAction.Success;
//        } catch (Exception e) {
//            e.printStackTrace();
//            //	logger.error("消费失败，异常为{}，订单号为：{},message为：{}",e,orderNo,message);
//            return OrderAction.Suspend;
//        }
//    }
//
//
//}
