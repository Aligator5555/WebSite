package org.example.website.config;

import org.example.website.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/", "/KovkaNaZakaz", "/emailGo",
                                "/portfolio/{id}", "/descriptionPortfolio/{id}",
                                "/imagesPortfolio/{id}", "/description/{id}",
                                "/product/edit/{id}", "/product/{id}",
                                "/images/{id}", "/product/create", "/VorotaKatalog",
                                "/KalitkaKatalog", "/ZaborKatalog", "/PerilaKatalog",
                                "/ReshotkaKatalog", "/BesedkaKatalog", "/MostiKatalog",
                                "/KaheliKatalog", "/MongaliKatalog", "/SkameikiKatalog",
                                "/YrbanKatalog", "/ChugunSkameyki", "/SadovStoli",
                                "/NaboriKatalog", "/MebelLoft", "/UlishnieYrni",
                                "/KonteinerTBO", "/FonarStolb", "/MebelKatalog",
                                "/OgragdeniaKatalog", "/RitualIzdeliaKatalog",
                                "/KozirkiiNavesiKatalog", "/AlementiKovkiKatalog",
                                "/TableDoma", "/OgragdenPloshadok", "/RazhieIzdeliaKatalog",
                                "/PolomernaiKraskaKatalog", "/RezinovaiKroshkaKatalog",
                                "/Katalog", "/WholesaleBuyer", "/Contacts", "/portfolio",
                                "/searchByTitle", "/img/**", "/css/**", "/static/**",
                                "/templates/**").permitAll()
                        .requestMatchers("/admin/**", "/secret-page").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**", "/img/**", "/css/**", "/static/**")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                );

        return http.build();
    }
}

