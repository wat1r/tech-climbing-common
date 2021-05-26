package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:36
 * @description:
 */
//访问者的抽象类
public abstract class Visitor {
    //声明对不同数据结构的处理方法
    public abstract void visit(File file);

    public abstract void visit(Directory directory);
}
