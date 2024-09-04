package company.one;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/09/02/ 19:18
 * @description
 */
public class Three {
    public static void main(String[] args) {
        long[] totalPlanQtyArr = {35, 55, 60, 80, 73, 77, 112, 138, 100, 150, 70, 80};
        String batchStr = "{\"min\":20,\"max\":30,\"step\":5}";
        JSONObject batchJson = JSON.parseObject(batchStr);
        BatchDTO batchDTO = JSON.toJavaObject(batchJson, BatchDTO.class);
        for (long totalPlanQty : totalPlanQtyArr) {
            System.out.println(totalPlanQty);
//            List<Long> splitResult = splitGroupQuantity(totalPlanQty, batchDTO.getMin().longValue(), batchDTO.getMax().longValue(), batchDTO.getStep().longValue());
            List<Long> splitResult = splitGroupQuantity1(totalPlanQty, batchDTO.getMin().longValue(), batchDTO.getMax().longValue(), batchDTO.getStep().longValue());
            System.out.println(splitResult);
            System.out.println("---------------------------------------");
        }

    }

    private static List<Long> splitGroupQuantity(long totalPlanQty, long min, long max, long step) {
        int loopLimit = 1000;
        List<Long> splitResult = new ArrayList<>();
        long remain = totalPlanQty;
        while (remain > 0) {
            // 添加不超过总数的最大值
            long value = Math.min(max, remain);
            // 将值调整为步长的倍数
            value = (value / step) * step;
            if (value < min) {
                // 如果调整后的值小于最小值，取下一个步长值
                value = ((value / step) + 1) * step;
            }
            splitResult.add(value);
            remain -= value;
        }
        return splitResult;
    }


    private static List<Long> splitGroupQuantity1(long totalPlanQty, long min, long max, long step) {
        List<Long> splitResult = new ArrayList<>();
        long remain = totalPlanQty;
        while (remain > 0) {
            // 从最小值开始增加，直到总和至少达到总数
            long value = min;
            while (value <= max && splitResult.stream().mapToLong(Long::longValue).sum() + value <= totalPlanQty) {
                splitResult.add(value);
                remain -= value;
                if (remain > 0) {
                    // 将值增加到下一个步长
                    value = ((value / step) + 1) * step;
                    if (value < min) {
                        value = min;
                    }
                }
            }
            // 如果剩余的超过最大值，就添加最大值
            if (remain > max) {
                splitResult.add(max);
                remain -= max;
            } else {
                splitResult.add(remain);
                remain = 0;
            }
        }
        return splitResult;
    }
}
