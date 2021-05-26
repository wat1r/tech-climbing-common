package pattern.visitor.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:35
 * @description:
 */
//具体的实体类，文件类，提供类内数据
public class File extends Entry {

    private String name;
    private int size;

    public File(String name, int size) {
        // TODO Auto-generated constructor stub
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void accpet(Visitor v) {
        // TODO Auto-generated method stub
        v.visit(this);

    }

}