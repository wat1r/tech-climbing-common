package pattern.responsibility.four;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Approver {

    protected Approver approver;
    protected String name;

    public Approver(String name) {
        this.name = name;
    }

    public abstract void processRequest(PurchaseRequest purchaseRequest);
}
