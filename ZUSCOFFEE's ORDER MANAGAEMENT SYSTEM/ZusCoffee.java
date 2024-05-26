import java.util.*;
import java.io.*;
public class ZusCoffee
{
    //attributes in linkedlist
    private String orderID;
    private String custName;
    private String date;
    private char itemType;
    private String itemName;
    private int quantity;
    private double price;
    private char paymentMethod;
    
    //default constructor
    public ZusCoffee(){}
    
    //normal constructor
    public ZusCoffee(String o, String c, String d, char it, String in, int q, double p, char pm)
    {
        this.orderID = o;
        this.custName = c;
        this.date = d;
        this.itemType = it;
        this.itemName = in;
        this.quantity = q;
        this.price = p;
        this.paymentMethod = pm;
    }
    
    //mutator
    public void setOrderID(String o) {this.orderID = o;}
    public void setCustName(String n) {this.custName = n;}
    public void setDate(String d) {this.date = d;}
    public void setItemType(char it) {this.itemType = it;}
    public void setItemName(String in) {this.itemName = in;}
    public void setQuantity(int q){this.quantity = q;}
    public void setPrice(double p){this.price = p;}
    public void setPaymentMethod(char pm){this.paymentMethod = pm;}
    
    //accessor
    public String getOrderID(){return this.orderID;}
    public String getCustName(){return this.custName;}
    public String getDate(){return this.date;}
    public char getItemType(){return this.itemType;}
    public String getItemName(){return this.itemName;}
    public int getQuantity(){return this.quantity;}
    public double getPrice(){return this.price;}
    public char getPaymentMethod(){return this.paymentMethod;}
    
    //printer
    public String toString()
    {
        return "\nOrder ID: " +this.getOrderID()+
               " Customer Name: " +this.getCustName()+
               " Date: " +this.getDate()+
               " Item Type: " +this.getItemType()+
               " Item Name: " +this.getItemName()+
               " Quantity: " +this.getQuantity()+
               " Price: " +this.getPrice()+
               " Payment Method: " +this.getPaymentMethod();
    }
    
    //processor
    public double calcTotalPrice()
    {
     return price * quantity;
    }
}