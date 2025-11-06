package com.uniquindio.proyecto_final.accommodation_management.config;

import com.uniquindio.proyecto_final.accommodation_management.securityLayer.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuración de seguridad de Spring Security.
 *
 * <p>Define la seguridad de la API REST, incluyendo:</p>
 * <ul>
 *     <li>Protección CSRF deshabilitada.</li>
 *     <li>Configuración de CORS para permitir solicitudes desde Angular u otras fuentes permitidas.</li>
 *     <li>Autorización de endpoints específicos para acceso público.</li>
 *     <li>Uso de JWT para autenticación sin estado (stateless).</li>
 *     <li>Definición del codificador de contraseñas {@link BCryptPasswordEncoder}.</li>
 * </ul>
 *
 * <p>Se integra con {@link JwtAuthenticationFilter} para validar tokens JWT en cada petición.</p>
 *
 * @since 1.0
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * Constructor que inyecta el filtro de autenticación JWT.
     *
     * @param jwtAuthFilter filtro personalizado para validar tokens JWT
     */
    public SpringSecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Configura la cadena de filtros de seguridad de Spring.
     *
     * <p>Incluye:</p>
     * <ul>
     *     <li>Deshabilitar CSRF.</li>
     *     <li>Configurar CORS con dominios, métodos y cabeceras permitidas.</li>
     *     <li>Permitir acceso público a endpoints específicos.</li>
     *     <li>Forzar autenticación en los demás endpoints.</li>
     *     <li>Configurar sesiones como stateless.</li>
     *     <li>Agregar el filtro JWT antes del filtro de autenticación estándar.</li>
     * </ul>
     *
     * @param http objeto {@link HttpSecurity} proporcionado por Spring
     * @return la cadena de filtros {@link SecurityFilterChain} configurada
     * @throws Exception si ocurre un error en la configuración
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowedOrigins(java.util.List.of("https://stayfinder-2dca8.web.app/"));
                    corsConfig.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfig.setAllowedHeaders(java.util.List.of("*"));
                    corsConfig.setAllowCredentials(true);
                    return corsConfig;
                }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/departments/**").permitAll()
                        .requestMatchers("/api/cities/**").permitAll()
                        .requestMatchers("/api/images/upload").permitAll()
                        .requestMatchers("/api/users/**").permitAll()
                        .requestMatchers("/api/accommodations/**").permitAll()
                        .requestMatchers("/api/comments/**").permitAll()
                        .requestMatchers("/api/administrators/**").permitAll()
                        .requestMatchers("/api/reservations/**").permitAll()
                        .requestMatchers("/api/hosts/**").permitAll()
                        .requestMatchers("/api/roles/**").permitAll()
                        .requestMatchers("/api/responses/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Proporciona el {@link AuthenticationManager} de Spring para autenticación programática.
     *
     * @param config configuración de autenticación proporcionada por Spring
     * @return {@link AuthenticationManager} listo para usar
     * @throws Exception si falla la creación
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Bean para codificar contraseñas usando {@link BCryptPasswordEncoder}.
     *
     * @return un {@link PasswordEncoder} de tipo BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}