package com.seohee.fcfsordermsa.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

/**
 * Jwt 관련 함수들을 저장한 클래스.
 */
@Component
public class JwtUtil {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_KEY = "auth";
    private final long Token_Time = 60*60*1000L;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm =  SignatureAlgorithm.HS256;

    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그");

    /**
     * Keys.hmacShaKeyFor() : key byte array를 HMAC 알고리즘을 적용한 Key 객체를 생성함.
     */
    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * jwt를 만드는 함수.
     * @param email 유저의 이메일
     * @return jwt
     */
    public String createToken(String email) {
        Date date = new Date();
        logger.info("만드는중");
        return TOKEN_PREFIX +
                Jwts.builder()
                        .setSubject(email)
                        .setIssuedAt(date)
                        .setExpiration(new Date(date.getTime() + Token_Time))
                        .signWith(key, signatureAlgorithm)
                        .compact();
    }

    /**
     * jwt를 Cookie에 저장하고, Response 객체에 담아 보낸다.
     * @param token jwt
     * @param response
     */
    public void addJwtToCookie(String token, HttpServletResponse response) {
        try {
            logger.info("쿠키에 담는중");
            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20");
            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token);
            cookie.setPath("/");

            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Cookie에 들어있던 jwt 토큰을 TOKEN_PREFIX("BEARER ")와 분리
     * @param token 토큰 값
     * @return 분리된 토큰 값
     */
    public String subStringToken(String token){
        if (StringUtils.hasText(token) && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    /**
     * 토큰을 검증하는 함수
     * @param token
     * @return 검증되면 true, 검증 안되면 false
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            logger.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    /**
     * JWT에서 사용자 정보 가져오기
     * @param token
     * @return 사용자 정보
     */
    public Claims getUserInfoFormToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    /**
     * HttpServletRequest에서 Cookie 가져와서 JWT 가져오기
     * @param request
     * @return jwt
     */
    public String getTokenFromRequest(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(AUTHORIZATION_HEADER)){
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

}