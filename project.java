import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

//import java.security.spec.ECFieldF2m;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.concurrent.*;



public class project {

    //Constants
    public static AtomicInteger[] arrayLetters = new AtomicInteger[26];
    //public static int cores = Runtime.getRuntime().availableProcessors();
    public static int cores = 8; // Runtime.getRuntime().availableProcessors();
    
    //public static AtomicInteger counter = new AtomicInteger(0);


   
        
    
    // I/O handling
    // make this guy a double array
    public static byte[][] parseInputFile(String inputFile, int threads) throws IOException {
        File file = new File(inputFile);
        try (FileChannel fileChannel = new FileInputStream(file).getChannel()) {
            byte[][] barray = new byte[threads][(int) file.length() / threads +1];
            ByteBuffer[] bb = new ByteBuffer[threads];
            for( int i =0; i < threads; i++){
                bb[i] = ByteBuffer.wrap(barray[i]);
            }
            fileChannel.read(bb);
            return barray;
        }
    }
    
    
   
   //Serial addition that will be used by multiple threads
    public static void increment(byte[] segement, int FileLength){
        
            for(byte c: segement){    
                    // check if newline
                    if (c == 10){
                        break;
                    }

                    int index = c - 97;
                    //System.out.println(c);
                    
                    // at that index corresponding to the letter increase the counter.
                    arrayLetters[index].incrementAndGet();
                
                }

            }

    // should do the final summation overall of each thread
    public static void finalSummation(AtomicInteger[] arrayLetters){
            for(char c ='a'; c <= 'z'; c++){
                int index = c-97;
                AtomicInteger indexTrue = arrayLetters[index];
                if(indexTrue.get() != 0){
                
                    System.out.println(c + ": " + arrayLetters[c-97]);
            
                }
            }
            System.out.println("WE ARE USING " + cores + " this time running through it");
    }   
        
   // public static void serial(byte[] segement, AtomicInteger[] arrayLetters){
     //   increment(segement, 0);
       // finalSummation(arrayLetters);
        //System.exit(0);
    //}
    
    
    public static void main(String []args){
        // set each thing to be 0
        for (int i =0; i < arrayLetters.length;i++){
            arrayLetters[i] = new AtomicInteger();
             //.set(0);
        }
        
        //multi-threading
        try{
            // create the file
            String filename = args[0];    
            byte[][] arrayName = parseInputFile(filename, cores);
           
            int totalLen = arrayName.length;
            //int threshold = 10;
            // if it's just faster to do it in serial it'll skip everything else
        //     if( totalLen < threshold){
        //         for(int i = 0; i < cores; i++){
              
        //         final byte[] section = arrayName[i];
        //         serial(section, arrayLetters);

        //     }
        // }

            // split the array into the amount of cores
            ExecutorService executor = Executors.newFixedThreadPool(cores);
            
            // list of Futturer atomic ints?
            ArrayList<Future<?>> Futures = new ArrayList<Future<?>>(cores);
            

                        
            // go through the array of futures and make each run it
            for(int i = 0; i < cores; i++){

                // multi-threading part
                final byte[] section = arrayName[i];
                Future<?> future = executor.submit(() -> increment(section, totalLen));
                Futures.add(future);
            }

            // Wait for all the tasks to complete
            for ( Future<?> future: Futures){
                try{
                    future.get();
                }
                catch(InterruptedException | ExecutionException e){
                    
                }
            }

            finalSummation(arrayLetters);
            executor.shutdown();
        }         
        
        catch(IOException e){
            throw new RuntimeException(e);
        }   
    }

}    
    