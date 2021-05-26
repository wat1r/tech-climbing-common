package pattern.visitor.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:31
 * @description:
 */
public interface ComputerPartVisitor {
    public void visit(Computer computer);
    public void visit(Mouse mouse);
    public void visit(Keyboard keyboard);
    public void visit(Monitor monitor);
}
