package pattern.adapter.object;

public class VoltageAdapter implements IVoltage5V {
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }


    @Override
    public int output5V() {
        int dest = 0;
        if (null != voltage220V) {
            int src = voltage220V.output220V();
            dest = src / 44;
            System.out.println("success adapter，output =" + dest + "V");
        }
        return dest;
    }
}
