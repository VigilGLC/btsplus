package fd.se.btsplus.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static fd.se.btsplus.utils.TokenUtils.dateAfter;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenUtilsTest {
    private final Algorithm algorithm = Algorithm.HMAC256("二刺螈");

    @Test
    void test() {
        final String token = JWT.create().
                withClaim("username", "admin").
                withClaim("password", "password").
                withExpiresAt(dateAfter(12)).
                sign(algorithm);
        final DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
        final String username = decodedJWT.getClaim("username").asString();
        assertEquals(username, "admin");
        assertEquals("password", "password");
    }

    @Test
    void testExpired() {
        Assertions.assertThrows(TokenExpiredException.class, () -> {
            final String token = JWT.create().
                    withClaim("username", "admin").
                    withClaim("password", "password").
                    withExpiresAt(dateAfter(-1)).
                    sign(algorithm);
            JWT.require(algorithm).build().verify(token);
        });
    }

}
