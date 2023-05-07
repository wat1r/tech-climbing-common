//package message.rocketMQ.two;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.Properties;
//
///**
// * @Auther: willow
// * @Date: 2018/9/14 11:09
// * @Description: see com.aliyun.openservices.ons.api.PropertyKeyConst
// */
//@ConfigurationProperties(prefix = "aliyun.rabbitmq")
//public class RabbitMqProperties extends Properties {
//
//    /**
//     * 消费模式，包括集群模式、广播模式
//     */
//    public static final String MessageModel = "MessageModel";
//
//    /**
//     * Producer的标识
//     */
//    public static final String ProducerId = "ProducerId";
//
//    /**
//     * Consumer的标识
//     */
//    public static final String ConsumerId = "ConsumerId";
//    /**
//     * AccessKey, 用于标识、校验用户身份
//     */
//    public static final String AccessKey = "AccessKey";
//
//    /**
//     * SecretKey, 用于标识、校验用户身份
//     */
//    public static final String SecretKey = "SecretKey";
//
//    /**
//     * 消息发送超时时间，如果服务端在配置的对应时间内未ACK，则发送客户端认为该消息发送失败。
//     */
//    public static final String SendMsgTimeoutMillis = "SendMsgTimeoutMillis";
//
//    /**
//     * 消息队列服务接入点
//     */
//    public static final String ONSAddr = "ONSAddr";
//
//    /**
//     * Name Server地址
//     */
//    public static final String NAMESRV_ADDR = "NAMESRV_ADDR";
//
//    /**
//     * 消费线程数量
//     */
//    public static final String ConsumeThreadNums = "ConsumeThreadNums";
//
//    /**
//     * 设置客户端接入来源，默认ALIYUN
//     */
//    public static final String OnsChannel = "OnsChannel";
//
//    /**
//     * 消息类型，可配置为NOTIFY、METAQ
//     */
//    public static final String MQType = "MQType";
//    /**
//     * 是否启动vip channel
//     */
//    public static final String isVipChannelEnabled = "isVipChannelEnabled";
//
//    /**
//     * 顺序消息消费失败进行重试前的等待时间 单位(毫秒)
//     */
//    public static final String SuspendTimeMillis = "suspendTimeMillis";
//
//    /**
//     * 消息消费失败时的最大重试次数
//     */
//    public static final String MaxReconsumeTimes = "maxReconsumeTimes";
//
//    /**
//     * 设置每条消息消费的最大超时时间,超过这个时间,这条消息将会被视为消费失败,等下次重新投递再次消费. 每个业务需要设置一个合理的值. 单位(分钟)
//     */
//    public static final String ConsumeTimeout = "consumeTimeout";
//    /**
//     * 设置事务消息的第一次回查延迟时间
//     */
//    public static final String CheckImmunityTimeInSeconds = "CheckImmunityTimeInSeconds";
//
//    /**
//     * 是否每次请求都带上最新的订阅关系
//     */
//    public static final String PostSubscriptionWhenPull = "PostSubscriptionWhenPull";
//
//    /**
//     * BatchConsumer每次批量消费的最大消息数量, 默认值为1, 允许自定义范围为[1, 32], 实际消费数量可能小于该值.
//     */
//    public static final String ConsumeMessageBatchMaxSize = "ConsumeMessageBatchMaxSize";
//
//    /**
//     * Consumer允许在客户端中缓存的最大消息数量，默认值为5000，设置过大可能会引起客户端OOM，取值范围为[100, 50000]
//     * <p>
//     * 考虑到批量拉取，实际最大缓存量会少量超过限定值
//     * <p>
//     * 该限制在客户端级别生效，限定额会平均分配到订阅的Topic上，比如限制为1000条，订阅2个Topic，每个Topic将限制缓存500条
//     */
//    public static final String MaxCachedMessageAmount = "maxCachedMessageAmount";
//
//    /**
//     * Consumer允许在客户端中缓存的最大消息容量，默认值为512 MiB，设置过大可能会引起客户端OOM，取值范围为[16, 2048]
//     * <p>
//     * 考虑到批量拉取，实际最大缓存量会少量超过限定值
//     * <p>
//     * 该限制在客户端级别生效，限定额会平均分配到订阅的Topic上，比如限制为1000MiB，订阅2个Topic，每个Topic将限制缓存500MiB
//     */
//    public static final String MaxCachedMessageSizeInMiB = "maxCachedMessageSizeInMiB";
//
//    /**
//     * MQ消息轨迹开关
//     */
//    public static final String MsgTraceSwitch = "MsgTraceSwitch";
//    /**
//     * Mqtt消息序列ID
//     */
//    public static final String MqttMessageId = "mqttMessageId";
//
//    /**
//     * Mqtt消息
//     */
//    public static final String MqttMessage = "mqttMessage";
//
//    /**
//     * Mqtt消息保留关键字
//     */
//    public static final String MqttPublishRetain = "mqttRetain";
//
//    /**
//     * Mqtt消息保留关键字
//     */
//    public static final String MqttPublishDubFlag = "mqttPublishDubFlag";
//
//    /**
//     * Mqtt的二级Topic，是父Topic下的子类
//     */
//    public static final String MqttSecondTopic = "mqttSecondTopic";
//
//    /**
//     * Mqtt协议使用的每个客户端的唯一标识
//     */
//    public static final String MqttClientId = "clientId";
//
//    /**
//     * Mqtt消息传输的数据可靠性级别
//     */
//    public static final String MqttQOS = "qoslevel";
//
//
//    //my properties
//
//    public static final String Topic = "";
//
//    public static final String Tag = "";
//
//
//}
