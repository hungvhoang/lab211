package models;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Warehouse implements Serializable{
    private boolean status;     //0: import, 1: export
    private String code;
    private Date date;
    private List<Product> products;
    public Warehouse() {
        this.date = new Date();
        this.products = new ArrayList<>();
    }
    public Warehouse(boolean status, String code, Date date, List<Product> products) {
        this.status = status;
        this.code = code;
        this.date = date;
        this.products = products;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void displayReceipt(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        System.out.println((status) ? "Import receipt: " : "Export receipt: ");
        System.out.println("Code: "+code);
        System.out.println("Date: "+dateFormat.format(date));
        System.out.println("List of product: "+displayProductCode()+".");
    }    


    private String displayProductCode(){
        String products ="";
        for (Product product : this.products) {
            products += product.getCode()+" ";
        }
        return products;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("|%12s|%12s|%12s|%24s|",code,status?"Import":"Export",dateFormat.format(date),displayProductCode());
    }
    
}