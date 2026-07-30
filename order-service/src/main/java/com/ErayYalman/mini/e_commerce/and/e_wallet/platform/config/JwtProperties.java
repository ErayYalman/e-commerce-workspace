package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(String secret, long expiration) {
    //record class, JWT ile ilgili yapılandırma özelliklerini temsil eder. Bu sınıf, uygulamanın yapılandırma dosyasından 
    // (örneğin application.properties veya application.yml) JWT ile ilgili ayarları almak için kullanılır.
    
}
