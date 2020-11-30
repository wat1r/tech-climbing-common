package basic.clazz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestClazz {


    public static void main(String[] args) throws CloneNotSupportedException {
        Father father = new Father("a", "b1");
//        Son son = new Son("c", father);
//        System.out.println(JSONObject.toJSONString(son));
        List<Father> list = new ArrayList<>();
        List<String> strings = Arrays.asList("b2", "b3");

        for (String string : strings) {
            Father curr = father.clone();
            curr.setB(string);
            list.add(curr);
        }

        System.out.println(JSONObject.toJSONString(list));


    }


}
