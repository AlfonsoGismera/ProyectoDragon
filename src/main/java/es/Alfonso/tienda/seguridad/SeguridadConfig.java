package es.Alfonso.tienda.seguridad;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
public class SeguridadConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                // H2 esta para hacer pruebas donde no tenga la base de datos de postgrest
                .antMatchers().permitAll()
//                .antMatchers("/webjars/**", "/css/**", "/js/**", "application/json/**", "/h2-console/**").permitAll()
                //.antMatchers("/").permitAll()
                .anyRequest().permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/") // Página de inicio predeterminada
                .permitAll()
                .and()
                //.rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                //.and()
                .logout()
                .logoutSuccessUrl("/login?logout").permitAll();

        // Para que funcione el h2-console estas dos líneas son necesarias
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}
