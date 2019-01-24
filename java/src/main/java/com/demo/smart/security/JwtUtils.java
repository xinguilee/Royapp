package com.demo.smart.security;

import com.demo.smart.model.JwtUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";

    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";
    private static final String CLAIM_KEY_ACCOUNT_ENABLED = "enabled";
    private static final String CLAIM_KEY_ACCOUNT_NON_LOCKED = "non_locked";
    private static final String CLAIM_KEY_ACCOUNT_NON_EXPIRED = "non_expired";

    private String secret = "secret";

    private Long expiration = 6000l;

    public static JwtUtils jwtUtils;

    public static JwtUtils Instance(){
        if(jwtUtils == null){
            jwtUtils = new JwtUtils();
        }
        return jwtUtils;
    }

    /**
     * 获取用户名
     * @param token
     * @return
     */
    public String getUsername(String token){
        return getClaimAttribute(token, Claims::getSubject);
    }

    /**
     * 获取签发时间
     * @param token
     * @return
     */
    public Date getIssuedAtDate(String token){
        return getClaimAttribute(token, Claims::getIssuedAt);
    }

    /**
     * 获取过期时间
     * @param token
     * @return
     */
    public Date getExpirationDate(String token){
        return getClaimAttribute(token, Claims::getExpiration);
    }

    /**
     * 验证token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public Boolean validateToken(String token, UserDetails userDetails){
        JwtUserDetails user = (JwtUserDetails) userDetails;
        final String username = getUsername(token);
        final Date created = getIssuedAtDate(token);
        return username.equals(user.getUsername()) && !isExpired(token);
    }

    /**
     * 创建Token
     * @param auth
     * @return
     */
    public String createToken(Authentication auth){
        Map<String, Object> claims = new HashMap<>(16);
        //TODO
        claims.put("role", auth.getAuthorities());

        final Date date = DefaultClock.INSTANCE.now();
        final Date expirationDate = calculateExpirationDate(date);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(auth.getName())
                .setIssuedAt(date)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取Claims中属性值
     * @param token
     * @param claimsFunction
     * @param <T>
     * @return
     */
    private <T> T getClaimAttribute(String token, Function<Claims, T> claimsFunction){
        final Claims claims = getClaims(token);
        return claimsFunction.apply(claims);
    }

    /**
     * 解析token获取Claims
     * @param token
     * @return
     */
    private Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private Boolean isExpired(String token){
        final Date expiration = getExpirationDate(token);
        return expiration.before(DefaultClock.INSTANCE.now());
    }

    /**
     * 计算过期时间
     * @param date
     * @return
     */
    private Date calculateExpirationDate(Date date){
        return new Date(date.getTime() + expiration * 1000);
    }
}
