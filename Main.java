import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    static File fileForExtraction = null;
    static File extractHere = null;
    static Socket socket = null;
    static PrintWriter pw2 = null;
    public static void main(String[] args){
        while(true) {
            try {

                Scanner scan = new Scanner(System.in);
                System.out.println("Enter the name of a file to extract metadata");
                fileForExtraction = new File(scan.nextLine());
                Parser parser = new AutoDetectParser();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();
                FileInputStream inputstream = new FileInputStream(fileForExtraction);
                ParseContext context = new ParseContext();
                parser.parse(inputstream, handler, metadata, context);
                String[] metadataNames = metadata.names();

                for (String name : metadataNames) {
                    System.out.println(name + ": " + metadata.get(name));
                }
                /*Scanner scan3 = new Scanner(System.in);
                System.out.println("Enter a file location here to extract metadata to");
                String location = scan3.nextLine();
                extractHere = new File(location);
                PrintWriter pw = new PrintWriter(new FileWriter(extractHere));
                for (String name : metadataNames){
                    pw.println(name + ": " + metadata.get(name));
                }
                pw.close();*/

                socket = new Socket("127.0.0.1", 8000);
                pw2 = new PrintWriter(socket.getOutputStream());
                for(String name : metadataNames){
                    pw2.println(name + ": " + metadata.get(name));
                }
                pw2.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (TikaException e) {
                throw new RuntimeException(e);
            } catch (SAXException e) {
                e.printStackTrace();
            }
            System.out.println("Do you want to continue? Yes or No");
            Scanner scan2 = new Scanner(System.in);
            String answer = scan2.nextLine();
            if(answer.equalsIgnoreCase("Yes")){
                continue;
            }else{
                break;
            }
        }
    }
}
