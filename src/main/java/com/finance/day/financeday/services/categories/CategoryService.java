package com.finance.day.financeday.services.categories;

import com.finance.day.financeday.entities.Category;
import com.finance.day.financeday.entities.User;
import com.finance.day.financeday.records.categories.CategoryRecord;
import com.finance.day.financeday.repositories.categories.CategoryRepository;
import com.finance.day.financeday.repositories.users.UserRepository;
import com.finance.day.financeday.utils.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final UserUtils userUtils;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> createCategory(CategoryRecord categoryRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Category category = categoryRepository.findByNameAndUser(categoryRecord.name(), user);

        if (category != null)
            return new ResponseEntity<>(HttpStatus.OK);


        category = Category.builder()
                .name(categoryRecord.name())
                .description(categoryRecord.description())
                .user(user)
                .build();

        categoryRepository.save(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    public ResponseEntity<?> editCategory(Long id, CategoryRecord categoryRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (category.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        category.setName(categoryRecord.name() != null ? categoryRecord.name() : category.getName());
        category.setDescription(categoryRecord.description());

        categoryRepository.save(category);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCategory(Long id) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

        if (category.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        categoryRepository.delete(category);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> listAllCategories() {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        List<Category> categories = categoryRepository.findByUser(user);

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
