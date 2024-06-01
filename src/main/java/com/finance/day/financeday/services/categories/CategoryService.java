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

import java.util.Optional;

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

        if (categoryRecord.name() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Category category = categoryRepository.findByNameAndUser(categoryRecord.name(), user);

        if (category == null) {
            category = Category.builder()
                    .name(categoryRecord.name())
                    .description(categoryRecord.description())
                    .user(user)
                    .build();

            categoryRepository.save(category);

            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> editCategory(CategoryRecord categoryRecord) {

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Optional<Category> categoryOptional = categoryRepository.findById(categoryRecord.id());

        if (categoryOptional.isPresent()){
            Category category = categoryOptional.get();

            if (category.getUser() != user)
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            category.setName(categoryRecord.name() != null ? categoryRecord.name() : category.getName());
            category.setDescription(categoryRecord.description());

            categoryRepository.save(category);

            return new ResponseEntity<>(category, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteCategory(CategoryRecord categoryRecord){

        UserDetails userDetails = userUtils.getUser();

        if (userDetails == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        User user = (User) userRepository.findByUsername(userDetails.getUsername());

        Category category = categoryRepository.getReferenceById(categoryRecord.id());

        if (category.getUser() != user)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);


    }
}
