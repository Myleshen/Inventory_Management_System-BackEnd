package dev.myleshenp.ims.controller;

import dev.myleshenp.ims.dto.CategoryDTO;
import dev.myleshenp.ims.entity.Category;
import dev.myleshenp.ims.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
       return categoryService.getAllCategories();
    }

}
