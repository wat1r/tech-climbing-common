package pattern.responsibility.three;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 14:12
 * @description:
 */
public class FilterChain {

    private int index = 0;

    private TaskProcessor.TaskReq taskReq;
    private List<TaskHandler> handlers = new ArrayList<>();


    public FilterChain(TaskProcessor.TaskReq taskReq) {
        this.taskReq = taskReq;
    }

    public FilterChain addHandler(TaskHandler taskHandler) {
        this.handlers.add(taskHandler);
        return this;
    }


    public void doFilter() {
        if (index == handlers.size()) {
            System.out.println("the task is done");
            return;
        }
        handlers.get(index++).handleReq(taskReq, this);
    }
}
