package cglib.beanCopier;

import lombok.Getter;

/**
 * @Author:Cooper
 * @Date:2019/1/26 13:02
 */
@Getter
public class LackOfSetter {

    private int id;
    private String name;

    public LackOfSetter() {
    }

    public LackOfSetter(int id, String name) {
        this.id = id;
        this.name = name;
    }



    // Getters and setters are omitted
    // public void setName(String name) {
    //  this.name = name;
    // }
}
