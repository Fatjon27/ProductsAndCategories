package com.example.productsandcategories.controllers;

import com.example.productsandcategories.models.Category;
import com.example.productsandcategories.models.Product;
import com.example.productsandcategories.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    private ProductCategoryService productCategoryService;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("products",productCategoryService.findAllProducts());
        model.addAttribute("categories",productCategoryService.findAllCategories());
        return "index";
    }
    @GetMapping("/products/new")
    public String createP(@ModelAttribute("product")Product product){
        return "newP";
    }
    @PostMapping("/products/new")
    public String createProd(@Valid @ModelAttribute("product") Product product, BindingResult result){
        if(result.hasErrors()){
            return "newP";
        }
        else {
            productCategoryService.createProduct(product);
            return "redirect:/";
        }
    }
    @GetMapping("/categories/new")
    public String createC(@ModelAttribute("category")Category category){
        return "newC";
    }
    @PostMapping("/categories/new")
    public String createCat(@Valid @ModelAttribute("category") Category category,BindingResult result){
        if(result.hasErrors()){
            return "newC";
        }
        else {
            productCategoryService.createCategory(category);
            return "redirect:/";
        }
    }
    @GetMapping("products/{id}")
    public String assignP(@PathVariable("id") Long id,Model model){
        model.addAttribute("product",productCategoryService.findProdById(id));
        model.addAttribute("categoriesOfProduct",productCategoryService.allCategoriesInThisProduct(productCategoryService.findProdById(id)));
        model.addAttribute("availableCategories",productCategoryService.allCategoriesNotInThisProduct(productCategoryService.findProdById(id)));
        return "assignCategory";
    }
    @PostMapping("products/{id}")
    public String assignProd(@RequestParam(value = "categoryId") Long categoryId,Model model,@PathVariable("id") Long productId) {
        Product product = productCategoryService.findProdById(productId);
        Category category = productCategoryService.findCatById(categoryId);
        product.getCategories().add(category);
        productCategoryService.createProduct(product);
        return "redirect:/products/"+productId;
    }
    @GetMapping("/categories/{id}")
    public String assignC(@PathVariable("id") Long id,Model model){
        model.addAttribute("category",productCategoryService.findCatById(id));
        model.addAttribute("productsOfCategory",productCategoryService.allProductsInThisCategory(productCategoryService.findCatById(id)));
        model.addAttribute("availableProducts",productCategoryService.allProductsNotInThisCategory(productCategoryService.findCatById(id)));
        return "assignProduct";
    }
    @PostMapping("/categories/{id}")
    public String assignCat(@RequestParam(value = "productId") Long id,Model model,@PathVariable("id") Long catId){
        Category category =productCategoryService.findCatById(catId);
        category.getProducts().add(productCategoryService.findProdById(id));
        productCategoryService.createCategory(category);
//            productCategoryService.assignProduct(productCategoryService.findProdById(id),category);
//            productCategoryService.createCategory(category);
            return "redirect:/categories/"+catId;
        }
    }
