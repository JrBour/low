package jrbour.blog.service;

import jrbour.blog.dao.CategoryDao;
import jrbour.blog.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doReturn;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryDao repository;

    @Test
    @DisplayName("Test save category")
    void testSave(){
        Category category = new Category("Gender Reveal Party");
        doReturn(category).when(this.repository).save(any());
        // when(repository.save(any(Category.class))).thenReturn(new Category());

        Category created = this.categoryService.save(category);
        Assertions.assertEquals("Gender Reveal Party", created.getName());
    }
}
