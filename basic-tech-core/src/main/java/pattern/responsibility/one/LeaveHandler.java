package pattern.responsibility.one;


/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 13:14
 * @description:
 */
//一个请假的处理接口，具体的处理类都要实现这个接口
@FunctionalInterface
public interface LeaveHandler {
    // 直属主管审批处理的请假天数
    int MIN = 3;

    // 部门经理需要处理的请假天数
    int MIDDLE = 10;

    // 总经理审批
    int MAX = 30;

    void handRequest(LeaveRequest leaveRequest, FilterChain filterChain);
}