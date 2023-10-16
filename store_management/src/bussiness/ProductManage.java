package bussiness;

import java.io.*;
import java.util.*;

import models.Product;
import tools.Tools;

public class ProductManage extends HashMap<String,Product> {
    private String header ="|  Code  |    Name    | Unit price | Quantity |   Production date   |  Expiration date  |";
    private String row =   "|---------------------------------------------------------------------------------------|";
    private static int dailyCode = 1;       //code for auto generate
    private static int longCode = 1;
    File file = new File("./product.dat");
    public ProductManage(){
    }

    /**
     * Convert hashmap to arraylist
     * @return
     */
    public ArrayList<Product> toList(){
        return new ArrayList<>(this.values());
    }    


    /**
     * Save data of product to file
     * @return
     * @throws Exception
     */
    public boolean saveToFile() throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        for(Product x : this.toList()) {
            oos.writeObject(x);
        }
        oos.close();
        return true;
    }


    /**
     * Load list of products from file
     * @return
     */
    public boolean loadFromFile(){
        if(this.file.isFile()&& this.file.exists()){
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                boolean hasNext = true;
                while(hasNext){
                    Product x = (Product)ois.readObject();
                    if(x != null) {
                        this.put(x.getCode(), x);
                         if(x.isType()) dailyCode++; else longCode++;   //count code for next product
                    }
                    else hasNext = false;
                }
                ois.close();
            }catch(Exception e){
                
                
            }
            return true;
            }else{
                System.out.println("The file could not be found.");
                return false;
            }
    }

    /**
     * Show all product in the list
     */
    public void showAll(){
        ArrayList<Product> sortList = this.toList();
        Collections.sort(sortList);
        System.out.println(header);
        System.out.println(row);
        for(Product i : sortList){
            System.out.println(i);
            System.out.println(row);
        }
    }

    /**
     * add one product
     * @return
     */
    public Product addOne(){
        
            boolean type = Tools.inputBoolean("Choose product's type(0: Daily/1: Long-shelf life)",'0');
            String code = Tools.generateCode((type)?"D":"L", 3, (type)?dailyCode:longCode); //auto generate code by its type
            while(this.containsKey(code)){ //if code already in the list
                if(type)  dailyCode++; else  longCode++; //auto increase code by 1.
                code = Tools.generateCode((type)?"D":"L", 3, (type)?dailyCode:longCode); //auto generate code by its type 
            }
            if(type)  dailyCode++; else  longCode++; 
            System.out.println("Product's code: "+code);
            String name = Tools.inputNonBlankString("Enter product's name");
            double unitPrice = Tools.inputDouble("Enter unit price");
            int quantity = Tools.inputInt("Enter quantity");
            Date productionDate = Tools.inputDate("Enter production date");
            Date expirationDate = Tools.inputDate("Enter expiration date");
            this.put(code, new Product(type, code, name, unitPrice, quantity, productionDate, expirationDate));
            System.out.println("Product "+code+" added successful.");
            return this.get(code);
    }

    /**
     * Add one product, then asked user to add another
     */
    public void addProducts(){
        boolean confirmation = false;
        do {
            this.addOne();
            confirmation = Tools.inputBoolean("Input another product?(yes/no)",'y');
        } while (confirmation);
    }

    /**
     * Update attributes of an product
     */
    public void updateProduct(){
        String code = Tools.inputNonBlankString("Enter product's code to update: ").toUpperCase();
        if(!this.containsKey(code)) System.out.println("Product does not exist.");
        else{
            System.out.println("The product:\n"+this.get(code).display());
            System.out.println("If you don't type anything, the product's information won't change.");
            this.get(code).setName(Tools.inputString("Enter new name"));
            this.get(code).setUnitPrice(Tools.inputDouble("Enter new unit price:(won't set if price=0)"));
            this.get(code).setCurrentQuantities(Tools.inputInt("Enter new quantity(won't set if = 0)"));
            this.get(code).setProductionDate(Tools.inputDate("Enter new production date"));
            this.get(code).setExpirationDate(Tools.inputDate("Enter new expiration date"));
            System.out.println("Update successful.");
        }
    }

    /**
     * Delete a product if it hasn't import/export yet.
     */
    public void delete(){
        String code = Tools.inputNonBlankString("Enter product's code to delete:").toUpperCase();
        if(!this.containsKey(code.toUpperCase())) System.out.println("Product does not exist.");
        else{
            System.out.println("The product:\n"+this.get(code));
            if(this.get(code).isGenerate()) {
                System.out.println("Product has imported/exported. Cannot delete.");
            }
            else{
                boolean confirm = Tools.inputBoolean("Delete this product(yes/no)",'y');
                if(confirm){
                    this.remove(code);
                    System.out.println("Delete successful.");
                }else{
                    System.out.println("Delete canceled.");
                }
            }
        }
    }

    /**
     * Print out a list of expired products
     */
    public void expired(){
        Date today = new Date();
        System.out.println("\tList of expired products:");
        System.out.println(header);
        System.out.println(row);
        for (Product product : this.toList()) {
            if(product.getExpirationDate().before(today)){
            System.out.println(product);
            System.out.println(row);
            }
        }
    }


    /**
     * Print ot a list of product is selling, which have currentQuantities > 0 and haven't expired yet
     */
    public void selling(){
        Date today = new Date();
        System.out.println("\tSelling list:");
        System.out.println(header);
        System.out.println(row);
        for (Product i : this.toList()) {
            if(i.getCurrentQuantities() > 0 && !i.getExpirationDate().before(today)){
                System.out.println(i);
                System.out.println(row);
            }
        }
    }
    

    /**
     * Print out list of products which have quantities <= 5 
     */
    public void outOfStock(){
        List<Product> list = new ArrayList<>();
        for(Product i : toList()){
            if(i.getCurrentQuantities() <= 5) list.add(i);
        }
        Comparator sort = new Comparator<Product>() {

            @Override
            public int compare(Product o1, Product o2) {
                if(o1.getCurrentQuantities()>o2.getCurrentQuantities()) return 1;
                else return -1;
            }
            
        };
        Collections.sort(list, sort);
        System.out.println("\tRunning out of stock list: ");
        System.out.println(header);
        System.out.println(row);
        for (Product product : list) {
            System.out.println(product);
        }
    }

}
