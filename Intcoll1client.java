//**********************************************************
// FILE: NAME   : intcoll1client.java
// DESCRIPTION  : This is a client of class Intcoll1.
//**********************************************************

import java.util.*;

public class Intcoll1client {

    public static final int SENTINEL = 0; //this signifies when the user input for array elements end.

    public static void main(String[] args) {
    ////////////////////////////////////////////////////////////////////////////  
    //  Variables
    ////////////////////////////////////////////////////////////////////////////
        int value; //create the variable value; this used used to store keyboard input
    //////////////////////////////////////////////////////////////////////////// 
    //  Objects
    ////////////////////////////////////////////////////////////////////////////
        Scanner keyboard = new Scanner(System.in);
        Intcoll1 P = new Intcoll1(1), N = new Intcoll1(1), L = new Intcoll1(1);
    //////////////////////////////////////////////////////////////////////////// 
    //  Ask for user input
    ////////////////////////////////////////////////////////////////////////////
        /*
    Until the value zero is entered, keep asking for user input
    
    Basically the values stored in each array are:
        Array P:    All postive inputs
        Array N:    All negative inputs
        Array L:    Integers that's last occurance was postive
         */
        System.out.println("Enter an integer to be inserted or 0 to quit:");
        value = keyboard.nextInt();
        while (value != SENTINEL) { //if the seninal value 0 hasn't been entered, keep managing the arrays
            if (value > 0) { //if the input is positive, put the number in these arrays
                P.insert(value);
                L.insert(value);
            } else {                //if the input is negative
                N.insert(-value);   //store the nagative number in this array
                L.omit(-value);     //and remove it from  this one
            }
            System.out.println("Enter next integer to be inserted or 0 to quit:");
            value = keyboard.nextInt();
        }

    ////////////////////////////////////////////////////////////////////////////
    //  Various Tests (these are really self-explanitory); I also reformatted the outputting for no reason
    ////////////////////////////////////////////////////////////////////////////
        System.out.println("\n"
                + "00000000000000000000000000000000000000000000000000\n"
                + "Sentnal value inputted. User input terminated\n"
                + "00000000000000000000000000000000000000000000000000");
    
        System.out.println("\n"
                + "##################################################\n"
                + "The values in collection P are:\n"
                + "##################################################");
        P.print();
        System.out.println("\n"
                + "##################################################\n"
                + "The values in collection N are:\n"
                + "##################################################");
        N.print();
        System.out.println("\n" 
                + "##################################################\n"
                + "The values in collection L are:\n"
                + "##################################################");
        L.print();
        if (P.equals(N)) {
            System.out.println("\n"
                    + "00000000000000000000000000000000000000000000000000\n"
                    + "P and N are equal.\n"
                    + "00000000000000000000000000000000000000000000000000");
        } else {
            System.out.println("\n"
                    + "00000000000000000000000000000000000000000000000000\n"
                    + "P and N are NOT equal.\n"
                    + "00000000000000000000000000000000000000000000000000");
        }
        Intcoll1 A = new Intcoll1();
        A.copy(L);
            System.out.println("\n" 
                + "##################################################\n"
                + "The values in the copy of L are:\n"
                + "##################################################");
        A.print();
    }
}
