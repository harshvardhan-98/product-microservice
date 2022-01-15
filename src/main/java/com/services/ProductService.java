package com.services;

import com.dao.ProductRepo;
import com.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public ProductModel saveProducts(ProductModel productModel) {
        return productRepo.save(productModel);
    }


    public List<ProductModel> getAllProducts() {
        List<ProductModel> productModelList=(List<ProductModel>) productRepo.findAll();
        return productModelList;
    }
}
