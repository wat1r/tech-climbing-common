package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:35
 * @description:
 */

import java.util.Iterator;

//数据结构的抽象类，中间声明了抽象方法
public abstract class Entry implements Element {

    public abstract String getName();

    public abstract int getSize();

    public Entry add(Entry entry) throws FileTreatmentException {
        throw new FileTreatmentException();
    }

    public Iterator iterator() throws FileTreatmentException {
        throw new FileTreatmentException();
    }

    public String toString() {
        return getName() + "(" + getSize() + ")";
    }

}