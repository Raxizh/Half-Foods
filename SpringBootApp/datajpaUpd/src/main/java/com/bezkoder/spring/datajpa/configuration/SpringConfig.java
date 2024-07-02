package com.bezkoder.spring.datajpa.configuration;

import com.bezkoder.spring.datajpa.JWT.JWTAuthenticationEntryPoint;
import com.bezkoder.spring.datajpa.JWT.JWTAuthenticationFilter;
import com.bezkoder.spring.datajpa.JWT.JWTAuthenticationProvider;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.ResourceHandlerRegistrationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSecurity
@EnableWebSocketMessageBroker
public class SpringConfig implements WebSocketMessageBrokerConfigurer {

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JWTAuthenticationProvider jwtAuthenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    /** Used to obtain Authorization Code when communicating with Google API. */
    //private final String authorizationEndpoint;

    @Autowired
    public SpringConfig(
            JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JWTAuthenticationProvider jwtAuthenticationProvider,
            JWTAuthenticationFilter jwtAuthenticationFilter
            //@Value("${api.google.authorization-endpoint}") String authorizationEndpoint
    ) {
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        //this.authorizationEndpoint = authorizationEndpoint;
    }


    /**
     * Configurers the security filter chain for client requests.
     * @param httpSecurity [HttpSecurity] Filter chain configurer.
     * @return [SecurityFilterChain] The filter chain to apply to requests.
     * @throws Exception Indicates filter chain could not be processed.
     */
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                .authenticationProvider(this.jwtAuthenticationProvider)
                .exceptionHandling()
                .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterAfter(this.jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    /**
     * Defines paths to be excluded from the security filter chain.
     * @return [WebSecurityCustomizer] The security customizer.
     */
    
    //Need to clean up the websecurity
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (WebSecurity webSecurity) -> {
            webSecurity.ignoring().antMatchers(
                    "/**",
                    "/h2-ui/**",
                    "/api/login/",
                    "/api2/foods",
                    "/api2/*",
                    "/api2/foods/list/",
                    "/api/foods/list",
                    "/api/*",
                    "/add",
                    "/login",
                    "/favicon.ico",
                    "/index.html",
                    "/api/user/login",
                    "/api/users/login",
                    "/api/users/logins",
                    "/api/users/registeruser",
                    "/api/registeruser",
                    "/api/user/verify/email/**",
                    "/api/user/create",
                    "/api/foods/getfoods"
            );
        };
    }
    

    /**
     * Data Source build to access PostgreSQL database.
     * @return [HikariDataSource] The Data Source.
     */
    //@Bean
    //@ConfigurationProperties("spring.datasource")
    /*public HikariDataSource hikariDataSource() {return DataSourceBuilder.create().type(HikariDataSource.class).build();}

    /**
     * Registers endpoint for Websocket connections.
     * @param registry [StompEndpointRegistry] Endpoint configurer.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/websocket")
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    /**
     * Registers send and receive paths for Websocket connections.
     * @param registry [MessageBrokerRegistry] Path configurer.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/request");
        registry.enableSimpleBroker("/receive");
    }

}
