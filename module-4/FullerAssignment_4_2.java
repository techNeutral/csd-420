//Brett Fuller
//CSD-420 Assignment 4.2
//4/4/25

//This class is designed to showcase the differnce in speed between the get(index) method and an iterator with a linked
//list. In this program we see that the get(index) method takes significantly longer than using an iterator. With a little
//research I learned that the reason for this is that because of how a linked list works each time you use the get(index)
//method the program has to start at the first element of the LinkedList and follow the list until it gets to the correct
//element and worse case leaves you with O(n^2). Whereas with an iterator we stoep through the list only one time
// calling the next element in the list and displaying it's value O(1) for the next element and O(n) for the whole list.
// https://medium.com/@KosteRico/stop-using-for-loops-for-java-linkedlist-use-listiterator-instead-8263e3f63b64

import java.util.Random;
import java.util.LinkedList;
import java.util.Iterator;
class FullerAssignment_4_2 {
    //Method that accepts a linked list full of integers and uses both a for loop and an iterator to access all elements
    //than prints the elapsed time to traverse all elements.
    static void timers(LinkedList<Integer> list) {
        System.out.println();
        //initialize timer by noting start time
        long startTime = System.nanoTime();
        int length = list.size();
        //loop through list and get value from index
        System.out.println("looping through " + length + " elements using the get(index) method:");

        for (int i = 0; i < length; i++) {
            list.get(i);
        }
        //note end time
        long endTime = System.nanoTime();
        //subtract times to get elapsed time
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ns");
        System.out.println();
        System.out.println("looping through " + length + " elements using an iterator:");
        //create an iterator
        Iterator<Integer> iterator = list.iterator();
        //note start time
        startTime = System.nanoTime();
        //iterate through each element
        while (iterator.hasNext()) {
            iterator.next();
        }
        //note end time
        endTime = System.nanoTime();
        //calculate elapsed time
        elapsedTime = endTime - startTime;
        System.out.println("Elapsed time: " + elapsedTime + " ns");
        System.out.println();
    }
    public static void main(String[] args) {
        Random random = new Random();
        //create initial list with 50,000 elements
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 50000; i++) {
            list.add(random.nextInt());
        }
        //results for a linkedlist with 50,000 elements
        //looping through 50000 elements using the get(index) method:
        //Elapsed time: 1106693667 nanoseconds
        //looping through 50000 elements using an iterator:
        //Elapsed time: 1652375 nanoseconds
        timers(list);
        //Add an additional 450000 elements by starting at element 500000.
        for (int i = 50000; i < 500000; i++) {
            list.add(random.nextInt());
        }
        //results for a linkedlist with 500,000 elements
        //looping through 500000 elements using the get(index) method:
        //Elapsed time: 113529006708 nanoseconds (a very long time, I was begining to wonder if it was broken)
        //looping through 500000 elements using an iterator:
        //Elapsed time: 6569500 nanoseconds
        timers(list);
    }
}