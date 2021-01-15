package app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FoodDto {
    @JsonProperty(value = "info")
    private final String info;

    public FoodDto() {
        this.info = "";
    }

    public FoodDto(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
