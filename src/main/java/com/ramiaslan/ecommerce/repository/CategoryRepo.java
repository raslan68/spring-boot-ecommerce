package com.ramiaslan.ecommerce.repository;

import com.ramiaslan.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

}
