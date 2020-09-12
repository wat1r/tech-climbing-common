package serailize.jackson;

/**
 * @Date 2020/9/11
 * @Author Frank Cooper
 * @Description
 */
public class Rectangle extends Shape {
    private int w;
    private int h;

    public Rectangle(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
