package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.mem.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier human = new Supplier("Human", "Most popular human lives");
        supplierDataStore.add(human);
        Supplier animal = new Supplier("Animal", "Most popular animal lives");
        supplierDataStore.add(animal);

        //setting up a new product category
        ProductCategory scientist = new ProductCategory("Scientist", "Science");
        productCategoryDataStore.add(scientist);
        ProductCategory animalLife = new ProductCategory("Animal", "Animal");
        productCategoryDataStore.add(animalLife);
        ProductCategory techJobs = new ProductCategory("Tech Job Lifestyles", "Tech Job Lifestyles");
        productCategoryDataStore.add(techJobs);

        productDataStore.add(new Product("Doc Brown", new BigDecimal("100"), "USD", "The way I see it, if you're gonna build a time machine into a car, why not do it with some style?", scientist, human));

        productDataStore.add(new Product("Dog", new BigDecimal("1000"), "USD", "Woof!", animalLife, animal));
        productDataStore.add(new Product("Cat", new BigDecimal("3000"), "USD", "Meow!", animalLife, animal));

        productDataStore.add(new Product("Hacker", new BigDecimal("1337"), "USD", "We're all living in each other's paranoia", techJobs, human));
        productDataStore.add(new Product("Virtual life", new BigDecimal("300"), "USD", "You have to understand; most people are not ready to be unplugged", techJobs, human));
        productDataStore.add(new Product("Digital nomad", new BigDecimal("1299"), "USD", "I don't know where I'm going, but I'm on my way", techJobs, human));
        productDataStore.add(new Product("Influencer", new BigDecimal("649"), "USD", "Post or perish", techJobs, human));
        productDataStore.add(new Product("Van life", new BigDecimal("199"), "USD", "I think careers are a 20th century invention and I don't want one", techJobs, human));

    }

}
