import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class ZusCoffeeLLApp
{
    static Scanner in = new Scanner(System.in);
    static Scanner inLine = new Scanner(System.in);
    static LinkedList customerList = new LinkedList();
    static LinkedList beverageList = new LinkedList();
    static LinkedList foodList = new LinkedList();
    
    /* 1. search to update the quantity of orders for certain f/b 
     * 2. calc the total sales of all order by customer in year of 2022,2023,2024
     * 3. find the order with highest totalPrice ordered
     * 4. Remove the food and the beverage from CustomerList
     * 5. count the num of customer who bought beverage from BeverageList
     */
    public static void main(String[] args) throws Exception {
        boolean exit = false;
        while(!exit){
            boolean repeat = true;
            System.out.println("\f+-------------------------------------+");
            System.out.println("|          ZUS COFFEE SDN BHN         |");
            System.out.println("+-------------------------------------+");
            System.out.println("|   Welcome to Order Management Site  |");
            System.out.println("+-------------------------------------+");
            System.out.println("\nMenu");
            System.out.println("A - View order");
            System.out.println("B - Update quantity");
            System.out.println("C - Total sales of the year");
            System.out.println("D - Remove from customerList to beverage list and food list");
            System.out.println("E - Highest total price ordered by customer");
            System.out.println("F - Number of customer who bought beverage");
            System.out.println("G - Exit");
            System.out.println("=============================================");
            System.out.print("Enter your choice [A/B/C/D/E/F/G]: ");
            char action = in.next().charAt(0);
            
            if(action == 'A' || action == 'a'){
                while(repeat){
                    viewOrder();
                    repeat = repeatProcess();
                }
            }
            else if(action == 'B' || action == 'b'){
                while(repeat){
                    updateQuantity();
                    repeat = repeatProcess();
                }
            }
            else if(action == 'C' || action == 'c'){
                while(repeat){
                    totalSales();
                    repeat = repeatProcess();
                }
            }
            else if(action == 'D' || action == 'd'){
                while(repeat){
                    removeOrder();
                    repeat = repeatProcess();
                }
            }
            else if(action == 'E' || action == 'e'){
                while(repeat){
                    highestTotalPrice();
                    repeat = repeatProcess();
                }
            }
            else if(action == 'F' || action == 'f'){
                while(repeat){
                    countBeverage();
                    repeat = repeatProcess();
                }
            }
            else if (action == 'G' || action == 'g'){
                System.out.println("Thank you for using our system. Good bye!");
                exit = true;
            }
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    
    public static void viewOrder() throws Exception {
        System.out.println("\f\tZUSCOFFEE VIEW ORDER ");
        System.out.println("\n************Order*****************");
        System.out.println("\nChoose list(s) to view from");
        LinkedList list = readFile(fileToRead());
        ZusCoffee z = (ZusCoffee) list.getFirst();
        int count = 1;
        while(z != null){
            System.out.println("\n" + count + ")" + z.toString());
            count++;
            z = (ZusCoffee) list.getNext();
        }
    }
    
    /**Process(5) Remove the food and the beverage from CustomerList into foodList and beverageList**/
    /*Process(5) Remove the food and the beverage from CustomerList into foodList and beverageList*/
    public static void removeOrder() throws Exception {
        System.out.println("\f\tZUSCOFFEE ORDER LIST ");
        System.out.println("\n*Remove Order List*");
        customerList = readFile("CustomerList.txt");
        ZusCoffee z = (ZusCoffee) customerList.getFirst();
        System.out.println("\nRemoving data from customerList into beverageList and foodList...");
        while(z != null){
            if(z.getItemType() == 'B' || z.getItemType() == 'b') {
                beverageList.addLast(z);
            }
            else{
                foodList.addLast(z);
            }
            z = (ZusCoffee) customerList.removeFirst();
        }
        writeFile(customerList, "CustomerList.txt");
        writeFile(beverageList, "BEVERAGE.txt");
        writeFile(foodList, "FOOD.txt");
        System.out.println("\nProcess finished!");
    }
    
    /**Process(1) Search to update the quantity of orders based on order ID**/
    public static void updateQuantity() throws Exception { //search to update the quantity of orders for certain f/b 
        System.out.println("\f\tZUSCOFFEE ORDER LIST ");
        System.out.println("\n**********Update Order**********");
        System.out.println("\nChoose list(s) to search from");
        String file = fileToRead();
        LinkedList list= readFile(file);
        ZusCoffee z = (ZusCoffee) list.getFirst();
        boolean found = false;
        System.out.print("\n Enter order ID: ");
        String input = inLine.nextLine();
        System.out.print("\n Enter new quantity: ");
        int inInt = in.nextInt();
        
        while(z != null){
            if(z.getOrderID().equalsIgnoreCase(input)){
                found = true;
                z.setQuantity(inInt);
            }
            z = (ZusCoffee) list.getNext();
        }
        if(!found)
            System.out.println("\nWrong input. Please try again.");
        else {
            writeFile(list, file);
        }
    }
    
    public static String fileToRead(){
        System.out.println("\nA - CustomerList\nB - BeverageList\nC - FoodList");
        System.out.print("\nList [A/B/C]: ");
        char choice = in.next().charAt(0);
        String file = "A";
        
        while (file.equals("A")){
            if(choice == 'A' || choice == 'a') file = "CustomerList.txt";
            else if(choice == 'B' || choice == 'b') file = "BEVERAGE.txt";
            else if(choice == 'C' || choice == 'c') file = "FOOD.txt";
            else System.out.println("Invalid input. Please try again.");
        }
        return file;
    }
    
    public static LinkedList readFile(String file) throws Exception{
        LinkedList list = new LinkedList();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null){
                StringTokenizer st = new StringTokenizer(line,"*");
                String orderID = st.nextToken(); 
                String custName= st.nextToken();
                String date= st.nextToken();
                String itemType= st.nextToken();
                String itemName= st.nextToken();
                String quantity= st.nextToken();
                String price = st.nextToken();
                String paymentMethod = st.nextToken();
                char itemTypeChar = itemType.charAt(0);
                char paymentMethodChar = paymentMethod.charAt(0);
                int quantityInt = Integer.parseInt(quantity);
                double priceDouble = Double.parseDouble(price);
                
                ZusCoffee z = new ZusCoffee (orderID, custName, date, itemTypeChar, itemName, quantityInt, priceDouble, paymentMethodChar);
                list.addLast(z);
                line = br.readLine();
            }
            br.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        return list;
    }
    
    public static void writeFile(LinkedList list, String file){
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File(file), false));
            ZusCoffee z = (ZusCoffee) list.getFirst();
            while(z != null){
                String input = z.getOrderID() + "*" + z.getCustName() + "*" + z.getDate() + "*" ;
                input += z.getItemType() + "*" + z.getItemName() + "*" + z.getQuantity() + "*" + z.getPrice() + "*" + z.getPaymentMethod() ;
                pw.println(input);
                z = (ZusCoffee) list.getNext();
            }
            pw.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public static boolean repeatProcess(){
        char input;
        System.out.print("\nRepeat? [X - return to menu / Y - repeat]: ");
        input = in.next().charAt(0);
        if(input == 'X' || input == 'x')
            return false;
        else
            return true;
    }
    
    /**Process(2) Calc the total sales (all total price) of all orders by customer in year of 2022,2023,2024**/
    public static void totalSales() throws Exception { //calc the total sales of all order by customer in year of 2022,2023,2024
        System.out.println("\f\tZUSCOFFEE SALES ");
        System.out.println("\n**********Total Sales**********");
        LinkedList list= readFile(fileToRead());
        ZusCoffee z = (ZusCoffee) list.getFirst();
        double totalSale2022 = 0.0;
        double totalSale2023 = 0.0;
        double totalSale2024 = 0.0;
        while(z != null){
            if(z.getDate().contains("2022")){
                totalSale2022 += z.calcTotalPrice();
            }
            else if(z.getDate().contains("2023")){
                totalSale2023 += z.calcTotalPrice();
            }
            else if(z.getDate().contains("2024")){
                totalSale2024 += z.calcTotalPrice();
            }
            else 
                System.out.println("Invalid input. Please try again.");
            z = (ZusCoffee) list.getNext();
        }
        System.out.println("Total Sales 2022: RM" + String.format("%.2f",totalSale2022));
        System.out.println("Total Sales 2023: RM" + String.format("%.2f",totalSale2023));
        System.out.println("Total Sales 2024: RM" + String.format("%.2f",totalSale2024));
    }
    
    /**Process(3) Count the number of customers who bought beverages from beverageList**/
    public static void countBeverage() throws Exception {//count the num of customer who bought beverage from BeverageList
        System.out.println("\f\tZUSCOFFEE BEVERAGE LIST ");
        System.out.println("\n**********Count Beverages**********");
        LinkedList list= readFile("BEVERAGE.txt");
        ZusCoffee z = (ZusCoffee) list.getFirst();
        int count = 0;
        
        while(z != null){
            count++;
            z = (ZusCoffee) list.getNext();
        }
        System.out.println("Number of customer who bought beverage from BeverageList: " + count);
    }
    
    /**Process(4) Find the order with the highest total price ordered in each food and beverage list**/
    public static void highestTotalPrice() throws Exception {//find the order with highest totalPrice ordered
        System.out.println("\f\tZUSCOFFEE ORDER PRICE ");
        System.out.println("\n**********Total Price**********");
        LinkedList list= readFile(fileToRead());
        ZusCoffee z = (ZusCoffee) list.getFirst();
        double highest = 0.0;
        ZusCoffee zHighest = new ZusCoffee();
    
        while (z != null){
            if(z.calcTotalPrice() > highest){
                zHighest = z;
                highest = z.calcTotalPrice();
            }
            z = (ZusCoffee) list.getNext();
        }
        System.out.println("Order with highest total price: " + "\n" + zHighest.toString());
    }
}