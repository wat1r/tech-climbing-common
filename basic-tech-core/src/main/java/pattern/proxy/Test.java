package pattern.proxy;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        /*
         * 普通的静态代理: 客户端不知道被代理对象，由代理对象完成其功能的调用
         */
        IGamePlayer proxy = new GamePlayerProxyA("X");
        System.out.println("开始时间是：" + new Date().toLocaleString());
        proxy.login("zhangSan", "abcd");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：" + new Date().toLocaleString());

        System.out.println();

        /*
         * 代理对象 增强了 被代理对象的功能
         */
        IGamePlayer proxy2 = new GamePlayerProxyB("M");
        proxy2.login("lisi", "efg");
        proxy2.killBoss();
        proxy2.upgrade();

        System.out.println();

        /*
         * 动态代理：使用jdk提供的InvocationHandler，反射调用被代理对象的方法
         * 结合java.reflect.Proxy 产生代理对象
         * 动态传入被代理对象构造InvocationHandler,在handler中的invoke时可以增强被代理对象的方法的功能
         * 或者说：(面向切面:)在什么地方（连接点）, 执行什么行为（通知）
         * GamePlayerProxy3中是方法名为login时通知开始时间，upgrade时通知结束时间
         */
        GamePlayerProxyC dynamic = new GamePlayerProxyC(new GamePlayer("Y"));
        IGamePlayer dynamicPlayer = dynamic.getProxy();
        dynamicPlayer.login("wangwu", "1234");
        dynamicPlayer.killBoss();
        dynamicPlayer.upgrade();
        /*
         * 面向切面： 一些相似的业务逻辑需要加在众多的地方，那们就可以把它提取到切面中， 切面也就是事务切面:如日志切面、权限切面、业务切面
         */
    }
}
