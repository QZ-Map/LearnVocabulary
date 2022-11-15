import javax.print.attribute.standard.RequestingUserName;
import javax.xml.*;
import java.util.*;
import java.io.*;

public class Learn_And_stuff extends FileIO
{
  public static void main(String[] args) {
    
    String file =  "/home/nils/share/Programieren verlinkt mit Webserver/LernVocabLikeQuizlet/Example.vocab.txt";//args[0];
    VocabSet vocabSet = readFile(file);
    writeFile(vocabSet, file);
  }
}