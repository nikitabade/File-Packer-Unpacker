import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.String;

public class UNPacker
{
   FileOutputStream outstream=null;

   public UNPacker(String src) throws Exception
   {
     unpack(src);
   }
   
   public void unpack(String filepath) throws Exception
   {
     try
     {
       FileInputStream instream = new FileInputStream(filepath);
       byte header[]=new byte[100];
       int length=0;

       byte magic[]=new byte[12];
       instream.read(magic,0,magic.length);
       String magicstr=new String(magic);
       if(!magicstr.equals("file11"))
       {
         throw new InvalidFileException("invalid packed file format");
       }
       while((length=instream.read(header,0,100))>0)
       {
         String str=new String(header);
         String ext=str.substring(str.lastIndexOf("/"));
         ext=ext.substring(1);
         String[] word=ext.split("\\s");
         String filename=word[0];
         int size=Integer.parseInt(word[1]);
         byte arr[]=new byte[size];
         instream.read(arr,0,size);
         FileOutputStream fout=new FileOutputStream(filename);
         fout.write(arr,0,size);
       }
     }
     catch (InvalidFileException obj)
     {
       throw new InvalidFileException("invalid packed file format");
     }
     catch(Exception e)
     {}
   }
   
}