package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:40
 * @description:
 */
import java.util.ArrayList;
import java.util.Iterator;

public class FileFindVisitor extends Visitor {
    private String type;
    private ArrayList found = new ArrayList();
    public FileFindVisitor(String type) {
        // TODO Auto-generated constructor stub
        this.type = type;
    }
    //返回已找到文件数组的迭代器
    public Iterator getFoundFiles(){
        return found.iterator();
    }
    //如果是文件，则将文件加入数组里
    @Override
    public void visit(File file) {
        // TODO Auto-generated method stub
        if(file.getName().endsWith(type)){
            found.add(file);
        }

    }
    //如果是文件夹，则递归访问，直至访问到文件
    @Override
    public void visit(Directory directory) {
        // TODO Auto-generated method stub
        Iterator it = directory.iterator();
        while(it.hasNext()){
            Entry entry = (Entry)it.next();
            entry.accpet(this);
        }

    }

}