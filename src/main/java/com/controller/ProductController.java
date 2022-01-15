package com.controller;
import com.customerException.productNotFoundException;
import com.dao.ProductRepo;
import com.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductRepo productRepo;


    /**
     * Save a product
     * @param productModel the product model
     * @return the string
     */

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ProductModel saveProduct(@RequestBody final ProductModel productModel){
        return productRepo.save(productModel);
    }


    /**
     * Save Product in Bulk
     * @param productModelList the product model list
     * @return the string
     */

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public String saveProductInBulk (@RequestBody List<ProductModel> productModelList){
        productRepo.saveAll(productModelList);
        return HttpStatus.CREATED.toString();
    }


    /**
     * Get all product
     * @return the product
     */
    @GetMapping(value = "/All")
    public List<ProductModel> getProduct(){
        return productRepo.findAll();
    }


//    @PutMapping("/{id}")
//    public ProductModel viewProducts(@PathVariable Long id){
//        return productRepo.findById(id).orElseThrow(()-> new productNotFoundException("ID Not Found"+" "+ id));
//    }

    /**
     * Find by ID
     * @param id
     * @return list or error message
     */
    @GetMapping("/{id}")
    public ProductModel viewProductByID(@PathVariable Long id){
        return productRepo.findById(id).orElseThrow(()-> new productNotFoundException("ID Not Found"+" "+ id));
    }
    /**
     * Update a specific id product
     * @param productModel the product model
     * @param id
     * @return updated product list
     */
    @PutMapping("/update/{id}")
    ProductModel updateProduct(@RequestBody ProductModel productModel, @PathVariable Long id){
        return productRepo.findById(id).map(prod-> {
            prod.setProductName(productModel.getProductName());
            prod.setProductCategory(productModel.getProductCategory());
            prod.setProductPrice(productModel.getProductPrice());
            return (productRepo.save(prod));
        } ).orElseThrow(()->new productNotFoundException("ID Not Found "+" "+id));
    }


    /**
     * Delete Product String
     * @param id the id
     * @return the string
     */

    @DeleteMapping("/delete/{id}")
    String deleteProduct(@PathVariable Long id){
        try{
            productRepo.deleteById(id);
        } catch (Exception e){
            throw new productNotFoundException("ID Not Found" + " " + id);
        }
        return "Successfully deleted";
    }

}
