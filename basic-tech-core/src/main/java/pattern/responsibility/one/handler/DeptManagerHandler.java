package pattern.responsibility.one.handler;

import pattern.responsibility.one.FilterChain;
import pattern.responsibility.one.LeaveHandler;
import pattern.responsibility.one.LeaveRequest;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 13:16
 * @description:
 */
//部门经理处理类
public class DeptManagerHandler implements LeaveHandler {

    @Override
    public void handRequest(LeaveRequest leaveRequest, FilterChain filterChain) {
        if (leaveRequest.getLeaveDays() <= MIDDLE){
            System.out.println("请假天数" + leaveRequest.getLeaveDays() + "我是部门经理，我处理了");
            return;
        }
        System.out.println("经理处理不了，下一个");
        filterChain.doFilter();
    }
}
