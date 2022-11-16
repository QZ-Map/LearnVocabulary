import javax.print.attribute.standard.RequestingUserName;
import javax.xml.*;
import java.util.*;
import java.io.*;

public class Learn_And_stuff extends FileIO
{
  public static void main(String[] args) {
    //print(args[1]);
    Scanner cmdScanner = new Scanner(System.in);
    Byte press = cmdScanner.nextByte();
    //print((char) (press)); ///

    String arger[] ={"-i", "../Test.txt"};
    switch(arger[0]){
      case "-i":
      importVocabs(arger);
      break;
      case "-l":
      learn(arger);
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
    }
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