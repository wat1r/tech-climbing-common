package pattern.visitor.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:30
 * @description:
 */
public interface ComputerPart {
    public void accept(ComputerPartVisitor computerPartVisitor);
}
