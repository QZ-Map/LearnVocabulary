import javax.print.attribute.standard.RequestingUserName;
import javax.xml.*;
import java.util.*;
import java.io.*;
public class FileIO {


  public static VocabSet importFromTXT(String path, String commaSeperatorREGEX, String lineSeperatorREGEX)
  {
    VocabSet vocabSet = new VocabSet();
    File inputFile = new File(path);
    try
    {
      Scanner fileScanner = new Scanner(inputFile);
      fileScanner.useDelimiter("\\Z");  
      String content = fileScanner.next();

      if(commaSeperatorREGEX == lineSeperatorREGEX)
      {
        String[] words = content.split(lineSeperatorREGEX);
        int i;
        for(i = 0; i<words.length; i++)
        {
          if(i%2 == 0)
          {
            vocabSet.vocabs.add(words[i].trim());
          }
          else
          {
            vocabSet.definitions.add(words[i].trim());
          }
        }
        if(i%2 == 1)
        {
          vocabSet.definitions.add(""); // Set empty in case input is odd
        }
      }
      else
      {
        String[] lines = content.split(lineSeperatorREGEX);
        for(int i = 0; i<lines.length; i++)
        {

          String[] words = {"",""};
          String[] tmp = lines[i].split(commaSeperatorREGEX);
          words[0] = tmp[0].trim();
          if(tmp.length !=1)
          words[1] = tmp[1].trim();
          vocabSet.add(words[0], words[1]);
        }
      }
    } catch (FileNotFoundException e)
    {
      System.out.println("Error: 404 File not found");
      e.printStackTrace();
    }
    return vocabSet;
    
  }

  public static void println(String ln)
  {
    System.out.println(ln);
  }

  public static void print(String ln)
  {
    System.out.print(ln);
  }


  public static void writeFile(VocabSet vocabSet, String path)
  {
    
    File outFile = new File(path);
    
    try
    {
      outFile.createNewFile();
      FileWriter outFileWriter = new FileWriter(outFile);
      outFileWriter.write(vocabSet.vocabLanguage + "|" + vocabSet.definitionLanguage +"|score\n");
      outFileWriter.write("-|-|-\n");
      for(int i = 0; i<vocabSet.vocabs.size(); i++)
      {
        outFileWriter.write(vocabSet.vocabs.get(i) + "|" + vocabSet.definitions.get(i) + "|" + vocabSet.score.get(i) + "\n");
      }
      outFileWriter.close();
    }
    catch(IOException e)
    {
      System.out.println("IO exeption. Couldn't save to file.");
      e.printStackTrace();
    }
    return;
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
        String[] splitLine = line.split("\\|");
        if(splitLine.length != 3)
          println("Language broken " + splitLine.length + "\n" + line );//throw exeption
        vocabSet.setLanguage(splitLine[0], splitLine[1]);
      }
      else
      println("file broken");//throw exeption

      if(fileScanner.hasNextLine())
        fileScanner.nextLine();
      else
      println("Language broken2");//throw exeption
      while(fileScanner.hasNextLine())
      {
        line = fileScanner.nextLine();
        String[] splitLine = line.split("\\|");
        if(splitLine.length != 3)
          println("vocabs broken " + splitLine.length);//throw exeption
        vocabSet.add(splitLine[0], splitLine[1], Integer.parseInt(splitLine[2]));
      }
      fileScanner.close();
    } catch (FileNotFoundException e)
    {
      System.out.println("Error: 404 File not found");
      e.printStackTrace();
    }
    return vocabSet;
  }
}
