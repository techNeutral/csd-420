//Brett Fuller
//CSD-420 Assignment 6.2
//4/12/25

import java.util.*;

//The intent of this assignment is to implement a bubble sort using both the comparable and comparator methods.
public class FullerAssignment_6_2 {
    //Comparable method to run bubble sort
    public static <E extends Comparable<E>> void bubbleSort(E[] list){
        System.out.println("Comparable method for Bubble Sort");
        //first iterator loops through the list
        for(int i = 1; i < list.length; i++){
            //second iterator loops through the list - the first iterator which counts the number of iterations
            for(int j = 0; j < list.length - i; j++){
                //compares the two elements if the first elemnt is larger than the second element it swaps them
                if(list[j].compareTo(list[j+1]) > 0){
                    E temp = list[j];
                    list[j] = list[j+1];
                    list[j+1] = temp;
                    //prints the elements in each run
                    for(int k = 0; k < list.length; k++){
                        System.out.print(list[k] + ", ");
                    }
                    System.out.println();
                }
            }
        }
    }
    //I was having an issue with abstract classes and not being able to instantiated. and I ran out of time to keep
    //testing. I will continue to work on this assignment because I need to understand how to make it work in this
    //format and will likely want to turn it in for module 12.
    // I was also unable to test my logic so there could be logical errors in here.


    public static void main(String[] args) {
        //create array to use with the first bubblesort method - comparable
        Integer[] integerArray = new Integer[7];
        integerArray[0] = 6;
        integerArray[1] = 2;
        integerArray[2] = 7;
        integerArray[3] = 1;
        integerArray[4] = 5;
        integerArray[5] = 3;
        integerArray[6] = 4;
        //integerArray.add(12);
        //integerArray.add(4);
        //integerArray.add(7);
        //integerArray.add(1);
        bubbleSort(integerArray);

        //Create array and use second bubble sort method
        Integer[] integerArray2 = new Integer[7];
        integerArray2[0] = 6;
        integerArray2[1] = 2;
        integerArray2[2] = 7;
        integerArray2[3] = 1;
        integerArray2[4] = 5;
        integerArray2[5] = 3;
        integerArray2[6] = 4;
        //I was having an issue with abstract classes and not being able to instantiated. and I ran out of time to keep
        //testing. I will continue to work on this assignment because I need to understand how to make it work in this
        //format.
        bubbleSort(integerArray2, new MyComparator());
        System.out.println();
    }
}
class MyComporator<E>{

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator){
        System.out.println("Comparator method for Bubble Sort");
        //same logic as the first method but in this case we have to explicitly define the comparison
        for(int i = 1; i < list.length; i++){
            for(int j = 0; j < list.length - i; j++){
                //if first element is greater than the second swap places
                if(comparator.compare(list[j], list[j+1]) > 0){
                    E temp = list[j];
                }
                //if they are equal continue to next element
                else if(comparator.compare(list[j], list[j+1]) == 0){
                    list[j] = list[j+1];
                }
                //if first element is smaller than the next element leave in their current order and continue to next element
                else if(comparator.compare(list[j], list[j+1]) == 0){
                    list[j] = list[j+1];
                }
                //Print out current list order
                for(int k = 0; k < list.length; k++){
                    System.out.print(list[k] + ", ");
                }
                System.out.println();
            }
        }
    }
}