package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.CustomUserDetailsService;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration //configuration sınıfı demek, Spring'in bu sınıfı bir konfigürasyon sınıfı olarak tanıyacağı anlamına gelir. 
// Bu sınıf, uygulamanın güvenlik yapılandırmasını içerecek ve Spring Security'nin nasıl çalışacağını belirleyecektir.
@EnableConfigurationProperties(JwtProperties.class) //JwtProperties sınıfını konfigürasyon özellikleri olarak tanımlar.
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    
    @Bean //Spring'in bu metodu bir bean olarak yönetmesini sağlar. Bu, PasswordEncoder nesnesinin Spring konteyneri
    //  tarafından yönetileceği ve gerektiğinde başka sınıflar tarafından kullanılabileceği anlamına gelir.
   public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean //Spring Security'nin güvenlik filtre zincirini yapılandırmak için kullanılan bir bean tanımlar. kendimizın güvenlik yapılandırmasını
    //  özelleştirmek için kullanılır.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         http
                .csrf(csrf -> csrf.disable()) // her forma gizli bir token ekleyerek CSRF saldırılarına karşı koruma sağlar. 
                // Ancak, session kullanmayacağız jwt kullanacağımız için CSRF korumasını devre dışı bırakıyoruz.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Session yönetimini yapılandırır.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                            "/auth/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                        ).permitAll() // Belirli bir URL desenine izin verir. Bu durumda, "/api/v1/auth/**" ile başlayan tüm istekler yetkilendirme gerektirmeden erişilebilir.
                        .anyRequest().authenticated() // Diğer tüm isteklerin kimlik doğrulaması gerektirdiğini belirtir.
                )
                .authenticationProvider(authenticationProvider) //AuthenticationProvider'ı yapılandırır. Bu, kullanıcı doğrulama işlemlerini yönetir.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        //kullanıcıyı bulur sifreyi karşılatırır ve sonucu AuthenticationManager'a döndürür. yani doğrulama yapar.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

        @Bean
        public AuthenticationManager authenticationManager( AuthenticationConfiguration configuration)
            throws Exception {
        //yöneticidir sadece doğrulama işlemlerini yönetir. AuthenticationProvider'ı kullanarak kullanıcıyı doğrular.
            return configuration.getAuthenticationManager();
}
//sistem akışı söyledir. Login->AuthenticationManager->AuthenticationProvider->CustomUserDetailsService->CustomUserDetails->UserRepository->User entity


}
