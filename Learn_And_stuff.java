import javax.print.attribute.standard.RequestingUserName;
import javax.xml.*;
import java.util.*;
import java.io.*;

public class Learn_And_stuff extends FileIO
{
  public static void main(String[] args) {
    //print(args[1]);
    //print((char) (press)); ///

   //String args[] ={"-l", "Test"}; // {"-i", "./test.txt"};
    switch(args[0]){
      case "-i":
      importVocabs(args);
      break;
      case "-l":
      learn(args);
      default:
      println("what?");
    }

    /*String file =  "";//args[0];
    VocabSet vocabSet = readFile(file);
    writeFile(vocabSet, file);*/
  }

  public static void learn(String[] args)
  {
    VocabSet vocabSet = readFile("./sets/" + args[1] + ".vocab.md");
    while(vocabSet.learned == false)
    {
    vocabSet.learn();
    println("-------Round over-------");
    writeFile(vocabSet, "./sets/" + args[1] + ".vocab.md");
    }
    println("You have learned " + args[1]);
  }

  public static void importVocabs(String[] args)
  {
    Scanner cmdScanner = new Scanner(System.in);
    print("Coma seperator: ");
    String commaSeperator = cmdScanner.nextLine();
    print("Line seperator: ");
    String lineSeperator = cmdScanner.nextLine();
    VocabSet vocabSet = importFromTXT(args[1], commaSeperator, lineSeperator);
    print("Vocab language: ");
    String vocabLang = cmdScanner.nextLine();
    print("Definition language: ");
    String definitionLang = cmdScanner.nextLine();
    vocabSet.setLanguage(vocabLang, definitionLang);

    print("Name of Set: ");
    String name = cmdScanner.nextLine();
    writeFile(vocabSet, "./sets/" + name + ".vocab.md");
    
  }
}