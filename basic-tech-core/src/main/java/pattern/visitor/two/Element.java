package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:36
 * @description:
 */
//数据结构的访问接口，声明数据结构对于访问者的接受方法
public interface Element {
    public abstract void accpet(Visitor v);
}
