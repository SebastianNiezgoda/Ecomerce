package pl.Sniezgoda.ecomerce.katalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private  String id;
    private  String name;
    private  String description;
    private BigDecimal price;

    Product() {
    }

    public Product(UUID id, String name, String description) {
        this.id = id.toString();
        this.name = name;
        this.description = description;
    }



    public Object getPrice() {
        return price;
    }

    public String getID() {
        return id;
    }

    public void changePrice(BigDecimal newPrice){
        this.price = newPrice;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}

