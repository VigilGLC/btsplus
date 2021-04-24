package fd.se.btsplus.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import fd.se.btsplus.model.entity.bts.User;
import fd.se.btsplus.repository.bts.UserRepository;
import fd.se.btsplus.service.IDateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class TokenUtils {
    private final IDateService dateService;
    private final UserRepository userRepository;
    private final Algorithm algorithm = Algorithm.HMAC256("二刺螈");

    public String generateToken(User user) {
        return JWT.create().
                withClaim("username", user.getUsername()).
                withClaim("password", user.getPassword()).
                withExpiresAt(dateService.dateAfter(12)).
                sign(algorithm);
    }

    public User getUser(String token) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(algorithm).build().verify(token);
        } catch (TokenExpiredException e) {
            if (expired(token)) {
                return null;
            }
            decodedJWT = JWT.decode(token);
        } catch (JWTVerificationException e) {
            return null;
        }
        final String username = decodedJWT.getClaim("username").asString();
        final String password = decodedJWT.getClaim("password").asString();
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Date getExpireAt(String token) {
        return JWT.decode(token).getExpiresAt();
    }

    private boolean expired(String token) {
        return getExpireAt(token).before(dateService.currDate());
    }
}
