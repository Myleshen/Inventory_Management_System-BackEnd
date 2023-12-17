package dev.myleshenp.ims.repository;

import dev.myleshenp.ims.entity.Category;
import dev.myleshenp.ims.entity.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends ListCrudRepository<Product, Long> {

    List<Product> findProductsByCategories(Category category);
}
