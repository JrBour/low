package jrbour.blog.service;

import jrbour.blog.dao.CategoryDao;
import jrbour.blog.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryDao repository;

    @Test
    @DisplayName("Test save category")
    void testSave(){
        Category category = new Category("Gender Reveal Party");
        doReturn(category).when(repository).save(any());
        // when(repository.save(any(Category.class))).thenReturn(new Category());

        Category created = categoryService.save(category);
        Assertions.assertEquals("Gender Reveal Party", created.getName());
    }
}
