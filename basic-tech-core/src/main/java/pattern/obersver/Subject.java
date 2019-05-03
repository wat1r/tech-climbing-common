package pattern.obersver;

/**
 * Created by FrankCooper
 * Date 2019/5/3 19:44
 * Description
 */
public interface Subject {
    /**
     * 注册观察者
     * @param observer
     */
    public void registerObserver(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    public void removeOberver(Observer observer);

    /**
     * 当主题状态发生改变时，这个方法需要被调用，以通知所有观察者
     */
    public void notifyObserver();
}
