package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.security;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity.User;

//user entity'mizi spring security'nin anlayacağı bir forma dönüştürmek için UserDetails arayüzünü implement ediyoruz.
public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { // Kullanıcının yetkilerini (rollerini) döndürür. 
    // Bu, Spring Security'nin kullanıcının hangi yetkilere sahip olduğunu bilmesini sağlar.
       return List.of(new SimpleGrantedAuthority(user.getRole().name()));
        
    }

    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
        //login'i email ile yapacağımız için username yerine email'i döndürüyoruz.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hesabın süresinin dolup dolmadığını kontrol eder. true döndürerek hesabın süresinin dolmadığını belirtir.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hesabın kilitli olup olmadığını kontrol eder. true döndürerek hesabın kilitli olmadığını belirtir.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Kimlik bilgilerinin süresinin dolup dolmadığını kontrol eder. true döndürerek kimlik bilgilerinin süresinin dolmadığını belirtir.
    }

    @Override
    public boolean isEnabled() {
        return true; // Kullanıcının etkin olup olmadığını kontrol eder. true döndürerek kullanıcının etkin olduğunu belirtir.
    }

    public User getUser() {
        return user; // CustomUserDetails nesnesi üzerinden User entity'sine erişim sağlar. sadece user'i dışarıya açtığımız için lombok kullanmadık.
    }

}
