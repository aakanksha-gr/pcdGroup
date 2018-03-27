package com.androidjson.pcdGroup;

/**
 * Created by HP on 19-03-2018.
 */

public class ProdactEntity {

    private String title,thumbnailUrl,description;
    private int availableStock;
    private Integer price,quantity,hsncode,gst,stock,reorderlevel;

    public ProdactEntity(String name, String thumbnailUrl, Integer price, Integer quantity, Integer hsncode, Integer gst, String description, Integer stock, Integer reorderlevel, int availableStock) {
        this.title = name;
        this.price = price;
        this.quantity = quantity;
        this.hsncode= hsncode;
        this.gst= gst;
        this.stock=stock;
        this.description=description;
        this.reorderlevel=reorderlevel;
        this.thumbnailUrl = thumbnailUrl;
        this.availableStock = availableStock;
    }

    public String getTitle() {
        return title;
    }
    public Integer getPrice() {return price;}
    public Integer getQuantity() {return quantity;}
    public Integer getHsncode() {return hsncode;}
    public Integer getGst() {return gst;}
    public String getDescription() {return description;}
    public Integer getstock() {return stock;}
    public Integer getReorderlevel() {return reorderlevel;}

    public void setTitle(String name) {
        this.title = name;
    }
    public void setPrice(Integer price) {this.price = price;}
    public void setQuantity(Integer quantity) {this.quantity = quantity;}
    public void setHsncode(Integer hsncode) {this.hsncode = hsncode;}
    public void setGst(Integer gst) {this.gst = gst;}
    public  void setDescription (String description) { this.description= description ;}
    public void setStock(Integer stock) {this.stock = stock;}
    public void setReorderlevel(Integer reorderlevel) {this.reorderlevel = reorderlevel;}

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public String gettitle() {
        return title;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public void setprice(Integer price) {
        this.price = price;
    }

    public int getStock() {
        return availableStock;
    }

    public void setStock(int availableStock) {
        this.availableStock = availableStock;
    }

}
