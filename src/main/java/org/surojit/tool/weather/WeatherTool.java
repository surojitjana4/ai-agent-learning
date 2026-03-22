package org.surojit.tool.weather;

import dev.langchain4j.agent.tool.Tool;

public class WeatherTool {

    @Tool("Get weather of a city")
    public String getWeather(String city) {
        System.out.println("getWeather called");
        return "35°C and sunny in " + city;
    }

    @Tool("Get current time")
    public String getTime() {
        System.out.println("getTime called");
        return java.time.LocalTime.now().toString();
    }

    @Tool("Get current date")
    public String getDate() {
        System.out.println("getDate called");
        return java.time.LocalDate.now().toString();
    }
}
