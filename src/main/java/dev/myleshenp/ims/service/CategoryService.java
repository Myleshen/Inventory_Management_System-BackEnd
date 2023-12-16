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

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::entityToDTO).toList();
    }
}
