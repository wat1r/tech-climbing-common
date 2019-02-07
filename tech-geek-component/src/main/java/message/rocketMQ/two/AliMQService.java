package message.rocketMQ.two;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Auther: hamer
 * @Date: 2018/9/14 14:07
 * @Description:
 */
@Component
public class AliMQService {

    private static final Logger logger = LoggerFactory.getLogger(AliMQService.class);

    @Autowired
    private Producer producer;
    @Autowired
    public RabbitMqProperties properties;

    // 发送消息
    public void sendMessage(String body) {
        try {
            Message msg = new Message(properties.get("Topic").toString(), properties.get("Tag").toString(), body.getBytes("UTF-8"));
            SendResult sendResult = producer.send(msg);
            if (sendResult != null) {
                logger.info("消息发送成功：" + body + "result" + sendResult.toString());
            }
        } catch (ONSClientException e) {
            logger.info("消息发送失败：", e);
            // 出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("发送失败,Topic:{},Tag:{}body为{}", new Object[]{properties.get("Topic").toString(), properties.get("Tag").toString(), body});
        }
    }
}
