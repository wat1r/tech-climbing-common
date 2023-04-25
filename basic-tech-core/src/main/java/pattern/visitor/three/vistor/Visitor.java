package pattern.visitor.three.vistor;

import pattern.visitor.three.shapes.Circle;
import pattern.visitor.three.shapes.CompoundShape;
import pattern.visitor.three.shapes.Dot;
import pattern.visitor.three.shapes.Rectangle;

//通用访问者接口
public interface Visitor {
    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
