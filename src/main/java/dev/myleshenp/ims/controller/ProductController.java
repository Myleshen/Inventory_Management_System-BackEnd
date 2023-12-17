package dev.myleshenp.ims.controller;

import dev.myleshenp.ims.dto.ProductDTO;
import dev.myleshenp.ims.entity.Product;
import dev.myleshenp.ims.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody Product product) {
        log.info(product.toString());
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public ProductDTO updateProductById(@PathVariable Long id, @RequestBody Product product) throws Exception {
        return productService.updateProductById(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

    @GetMapping("/categories/{category_id}")
    public List<ProductDTO> getProductsByCategoryId(@PathVariable Long category_id) throws Exception {
        return productService.getProductsByCategoryId(category_id);
    }
}
