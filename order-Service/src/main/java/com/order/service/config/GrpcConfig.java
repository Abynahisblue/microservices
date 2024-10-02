package com.order.service.config;

import com.order.service.grpc.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {


    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress("127.0.0.1", 9090)
                .usePlaintext()
                .build();
    }

    @Bean
    public ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub(ManagedChannel managedChannel) {
        return ProductServiceGrpc.newBlockingStub(managedChannel());
    }
}
