package pattern.obersver;

/**
 * Created by FrankCooper
 * Date 2019/5/3 19:56
 * Description
 */
public class WeatherStation {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
//        CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
//        weatherData.setMeasurements(80, 65, 30.4f);
//        weatherData.setMeasurements(82, 70, 29.2f);
//        weatherData.setMeasurements(78, 78, 40.4f);
//        BaiduDisplay baiduDisplay = new BaiduDisplay();
//        weatherData.registerObserver();
//        BaiduDisplay baiduDisplay = new BaiduDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);

    }
}
