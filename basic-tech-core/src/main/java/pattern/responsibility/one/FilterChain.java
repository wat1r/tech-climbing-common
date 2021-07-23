package pattern.responsibility.one;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 13:15
 * @description:
 */
//过滤器链，把各个处理类串联起来
public class FilterChain {
    private int index = 0;

    private LeaveRequest leaveRequest;

    private List<LeaveHandler> leaveHandlerList = new ArrayList<>();

    public FilterChain(LeaveRequest leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    public FilterChain addHandler(LeaveHandler leaveHandler) {
        if (null == leaveHandler) return null;
        this.leaveHandlerList.add(leaveHandler);
        return this;
    }

    public void doFilter() {
        if (index == leaveHandlerList.size()) {
            leaveRequest.leave();
            return;
        }
        leaveHandlerList.get(index++).handRequest(leaveRequest, this);
    }
}