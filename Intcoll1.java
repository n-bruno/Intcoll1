//*********************************************************************
// FILE NAME    : Intcoll1.java
// DESCRIPTION  : This file contains the class Intcoll1.
//*********************************************************************

import java.util.*;

public class Intcoll1 {

////////////////////////////////////////////
//  Variables
////////////////////////////////////////////
    private int[] c;

    /*this creates the array "c" that's private to this class.
     */

////////////////////////////////////////////
//  Constructors
////////////////////////////////////////////
    /*
    Here I present you two constructors, whenever you create an object of this
    class, you'll always run these.

    If no parameters are passed you'll run the first one. If an integer, you'll run the second one.

    If no parameters are passed the default array size is 500 and if one is passed,
    the size will be whatever was passed

    One major thing to note is that " + 1". This is here for various methods
    like "insert". Zero signifies the end of the array; however, to compensate
    for this for a full array, an extra member is added.

    So for when the number 2 is passed the array may look like this with + 1
    55 44 0

    If there was no +1 added
    55 44

    For the latter, our program would still think there are other elements beyond
    44 and read out of bounds, resulting in an error.

    By the way, we have "c[0] = 0;" to say our array is empty.
     */
    public Intcoll1() {
        cycle(500);
    }
    
    private void cycle (int i){
        c = new int[i + 1];
        c[0] = 0;
    }

