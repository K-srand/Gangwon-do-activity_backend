package com.multicampus.ganwonActivity.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

//토큰의 유효성을 검증하는 제공자
@Component
public class JwtProvider {

    //임의로 지정하였음. 추후 수정
    @Value("${secret-key}")
    private String secretKey; //application.properties 내에 있는 값을 불러옴

    //jwt 생성
    public String create(String email){
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));


        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(email)
                .setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();

        return jwt;
    }

    //jwt 만료일자
    public String validate(String jwt){
        Claims claims = null;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody(); //signingkey의 만료 여부
        }catch (Exception e){
            e.printStackTrace();;
            return null;
        }
        return claims.getSubject();
    }
}
