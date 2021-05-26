package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:35
 * @description:
 */

import java.util.ArrayList;
import java.util.Iterator;

//文件夹类，提供文件夹的相关数据
public class Directory extends Entry {
    private String name;
    private ArrayList dir = new ArrayList();

    public Directory(String name) {
        // TODO Auto-generated constructor stub
        this.name = name;
    }

    @Override
    public void accpet(Visitor v) {
        // TODO Auto-generated method stub
        v.visit(this);

    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return name;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        int size = 0;
        Iterator it = dir.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            size += entry.getSize();
        }
        return size;
    }

    public Entry add(Entry entry) {
        dir.add(entry);
        return this;
    }

    public Iterator iterator() {
        return dir.iterator();

    }
}