package explore.alibaba.producer_consumer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 11:41
 * @description:
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String name;
    private String subject;
    private Integer score;
}
