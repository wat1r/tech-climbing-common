package basic.jvm;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * Created by FrankCooper
 * Date 2019/1/26 22:27
 * Description
 */
@Data
@ToString
public class User {
    private String name;
    private int age;
    private Date birth;
    private boolean flag;



}
