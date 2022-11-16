import javax.print.attribute.standard.RequestingUserName;
import javax.xml.*;
import java.util.*;
import java.io.*;


public class VocabSet
{
  String vocabLanguage;
  String definitionLanguage;
  ArrayList<String> vocabs;
  ArrayList<String> definitions;
  ArrayList<Integer> score;
  LinkedList<Integer> isLearning;
  boolean learned;
  final Integer doneValue = 10;
  
  public VocabSet()
  {
    this.vocabLanguage = "";
    this.definitionLanguage = "";
    this.vocabs = new ArrayList<String>();
    this.definitions = new ArrayList<String>();
    this.score = new ArrayList<Integer>();
    this.learned = false;
    this.isLearning = new LinkedList<Integer>();
    
  }
  public void learn()
  {
    this.initLearn();
    for(int i = 0; i<7; i++)
    {
      Random random = new Random();
      int number = random.nextInt(this.isLearning.size() -1);
      if(this.score.get(this.isLearning.get(number))<=1)
      {
        //this.quiz(this.isLearning.get(number));
      }
      else
      {
        this.type(this.isLearning.get(number));
      }
    }
  }

  private void type(Integer index)
  {
    Scanner cmdScanner = new Scanner(System.in);
    System.out.print(this.definitionLanguage + "\t" + this.definitions.get(index) + "\n" + this.vocabLanguage + "\t" );
    String answer = cmdScanner.nextLine();
    if(answer.trim().toLowerCase() == this.vocabs.get(index).trim().toLowerCase())
    {
      System.out.println("Correct!");
    }
    else
      System.out.println(this.vocabLanguage + "\t" + this.vocabs.get(index));
    String press = cmdScanner.next();

  }

  private void initLearn()
  {
    int tolearn = 0;
    for(int i = 0; i < this.vocabs.size(); i++)
    {
      if(this.score.get(i) < this.doneValue)
      {tolearn ++;
      }
    }
    if(tolearn<=20)
    {
      int T = 0;
    }
    else
    {
      if(this.isLearning.size() <20)
      {
        this.refillIslearning();
      }
    }
    
  }

  private void refillIslearning() 
  {
    int i = 0;
    while(this.isLearning.size()<20)
    {
      if(this.score.get(i) == 0)
      {
        this.isLearning.add(i);
      }
      i++;
    }
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