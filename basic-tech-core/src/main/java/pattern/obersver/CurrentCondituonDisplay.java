package pattern.obersver;

/**
 * Created by FrankCooper
 * Date 2019/5/3 19:52
 * Description
 */
public class CurrentCondituonDisplay implements DisplayElement, Observer {
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentCondituonDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }


    @Override
    public void display() {
        System.out.println("Current conditions:" + temperature + "F degrees and " + humidity + "% humidity");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }
}
