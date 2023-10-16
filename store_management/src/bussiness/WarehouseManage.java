package bussiness;

import java.io.*;
import java.util.*;
import models.Product;
import models.Warehouse;
import tools.Tools;

public class WarehouseManage extends HashMap<String,Warehouse>{
    private ProductManage listOfProduct;
    private static int curImport = 1;
    private static int curExport = 1;
    private String header = "|    Code    |   Status   |    Date    |    List of products    |";
    private String row    = "|---------------------------------------------------------------|";
    private final File file = new File("./wareHouse.dat");


    /**
     * save object to .dat file
     * @return
     * @throws Exception
     */
    public boolean saveToFile() throws Exception{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        for(Warehouse x : this.toList()) oos.writeObject(x);
        oos.close();
        return true;
    }

    /**
     * read object from file
     * @return
     */
    public boolean loadFromFile(){
        if(this.file.isFile()&& this.file.exists()){
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                boolean hasNext = true;
                while(hasNext){
                    Warehouse x = (Warehouse)ois.readObject();
                    if(x != null) {
                        this.put(x.getCode(), x);
                        if(x.isStatus()) curImport++; else curExport++;    //count import/export receipts for generate code
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
     * constructor
     * @param listOfProduct
     */
    public WarehouseManage(ProductManage listOfProduct) {
        this.listOfProduct = listOfProduct;
    }
    
    /**
     * convert Hashmap to ArrayList
     * @return
     */
    public List<Warehouse> toList(){
        return new ArrayList<>(this.values());
    }

    /**
     * update attributes of product when add to import receipt
     * @param code
     * @return
     */
    private Product updateProductImport(String code){
        Product product = listOfProduct.get(code);
        int importQuanity = Tools.inputInt("Enter import quantities: ");
        product.setCurrentQuantities(importQuanity+product.getCurrentQuantities());     //set new quantities = current quantities + import quantities
        product.setProductionDate(Tools.inputDate("Enter new production date"));
        product.setExpirationDate(Tools.inputDate("Enter new expiration date"));
        product.setGenerate(true);  //this product has importe, cannot delete
        return product;
    }


    /**
     * create a import receipt
     * @return
     */
    public boolean createImport(){
        String code = Tools.generateCode("IM", 7, curImport);  //auto generate code
        while(this.containsKey(code)){      //if code already exist.
            curImport++;
            code = Tools.generateCode("IM", 7, curImport); 
        }
        System.out.println("Import receipt: "+code);
        Date importDate = new Date();
        boolean confirm = false;
        List<Product> importList = new ArrayList<>();
        do {
            listOfProduct.showAll();    //show all products for user to chose
            String productCode = Tools.inputString("Enter product's code you want to import(blank to create add product)").toUpperCase();
            if(productCode.isBlank()){ //if user doesn't type anything, create a new product and add to the import list.
                    Product product = listOfProduct.addOne();  //add new product
                    product.setGenerate(true);      //this product is in import receipt, cannot delete
                    importList.add(product);                   //add product to import list
            }
            else{ //else search product in list
                if(!listOfProduct.containsKey(productCode))     //if product doesn't exist, show for user.
                    System.out.println("Product does not exist.");
                else{       //else update new quantities, production date, and expiration date.
                    importList.add(updateProductImport(productCode)); //add to the import list
                }
            }
            confirm = Tools.inputBoolean("Input another product to receipt(yes/no)?",'y');
        } while (confirm);
        if(importList.isEmpty()){
            System.out.println("The import list is empty. Cannot import.");    //return false if the list empty
            return false;
        }
        Warehouse importReceipt = new Warehouse(true, code, importDate, importList);
        importReceipt.displayReceipt();
        this.put(code, importReceipt);
        curImport++;    //increase number of import receipt
        return true;
    }

    /**
     * Create a export receipt
     * @return
     */
    public boolean createExport(){
        String code = Tools.generateCode("EX", 7, curExport);      //auto generate code
        while(containsKey(code)){
            curExport++;
            code = Tools.generateCode("EX", 7, curExport);
        }
        System.out.println("Export receipt: "+code);
        Date exportDate = new Date();
        boolean confirm = false;
        List<Product> exportList = new ArrayList<>();
        do {
            listOfProduct.showAll();
            String productCode = Tools.inputString("Enter product's code to export").toUpperCase();       //get export code from user
            if(!listOfProduct.containsKey(productCode.toUpperCase())) 
                System.out.println("Product does not exist.");   //show user if code doesn't exist
            else if(listOfProduct.get(productCode).getCurrentQuantities() == 0)
                System.out.println("Out of stock");         //show user if quantities = 0
            else if(listOfProduct.get(productCode).getExpirationDate().before(exportDate))
                System.out.println("Product is out of date");  //show user if product out of date
            else{
                Product exportProduct = listOfProduct.get(productCode.toUpperCase());
                exportProduct.display();            //show product's attributes
                confirm = Tools.inputBoolean("Export this product?(yes/no)", 'y'); //comfirm to export this product
                if(confirm){
                    int exportQuantities = -1;
                    do {
                        exportQuantities = Tools.inputInt("Enter export quantity");   //get export quantities
                        if(exportQuantities <= exportProduct.getCurrentQuantities()){  
                            exportProduct.setCurrentQuantities(exportProduct.getCurrentQuantities()-exportQuantities);
                            exportProduct.setGenerate(true);   //cannot delete when exported
                            exportList.add(exportProduct);
                            break;
                        }
                        //export quantities cannot greater than current quantities
                        else System.out.println("Export quantities cannot greater than current quantities."); 
                    } while (exportQuantities > exportProduct.getCurrentQuantities());
                }
            }
            confirm = Tools.inputBoolean("Export another product?(yes/no)",'y');
        } while (confirm);
        if(exportList.isEmpty()){
            System.out.println("The export list is empty. Cannot export");
            return false;
            }
        Warehouse exportReceipt = new Warehouse(false, code, exportDate, exportList);
        exportReceipt.displayReceipt();
        this.put(code, exportReceipt);
        curExport++;
        return true;
    }


    /**
     * Print out import/export receipt by product
     */
    public void displayByProduct(){
        String productCode = Tools.inputString("Enter product's code").toUpperCase();       //get product code from user
        if(!listOfProduct.containsKey(productCode))
            System.out.println("Product does not exist");       //return if product doesn't exist
        else{
            Product product = listOfProduct.get(productCode);   //get product from the list
            if(!product.isGenerate()){
                System.out.println("This product hasn't import or export yet.");    //check if product been generate
            }
            else{
                System.out.println("\tReceipts of product "+productCode+":");
                System.out.println(header);
                System.out.println(row);
                for (Warehouse receipt : this.toList()) {       //loop through list of receipt
                    for(Product x : receipt.getProducts()){     //loop though list of products in the receipt    
                        if(x.getCode().equalsIgnoreCase(productCode)){                       //if the receipt contains the product, print out the receipt
                            System.out.println(receipt);
                            System.out.println(row);
                        }
                    }
                }
            }
        }
        
    }
}
