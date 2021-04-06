package fd.se.btsplus.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.repository.bts.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@AllArgsConstructor
public class TokenUtils {
    private final UserRepository userRepository;
    private final Algorithm algorithm = Algorithm.HMAC256("二刺螈");

    public String generateToken(User user) {
        return JWT.create().
                withClaim("username", user.getUsername()).
                withClaim("password", user.getPassword()).
                withExpiresAt(dateAfter(12)).
                sign(algorithm);
    }


    public User getUser(String token) {
        final DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(algorithm).build().verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
        final String username = decodedJWT.getClaim("username").asString();
        final String password = decodedJWT.getClaim("password").asString();
        return userRepository.findByUsernameAndPassword(username, password);
    }

    private boolean expired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(dateAfter(0));
    }

    public static java.util.Date dateAfter(int hours) {
        return Date.from(LocalDateTime.now().plusHours(hours).
                atZone(ZoneId.systemDefault()).toInstant());
    }
}
