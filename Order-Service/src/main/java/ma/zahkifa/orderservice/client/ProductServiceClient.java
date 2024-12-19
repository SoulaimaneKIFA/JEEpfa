package ma.zahkifa.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service") // This refers to the application name of Product Service
public interface ProductServiceClient {

    @GetMapping("/products/{id}") // Endpoint to call on the Product Service
    String getProductDetails(@PathVariable("id") Long id);
}
