package com.rhanza;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Creative> testCreatives = new ArrayList<>();

        testCreatives.add(new Creative(100, 1, Creative.Country.RU));
        testCreatives.add(new Creative(10, 1, Creative.Country.RU));
        testCreatives.add(new Creative(102, 2, Creative.Country.RU));
        testCreatives.add(new Creative(11, 3, Creative.Country.RU));
        testCreatives.add(new Creative(1, 1, Creative.Country.RU));
        testCreatives.add(new Creative(1, 4, Creative.Country.RU));
        testCreatives.add(new Creative(102, 2, Creative.Country.RU));
        testCreatives.add(new Creative(102, 3, Creative.Country.RU));
        testCreatives.add(new Creative(10, 4, Creative.Country.RU));
        testCreatives.add(new Creative(20, 5, Creative.Country.RU));
        testCreatives.add(new Creative(12, 6, Creative.Country.RU));
        testCreatives.add(new Creative(42, 12, Creative.Country.RU));
        testCreatives.add(new Creative(1, 214, Creative.Country.RU));
        testCreatives.add(new Creative(10, 44, Creative.Country.RU));
        testCreatives.add(new Creative(100, 42, Creative.Country.RU));
        testCreatives.add(new Creative(10, 24, Creative.Country.RU));
        testCreatives.add(new Creative(70, 42, Creative.Country.RU));
        testCreatives.add(new Creative(20, 4, Creative.Country.RU));
        testCreatives.add(new Creative(30, 241, Creative.Country.RU));
        testCreatives.add(new Creative(10, 24, Creative.Country.RU));

        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));
        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));
        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));
        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));
        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));
        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));
        System.out.println(Auction.startAuction(testCreatives, 2, Creative.Country.RU));

        System.out.println(Auction.startAuction(null, 0, null));

    }
}
