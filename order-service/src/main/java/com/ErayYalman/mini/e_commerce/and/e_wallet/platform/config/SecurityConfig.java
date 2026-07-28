package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //configuration sınıfı demek, Spring'in bu sınıfı bir konfigürasyon sınıfı olarak tanıyacağı anlamına gelir. 
// Bu sınıf, uygulamanın güvenlik yapılandırmasını içerecek ve Spring Security'nin nasıl çalışacağını belirleyecektir.
public class SecurityConfig {
    
    @Bean //Spring'in bu metodu bir bean olarak yönetmesini sağlar. Bu, PasswordEncoder nesnesinin Spring konteyneri
    //  tarafından yönetileceği ve gerektiğinde başka sınıflar tarafından kullanılabileceği anlamına gelir.
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf(csrf -> csrf.disable()) // Cross-Site Request Forgery (CSRF) korumasını devre dışı bırakır.
                //  Bu, özellikle RESTful API'ler için yaygın bir uygulamadır.
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                ) // Tüm HTTP isteklerini yetkilendirme gerektirmeden kabul eder. Yani, 
                // herhangi bir kullanıcı (oturum açmış veya açmamış) tüm isteklere erişebilir.
                .formLogin(form -> form.disable())
                    // Form tabanlı giriş sayfasını devre dışı bırakır. Bu, uygulamanın kendi giriş sayfasını kullanmak istemediği veya
                    //  RESTful API'ler için form tabanlı girişin gerekli olmadığı durumlarda yaygın bir uygulamadır.
                .httpBasic(httpBasic -> httpBasic.disable());
                // HTTP Basic kimlik doğrulamasını devre dışı bırakır. Bu, kullanıcı adı ve şifreyi her istekte göndermeyi 
                // gerektiren basit bir kimlik doğrulama yöntemidir.
        return http.build();
    }

}
