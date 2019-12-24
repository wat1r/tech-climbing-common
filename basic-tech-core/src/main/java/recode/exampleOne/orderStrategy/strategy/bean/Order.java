package recode.exampleOne.orderStrategy.strategy.bean;

/**
 * Created by FrankCooper
 * Date 2019/12/24 22:23
 * Description
 */
public class Order {

    public static final int FREE = 1; //免费订单
    public static final int HALF = 2; //半价订单
    public static final int DISCOUT = 3; //打折订单
    private String name;
    private Double price;
    private Integer type;//订单类型

    public static Order build() {
        return new Order();
    }

    public Order add(String filed, Object value) {
        switch (filed) {
            case "name":
                this.setName(String.valueOf(value));
                break;
            case "price":
                this.setPrice((Double) value);
                break;
            case "type":
                this.setType((Integer) value);
                break;
        }
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
