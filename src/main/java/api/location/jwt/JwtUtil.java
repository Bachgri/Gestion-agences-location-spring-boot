package api.location.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
 
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.token.validity}")
	private long tokenValidity;

	public String getUserName(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body.getSubject();
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}

		return null;
	}

	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Claims claims = Jwts.claims().setSubject(user.getUsername());

		final long nowMillis = System.currentTimeMillis();
		final long expMillis = nowMillis + tokenValidity;

		Date exp = new Date(expMillis);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(exp)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public void validateToken(final String token) throws Exception {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new Exception("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new Exception("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new Exception("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new Exception("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new Exception("JWT claims string is empty.");
		}
	}

}