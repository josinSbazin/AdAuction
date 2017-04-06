package com.rhanza;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AuctionTest {

    @org.junit.jupiter.api.Test
    void testStartAuctionWithNull() {
        List<Creative> result = Auction.startAuction(null, -1, null);
        Assertions.assertArrayEquals(result.toArray(), Collections.emptyList().toArray());

        List<Creative> result1 = Auction.startAuction(null, 0, null);
        Assertions.assertArrayEquals(result1.toArray(), Collections.emptyList().toArray());

        List<Creative> result2 = Auction.startAuction(null, 1, null);
        Assertions.assertArrayEquals(result2.toArray(), Collections.emptyList().toArray());

        List<Creative> result3 = Auction.startAuction(null, 42, null);
        Assertions.assertArrayEquals(result3.toArray(), Collections.emptyList().toArray());

        List<Creative> result4 = Auction.startAuction(null, -1);
        Assertions.assertArrayEquals(result4.toArray(), Collections.emptyList().toArray());

        List<Creative> result5 = Auction.startAuction(null, 0);
        Assertions.assertArrayEquals(result5.toArray(), Collections.emptyList().toArray());

        List<Creative> result6 = Auction.startAuction(null, 1);
        Assertions.assertArrayEquals(result6.toArray(), Collections.emptyList().toArray());

        List<Creative> result7 = Auction.startAuction(null, 42);
        Assertions.assertArrayEquals(result7.toArray(), Collections.emptyList().toArray());

    }

    @org.junit.jupiter.api.Test
    void testStartAuctionWithSamePricesAndAdsIds() {
        List<Creative> testCreatives = new ArrayList<>();

        testCreatives.add(new Creative(102, 4, Creative.Country.GK));
        testCreatives.add(new Creative(102, 1, Creative.Country.US));
        testCreatives.add(new Creative(102, 2));
        testCreatives.add(new Creative(11, 3, Creative.Country.RU));
        testCreatives.add(new Creative(102, 1));
        testCreatives.add(new Creative(1, 4, Creative.Country.RU));
        testCreatives.add(new Creative(102, 2, Creative.Country.RU));
        testCreatives.add(new Creative(102, 3, Creative.Country.RU));
        testCreatives.add(new Creative(10, 4, Creative.Country.UA));
        testCreatives.add(new Creative(20, 5, Creative.Country.RU));
        testCreatives.add(new Creative(12, 6, Creative.Country.RU));
        testCreatives.add(new Creative(42, 12, Creative.Country.RU));
        testCreatives.add(new Creative(1, 214, Creative.Country.GK));
        testCreatives.add(new Creative(10, 44, Creative.Country.RU));
        testCreatives.add(new Creative(100, 42, Creative.Country.RU));
        testCreatives.add(new Creative(10, 24, Creative.Country.RU));
        testCreatives.add(new Creative(70, 42, Creative.Country.UA));
        testCreatives.add(new Creative(20, 4, Creative.Country.RU));
        testCreatives.add(new Creative(30, 241, Creative.Country.RU));
        testCreatives.add(new Creative(10, 24, Creative.Country.RU));

        List<Creative> result1 = Auction.startAuction(testCreatives, 1, Creative.Country.RU);
        Assertions.assertEquals(result1.get(0).getPrice(), 102);
        Assertions.assertEquals(result1.size(), 1);

        List<Creative> result2 = Auction.startAuction(testCreatives, 10);
        Assertions.assertEquals(result2.size(), 4);

        List<Creative> result3 = Auction.startAuction(testCreatives, 3);
        Assertions.assertEquals(result3.size(), 3);

        List<Creative> result4 = Auction.startAuction(testCreatives, 10, Creative.Country.RU);
        Assertions.assertEquals(result4.size(), 3);

    }
//        1) all winners must have unique advertiser id
    @org.junit.jupiter.api.Test
    void testStartAuctionTask1() {
        List<Creative> testCreatives = new ArrayList<>();

        testCreatives.add(new Creative(102, 1, Creative.Country.GK));
        testCreatives.add(new Creative(102, 1, Creative.Country.RU));
        testCreatives.add(new Creative(102, 1, Creative.Country.RU));
        testCreatives.add(new Creative(102, 1, Creative.Country.UA));
        testCreatives.add(new Creative(102, 1, Creative.Country.UA));
        testCreatives.add(new Creative(102, 2, Creative.Country.UA));
        testCreatives.add(new Creative(102, 2, Creative.Country.US));
        testCreatives.add(new Creative(102, 2, Creative.Country.US));
        testCreatives.add(new Creative(102, 2, Creative.Country.US));
        testCreatives.add(new Creative(102, 3, Creative.Country.GK));
        testCreatives.add(new Creative(102, 3, Creative.Country.GK));
        testCreatives.add(new Creative(102, 3, Creative.Country.GK));
        testCreatives.add(new Creative(102, 3, Creative.Country.RU));
        testCreatives.add(new Creative(102, 4, Creative.Country.GK));
        testCreatives.add(new Creative(-1, 5, Creative.Country.GK));
        testCreatives.add(new Creative(102, 5, Creative.Country.GK));
        testCreatives.add(new Creative(102, 6, Creative.Country.RU));

        List<Creative> result1 = Auction.startAuction(testCreatives, 10);
        Assertions.assertEquals(result1.size(), 6);

        List<Creative> result2 = Auction.startAuction(testCreatives, 10, Creative.Country.RU);
        Assertions.assertEquals(result2.size(), 3);

        List<Creative> result3 = Auction.startAuction(testCreatives, 10);

        int[] ids = new int[6];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = result3.get(i).getIdOfAdvertiser();
        }

        Assertions.assertEquals(diffValues(ids), 6);
    }


