package com.example.webbanmypham.FunctionalAccessory;

import java.security.SecureRandom;

public class RandomID {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String NUMBERTERS = "0123456789";
    public static String generateRandomID(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Length must be at least 1");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder randomID = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomID.append(randomChar);
        }

        return randomID.toString();
    }
    public static void main(String[] args) {
        // Generate a random ID with a minimum length of 50 characters
        String randomID = generateRandomID(6);
        System.out.println("Random ID: " + randomID);
    }
}
