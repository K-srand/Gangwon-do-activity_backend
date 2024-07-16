package com.multicampus.gangwonActivity.common;

import java.security.SecureRandom;

//새 비밀번호 생성
public class ChangePassword {

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