//    2) if third argument (country) is provided, then only creatives
//    without country or creatives with same country can be among winners
    @org.junit.jupiter.api.Test
    void testStartAuctionTask2() {
        List<Creative> testCreatives = new ArrayList<>();

        testCreatives.add(new Creative(102, 1));
        testCreatives.add(new Creative(102, 2, Creative.Country.RU));
        testCreatives.add(new Creative(102, 3, Creative.Country.RU));
        testCreatives.add(new Creative(102, 4, Creative.Country.RU));
        testCreatives.add(new Creative(102, 5, Creative.Country.RU));

        testCreatives.add(new Creative(102, 6, Creative.Country.UA));
        testCreatives.add(new Creative(102, 7, Creative.Country.UA));
        testCreatives.add(new Creative(102, 8, Creative.Country.UA));
        testCreatives.add(new Creative(102, 9, Creative.Country.US));
        testCreatives.add(new Creative(102, 10, Creative.Country.US));
        testCreatives.add(new Creative(102, 11, Creative.Country.US));
        testCreatives.add(new Creative(102, 12, Creative.Country.GK));
        testCreatives.add(new Creative(102, 13, Creative.Country.GK));
        testCreatives.add(new Creative(102, 14, Creative.Country.GK));
        testCreatives.add(new Creative(102, 15));
        testCreatives.add(new Creative(102, 16));

//         without country and same
        List<Creative> result1 = Auction.startAuction(testCreatives, 10, Creative.Country.RU);
        Assertions.assertEquals(result1.size(), 7);

//          all winners
        List<Creative> result2 = Auction.startAuction(testCreatives, 20);
        Assertions.assertEquals(result2.size(), 16);

    }

//    3*) function should not give preference to any of equal by price
//    creatives, but should return such creatives equiprobable.

//    not stable test
    @org.junit.jupiter.api.Test
    void testStartAuctionTask3() {
        List<Creative> testCreatives = new ArrayList<>();

        testCreatives.add(new Creative(102, 2, Creative.Country.RU));
        testCreatives.add(new Creative(102, 2, Creative.Country.US));
        testCreatives.add(new Creative(102, 2, Creative.Country.UA));
        testCreatives.add(new Creative(102, 2, Creative.Country.GK));

        testCreatives.add(new Creative(102, 1, Creative.Country.RU));
        testCreatives.add(new Creative(102, 1, Creative.Country.US));
        testCreatives.add(new Creative(102, 1, Creative.Country.UA));
        testCreatives.add(new Creative(102, 1, Creative.Country.GK));

        testCreatives.add(new Creative(102, 3, Creative.Country.RU));
        testCreatives.add(new Creative(102, 3, Creative.Country.US));
        testCreatives.add(new Creative(102, 3, Creative.Country.UA));
        testCreatives.add(new Creative(102, 3, Creative.Country.GK));

        int contOfCicles = 100;

        int[] countryMarks = new int[contOfCicles];

        for (int i = 0; i < contOfCicles; i++) {
            countryMarks[i]
                    = Auction.startAuction(testCreatives, 1).get(0).getCountryNameToServe().ordinal();
            }

            Assertions.assertTrue(diffValues(countryMarks)==4);
        }


    @org.junit.jupiter.api.Test
    void testDiffValues() {
        int[] notSame = new int[] {1, 2, 3, 4, 5, 6, 7};
        int[] same = new int[] {1, 1, 2, 2, 3, 3, 4, 5, 6, 7};

        Assertions.assertEquals(diffValues(notSame), diffValues(same));
    }

    private int diffValues(int[] numArray){
        int numOfDifferentVals = 0;

        ArrayList<Integer> diffNum = new ArrayList<>();

        for(int i=0; i<numArray.length; i++){
            if(!diffNum.contains(numArray[i])){
                diffNum.add(numArray[i]);
            }
        }

        if(diffNum.size()==1){
            numOfDifferentVals = 0;
        }
        else{
            numOfDifferentVals = diffNum.size();
        }

        return numOfDifferentVals;
    }
}