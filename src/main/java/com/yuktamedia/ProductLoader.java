package com.yuktamedia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.yuktamedia.model.Product;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

    private List<Product> products = new ArrayList<Product>();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        Product p1 = new Product();
        p1.setPid(11);
        p1.setProductName("Bottle");
        p1.setProductImage("https://www.containerstore.com/catalogimages/318610/ColoredStopperBottlesClear_x.jpg?width=1200&height=1200&align=center");
        p1.setProductColor("Transperent");
        products.add(p1);

        Product p2 = new Product();
        p2.setPid(12);
        p2.setProductName("Fan");
        p2.setProductImage("https://rukminim1.flixcart.com/image/704/704/fan/a/z/f/havells-leganza-4blade-original-imadxxczv6vmj2mz.jpeg?q=70");
        p2.setProductColor("Silver");
        products.add(p2);
    }

    public List<Product> listAllProducts() {
        return products;
    }
}
