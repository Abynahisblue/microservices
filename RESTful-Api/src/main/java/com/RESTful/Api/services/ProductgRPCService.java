package com.RESTful.Api.services;


import com.RESTful.Api.model.Product;
import com.RESTful.Api.repository.ProductRepo;
import com.order.service.grpc.ProductRequest;
import com.order.service.grpc.ProductResponse;
import com.order.service.grpc.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityNotFoundException;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class ProductgRPCService extends ProductServiceGrpc.ProductServiceImplBase {
    private final ProductRepo productRepo;

    public ProductgRPCService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public void getProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        Product product = productRepo.findById(request.getOrderId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        ProductResponse productResponse = ProductResponse.newBuilder()
                .setId(product.getProductId())
                .setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice())
                .setQuantity(product.getQuantity())
                .build();
        responseObserver.onNext(productResponse);
        responseObserver.onCompleted();
    }
}
