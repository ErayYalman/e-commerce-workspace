package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.jwt;

import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.config.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret()); //String'i byte array'e çeviriyoruz. çünkü Keys.hmacShaKeyFor() 
        // metodu byte array alıyor.
        return Keys.hmacShaKeyFor(keyBytes); //SecretKey oluşturmak için kullanıyoruz. Bu key, JWT'yi imzalamak ve doğrulamak için kullanılacak.
    }


    public String generateToken(UserDetails userDetails) {
       return Jwts.builder() //yeni bir JWT oluşturmak için Jwts.builder() metodunu kullanıyoruz.
                .subject(userDetails.getUsername()) //JWT'nin sahip olduğu kullanıcı adını ayarlıyoruz. yani email adresini alıyoruz.
                .issuedAt(new Date()) //token'ın oluşturulma tarihi
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.expiration())) //bu günün tarihinden itibaren ne kadar 
                // süre geçerli olacağını ayarlıyoruz. yani token'ın geçerlilik süresini belirliyoruz.
                .signWith(getSigningKey()) //herkes token üzerinde değişiklik yapamasın diye imzalıyoruz. yani token'ı imzalıyoruz.
                .compact(); //bütün parçaları birleştiriyoruz ve String olarak döndürüyoruz.
    }

   public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    //Function<A,B> A'yı al işle B'ye döndür
        Claims claims = extractAllClaims(token); //token'dan tüm claim'leri alıyoruz.
        return claimsResolver.apply(claims); //claim'leri çözümleyip döndürüyoruz.
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser() //jwt yi okumaya başla
                .verifyWith(getSigningKey()) //Bu secret ile doğrula.
                .build() //parser'ı oluştur
                .parseSignedClaims(token) //token'ı çöz
                .getPayload(); //token'dan tüm alanları al ve döndür

    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); //token'dan kullanıcı adını alıyoruz. yani email adresini alıyoruz.
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); //token'dan geçerlilik süresini alıyoruz.
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); //token'ın geçerlilik süresinin dolup dolmadığını kontrol ediyoruz.
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token); //token'dan kullanıcı adını alıyoruz. yani email adresini alıyoruz.
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); //token'ın geçerlilik süresinin dolup dolmadığını ve 
        //kullanıcı adının eşleşip eşleşmediğini kontrol ediyoruz.
    }

    public Instant getexpirationInstant() {
        return Instant.now().plusMillis(jwtProperties.expiration()); 
        //token'ın geçerlilik süresini ayarlıyoruz. yani token'ın ne kadar süre geçerli olacağını belirliyoruz.
    }
}
