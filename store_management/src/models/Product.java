package models;

import java.io.Serializable;
import java.util.*;
import java.text.SimpleDateFormat;

public class Product implements Serializable,Comparable<Product>{
    private boolean type;  //true: Daily product, false: Long shelf life product;
    private String code;
    private String name;
    private double unitPrice;
    private int currentQuantities;
    private Date productionDate;
    private Date expirationDate;
    private boolean isGenerate = false; //check if this product has export/import
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public Product(boolean type, String code, String name, double unitPrice, int currentQuantities,
            Date productionDate, Date expirationDate) {
        this.type = type;
        this.code = code;
        this.name = name;
        this.unitPrice = unitPrice;
        this.currentQuantities = currentQuantities;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        if(!code.isBlank())
        this.code = code.toUpperCase();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(!name.isBlank())
            this.name = name;
    }
    
    public boolean isType() {
        return type;
    }
    public void setType(boolean type) {
        this.type = type;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(double unitPrice) {
        if(unitPrice>0)
            this.unitPrice = unitPrice;
    }

    public int getCurrentQuantities() {
        return currentQuantities;
    }
    public void setCurrentQuantities(int currentQuantities) {
        if(currentQuantities>0)
        this.currentQuantities = currentQuantities;
    }
    
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public String display(){
        return String.format("|Code: %s|Name: %s|Unit price: %.3f|Current Quantities: %s|Production date: %s|Expiration date: %s|",code,name,unitPrice,currentQuantities,dateFormat.format(productionDate),dateFormat.format(expirationDate));
    }

    @Override
    public String toString() {
        return String.format("|  %s  |%-12s|%12.3f|%10d|%21s|%19s|",code,name,unitPrice,currentQuantities,dateFormat.format(productionDate),dateFormat.format(expirationDate));
    }
    public Date getProductionDate() {
        return productionDate;
    }
    public boolean isGenerate() {
        return isGenerate;
    }
    public void setGenerate(boolean isGenerate) {
        this.isGenerate = isGenerate;
    }

    @Override
    public int compareTo(Product o) {
        return this.getCode().compareTo(o.getCode());
    }
    
}
