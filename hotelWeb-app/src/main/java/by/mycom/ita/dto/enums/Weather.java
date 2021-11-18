package by.mycom.ita.dto.enums;

public enum Weather {
    SUN("icons8-sun-20.png"),
    RAIN("icons8-rain-20.png"),
    SNOW("icons8-snow-20.png"),
    WIND("icons8-wind-20.png");

    private final String image;

    Weather(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
