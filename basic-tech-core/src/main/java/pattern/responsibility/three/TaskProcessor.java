package pattern.responsibility.three;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/23 14:13
 * @description:
 */
public class TaskProcessor {


    public static void main(String[] args) {

        TaskReq taskReq = new TaskReq(Lists.newArrayList("a", "b", "c", "d", "e"));
        FilterChain filterChain = new FilterChain(taskReq);
        filterChain.addHandler(new ATaskHandler())
                .addHandler(new BTaskHandler())
                .addHandler(new CTaskHandler())
                .addHandler(new DTaskHandler())
                .addHandler(new ETaskHandler()).doFilter();

    }


    @AllArgsConstructor
    @Getter
    @Setter
    static class TaskReq {
        private List<String> taskNames;
    }


    static class ATaskHandler implements TaskHandler {

        @Override
        public void handleReq(TaskReq taskReq, FilterChain filterChain) {
            for (String taskName : taskReq.getTaskNames()) {
                if (taskName.equals("a")) {
                    System.out.printf("A is handling %s\n", taskName);
                }
            }
            filterChain.doFilter();
        }
    }


    static class BTaskHandler implements TaskHandler {

        @Override
        public void handleReq(TaskReq taskReq, FilterChain filterChain) {
            for (String taskName : taskReq.getTaskNames()) {
                if (taskName.equals("b")) {
                    System.out.printf("B is handling %s\n", taskName);
                }
            }
            filterChain.doFilter();
        }

    }


    static class CTaskHandler implements TaskHandler {

        @Override
        public void handleReq(TaskReq taskReq, FilterChain filterChain) {
            for (String taskName : taskReq.getTaskNames()) {
                if (taskName.equals("c")) {
                    System.out.printf("C is handling %s\n", taskName);
                }
            }
            filterChain.doFilter();
        }
    }


    static class DTaskHandler implements TaskHandler {

        @Override
        public void handleReq(TaskReq taskReq, FilterChain filterChain) {
            for (String taskName : taskReq.getTaskNames()) {
                if (taskName.equals("d")) {
                    System.out.printf("D is handling %s\n", taskName);
                }
            }
            filterChain.doFilter();
        }
    }


    static class ETaskHandler implements TaskHandler {

        @Override
        public void handleReq(TaskReq taskReq, FilterChain filterChain) {
            for (String taskName : taskReq.getTaskNames()) {
                if (taskName.equals("e")) {
                    System.out.printf("E is handling %s\n", taskName);
                }
            }
            filterChain.doFilter();
        }
    }
}
