package pattern.adapter.object;

public class Phone {
    //charging
    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("Voltage is 5V, charging");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("Voltage is more than  5V, non-charging");
        }
    }
}
