package com.splitwizard.splitwizard.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
    private static final String CLAIMS_KEY_USER_ROLES = "userRoles";
    private static final String CLAIMS_KEY_USER_ID = "userId";
    private static final String CLAIMS_KEY_UID = "UID";
    private static final String CLAIMS_KEY_MEMBER_NAME = "name";

//    private static @Value("${jwt.signKey}") String jwtSignKey;
    private final String jwtSignKey = "sexyEasonHasadghaspdogiuhas;dlgkhjasfgarharhsarh";

    public String createToken(String account, List<String> userRoles, Integer userId, String UID, String memberName){
        //    private static @Value("${jwt.expireTimeAsSec}") long jwtExpireTimeAsSec;
        long jwtExpireTimeAsSec = 86400L; // one day
        String token = Jwts.builder()
                .setSubject(account)
                .addClaims(Map.of(CLAIMS_KEY_USER_ROLES, userRoles)) // 把 userRoles 也記錄進來
                .addClaims(Map.of(CLAIMS_KEY_USER_ID, userId))
                .addClaims(Map.of(CLAIMS_KEY_UID, UID))
                .addClaims(Map.of(CLAIMS_KEY_MEMBER_NAME, memberName))
                .setIssuedAt(new Date()) //產生 JWT 的時間
                .setExpiration(Date.from(Instant.now().plusSeconds(jwtExpireTimeAsSec))) // JWT 過期時間
                .signWith(Keys.hmacShaKeyFor(jwtSignKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
        logger.debug("token : {}", token);
        return token;
    }


    /**
     * 當 token 解析失敗時，會丟出對應的 Exception。一般來說會遇到失敗是因為 token 過期、token 內容被竄改。
     *
     */
    private Claims parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSignKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();
        logger.debug("claims : {}", claims);
        return claims;
    }

    public String parseUserNameFromToken(String token) {
        return parseToken(token).getSubject();
    }

    public List<SimpleGrantedAuthority> parseUserAuthoritiesFromToken(String token) {
        List<String> userRoles = parseToken(token).get(CLAIMS_KEY_USER_ROLES, List.class);
        logger.debug("userRoles : {}", userRoles);
        return userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Integer parseUserIdFromToken(String token){
        return parseToken(token).get(CLAIMS_KEY_USER_ID, Integer.class);
    }

    public String parseUIDFromToken(String token){
        return parseToken(token).get(CLAIMS_KEY_UID, String.class);
    }
    public String parseMemberNameFromToken(String token){
        return parseToken(token).get(CLAIMS_KEY_MEMBER_NAME, String.class);
    }
}
