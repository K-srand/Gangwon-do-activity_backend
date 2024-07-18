package com.multicampus.gangwonActivity.common;

//인증번호 생성
public class CertificationNumber {

    public static String getCertificationNumber(){

        String certificationNumber = "";

        for (int count= 0; count < 5 ; count++ ) certificationNumber += (int) (Math.random()*10);

        return certificationNumber;

    }

}
