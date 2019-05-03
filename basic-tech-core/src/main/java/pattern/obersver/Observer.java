package pattern.obersver;

/**
 * Created by FrankCooper
 * Date 2019/5/3 19:45
 * Description
 */
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}
