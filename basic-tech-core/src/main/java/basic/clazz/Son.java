package basic.clazz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Son extends Father {

    private String c;

    public Son(String c,Father father) {
        super(father);
        this.c = c;
    }
}
