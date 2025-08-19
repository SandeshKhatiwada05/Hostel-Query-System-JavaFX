package org.javafx.hostelmanagementfx.model;

public class Hostel {
    private final String name;
    private final String imageUrl;
    private final double price1;
    private final double price2;
    private final double price3;
    private final double price4;
    private final String category; // "Boys" or "Girls"

    public Hostel(String name, String imageUrl,
                  double price1, double price2, double price3, double price4,
                  String category) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        this.price4 = price4;
        this.category = category;
    }

    public String getName() { return name; }
    public String getImageUrl() { return imageUrl; }
    public double getPrice1() { return price1; }
    public double getPrice2() { return price2; }
    public double getPrice3() { return price3; }
    public double getPrice4() { return price4; }
    public String getCategory() { return category; }
}
