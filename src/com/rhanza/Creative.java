package com.rhanza;

/**
 * sample Creative class
 *
 * by RH
 * 17-04-06
 */
public class Creative implements Comparable<Creative> {
    //need use BigDecimals class for operation with money in java, or custom Money class,
    //but for this example i'll use double type.

    private double price;
    private int idOfAdvertiser;
    private Country countryNameToServe;

    public Creative(double price, int idOfAdvertiser, Country countryNameToServe) {
        this.price = price;
        this.idOfAdvertiser = idOfAdvertiser;
        this.countryNameToServe = countryNameToServe;
    }


    public Creative(double price, int idOfAdvertiser) {
        this.price = price;
        this.idOfAdvertiser = idOfAdvertiser;
        this.countryNameToServe = null;
    }

    public double getPrice() {
        return price;
    }

    public int getIdOfAdvertiser() {
        return idOfAdvertiser;
    }

    public Country getCountryNameToServe() {
        return countryNameToServe;
    }

    @Override
    public int compareTo(Creative creative) {
        return (int) ((creative.price - this.price) * 100);
    }

    //this mocked list of countries
    enum Country {
        RU,
        UA,
        GK,
        US
    }

    @Override
    public String toString() {
        return "Creative{" +
                "price=" + price +
                ", idOfAdvertiser=" + idOfAdvertiser +
                ", countryNameToServe=" + countryNameToServe +
                ", " + this.hashCode() +
                '}';
    }
}
