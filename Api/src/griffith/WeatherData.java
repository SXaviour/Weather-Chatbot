package Api.src.griffith;

/**
 * Task for Saviour;
 * Enhancements to WeatherData class:
 * - This class will now includes additional attributes to store comprehensive weather data, such as temperature, humidity, wind speed, and a descriptive weather condition (e.g., sunny, cloudy, rainy).
 * - A method to determine the general weather condition ('cold', 'mild', 'hot') based on the temperature is included within the class. This method categorizes the temperature at the time of object creation.
 * - The constructor has been updated to initialize all new fields and calculate the weather condition based on the provided temperature.
 * - Getters are provided for each field, allowing other parts of the application to access the weather details. No setters are included, making instances of this class immutable once created.
 * - The purpose of these enhancements is to enable more detailed and accurate clothing recommendations based on a richer set of weather parameters.
 *
 * Responsibilities:
 * - Implement the new fields (temperature, humidity, wind speed, weatherDescription) with appropriate data types.
 * - Update the constructor to initialize these new fields and to set weatherCondition based on the calculated value from temperature windspeed and rain level.
 * - Implement the private method `determineWeatherCondition(double temperature, .......... e.t.c)` to categorize temperature into 'cold', 'mild', or 'hot' etc.
 * - Ensure that all data fields are properly encapsulated with public getter methods.
 */

//Saviour will implement this method
public class WeatherData {
    private double temperature;
    private double humidity;
    private double windSpeed;
    private double rainLevel;
    private final String weatherCondition;

    /**
     * Constructor to initialize all fields.
     * @param temperature Temperature in degrees Celsius.
     * @param humidity Humidity percentage.
     * @param windSpeed Wind speed in m/s.
     * @param rainLevel Rain intensity in mm/h.
     */

    //Initializes WeatherData with temperature, humidity, wind speed, rain level, and calculates the overall weather condition.
    public WeatherData(double temperature, double humidity, double windSpeed, double rainLevel) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.rainLevel = rainLevel;
        this.weatherCondition = determineWeatherCondition(temperature, rainLevel, windSpeed);
    }

    /**
     * Private method to determine weather condition based on temperature, rain level, and wind speed.
     * Implements logic to categorize weather into various conditions such as "veryCold&Stormy", "warm&Raining", etc.
     * @param temperature Temperature in degrees Celsius.
     * @param rainLevel Rain intensity in mm/h.
     * @param windSpeed Wind speed in m/s.
     * @return A string representing the categorized weather condition.
     */
    private String determineWeatherCondition(double temperature, double rainLevel, double windSpeed) {

        // Checking for cold conditions
        if (temperature <= 12) {
            if (rainLevel >= 2 && windSpeed > 10) {
                return "veryCold&Stormy";
            } else if (rainLevel >= 2) {
                return "veryCold&Raining";
            } else if (windSpeed > 10) {
                return "veryCold&Windy";
            } else {
                return "veryCold";
            }

            //Checking for warm conditions
        } else if (temperature >= 13 && temperature <= 19) {
            if (rainLevel >= 2 && windSpeed > 10) {
                return "warm&Stormy";
            } else if (rainLevel >= 2) {
                return "warm&Raining";
            } else if (windSpeed > 10) {
                return "warm&Windy";
            } else {
                return "warm";
            }

            //Checking for hot conditions
        } else if (temperature > 20) {
            if (rainLevel >= 2 && windSpeed > 10) {
                return "hot&Stormy";
            } else if (rainLevel >= 2) {
                return "hot&Raining";
            } else if (windSpeed >= 10) {
                return "hot&Windy";
            } else {
                return "hot";
            }
        }
        return "unknown"; // Default condition
    }

    // Getters
    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getRainLevel() {
        return rainLevel;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }
}
