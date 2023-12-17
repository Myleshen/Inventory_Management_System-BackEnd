package dev.myleshenp.ims.service;

import dev.myleshenp.ims.dto.ProductDTO;
import dev.myleshenp.ims.entity.Category;
import dev.myleshenp.ims.entity.Product;
import dev.myleshenp.ims.mapper.ProductMapper;
import dev.myleshenp.ims.repository.CategoryRepository;
import dev.myleshenp.ims.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ProductDTO updateProductById(Long id, Product product) throws Exception {
        var oldProduct = productRepository.findById(id);
        if (oldProduct.isEmpty()) {
            throw new Exception("Product Not Found");
        }
        productRepository.deleteById(id);
        product.setId(id);
        product.setCreatedDate(oldProduct.get().getCreatedDate());
        return productMapper.entityToDTO(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getProductsByCategoryId(Long categoryId) throws Exception {
        var category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new Exception("Category Not Found");
        }
        return productRepository.findProductsByCategories(category.get()).stream().map(productMapper::entityToDTO).toList();
    }

    private void populateProductWithCategory(Product product) {
        product.setCategories(product.getCategories().stream().map(x -> categoryRepository.findById(x.getId()).orElse(new Category())).toList());
    }
}
