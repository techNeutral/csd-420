//Brett Fuller
//CSD-420 Assignment 5.2
//4/12/25

import java.util.*;
import java.io.*;

//The purpose of this assignment is to utilize collections of some variety, I chose sets, to grab unique words from
//a text document and then display them in order and then reverse the order. I created a text file and then imported it.
// I was not sure if the assignment wanted us to display the words chronologically, as in the order they appeared
// in the file. Or if the assignment wanted them alphabetically so I wrote code to account for both orders. This
//also allowed me to utilize both LinkedHashSets which maintained chronological order and TreeSets which did a great job
//with alphabetical.
public class FullerAssignment_5_2 {
    public static void main(String[] args) {
        //import my file
        java.io.File file = new java.io.File("collection_of_words.txt");
        //Create a linkedHashSet and a TreeSet to hold the worsds in.
        LinkedHashSet<String> linkedHSet = new LinkedHashSet<String>();
        TreeSet<String> tSet = new TreeSet<String>();
        //Created this counter so I can see how many total words were in the document to compare against unique words.
        int i = 0;
        try{
            //create a scanner to read the file
            Scanner reader = new Scanner(file);
            //loop through and read the file
            while(reader.hasNext()){
                //capture each word in the file
                String word = reader.next();
                //convert each word to lowercase to alleviate case discrepancies and add it to the two sets
                linkedHSet.add(word.toLowerCase());
                tSet.add(word.toLowerCase());
                i++;
            }
            //close the file
            reader.close();
        }
        //exception handling
        catch(IOException ioe){

            System.out.println("IOException has been thrown. - 2");
        }
        //Shows original file word count
        System.out.println("Original file has " + i + " words!");

        //Displays unique words in the order they appeared in the file descriptions describe the order they are displayed.
        //linkedHashSets for chronological and TreeSets for alphabetical.
        System.out.println("\nPrinting " + linkedHSet.size() + " words in chronological order");
        System.out.println(linkedHSet);
        System.out.println("\nPrinting " + linkedHSet.size() + " words in reverse chronological order");
        System.out.println(linkedHSet.reversed());
        System.out.println("\nPrinting " + tSet.size() + " words in alphabetical order");
        System.out.println(tSet);
        System.out.println("\nPrinting " + tSet.size() + " words in reverse alphabetical order");
        System.out.println(tSet.reversed());
    }
}