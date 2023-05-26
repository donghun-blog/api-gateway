package me.donghun.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
public class CustomGlobalFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long begin = System.currentTimeMillis();
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("GlobalFilter [{}] => {}ms", exchange.getRequest().getPath(), System.currentTimeMillis() - begin);
                }));
    }


//    @Override
//    public GatewayFilter apply(Config config) {
//        // Global Pre Filter
//        return (exchange, chain) -> {
//            ServerHttpRequest request = exchange.getRequest();
//            ServerHttpResponse response = exchange.getResponse();
//
//            long begin = System.currentTimeMillis();
//            log.info("Global Filter baseMessage : {}", config.getBaseMessage());
//
//            if(config.isPreLogger()){
//                log.info("Global Filter Start : request id -> {}", request.getId());
//            }
//
//            // Global Post Filter
//            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//                if(config.isPostLogger()) {
//                    log.info("Global Filter End : response code -> {}", response.getStatusCode());
//                    log.info("GlobalFilter [{}] => {}ms", request.getPath(), System.currentTimeMillis() - begin);
//                }
//            }));
//        };
//    }
}
