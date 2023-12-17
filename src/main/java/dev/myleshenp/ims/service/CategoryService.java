package dev.myleshenp.ims.service;

import dev.myleshenp.ims.dto.CategoryDTO;
import dev.myleshenp.ims.entity.Category;
import dev.myleshenp.ims.mapper.CategoryMapper;
import dev.myleshenp.ims.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryDTO saveCategory(Category category) {
        return categoryMapper.entityToDTO(categoryRepository.save(category));
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::entityToDTO).toList();
    }

    public CategoryDTO updateCategoryById(Long id, Category category) throws Exception {
        var oldCategory = categoryRepository.findById(id);
        if (oldCategory.isEmpty()) {
            throw new Exception("Category Not Found");
        }
        categoryRepository.deleteById(id);
        category.setId(id);
        category.setCreatedDate(oldCategory.get().getCreatedDate());
        return categoryMapper.entityToDTO(categoryRepository.save(category));
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    public CategoryDTO getCategoryById(Long id) throws Exception {
        var category = categoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new Exception("Category with ID: " + id + " not found");
        }
        return categoryMapper.entityToDTO(category.get());
    }
}
