package pattern.visitor.three.shapes;

import pattern.visitor.three.vistor.Visitor;

import java.util.ArrayList;
import java.util.List;

//组合形状
public class CompoundShape implements Shape {
    public int id;
    public List<Shape> children = new ArrayList<>();

    public CompoundShape(int id) {
        this.id = id;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void draw() {

    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompoundGraphic(this);
    }

    public int getId() {
        return id;
    }

    public void add(Shape shape) {
        this.children.add(shape);
    }
}
