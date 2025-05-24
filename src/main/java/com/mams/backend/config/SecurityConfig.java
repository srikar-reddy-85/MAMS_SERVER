////package com.mams.backend.config;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.http.HttpMethod;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers(HttpMethod.POST, "/api/v1/**").authenticated()
////                        .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .httpBasic();
////
////        return http.build();
////    }
////}
//
//package com.mams.backend.config;
//
//import com.mams.backend.filter.JwtAuthenticationFilter;
//import com.mams.backend.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final UserService userService;
//    private final JwtAuthenticationFilter jwtAuthFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        // Public endpoints
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//
//                        // Dashboard endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//
//                        // Assets endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/assets/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/assets/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/assets/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/assets/**").hasAnyRole("ADMIN")
//
//                        // Personnel endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/personnel/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/personnel/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/personnel/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/personnel/**").hasAnyRole("ADMIN")
//
//                        // Location endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/locations/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/locations/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/locations/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/locations/**").hasAnyRole("ADMIN")
//
//                        // Assignment endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//
//                        // Purchase endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/purchases/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/purchases/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
//
//                        // Transfer endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/transfers/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/transfers/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/transfers/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/transfers/**").hasAnyRole("ADMIN")
//
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

//package com.mams.backend.config;
//
//import com.mams.backend.filter.JwtAuthenticationFilter;
//import com.mams.backend.service.UserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    private final UserService userService;
//    private final JwtAuthenticationFilter jwtAuthFilter;
//
//    // Use constructor injection with @Lazy on UserService
//    public SecurityConfig(@Lazy UserService userService, JwtAuthenticationFilter jwtAuthFilter) {
//        this.userService = userService;
//        this.jwtAuthFilter = jwtAuthFilter;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        // Public endpoints
//                        .requestMatchers("/api/v1/auth/**").permitAll()
//
//                        // Dashboard endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//
//                        // Assets endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/assets/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/assets/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/assets/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/assets/**").hasAnyRole("ADMIN")
//
//                        // Personnel endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/personnel/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/personnel/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/personnel/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/personnel/**").hasAnyRole("ADMIN")
//
//                        // Location endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/locations/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/locations/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/locations/**").hasAnyRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/locations/**").hasAnyRole("ADMIN")
//
//                        // Assignment endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
//
//                        // Purchase endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/purchases/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/purchases/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
//
//                        // Transfer endpoints
//                        .requestMatchers(HttpMethod.GET, "/api/v1/transfers/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/transfers/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/transfers/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/transfers/**").hasAnyRole("ADMIN")
//
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

package com.mams.backend.config;

import com.mams.backend.filter.JwtAuthenticationFilter;
import com.mams.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CorsConfigurationSource corsConfigurationSource;

    // Use constructor injection with @Lazy on UserService
    public SecurityConfig(@Lazy UserService userService, JwtAuthenticationFilter jwtAuthFilter,
                          CorsConfigurationSource corsConfigurationSource) {
        this.userService = userService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Dashboard endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/dashboard/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")

                        // Assets endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/assets/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/assets/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/assets/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/assets/**").hasAnyRole("ADMIN")

                        // Personnel endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/personnel/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/personnel/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/personnel/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/personnel/**").hasAnyRole("ADMIN")

                        // Location endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/locations/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/locations/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/locations/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/locations/**").hasAnyRole("ADMIN")

                        // Assignment endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/assignments/**").hasAnyRole("ADMIN", "BASE_COMMANDER")

                        // Purchase endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/purchases/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/purchases/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")

                        // Transfer endpoints
                        .requestMatchers(HttpMethod.GET, "/api/v1/transfers/**").hasAnyRole("ADMIN", "BASE_COMMANDER", "LOGISTICS_OFFICER")
                        .requestMatchers(HttpMethod.POST, "/api/v1/transfers/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/transfers/**").hasAnyRole("ADMIN", "LOGISTICS_OFFICER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/transfers/**").hasAnyRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}