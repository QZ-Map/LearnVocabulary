import javax.xml.*;
import java.util.*;
import java.io.*;

class Learn_And_stuff
{
  public static void main(String[] args) {
    
    String file = args[0];
    readFile(file);
  }

  public static VocabSet readFile(String path)
  {
    VocabSet vocabSet = new VocabSet();
    File inputFile = new File(path);
    try
    {
      String line;
      Scanner fileScanner = new Scanner(inputFile);
      if(fileScanner.hasNextLine())
      {
        line = fileScanner.nextLine();
        String[] splitLine = line.split("|");
        vocabSet.setLanguage(splitLine[0], splitLine[1]);
      }
      else
      ;//throw exeption

      if(fileScanner.hasNextLine())
        fileScanner.nextLine();
      else
      ;//throw exeption
      while(fileScanner.hasNextLine())
      {
        line = fileScanner.nextLine();
        String[] splitLine = line.split("|");
        if(splitLine.length != 2)
          ;//throw exeption
        vocabSet.add(splitLine[0], splitLine[1]);
      }
      fileScanner.close();
    } catch (FileNotFoundException e)
    {
      System.out.println("Error: 404 File not found");
      e.printStackTrace();
    }
    return vocabSet;
  }

  public void writeFile(VocabSet vocabSet, String path)
  {
    File outFile = new File(path);
    
    try
    {
      outFile.createNewFile();
      FileWriter outFileWriter = new FileWriter(outFile);
      outFileWriter.write(vocabSet.vocabLanguage + "|" + vocabSet.definitionLanguage +"\n");
      outFileWriter.write("-|-\n");
      for(int i = 0; i<vocabSet.vocabs.size(); i++)
      {
        outFileWriter.write(vocabSet.vocabs.get(i) + "|" + vocabSet.definitions.get(i) + "\n");
      }
    }
    catch(IOException e)
    {
      System.out.println("IO exeption. Couldn't save to file.");
      e.printStackTrace();
    }
  }
}

class VocabSet
{
  String vocabLanguage;
  String definitionLanguage;
  ArrayList<String> vocabs;
  ArrayList<String> definitions;
  ArrayList<Integer> score;
  
  public VocabSet()
  {
    this.vocabLanguage = "";
    this.definitionLanguage = "";
    this.vocabs = new ArrayList<String>();
    this.definitions = new ArrayList<String>();
    this.score = new ArrayList<Integer>();
  }

  public void setLanguage(String vocabLanguage, String definitionLanguage)
  {
    this.vocabLanguage = vocabLanguage;
    this.definitionLanguage = definitionLanguage;
  }

  public void add(String vocab, String definition, Integer score)
  {
    this.vocabs.add(vocab);
    this.definitions.add(definition);
    this.score.add(score);
  }
  
  public void add(String vocab, String definition)
  {
    add(vocab, definition, 0);
  }
}