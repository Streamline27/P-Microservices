package lv.citadele.storage.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;


public class TokenAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain)throws ServletException, IOException {

        String token = request.getHeader("Access-Token");
        if (token != null) {
            authorize(token);
        }
        chain.doFilter(request, response);
    }

    private void authorize(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("secret".getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();

            TokenStorage.put(toMap(claims));
            TokenStorage.put(TokenStorage.KEY_USERNAME, username);
            TokenStorage.put(TokenStorage.KEY_TOKEN, token);

            UsernamePasswordAuthenticationToken auth = getAuth(username);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }
    }

    private UsernamePasswordAuthenticationToken getAuth(String username) {
        Assert.notNull(username, "Username is not specified");
        return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    private HashMap<String, Object> toMap(Claims claims) {
        return (HashMap<String, Object>) claims.get("data");
    }
}
