package pattern.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/*
 * 客户端 对被代理对象不可见
 */
public class GamePlayerProxyC {

    private IGamePlayer gamePlayer;
    // 通过构造函数传递 被代练(代理)对象
    public GamePlayerProxyC(IGamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
        System.out.println("我是一名代练,我玩的角色是别人的，可以动态传递进来");
    }

    public IGamePlayer getProxy() {
        return (IGamePlayer) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{IGamePlayer.class}, new MyInvocationHandler());
    }

    private class MyInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("login")) {
                System.out.println("登录时间是：" + new Date().toLocaleString());
            } if (method.getName().equals("upgrade")) {
                System.out.println("升级时间是：" + new Date().toLocaleString());
            }
            method.invoke(gamePlayer, args);
            return null;
        }

    }
}
