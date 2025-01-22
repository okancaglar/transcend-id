package com.example.demo.services.blockchainservice;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


	@Value("${security.jwt.secret-key}")
	private String jwtKey;

	@Value("${security.jwt.expiration-time}")
	private long jwtExpiration;

	public String extractUserId(String token)
	{
		System.out.println("Extracting user ID from token: " + token);
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
	{
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token)
	{
		try {
			return Jwts.parserBuilder()
					.setSigningKey(getSignInKey())
					.build()
					.parseClaimsJws(token)
					.getBody();
		}catch (Exception e) {
			System.out.println("Failed to extract claims: " + e.getMessage());
		}
		return null;
	}

	private Key getSignInKey()
	{
		byte[] keyBytes = Decoders.BASE64.decode(jwtKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}


	public String generateToken(UserDetails userDetails)
	{
		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails)
	{
		return generateToken(extraClaims, userDetails, jwtExpiration);
	}

	public String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails, long jwtExpiration)
	{
		return buildToken(extraClaims, userDetails, jwtExpiration);
	}

	public long getJwtExpiration()
	{
		return jwtExpiration;
	}

	private String buildToken(HashMap<String, Object> extraClaims, UserDetails userDetails, long jwtExpiration)
	{
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean isTokenValid(String token, UserDetails userDetails)
	{
		final String userName = extractUserId(token);
		return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}


}
