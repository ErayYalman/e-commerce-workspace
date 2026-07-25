package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //configuration sınıfı demek, Spring'in bu sınıfı bir konfigürasyon sınıfı olarak tanıyacağı anlamına gelir. 
// Bu sınıf, uygulamanın güvenlik yapılandırmasını içerecek ve Spring Security'nin nasıl çalışacağını belirleyecektir.
public class SecurityConfig {
    
    @Bean //Spring'in bu metodu bir bean olarak yönetmesini sağlar. Bu, PasswordEncoder nesnesinin Spring konteyneri
    //  tarafından yönetileceği ve gerektiğinde başka sınıflar tarafından kullanılabileceği anlamına gelir.
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
