//Here's how this authentication stuff goes:
//A user using a client(website,app) sends a login request consisting of username/password/email
//Some Authentication Manager, checks from the DB whether this user exists or not
//After checking it goes “Cool, you’re legit. Here’s your access card.” and sends a Jwt token(access card)
// back to the client
//Then the user needs to call other protected APIs,there we attach the token in the form like
// "Authorization: Bearer eyJhbGciOi..."
//Now your backend needs to ask:
//“Is this token still valid? Was it really issued by me? Has it expired? Is it tampered with?”
//Even though the user was authenticated earlier, the server doesn’t remember that — because JWT is stateless.
//That means:
// 1:Server dosen't store any session
// 2:It has to validate each token on every request from scratch
//Think of JWT as an ID card:
//        When you first log in (step 1), you get the card.
//        Now, every time you enter a secure building (API route), you must show your ID card
//        The guard (backend) checks:
//        Is it real?
//        Is it expired?
//        Was it forged?

//***We don’t remember who you are between requests. Your JWT token proves your identity every time.***



        package com.project.TaskManager.Security.Jwt;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils { //this class is the core handler of Jwt operations
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    @Value("${spring.ecom.app.jwtCookieName}")
    private String jwtCookie;


    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal) {
        String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, jwt)
                .path("/api")
                .maxAge(24 * 60 * 60)
                .httpOnly(false)
                .secure(false)
                .build();
        return cookie;
    }

    public ResponseCookie getCleanJwtCookie() {
        ResponseCookie cookie = ResponseCookie.from(jwtCookie, null)
                .path("/api")
                .build();
        return cookie;
    }
    public String generateTokenFromUsername(String username) { //Takes the username and generates a signed JWT
        return Jwts.builder()
                .subject(username) //This username is passed during login request
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken) {
        try {
            System.out.println("Validate");
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
