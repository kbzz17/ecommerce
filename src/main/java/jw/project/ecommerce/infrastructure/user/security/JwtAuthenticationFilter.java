package jw.project.ecommerce.infrastructure.user.security;

import jw.project.ecommerce.infrastructure.user.jwt.AuthenticatedAccount;
import jw.project.ecommerce.infrastructure.user.jwt.JwtTokenParser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenParser tokenParser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        tokenParser.extract(request.getHeader("Authorization")).ifPresent(
                extractToken -> {
                    AuthenticatedAccount account = tokenParser.parse(extractToken);
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken context = new UsernamePasswordAuthenticationToken(
                                account, null, getGrantedAuthorities(account)
                        );
                        SecurityContextHolder.getContext().setAuthentication(context);
                    }
                }
        );

        filterChain.doFilter(request, response);
    }

    private static List<GrantedAuthority> getGrantedAuthorities(AuthenticatedAccount account) {
        return account.role()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
