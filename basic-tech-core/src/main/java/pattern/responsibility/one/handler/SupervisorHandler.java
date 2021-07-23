package pattern.responsibility.one.handler;


import pattern.responsibility.one.FilterChain;
import pattern.responsibility.one.LeaveHandler;
import pattern.responsibility.one.LeaveRequest;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 13:14
 * @description:
 */
//直属主管处理类
public class SupervisorHandler implements LeaveHandler {

    @Override
    public void handRequest(LeaveRequest leaveRequest, FilterChain filterChain) {
        if (leaveRequest.getLeaveDays() < MIN){
            System.out.println("请假天数" + leaveRequest.getLeaveDays() + "我是直属主管，我处理了");
            return;
        }
        System.out.println("主管处理不了，下一个");
        filterChain.doFilter();
    }
}
