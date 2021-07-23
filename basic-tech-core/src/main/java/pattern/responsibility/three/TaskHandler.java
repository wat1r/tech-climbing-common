package pattern.responsibility.three;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 14:12
 * @description:
 */
public interface TaskHandler {

    void handleReq(TaskProcessor.TaskReq taskReq, FilterChain filterChain);
}
