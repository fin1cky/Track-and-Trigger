package com.example.trackntriggery;

public class model_inventory {
    String name,image_url,quantity;
    String category;
    public model_inventory(){

    }

    public model_inventory(String name, String quantity,String image_url,String category) {
        this.name = name;
        this.image_url = image_url;
        this.quantity = quantity;
        this.category=category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String name) {
        this.category = category;
    }
}
