package ma.zahkifa.productservice.Service;

import ma.zahkifa.productservice.Model.Product;
import ma.zahkifa.productservice.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        // Convert Iterable<Object> to List<Product>
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(obj -> (Product) obj) // Cast each object to Product
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        return Optional.ofNullable(productRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Product not found with id " + id)))
                .map(obj -> (Product) obj) // Cast the object to Product
                .orElseThrow(() -> new RuntimeException("Product conversion failed"));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
