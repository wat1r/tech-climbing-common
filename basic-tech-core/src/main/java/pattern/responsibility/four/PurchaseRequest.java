package pattern.responsibility.four;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseRequest {

    private int type = 0;
    private double price = 0.0;
    private int id;


}
