//Brett Fuller
//Brett Fuller
//CSD-420 Assignment 8.2
//4/27/25


/*
Create a class titled [your first="" name="" here=""] ThreeThreads.
In this class, you are to use three threads to output three types of characters to a text area for display.
In the first thread, you are to output random letter characters such as a, b, c, d …
In the second thread, you are to output random number digits such as 0, 1, 2, 3, 4, 5, 6, 7, 8, 9.
In the third thread, you are to output random characters such as !, @, #, $, %, &, *
Display a minimum of 10,000 for each of the three sets.
Write test code that ensures all methods function correctly.
 */

//the purpose of this assignment is to utilize multiple threads, and illustrate that the processing is occurring at the
//same time rather than have one loup finish before the other loops complete.

import java.util.Random;
class FullerAsssignment_8_2 {
    public static void main(String[] args) {
        //Create arrays with the different types of chars to output.
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] specials = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '<', '>', ',', '.', '?'};
        //create three different instances of my class to assign to three different different threads each with a different
        //array of elements to output.
        BrettThreeThreads brettRunnable1 = new BrettThreeThreads(numbers);
        BrettThreeThreads brettRunnable2 = new BrettThreeThreads(letters);
        BrettThreeThreads brettRunnable3 = new BrettThreeThreads(specials);

        //Assign each instance of BrettThreeThreads to its own thread
        Thread thread1 = new Thread(brettRunnable1);
        Thread thread2 = new Thread(brettRunnable2);
        Thread thread3 = new Thread(brettRunnable3);

        //start all 3 threads
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
//my class that implements runnable which accepts an array of chars and overrides run
class BrettThreeThreads implements Runnable {
    char[] outputChars;
    public BrettThreeThreads(char[] outputChars) {
        this.outputChars = outputChars;
    }

    @Override
    public void run(){
        //code that loops an obscene number of times and ouputs a random char from the elements passed.
        Random random = new Random();
        int randomIndex = 0;
        int range = outputChars.length;
        for(int i = 0; i < 10500; i++){
            //get candom element index
            randomIndex = random.nextInt(range);
            //output char that corresponds with the random index.
            System.out.print(outputChars[randomIndex]);
        }
    }
}