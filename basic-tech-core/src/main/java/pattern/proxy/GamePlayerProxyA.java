package pattern.proxy;

/**
 * 客户端 对被代理对象不可见
 */
public class GamePlayerProxyA implements IGamePlayer {

    private IGamePlayer gamePlayer = null;//被代理对象

    // 通过构造函数传递要对谁进行代练
    public GamePlayerProxyA(String username) {
        this.gamePlayer = new GamePlayer(username);
    }

    // 代练杀怪
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    // 代练登录
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    // 代练升级
    public void upgrade() {
        this.gamePlayer.upgrade();
    }

}
