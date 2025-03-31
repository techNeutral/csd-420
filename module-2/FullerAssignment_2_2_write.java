//Brett Fuller
//CSD-420 Assignment 2.2 - write to file
//3/29/25
import java.io.*;
import java.util.Random;

//Program that writes 5 random ints and 5 random doubles to a Rndom Acces File. If the file exists
//the program will append data to it and if not the program will create a new file.
class FullerAssignment_2_2_write{
    public static void main(String[] args) {

        try {
            //create new random access file object associated with a local file
            RandomAccessFile raf = new RandomAccessFile("Fullerdatafile.dat", "rw");
            //Sets cursor to end of file so that you don't write over existing data
            raf.seek(raf.length());
            //New random number generator
            Random random = new Random();

            //create a series of 5 integers
            for (int i = 0; i < 5; ++i) {

                raf.writeInt((int) (random.nextInt()));

            }
            //create a series of 5 doubles
            for (int i = 0; i < 5; ++i) {

                // Note a larger size
                raf.writeDouble(random.nextDouble(-1000000, 1000000));
            }
        }
        //catches and prints stack trace of ioerrors
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}