package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:35
 * @description:
 */

import java.util.Iterator;

//访问者的具体类，实现对数据结构的处理

public class ListVisitor extends Visitor {
    private String currentdir = "";

    //根据传入参数的不同，对同名方法进行重载
    @Override
    public void visit(File file) {
        // TODO Auto-generated method stub
        System.out.println(currentdir + "/" + file);

    }

    @Override
    public void visit(Directory directory) {
        // TODO Auto-generated method stub
        System.out.println(currentdir + "/" + directory);
        String savedir = currentdir;
        currentdir = currentdir + "/" + directory.getName();
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.accpet(this);
        }
        currentdir = savedir;

    }

}
