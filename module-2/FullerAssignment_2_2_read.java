//Brett Fuller
//CSD-420 Assignment 2.2 - read from file
//3/29/25
import java.io.*;

//Program that reads a random access file and prints the output. We know that the file is in the format of 5 ints,
// followed by five doubles and repeats until the end of the file so we know to follow that template when
//scanning in each line of the file.
class FullerAssignment_2_2_read {
    public static void main(String[] args) {
        try{
            //create new random access file object associated with a local file
            RandomAccessFile raf = new RandomAccessFile("Fullerdatafile.dat", "r");
            //Start at the beginning of the document
            raf.seek(0);
            //counter used to switch from ints to doubles
            int i = 0;
            //loop through full file
            while (raf.getFilePointer() < raf.length()){
                //handles elements 0-4 in any series as ints
                if (i < 5){
                    System.out.println(raf.readInt());
                }
                //hanels elements 5-9 in any sequence as doubles
                else{
                    System.out.println(raf.readDouble());
                }
                //iterates i
                i++;
                //resets iterator baco to 0 to repeat sequence above
                if (i == 10){
                    i = 0;
                }

            }

        }
        //catches and prints stack trace of ioerrors
        catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
}