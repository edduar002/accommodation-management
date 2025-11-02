package com.uniquindio.proyecto_final.accommodation_management.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;

/**
 * Configuración y utilidad para generación y validación de JSON Web Tokens (JWT).
 *
 * <p>Proporciona métodos para generar un token a partir de un nombre de usuario,
 * extraer el nombre de usuario de un token existente y validar tokens.</p>
 *
 * <p>Esta clase utiliza una clave secreta HMAC-SHA256 para firmar los tokens.
 * La expiración del token está configurada en 1 día (86400000 ms).</p>
 *
 * <h2>Responsabilidades</h2>
 * <ul>
 *   <li>Generar tokens JWT con fecha de emisión y expiración: {@link #generateToken(String)}</li>
 *   <li>Extraer el nombre de usuario contenido en un token: {@link #extractUsername(String)}</li>
 *   <li>Validar que un token sea correcto y no haya expirado: {@link #isTokenValid(String)}</li>
 * </ul>
 *
 * @since 0.0.1-SNAPSHOT
 * @version 1.0
 */
@Component
public class JwtConfig {

    /** Clave secreta utilizada para firmar y validar tokens JWT */
    private static final String SECRET_KEY =
            "654981989287987asdlfjoweibvaposei654981989287987asdlfjoweibvaposei";

    /** Tiempo de expiración del token en milisegundos (1 día) */
    private static final long EXPIRATION_TIME = 86400000;

    /** Objeto Key generado a partir de la clave secreta para firmar tokens */
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Genera un token JWT para el usuario dado.
     *
     * @param username nombre de usuario a incluir como sujeto del token
     * @return token JWT firmado como {@link String}
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extrae el nombre de usuario (subject) de un token JWT válido.
     *
     * @param token token JWT a analizar
     * @return nombre de usuario contenido en el token
     * @throws JwtException si el token es inválido o ha sido modificado
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Valida si un token JWT es válido y no ha expirado.
     *
     * @param token token JWT a validar
     * @return {@code true} si el token es válido, {@code false} en caso contrario
     */
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}