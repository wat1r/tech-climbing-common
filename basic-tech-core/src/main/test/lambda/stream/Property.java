package lambda.stream;

import lombok.Data;
import lombok.ToString;

/**
 * Created by FrankCooper
 * Date 2019/1/20 23:10
 * Description
 */
@Data
@ToString
public class Property {
    String name;
    // 距离，单位:米
    Integer distance;
    // 销量，月售
    Integer sales;
    // 价格，这里简单起见就写一个级别代表价格段
    Integer priceLevel;

    public Property(String name, int distance, int sales, int priceLevel) {
        this.name = name;
        this.distance = distance;
        this.sales = sales;
        this.priceLevel = priceLevel;
    }
}
