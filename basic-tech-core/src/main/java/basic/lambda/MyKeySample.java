package basic.lambda;

import java.util.HashMap;

/**
 * @Author:Cooper
 * @Date:2019/2/11 20:26
 */
public class MyKeySample {


    public static void main(String[] args) {


        HashMap<MyKey, String> myHashMap = new HashMap<MyKey, String>();

//传递给 MyKey 的 name 参数被用于 equals() 和 hashCode() 中
        MyKey key = new MyKey("Pankaj"); // 假设 hashCode=1234
        myHashMap.put(key, "Value");

// 以下的代码会改变 key 的 hashCode() 和 equals() 值
        key.setName("Amit"); // 假设新的 hashCode=7890

//下面会返回 null，因为 HashMap 会尝试查找存储同样索引的 key，而 key 已被改变了，匹配失败，返回 null
        System.out.println(myHashMap.get(new MyKey("Pankaj")));
    }


}


class MyKey {


    private String name;

    public MyKey() {
    }

    public MyKey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}