package com.finance.day.financeday.repositories.categories;

import com.finance.day.financeday.entities.Category;
import com.finance.day.financeday.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByNameAndUser(String name, User user);

    List<Category> findByUser(User user);
}
