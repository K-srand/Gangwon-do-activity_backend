package com.multicampus.gangwonActivity.common;

import java.security.SecureRandom;

public class TemporaryPassword {

    private static final String Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";

    public static String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(15);

        for (int count = 0; count < 15; count++) {
            int index = random.nextInt(Char.length());
            password.append(Char.charAt(index));

        }
        return password.toString();
    }
}
