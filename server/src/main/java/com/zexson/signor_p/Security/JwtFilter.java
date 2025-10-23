package com.zexson.signor_p.Security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter implements jakarta.servlet.Filter {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        System.out.println("Requested URL: " + url);
        if (url.endsWith("register") || url.endsWith("login")) {
            chain.doFilter(request, response);
            return;
        }
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Missing or invalid Authorization header");
            return;
        }
        String token = authHeader.substring(7);
        System.out.println("Auth token: " + token);
        Jws<Claims> jwt = jwtUtil.validToken(token);
        if (jwt == null) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.getWriter().write("INVALID REQUEST");
            return;
        }
        Claims body = jwt.getBody();
        String username = body.getSubject();
        String role = "ROLE_" + (String) body.get("role");
        System.out.println("User role: " + role);
        if (username == null || username.isEmpty()) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Invalid token");
            return;
        }
        System.out.println(username);
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                List<GrantedAuthority> authorityList = Collections.emptyList();
                if (role != null) {
                    authorityList = List.of(new SimpleGrantedAuthority(role));
                }
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(username, null, authorityList);
                authentication.setDetails(new WebAuthenticationDetails((req)));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }
        req.setAttribute("username", username);
        chain.doFilter(request, response);
    }

}
