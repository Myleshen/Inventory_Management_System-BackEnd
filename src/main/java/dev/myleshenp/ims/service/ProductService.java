package dev.myleshenp.ims.service;

import dev.myleshenp.ims.dto.ProductDTO;
import dev.myleshenp.ims.entity.Product;
import dev.myleshenp.ims.mapper.ProductMapper;
import dev.myleshenp.ims.repository.CategoryRepository;
import dev.myleshenp.ims.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductDTO saveProduct(Product product) {
        var savedProduct = productRepository.save(product);
        populateProductWithCategory(savedProduct);
        return productMapper.entityToDTO(savedProduct);
    }

    public ProductDTO getProductById(Long id) throws Exception {
        var product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new Exception("Entity with ID: " + id + " not found");
        }
        return productMapper.entityToDTO(product.get());
    }


    private void populateProductWithCategory(Product product) {
        product.setCategories(product.getCategories().stream().map(x -> categoryRepository.findById(x.getId()).get()).toList());
    }
}
