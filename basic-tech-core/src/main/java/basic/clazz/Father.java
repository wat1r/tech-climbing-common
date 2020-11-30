package basic.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Father implements Cloneable {
    private String a;
    private String b;

    public Father(Father father) {

    }


    @Override
    protected Father clone() throws CloneNotSupportedException {
        return (Father) super.clone();
    }
}
