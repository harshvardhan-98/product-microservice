package com.dao;

import com.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * The interface product repo
 */
public interface ProductRepo extends JpaRepository<ProductModel, Long> {


}
