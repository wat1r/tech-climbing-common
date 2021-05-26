package pattern.visitor.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:32
 * @description:
 */
public class VisitorPatternDemo {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
