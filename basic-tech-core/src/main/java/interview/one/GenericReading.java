package interview.one;

import java.util.Arrays;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/4/5 9:45
 * Description
 */
public class GenericReading {
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());

    static class Reader<T> {
        T readExact(List<T> list) {
            return list.get(0);
        }
    }

    static class CovariantReader<T> {
        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f1() {
        Reader<Fruit> fruitReader = new Reader<Fruit>();
        // Errors: List<Fruit> cannot be applied to List<Apple>.
//         Fruit f = fruitReader.readExact(apples);
    }

    static void f2(){
        CovariantReader<Fruit> fruitReader = new CovariantReader<>();
        Fruit f = fruitReader.readCovariant(GenericReading.fruit);
        Fruit a = fruitReader.readCovariant(GenericReading.apples);
    }

    public static void main(String[] args) {
        f1();
    }
}

class Fruit {
}

class Apple extends Fruit {
}

class Orange extends Fruit {
}
