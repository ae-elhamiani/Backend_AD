package com.apiGatewayService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class JwtTokenFilter implements GlobalFilter {

    @Value("${jwt.secret}")
    private String secretKey;
    private final List<String> whitelistedEndpoints = Arrays.asList(
            "/client-service/banking/",
            "/client-service/banking/pack/[^/]+$",
            "/client-service/banking/email",
            "/client-service/banking/otp-email",

            "/otp-service/[^/]+$",
            "/notification-service/[^/]+$"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();

        boolean isWhitelisted = whitelistedEndpoints.stream().anyMatch(pattern -> path.matches(pattern));
        if (isWhitelisted) {
            return chain.filter(exchange);
        }


        List<HttpCookie> cookies = exchange.getRequest().getCookies().get("token");
        String token =null;

        if (cookies != null && !cookies.isEmpty()) {
             token = cookies.get(0).getValue();
            log.info(token);

        }

        try {
            if (token != null) {
                Claims claims = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(token)
                        .getBody();

            } else {
                log.info("No or invalid JWT, block the request");
                return unauthorizedResponse(exchange);
            }
        } catch (SignatureException e) {
            log.info("JWT validation failed");
            return unauthorizedResponse(exchange);
        }
        log.info("Proceed with the request");
        return chain.filter(exchange);
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
