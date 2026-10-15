package uz.pdp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
       private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{

              http.userDetailsService(customUserDetailsService);

         http.authorizeHttpRequests( authorizeRequests ->
                 authorizeRequests
                         .requestMatchers("/auth/**", "/", "/get-book/{id}").permitAll()
                         .requestMatchers("/book/create", "/book/delete/", "/book/update/").hasAnyRole("ADMIN")
                         .anyRequest().authenticated()

         );

       http.formLogin( formLogin->
               formLogin.loginPage("/auth/login")
                       .usernameParameter("username")
                       .passwordParameter("password")
                       .defaultSuccessUrl("/", false)
               );

        http.logout( logout->
                logout
                        .logoutUrl("/auth/logout")
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST, "/logout"))

        );

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
