package serailize.jackson;

/**
 * @Date 2020/9/11
 * @Author Frank Cooper
 * @Description
 */
public class Circle extends Shape {
    int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
