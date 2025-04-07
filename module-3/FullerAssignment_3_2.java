//Brett Fuller
//CSD-420 Assignment 3.2
//4/4/25

//This program creates an ArrayList of random ints between 1 and 20, it then sends that array list to a method that takes
//all unique values from that arraylist and adds them to a new arraylist. The outputs for this assignment are really
//busy but it allows me to validate all data with test code as requested in the assignment.
import java.util.Random;
import java.util.ArrayList;
class FullerAssignment_3_2 {
    //required method that adds unique values to a new arraylist
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
        ArrayList<E> newList = new ArrayList<E>();
        //cor loop that adds new elements to the new array list
        int notAdded = 0;
        for(E e : list){
            if(!newList.contains(e)){
                newList.add(e);
            }
            else{
                //Added this to be extra sure that there were no numbers declared a duplicate that were not
                System.out.println(e + " is already in the new list");
                //tracked duplicates
                notAdded++;
            }
        }
        //print out total number of duplcates to make sure that all elements were handled
        System.out.print("\nThere were " + notAdded + " duplicates in the original list and ");
        //returns new arraylist
        return newList;
    }
    public static void main(String[] args) {
        Random random = new Random();
        //first array list
        ArrayList<Integer> originalList = new ArrayList<Integer>();
        //for loop to add random integers to the first arraylist
        for (int i = 0; i < 50; i++) {
            originalList.add(random.nextInt(20)+1);
        }
        //call remove duplicate method and assign the returned ArrayList to uniqueList
        ArrayList<Integer> uniqueList = removeDuplicates(originalList);
        //Print out the number of elements in the list and all values in unique list
        System.out.println("there are " + uniqueList.size() + " unique elements in the new unique list.");
        for (int i = 0; i < uniqueList.size(); i++) {
            System.out.println(uniqueList.get(i));
        }
    }
}