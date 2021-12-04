package interview.alibaba.question.two;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Request {

    private String name;

    private Integer sum ;

    private Integer pay;

    public void end() {
        System.out.println("the end");
    }
}