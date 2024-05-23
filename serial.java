//import java.util.concurrent.atomic.AtomicInteger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

public class serial {
    //This does everything but in serial just for testing purposes
       

    public static Integer[] arrayLetters = new Integer[26];
    //public static int cores = Runtime.getRuntime().availableProcessors();
    
    public static byte[] parseInputFile(String inputFile) throws IOException {
        File file = new File(inputFile);
        try (FileChannel fileChannel = new FileInputStream(file).getChannel()) {
        byte[] barray = new byte[(int) file.length()];
        ByteBuffer bb = ByteBuffer.wrap(barray);
        fileChannel.read(bb);
    return barray;
    }
}

    public static void increment(byte[] input){
        for(byte c: input){    
            // check if newline
            if (c == 10){
                break;
            }

            int index = c - 97;
            //System.out.println(c);
            
            // at that index corresponding to the letter increase the counter.
            arrayLetters[index]++;
        
        }
            for(char c ='a'; c <= 'z'; c++){
                int index = c-97;
                //Integer indexTrue = arrayLetters[index];
                if(arrayLetters[index] != 0){
                
                    System.out.println(c + ": " + arrayLetters[c-97]);
            
                }
            }
           // System.out.println("WE ARE USING " + cores + " this time running through it");



    }
    public static void main(String []args){
        for (int i =0; i < arrayLetters.length;i++){
            arrayLetters[i] = 0;
             //.set(0);
        }

        try{
            // create the file
            String filename = args[0];    
            byte[] arrayName = parseInputFile(filename);
           
            //int totalLen = arrayName.length;

            increment(arrayName);

        }

        catch(IOException e){
            throw new RuntimeException(e);
                }  

    }








}
