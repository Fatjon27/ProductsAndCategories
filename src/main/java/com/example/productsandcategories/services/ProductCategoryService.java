package com.example.productsandcategories.services;

import com.example.productsandcategories.models.Category;
import com.example.productsandcategories.models.Product;
import com.example.productsandcategories.repositories.CategoryRepository;
import com.example.productsandcategories.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }
    public Product findProdById(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return optionalProduct.get();
        }
        else{
            return null;
        }
    }
    public Category findCatById(Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            return optionalCategory.get();
        }
        else {
            return null;
        }
    }
    public List<Product> allProductsInThisCategory(Category category){
        return productRepository.findAllByCategories(category);
    }
    public List<Product> allProductsNotInThisCategory(Category category){
        return productRepository.findByCategoriesNotContains(category);
    }
    public List<Category> allCategoriesInThisProduct(Product product){
        return categoryRepository.findAllByProducts(product);
    }
    public List<Category> allCategoriesNotInThisProduct(Product product){
        return categoryRepository.findByProductsNotContains(product);
    }
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }
    public List<Category> findAllCategories(){
        return categoryRepository.findAll();
    }
    public void assignCategory(Product product,Category category){
        product.getCategories().add(category);
        productRepository.save(product);
    }
    public void assignProduct(Product product,Category category){
        category.getProducts().add(product);
        categoryRepository.save(category);
    }

}
