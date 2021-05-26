package pattern.visitor.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:31
 * @description:
 */
public class Mouse  implements ComputerPart {

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}