    public Intcoll1(int i) {
        cycle(i);
    }

////////////////////////////////////////////
//  Copy
////////////////////////////////////////////
    public void copy(Intcoll1 obj) {
        
        /*
        First we check if the two objects have the same pointer, 
        if they do, they are already equal. If we execute the code we
        will delete the information and copy over a blank array.
        
        However, from what I see, "c = new int[obj.c.length];" is a bit
        unnecessary. We already have the element 0 to tell the program the end
        of the array, so I am curious why it's here. I see no errors from removing it. 
        I assume it's here to free up memory? 
        */
        if (this != obj) { 
            c = new int[obj.c.length]; //overwrite array c
            int j = 0;
            while (obj.c[j] != 0) {
                c[j] = obj.c[j];
                j++;
            }
            c[j] = 0; //insert the zero since we didn't copy it over during the copying process
        }
    }

////////////////////////////////////////////
//  Belongs
////////////////////////////////////////////
    public boolean belongs(int i) {
        int j = 0;
        
        /*
        The while loop will terminate when c[]j]
            * is 0, indicating the end of the list
            * is i
        */
        
        while ((c[j] != 0) && (c[j] != i)) {
            j++;
        }
        return ((i > 0) && (c[j] == i));
        /*
        Some interesting code here. It's very too the point and efficient.
        
        Firstly, the method will always return false when i is nagative.
        
        Also we will get false if c[j] is not equal to i.
        The only way for this statement to be false is if we exited the while
        loop because c[j] = 0, or in other words didn't find an instance
        of i in the array.
        */
    }
////////////////////////////////////////////
//  Insert
////////////////////////////////////////////
    public void insert(int i) {
        if (i > 0) { //check if the value is positive, if not, don't insert anything
            int j = 0;
            /*
            This while loop will keep incrementing j if both are true:
                * c[j] is 0, this signifies the end of the elements stored
                * c[j] is the value we want to insert
             */

            while ((c[j] != 0) && (c[j] != i)) {    //this code has been repeated a lot, perhaps create a method for this
                j++;
            }

            if (c[j] == 0) {
                if (j == c.length - 1) {                    
                    /*
                    If the array c is full, we'll have to increase its size.
                    Due to the nature of arrays, we cannot increase the size on
                    the fly, instead we have to overwrite it with an array 
                    of a larger size.
                    
                    To prevent data loss, c will have all of its elements stored
                    in newArray before being overwritten. 
                    
                    After c is overwritten, elements from newArray will be put
                    back into array c that is one index longer. 
                    */
                    int[] newArray = new int[c.length + 1]; //create new array for temporary storage
                    for (int aa = 0; aa < c.length; aa++)   //store everything in c in newArray
                        newArray[aa] = c[aa];
                    c = new int[c.length + 1];              //overwrite c with a larger size
                    for(int aa = 0; aa < c.length; aa++)    //store everything from newArray into c
                        c[aa] = newArray[aa];
                }
                c[j] = i;       //overwrite index j with the value we want inserted
                c[j + 1] = 0;   //make the index after index j have a value of 0
                                //to show this is the end off the array
                
                /*System.out.println("$$$$$$$$$");      //for debug purposes
                for(int aa = 0; aa < c.length; aa++)
                    System.out.println(c[aa]);*/
            }
        }
    }
////////////////////////////////////////////
//  Omit
////////////////////////////////////////////
    /*
    In here we delete an element in c of the value i
    */
    public void omit(int i) {
        if (i > 0) { //if i is positive
            int j = 0;
            /*
            Finds the index where i is and stores it in j.
            If there isn't an instance of i, then j is the index where it's 0
            
            if j is 0 we skip over the the deletion process as i isn't in the array
            so we can't remove it
            */
            while ((c[j] != 0) && (c[j] != i)) { 
                j++;
            }

            if (c[j] == i) { //check if c[j] is i (the value we want to remove)
                int k = j + 1;
                while (c[k] != 0) { //Here we find how many elements we are storing
                    k++;            //We do this a lot, why not modify the get_howmany method to avoid redundancy?
                }
                
                c[j] = c[k - 1]; //here we overwrite the element at c[j] with the last element
                c[k - 1] = 0;    //we overwrite the element we copied over with zero to avoid duplicates
            }                       
        }
    }
////////////////////////////////////////////
//  Howmany
////////////////////////////////////////////
    public int get_howmany() {
        /*
        In this method we calculate the size of the array by incrementing until
        the while loop reaches "0", the end of elements.
        
        For what it's worth, this code can be made shorter
        by getting rid of j. It's not needed, but j does does make things
        make more sense for the reader
        
        for a shorter way:
            int howmany;
            for(howmany = 0; c[howmany] != 0, howmany++){}
            return howmany;
        */

        int j = 0, howmany = 0;
        while (c[j] != 0) {
            howmany++;
            j++;
        }
        return howmany;
    }
////////////////////////////////////////////
//  Print
////////////////////////////////////////////
    public void print() {
        /*
        This prints all the elements in the object's array with a while loop.
        */
        int j = 0;
        System.out.println();
        while (c[j] != 0) {
            System.out.println(c[j]);
            j++;
        }
    }
////////////////////////////////////////////
//  Equals
////////////////////////////////////////////
    public boolean equals(Intcoll1 obj) {
        int j = 0;
        boolean result = true;
        /*
        basically, once we reach the end of the elements
        (find an element that's 0) or get a result of true, we exit from the loop
        
        The boolean "results" represents whether or not we found an index that
        doesn't match. If the boolean is false, we found an index that doesn't match.
        Since we already found out the arrays aren't eaqual, no further comparisons are needed.
        
        We also exit the loop once we reach the end of the elements. In this case,
        results is true, they're equal in the sense their elements are equivalent.
        
        For the loop below we compare array c with the object from the parameter's
        */
        while ((c[j] != 0) && result) { 
            result = obj.belongs(c[j]); 
            j++;                        
        }                               
        j = 0;
        
        /*We compare the parameter's array with c.
        
        There are two big problems with the code in this method:
            
        1) Is there a good reason why we are comparing again? Not really.
        Another check is required in the case that two arrays have different lengths.
        This can all be avoided by comparing lengths beforehand.
        If their lengths aren't the same, they aren't equal.
        
        2) There isn't a pointer comparison. If two arrays pointers are the same,
        they are equal, there's no point in comparing them if the have the same pointer
        */
        while ((obj.c[j] != 0) && result) { 
            result = belongs(obj.c[j]);     
            j++;                            
        }                                   
        return result;
    }
}
