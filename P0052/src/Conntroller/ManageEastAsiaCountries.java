
package Conntroller;

import Model.EastAsiaCountries;
import View.userInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class ManageEastAsiaCountries {
    private final Scanner sc = new Scanner(System.in);
    private final String NAME = "[A-Za-z\\s]+";
    private final ArrayList<EastAsiaCountries> eam = new ArrayList<>();
    private final userInterface ui = new userInterface();
    
    private int checkInteger(String mess, int min, int max) {
        String input;
        int n;
        while(true) {
            System.out.println(mess);
            input = sc.nextLine();
            if(input.isEmpty()) {
                System.err.println("Input can not be empty.");
            }
            else {
                try{
                    n = Integer.parseInt(input);
                    if(n < min || n > max) {
                        System.err.println("Only Integer in range " + min + " to " + max);
                        continue;
                    }
                    return n;
                }
                catch(NumberFormatException e) {
                    System.err.println("Invalid Input Format. Only Integer is permitted.");
                }
            }
        }
    }
    
    private float checkFloat(String mess, float min, float max) {
        String input;
        float n;
        while(true) {
            System.out.println(mess);
            input = sc.nextLine();
            if(input.isEmpty()) {
                System.err.println("Input can not be empty.");
            }
            else {
                try{
                    n = Float.parseFloat(input);
                    if(n < min || n > max) {
                        System.err.println("Only real number in range " + min + " to " + max);
                        continue;
                    }
                    return n;
                }
                catch(NumberFormatException e) {
                    System.err.println("Invalid Input Format. Only Real Number is permitted.");
                }
            }
        }
    }
    
    private boolean checkLimitList(){
        System.err.println("The List is full of 11 Countries.");
        return eam.size() <= 11;
    }
    
    private String checkValidName(String mess) {
        String input;
        while(true) {
            System.out.println(mess);
            input = sc.nextLine();
            if(input.isEmpty()) {
                System.err.println("The input can not be empty.");
            }
            else{
                if(input.matches(NAME)) {
                    return input;
                }
                else System.err.println("Invalid Input Format. Only letters and blanks are permitted.");
            }
        }
    }
    
    private boolean checkEmpty() {
        return eam.isEmpty();
    }
    
    private EastAsiaCountries checkExistName(String name) {
        for(int i = 0; i < eam.size(); ++i) {
            if(eam.get(i).getCountryName().equals(name)) {
                return eam.get(i);
            } else {
            }
        }
        return null;
    }
    
    private EastAsiaCountries checkExistCode(String code) {
        for(int i = 0; i < eam.size(); ++i) {
            if( eam.get(i).getCountryCode().equals(code)) {
                return eam.get(i);
            } else {
            }
        }
        return null;
    }
    
    private void print(EastAsiaCountries e) {
        System.out.printf("%-10s%-20s%-20s%-20s",e.getCountryCode(), e.getCountryName(), e.getTotalArea(), e.getCountryTerrain());
        System.out.println("");
    }
    
    private void addInfo() {
        String code;
        String name;
        while(true) {
            code = checkValidName("Enter Code of Country: ");
            name = checkValidName("Enter Name of Country: ");
            if(checkExistCode(code) != null && checkExistName(name) != null) {
                System.err.println("Contry code and name is existed.");
                continue;
            }
            break;
        }
        float area = checkFloat("Enter the Total Area: ", Float.MIN_VALUE, Float.MAX_VALUE);
        String terrain = checkValidName("Enter Terrain of Country: ");
        if(checkLimitList()) {
            eam.add(new EastAsiaCountries(terrain, code, name, area));
            System.out.println("Information added successfully.");
        }          
    }
    
     private EastAsiaCountries getLast() {
         if(!checkEmpty()) {
             return eam.get(eam.size()-1);
         }
         else{
             System.err.println("The List is Empty.");
             return null;
         }
    }
    
    private void display( EastAsiaCountries eac) {
        System.out.printf("%-10s%-20s%-20s%-20s\n","Code","Name","Total Area","Terrain");
        print(eac);
    }
    
    private void searchByName(String name) {
        name = checkValidName("Enter Country Name you want to search: ");
        if(checkExistName(name) != null) {
            System.out.println(checkExistName(name));
        }
        else System.err.println("The Country Name not found.");
    }
    
    private void sortByName() {
        ArrayList<EastAsiaCountries> sortedList = new ArrayList<>();
        sortedList.addAll(eam);
        Collections.sort(sortedList, (e1, e2) -> e1.getCountryName().compareTo(e2.getCountryName()));

        System.out.println("After Sorted by Name: ");
        System.out.printf("%-10s%-20s%-20s%-20s\n","Code","Name","Total Area","Terrain");
        for(EastAsiaCountries e : sortedList) {
            print(e);
        }
    }
    
    public void run() {
        int choice;
        while(true) {
            ui.menu();
        choice = checkInteger("Enter your choice: ", 1, 5);
        switch(choice){
            case 1: 
                addInfo();
                break;
            case 2: 
                display(getLast());
                break;
            case 3:
                String name = checkValidName("Enter Country Name to search: ");
                searchByName(name);
            case 4:
                sortByName();
                break;
            case 5:
                return;
            }
        }
    }
}        
