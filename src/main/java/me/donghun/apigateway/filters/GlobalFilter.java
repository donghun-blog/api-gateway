package me.donghun.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {

    public GlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            long begin = System.currentTimeMillis();

            //custom post filter
            return chain.filter(exchange)
                        .then(Mono.fromRunnable(() -> {
                            log.info("GlobalFilter [{}] => {}ms", request.getPath(), System.currentTimeMillis() - begin);
                        }));
        });
    }

    public static class Config {
    }
}
