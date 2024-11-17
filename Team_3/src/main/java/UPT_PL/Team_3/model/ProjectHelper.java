package UPT_PL.Team_3.model;

import java.util.Scanner;

public class ProjectHelper {
	/** Static variable Scanner **/
	private static Scanner input = new Scanner(System.in);
	
	
	/**
     * Input string
     * 
     * @param message which will output during the ask
     * @return String
     */
    public static String inputStr(String message) {
        String line;
        System.out.println(message);
        line = input.next();
        while (line.equals("")) {
            System.out.println("Empty string!");
            System.out.println(message);
            line = input.next();
        }
        return line;
    }

    /**
     * Input Double
     * 
     * @param massage which will output during the ask
     * @return double
     */
    public static double inputDouble(String message) {
    	double line;
        System.out.println(message);
        line = input.nextDouble();
        while (line <= 0) {
            System.out.println("Value less or equal 0");
            System.out.println(message);
            line = input.nextDouble();
        }
        return line;
    }
    
    /**
     * Input Integer
     * 
     * @param massage which will output during the ask
     * @return integer
     */
    public static int inputInt(String message) {
        int line;
        System.out.println(message);
        line = input.nextInt();
        while (line < 0) {
            System.out.println("Value less or equal 0");
            System.out.println(message);
            line = input.nextInt();
        }
        return line;
    }
    /**
     * printTypes
     */
    public static void printTypes() {
    	System.out.println("(1) - All countries");
    	System.out.println("(2) - Country");
    	System.out.println("(3) - Product");
    }

    
    /**
     * showMenuInfo
     */
    public static void showMenuInfo() {
    	System.out.println("\n******************************");
    	System.out.println("(0) - Exit");
    	System.out.println("(1) - Add product");
    	System.out.println("(2) - Add country");
    	System.out.println("(3) - Add transport");
    	System.out.println("(4) - Add logistics site to country");
    	System.out.println("(5) - Add product to country");
    	System.out.println("(6) - Add logistics supply chain");
    	System.out.println("(7) - New calculation for logistics route");
    	System.out.println("(8) - Save logistics routes in DB");
    	System.out.println("(9) - Print logistics processor");
    	System.out.println("(10) - Print Products");
    	System.out.println("(11) - Print Logistics Supply Chains");
    	System.out.println("(12) - Print Countries");
    	System.out.println("(13) - Print Transports");
    	System.out.println("(14) - Delete");
    	System.out.println("\n******************************");
    }
	
    
    /**
     * showDeleteMenuInfo
     */
    public static void showDeleteMenuInfo() {
    	System.out.println("\n******************************");
    	System.out.println("(1) - Delete calculation");
    	System.out.println("(2) - Delete logistics supply chain");
    	System.out.println("(3) - Delete product");
    	System.out.println("(4) - Delete country");
    	System.out.println("(5) - Delete products by country");
    	System.out.println("(6) - Delete logistics site");
    	System.out.println("(7) - Delete transport");
    	System.out.println("\n******************************");
    }
}
