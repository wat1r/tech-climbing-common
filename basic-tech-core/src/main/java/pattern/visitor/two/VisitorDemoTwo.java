package pattern.visitor.two;

import java.util.Iterator;

/**
 *
 * https://blog.csdn.net/zoinsung_lee/article/details/82777748
 *
 *
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/5/25 14:38
 * @description:
 */
public class VisitorDemoTwo {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            System.out.println("Making root entries...");
            Directory rootdir = new Directory("root");
            Directory bindir = new Directory("bin");
            Directory userdir = new Directory("user");
            Directory tmpdir = new Directory("tmp");
            rootdir.add(bindir);
            rootdir.add(tmpdir);
            rootdir.add(userdir);
            bindir.add(new File("vi", 10000));
            bindir.add(new File("latex", 20000));
            rootdir.accpet(new ListVisitor());

            System.out.println("========");
            Directory yuki = new Directory("yuki");
            Directory hanako = new Directory("hanako");
            Directory tomura = new Directory("tomura");
            userdir.add(yuki);
            userdir.add(hanako);
            userdir.add(tomura);
            yuki.add(new File("diary.html", 100));
            yuki.add(new File("Composite.java", 200));
            hanako.add(new File("memo.tex", 300));
            tomura.add(new File("game.doc", 400));
            tomura.add(new File("junk.mail", 500));
            rootdir.accpet(new ListVisitor());


            FileFindVisitor ffv = new FileFindVisitor(".html");
            rootdir.accpet(ffv);

            Iterator it = ffv.getFoundFiles();
            while (it.hasNext()) {
                File file = (File) it.next();
                System.out.println(file.toString());

            }

        } catch (FileTreatmentException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

}
