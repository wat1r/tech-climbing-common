package serailize.jackson;

import java.util.List;

/**
 * @Date 2020/9/11
 * @Author Frank Cooper
 * @Description
 */
public class View {
    private List<Shape> shapes;

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }
}