package pattern.responsibility.one;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 13:13
 * @description:
 */
//一个请假请求
@Data
@AllArgsConstructor
public class LeaveRequest {
    private int leaveDays;

    private String name;

    public void leave() {
        System.out.println("走完了流程都没人处理我");
    }
}