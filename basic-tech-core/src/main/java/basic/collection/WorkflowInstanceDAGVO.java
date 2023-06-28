package basic.collection;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/20 14:27
 * @description: 工作流实例组成的dag的数据结构
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkflowInstanceDAGVO {


    //k：wfInstanceId v : List<Long> wfInstanceId
    private Map<Long, List<Long>> adjMap;


    public boolean addAdjMap(Map<Long, List<Long>> ele) {
        if (this.adjMap == null) {
            this.adjMap = Maps.newHashMap();
        }
        if (ele != null) {
            for (Map.Entry<Long, List<Long>> e : ele.entrySet()) {
                if (this.adjMap.containsKey(e.getKey())) {
                    List<Long> values = this.adjMap.get(e.getKey());
                    values.addAll(new ArrayList<>(new LinkedHashSet<>(e.getValue())));
                    this.adjMap.put(e.getKey(), values);
                } else {
                    this.adjMap.putAll(ele);
                }
            }
            return true;
        }
        return false;
    }


}
