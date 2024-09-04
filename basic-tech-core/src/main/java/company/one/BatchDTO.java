package company.one;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/09/02/ 19:19
 * @description
 */
public class BatchDTO {

    private Long min;
    private Long max;
    private Long step;

    public BatchDTO() {
    }

    public BatchDTO(Long min, Long max, Long step) {
        this.min = min;
        this.max = max;
        this.step = step;
    }


    public Long getMin() {
        return min;
    }

    public void setMin(Long min) {
        this.min = min;
    }

    public Long getMax() {
        return max;
    }

    public void setMax(Long max) {
        this.max = max;
    }

    public Long getStep() {
        return step;
    }

    public void setStep(Long step) {
        this.step = step;
    }
}
