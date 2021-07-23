package pattern.responsibility.one;

import pattern.responsibility.one.handler.*;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 13:17
 * @description:
 */
public class ResponsibilityOneTest {
    public static void main(String[] args) {
        LeaveRequest leaveRequest = new LeaveRequest(49, "小明");

        FilterChain filterChain = new FilterChain(leaveRequest);

        filterChain.addHandler(new SupervisorHandler())
                .addHandler(new DeptManagerHandler())
                .addHandler(new GeneralManagerHandler())
                //使用lambda表达式创建自定义处理类
                .addHandler((LeaveRequest request, FilterChain filter) -> {
                    if (request.getLeaveDays() <= 50) {
                        System.out.println("请假天数" + request.getLeaveDays() + "我是自定义处理器，我处理了");
                        return;
                    }
                    System.out.println("自定义处理不了，下一个");
                    filter.doFilter();
                })
                .doFilter();
    }
}
