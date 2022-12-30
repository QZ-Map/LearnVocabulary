import java.util.*;
//import org.jline.terminal.*;
//import org.fusesource.jansi.*;

public class Learn_And_stuff extends FileIO 
{
  public static void main(String[] args) throws Exception {
    //Terminal terminal = TerminalBuilder.terminal();
    TerminalIO.cmdScanner = new Scanner(System.in); // Global Terminal scanner
    
    String args2[] ={"-l", "Test"}; // {"-i", "./test.txt"};
    
    switch(args2[0]){
      case "-i":
      importVocabs(args2);
      break;
      case "-l":
      learn(args2);
      default:
      println("what?");
    }
    TerminalIO.cmdScanner.close();
    /*String file =  "";//args[0];
    VocabSet vocabSet = readFile(file);
    writeFile(vocabSet, file);*/
  }

  /*public static String[] noArgs()
  {
    println("i \t Import a vocab set from a text document.");
    println("l \t Learn a vocab set.");
    println("r \t Reset progress of a vocab set");
    Character[] options = {'i', 'l', 'r'};
    Character choise = TerminalIO.awaitKeyPress(options);
    switch(choise)
    {
      case 'i':
        println("Where is the text file?");
        String path = TerminalIO.cmdScanner.nextLine();
        String[] args = {choise.toString(), path};
        return args;
      case 'l':
        print("What do you want to learn? (either the name of a listed set or a path to the set)");
        String[] knownSets = 
        for()
    }
  } */

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
    Scanner cmdScanner = TerminalIO.cmdScanner;
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
    cmdScanner.close();
  }
}