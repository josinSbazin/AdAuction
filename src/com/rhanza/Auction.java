package com.rhanza;

import com.sun.istack.internal.NotNull;

import java.util.*;

/**
 * Auction util
 *
 * by RH
 * 17-04-06
 */
public class Auction {

    /**
     * Returning winner creatives, obeying the following rules:
     *
     * 1) all winners must have unique advertiser id
     * 2) if third argument (country) is provided, then only creatives
     * without country or creatives with same country can be among winners
     * 3*) function should not give preference to any of equal by price
     * creatives, but should return such creatives equiprobable.
     *
     * @param creatives list of creatives for auction
     * @param numberOfWinners max number of winners
     * @param countryToServe country, where creatives can shown
     * @return list of winner creatives, obeying the following rules:
     *
     * 1) all winners must have unique advertiser id
     * 2) if third argument (country) is provided, then only creatives
     * without country or creatives with same country can be among winners
     * 3*) function should not give preference to any of equal by price
     * creatives, but should return such creatives equiprobable.
     */
    public static List<Creative> startAuction(@NotNull List<Creative> creatives, int numberOfWinners,
                                          Creative.Country countryToServe) {

        if (creatives == null || creatives.size() == 0 || numberOfWinners <= 0) {
            return Collections.emptyList();
        }

        List<Creative> validCreatives = getValidLocalityCreatives(creatives, countryToServe);
        if (validCreatives.size() == 1 || validCreatives.isEmpty()) {
            return validCreatives;
        }

        validCreatives = getMaxPricedCreatives(validCreatives);
        if (validCreatives.size() == 1) {
            return validCreatives;
        }

        return getWinners(validCreatives, numberOfWinners);
    }


    public static List<Creative> startAuction(@NotNull List<Creative> creatives,
                                          int numberOfWinners) {
        return startAuction(creatives, numberOfWinners, null);
    }


    //get creatives with valid country to serve or without that;
    private static List<Creative> getValidLocalityCreatives(List<Creative> creatives, Creative.Country countryToServe) {
        List<Creative> validCreatives;

        if (countryToServe == null) validCreatives = new ArrayList<>(creatives);
        else {
            validCreatives = new ArrayList<>();
            for(Creative creative : creatives) {
                Creative.Country country = creative.getCountryNameToServe();

                if (country == null || country.equals(countryToServe)) {
                    validCreatives.add(creative);
                }
            }

            if (validCreatives.isEmpty()) {
                validCreatives = Collections.emptyList();
            }
        }
        return validCreatives;
    }


    private static List<Creative> getMaxPricedCreatives(List<Creative> creatives) {
        List<Creative> creativesForManipulate = new ArrayList<>(creatives);
        Collections.sort(creativesForManipulate);

        List<Creative> maxPricedCreatives = new ArrayList<>();

        double bestPrice = creativesForManipulate.get(0).getPrice();

        for (Creative creative : creativesForManipulate) {
            if (creative.getPrice() == bestPrice) {
                maxPricedCreatives.add(creative);
            }
        }

        return maxPricedCreatives;
    }

    //better use Guava Multimap lib, but i'll go a little heavier way for clarity
    private static List<Creative> getWinners(List<Creative> creatives, int numberOfWinners) {
        Map<Integer, List<Creative>> creativesMap = new HashMap<>();

        for (Creative creative : creatives) {
            Integer keyIdAd = creative.getIdOfAdvertiser();
            List<Creative> currentList = creativesMap.get(keyIdAd);

            if (currentList == null) {
                currentList = new ArrayList<>();
            }
            currentList.add(creative);
            creativesMap.put(keyIdAd, currentList);
        }

        return getEquiprobableResult(creativesMap, numberOfWinners);
    }

    private static List<Creative> getEquiprobableResult(Map<Integer, List<Creative>> map, int numberOfWiners) {
        List<Creative> result = new ArrayList<>();
        Random random = new Random();

        List<Integer> keyArray = new ArrayList<>(map.keySet());

        int limit = numberOfWiners > keyArray.size() ? keyArray.size() : numberOfWiners;

        for (int i = 0; i < limit; i++) {
            Integer randomKey = keyArray.get(random.nextInt(keyArray.size()));
            keyArray.remove(randomKey);
            List<Creative> randomList = map.get(randomKey);
            int randomId = random.nextInt(randomList.size());

            result.add(randomList.get(randomId));
        }

        return result;
    }
}
