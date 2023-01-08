package com.scm.repository;

import com.scm.domain.Products;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    @Query("Select c from Car c join c.image im where im.id=:id")
    List<Products> findProductsByImageId(@Param("id") String id);

    @EntityGraph(attributePaths = "image")
    Optional<Products> findProductsByName(String name);
}
