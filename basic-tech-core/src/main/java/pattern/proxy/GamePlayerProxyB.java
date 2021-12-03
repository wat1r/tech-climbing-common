package pattern.proxy;

import java.util.Date;

/*
 * 客户端 对被代理对象不可见
 */
public class GamePlayerProxyB implements IGamePlayer {

    private IGamePlayer gamePlayer = null;//被代理对象

    // 通过构造函数传递要对谁进行代练
    public GamePlayerProxyB(String username) {
        this.gamePlayer = new GamePlayer(username);
    }

    // 代练杀怪
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    // 代练登录
    public void login(String user, String password) {
        System.out.println("登录时间是：" + new Date().toLocaleString());
        this.gamePlayer.login(user, password);
    }

    // 代练升级
    public void upgrade() {
        this.gamePlayer.upgrade();
        System.out.println("升级时间是：" + new Date().toLocaleString());
    }

}