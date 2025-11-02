package com.uniquindio.proyecto_final.accommodation_management.securityLayer;

import com.uniquindio.proyecto_final.accommodation_management.config.JwtConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Filtro de autenticación JWT que se ejecuta una vez por petición.
 * Valida el token JWT del encabezado Authorization y configura
 * la autenticación en el contexto de seguridad de Spring.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Utilidad para manejar operaciones con JWT
    private final JwtConfig jwtUtil;

    /**
     * Constructor que inyecta la utilidad de JWT.
     * @param jwtUtil instancia de JwtConfig.
     */
    public JwtAuthenticationFilter(JwtConfig jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    /**
     * Método principal del filtro que intercepta cada petición HTTP,
     * valida el token JWT y establece la autenticación en el contexto de seguridad.
     * @param request solicitud HTTP entrante.
     * @param response respuesta HTTP.
     * @param filterChain cadena de filtros.
     * @throws ServletException excepción de servlet.
     * @throws IOException excepción de IO.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Ignorar rutas públicas (sin token)
        if (path.startsWith("/api/hosts/register") ||
                path.startsWith("/api/users/register") ||
                path.startsWith("/api/administrators/register") ||
                path.startsWith("/api/auth/login") ||
                path.startsWith("/api/images/upload")) {

            // Continuar sin validar JWT
            filterChain.doFilter(request, response);
            return;
        }

        // Obtiene el encabezado Authorization
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // Verifica que el encabezado comience con "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extrae el token JWT
            token = authHeader.substring(7);
            // Obtiene el nombre de usuario del token
            username = jwtUtil.extractUsername(token);
        }

        // Si hay un username y no hay autenticación en el contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Valida el token
            if (jwtUtil.isTokenValid(token)) {
                // Crea un token de autenticación vacío con username y roles vacíos
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                // Configura detalles del request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Establece la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continúa con la cadena de filtros
        filterChain.doFilter(request, response);
    }

}