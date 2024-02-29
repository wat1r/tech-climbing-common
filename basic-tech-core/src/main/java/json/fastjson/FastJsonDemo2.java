package json.fastjson;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FastJsonDemo2 {

    private static final String EMAIL_SUFFIX = "shengqugames.com";

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        List<String> emailList = new ArrayList<String>() {{
            add("a@163.com");
            add("b@163.com");
            add("c@shengqugames.com");
        }};
        JSONObject inputJSON = new JSONObject();
        inputJSON.put("emailList", JSONObject.toJSONString(emailList));
        String input = JSONObject.toJSONString(inputJSON);
        System.out.println(input);
        JSONObject res = (JSONObject) JSONObject.parse(input);
        if (res.containsKey("emailList")) {
            List<String> emailListResult = JSONObject.parseArray(res.getString("emailList"), String.class);
            for (String x : emailListResult) {
                if (x.endsWith(EMAIL_SUFFIX)) {
                    System.out.println(x);
                }
            }
            System.out.println();
        }
    }
}
