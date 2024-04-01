package com.ghanem.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SchoolSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        Require Authentication to show the screen
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());

//        Permit All Request inside the Web Application
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());

//        Denay all Request inside the Web Application
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//        http.authorizeHttpRequests((requests) -> requests.requestMatchers(PathRequest.toH2Console()).permitAll());

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/displayMessages").hasRole("ADMIN"));
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/closeMsg/**").hasRole("ADMIN"));
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/displayProfile").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/updateProfile").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/login").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/","/home").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/holidays/**").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/contact").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/saveMsg").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/courses").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/about").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/logout").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/public/**").permitAll());
        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/assets/**").permitAll());
        http.formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll()
                );
        http.logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                );


//        To disable the protection comes from the front end request if not thymeleaf
//        http.csrf(csrf -> csrf.disable());
//        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers(PathRequest.toH2Console()));
//      http.headers(header ->header.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/public/**"));


        return http.build();
    }

/*    @Bean
    public InMemoryUserDetailsManager userDetailsService(){
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
