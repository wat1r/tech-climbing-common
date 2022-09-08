package json.fastjson;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2022/8/31 9:23
 * @description:
 */
public class FastJsonDemo1 {


    public static void main(String[] args) {
        List<KV> kvs = new ArrayList<KV>() {{
            add(new KV("maven", "dddd"));
            add(new KV("m1", "eee"));
        }};
        Dependencies dependencies = new Dependencies(kvs);
        ConfigJson configJson = new ConfigJson();
        configJson.setDependencies(dependencies);
        System.out.println(JSON.toJSONString(configJson));

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ConfigJson {
        private Dependencies dependencies;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Dependencies {
        private List<KV> kvs;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class KV {
        private String key;
        private String value;
    }

}
