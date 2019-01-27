package basic.jvm;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by FrankCooper
 * Date 2019/1/27 21:16
 * Description
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.indexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null) {
                    return super.loadClass(name);
                }
                try {
                    byte[] buff = new byte[is.available()];
                    is.read(buff);
                    return defineClass(name, buff, 0, buff.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }


            }
        };

        Object obj = loader.loadClass("basic.jvm.ClassLoaderTest").newInstance();
        System.out.println(JSON.toJSON(obj.getClass()));
        System.out.println(obj instanceof ClassLoaderTest);

    }
}
