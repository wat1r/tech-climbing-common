package basic.jvm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by FrankCooper
 * Date 2019/2/3 20:39
 * Description
 */
public class HelloWorld extends FileInputStream implements Runnable,ActionListener{

    public HelloWorld(File file) throws FileNotFoundException {
        super(file);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }
}
