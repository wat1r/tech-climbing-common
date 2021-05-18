package basic.generic;

import lombok.Data;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/18 17:19
 * @description:
 */
public class GenericTestOne {


    public static void main(String[] args) {

    }


    @Data
    class Pair<T> {
        private T first;
        private T second;

    }


}
