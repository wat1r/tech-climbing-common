package importNew.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by FrankCooper
 * Date 2019/2/4 11:15
 * Description
 */
public class StreamGeek {



    public static void stepOne(){
        List<String> strings = Stream.of("C", "A", "B")
                .sorted()
                .collect(Collectors.toList());
    }

    public static void stepTwo(){
//        new SakilaApplicationBuilder()



    }


    public static void main(String[] args) {
        stepOne();
    }
}
