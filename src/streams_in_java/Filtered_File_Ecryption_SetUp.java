/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streams_in_java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author bhayu
 */
public class Filtered_File_Ecryption_SetUp {

    /**
     * @param args the command line arguments
     * 
     * CONSOLE BASED 
     * NON  UI
     * SINGLE THREADED SEQUENTIAL 
     * SPIRAL MODEL BASED
     * BLACK BOX TESTING
     * SINGLE CLASS
     * Batch File Encryption
     */
    
    
    private static String KEY;
    private static  String dir;
    public static void main(String[] args)throws IOException {
        // TODO code application logic here \\\
          int num;
          List<Path> paths;
          Scanner sc=new Scanner(System.in);
          System.out.println(" \n              -------WELCOME TO ENCRYPTCO PORTAL----        \n\n     Enter the directory path..............");
          dir=sc.nextLine();
         

                                                        
          
                                                     /* Files.isDirectory(path, options)
                                                    Files.exists(;, options);
                                                    Files.find(start, 0, matcher, options);
                                                    Files.isReadable(path);
                                                    Files.isWritable();
                                                    Files.list(dir);
                                                    Files.lines(path, cs);
                                                    Files.;
                                                    Files.size(path);
                                                    Files.createFile(path, attrs); */
            Path path=Paths.get(dir);
            if(Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS))
             {
                long count=Files.list(path).count();
                System.out.println("\n\n\n   Total Files are  :"+count+"\n");
                System.out.println("\n\n\nEnter the Number of  File To be Encrypted****** \nMust be greater than "+count);
                num=sc.nextInt();
                if(!(num>=count))
                {
                  System.out.println("\n Wrong Number Leaving portal");  
                  System.exit(0);
                }
                //Files.
                System.out.println("Do you want to encrypt or decrypt\n Press 1 to Encrypt and 2 to Decrypt\n");
                int flag=sc.nextInt();
                if(flag!=1&&flag!=2)
                {
                  System.out.println("\n Wrong Number Leaving portal");  
                  System.exit(0);  
                }
                meth(path,num, flag);
             }
                       /* Method ref  is very Imp*/
}
    public static int fileSize(String n) throws IOException
    {
        return (int)Files.size(Path.of(n));
    }
    
    
    public static void meth(Path path,int num,int flag) throws IOException
    {
         Files.list(path).sorted().limit(num).filter(Files::isRegularFile)  
                .filter(p -> p.getFileName().toString().endsWith(".txt")).collect(Collectors.toList()).forEach(p-> {
             try {
                 /*Means try can take multiple statements and operations on p variable or path*/
               
                 if(flag==2)
                 dec(p.toString());
                 else
                 enc(p.toString());
             } catch (IOException ex) {
                 Logger.getLogger(Filtered_File_Ecryption_SetUp.class.getName()).log(Level.SEVERE, null, ex);
             }
         });
                
             
    }
    
    public static void enc(String p) throws FileNotFoundException, IOException


                         ///COcept of error usig commas ,,,
    {
        if(Files.size(Path.of(p))<=10000000)
        {
                
        File f=new File(p);
        
        //  FileReader fr=new FileReader(f);
        FileInputStream fis=new FileInputStream(f);
        
        Encoder enc= Base64.getEncoder();
           //FileWriter ff=new FileWriter("h.txt");
          // ff.write();
        File ff=new File(p+"_Encrypted.txt");
        ff.setWritable(true);
       
        /* Data will e lost on same file */
        
        FileOutputStream fos=new FileOutputStream(ff);
        FileChannel ps;
        fos.write(enc.encode(fis.readAllBytes()));
        fis.close();
        fos.close();
        Files.lines(ff.toPath()).forEach(hh->System.out.println(hh));
        Files.delete(Path.of(p));
        }
              //  enc.encodeToString(src)
              
       
    }
     private static void dec(String p) throws IOException
        {
          try {
          Decoder dec=Base64.getDecoder();
          if(Files.size(Path.of(p))<=10000000)
        {
                
        File f=new File(p);
        
        //  FileReader fr=new FileReader(f);
        FileInputStream fis=new FileInputStream(f);
        
        
           //FileWriter ff=new FileWriter("h.txt");
          // ff.write();
        File ff=new File(p+"_Decrypted.txt");
        ff.setWritable(true);
       
        /* Data will e lost on same file */
        
        FileOutputStream fos=new FileOutputStream(ff);
        FileChannel ps;
        fos.write(dec.decode(fis.readAllBytes()));
        fis.close();
        fos.close();
        Files.lines(ff.toPath()).forEach(hh->System.out.println(hh));
        Files.delete(Path.of(p));
        }
          }
          catch(Exception e)
          {
              System.out.println("\n File cant be decrypted either it is not encrypted or something went wrong");
          }
        }
}
