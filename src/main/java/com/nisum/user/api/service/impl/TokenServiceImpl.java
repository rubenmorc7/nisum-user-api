package com.nisum.user.api.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.nisum.user.api.exceptions.NotAuthorizedException;
import com.nisum.user.api.model.entity.User;
import com.nisum.user.api.service.TokenService;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

@Service
public class TokenServiceImpl implements TokenService {
	
	@Override
	public String generateToken(User user) {

        try {
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;
            HashMap<String, Object> header = new HashMap<String, Object>();
            header.put("alg", signatureAlgorithm.toString());
            header.put("typ", "JWT");

            JwtBuilder tokenJWT = Jwts
                    .builder()
                    .setHeader(header)
                    .setId("1")
                    .setSubject("http://nisum.com")
                    .claim("name", user.getName())
                    .claim("email", user.getEmail())
                    .setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
                    .setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
                    .signWith(SignatureAlgorithm.HS256, "secretkey".getBytes());

            String tokenJWTString = tokenJWT.compact();
            System.out.println(tokenJWTString);
            return tokenJWTString;

        } catch (Exception ex) {

            return "Error creating the token JWT" + ex.getMessage();
        }
    }

	@Override
	public void decodeToken(String token){

        String[] chunks = token.split("\\.");
        String key ="secretkey";

        SignatureAlgorithm sa = SignatureAlgorithm.HS256;

        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), sa.getJcaName());

        String tokenWithoutSignature = chunks[0] + "." + chunks[1];

        String signature = chunks[2];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature)) {
            throw new NotAuthorizedException();
        }

    }

}
