package com.github.qz_map.learn_vocabulary;

//import javax.print.attribute.standard.RequestingUserName;
//import javax.xml.*;
import java.util.*;
//import java.io.*;
//import java.lang.reflect.InaccessibleObjectException;


public class VocabSet
{
  String vocabLanguage;
  String definitionLanguage;
  ArrayList<String> vocabs;
  ArrayList<String> definitions;
  ArrayList<Integer> score;
  LinkedList<Integer> isLearning;
  boolean learned;
  final Integer doneValue = 3;
  final Integer maxIsLearning = 15;
  final Integer maxSessionSize = 7;
  
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
    if(this.isLearning.size() == 0)
      {
        System.out.println("You have learned this set, congratulation!");
        return;
      }
    Integer sessionSize;
    if(this.isLearning.size() < this.maxSessionSize)
      sessionSize = this.isLearning.size();
    else
      sessionSize = this.maxSessionSize;
    for(int i = 0; i<sessionSize; i++)
    {
      int vocabToLearn = this.isLearning.get(i);
      if(this.score.get(vocabToLearn)<=1)//\\
      {
        this.quiz(vocabToLearn);
      }
      else
      {
        this.type(vocabToLearn);
      }
    }
  }

  private void quiz(Integer index)
  {
    String[] possibleAnswers = this.populatePossibleAnswers(index);
    int paddetLengh;
    final int extraPad = 2;
    if(possibleAnswers[0].length() > possibleAnswers[2].length()) // find padding size
      paddetLengh = possibleAnswers[0].length() + extraPad; 
    else
      paddetLengh = possibleAnswers[2].length() + extraPad;
    String line2 = String.format("1) %-" + paddetLengh + "s", possibleAnswers[0]) + "2) " +  possibleAnswers[1];
    String line3 = String.format("3) %-" + paddetLengh + "s", possibleAnswers[2]) + "4) " +  possibleAnswers[3];
    System.out.println(this.definitions.get(index));
    System.out.println(line2);
    System.out.println(line3);
    Character[] validKeys = {'1','2','3','4'};
    Character answer = TerminalIO.awaitKeyPress(validKeys);
    if(possibleAnswers[Integer.parseInt(answer.toString()) - 1].equals(this.vocabs.get(index)))
      {
        System.out.println("Correct!");
        this.score.set(index, this.score.get(index) + 1);
      }
      else
      {
        System.out.println("False:" + "\t" + this.vocabs.get(index));
        this.score.set(index, 1);
      }
      TerminalIO.acceptDeny_Jn('r');
    
  }


  /*private static String padRight(String s, int n) {
    return String.format("%-" + n + "s", s);  
  }

  private static String padLeft(String s, int n) {
     return String.format("%" + n + "s", s);   
  }*/

  public String[] populatePossibleAnswers(int correctAnswerIndex)
  {
    String[] possibleAnswers = new String[4];
    Integer[] shuffledOrder = shuffledOrder(this.vocabs.size());
    int shuffledOrderIndex = 0;
    for(int i = 0; i<3; i++) // a Vocabset smaller than 4 will make problems, it is possible that the answer is shown twice
    {
      String tmpString = this.vocabs.get(shuffledOrder[shuffledOrderIndex]-1);
      if(shuffledOrder[shuffledOrderIndex]-1 == correctAnswerIndex) // if the shuffled index equals the index of the correct answer
        {
          shuffledOrderIndex++;
          tmpString = this.vocabs.get(shuffledOrder[shuffledOrderIndex]-1);
        }
      possibleAnswers[i] = tmpString; //Copies references...
      shuffledOrderIndex++;
    }
    possibleAnswers[3] = this.vocabs.get(correctAnswerIndex); 
    
    return shuffleArray(possibleAnswers);
  }

  private Integer[] shuffledOrder(int length) // return a shuffled array {1 … n}
  {
    Integer[] order = new Integer[length];
    Arrays.setAll(order, i -> i + 1); 
    List<Integer> list = Arrays.asList(order);
    Collections.shuffle(list);
    return list.toArray(order);

  }

  private static <Array> Array[] shuffleArray(Array[] Array)
  {
    List<Array> list = Arrays.asList(Array);
    Collections.shuffle(list);
    list.toArray(Array);
    return Array;

  }

  private void type(Integer index)
  {
    Scanner cmdScanner = TerminalIO.cmdScanner;
    System.out.print(this.definitionLanguage + ":\t" + this.definitions.get(index) + "\n" + this.vocabLanguage + ":\t" );
    String answer = cmdScanner.nextLine();
    if(this.vocabs.get(index).trim().toLowerCase().matches("\\b" + answer.trim().toLowerCase() + "\\b")) //Regex. search for full word in definition
    {
      System.out.println("Correct!");
      TerminalIO.acceptDeny_Jn('r');
      this.score.set(index, this.score.get(index) +1);
    }
    else
    {
      System.out.println("False:" + "\t" + this.vocabs.get(index));
      if(! TerminalIO.acceptDeny_Jn('r'))
      {
        this.score.set(index, this.score.get(index) +1);
        System.out.println("Correct!");
      }
      else
        this.score.set(index, 1);
    }
  }

  public void initLearn()
  {
    int tolearn = 0; // count vocabs to learn
    for(int i = 0; i < this.vocabs.size(); i++)
    {
      if(this.score.get(i) < this.doneValue)
        tolearn ++;
    }

    for (int i : this.isLearning) // Removes all learned vocabs
    { 
      if(this.score.get(i) >= this.doneValue)
      {
        this.isLearning.remove(i);
      }
    }

    if(tolearn > this.maxIsLearning) // Refill learning vocabs
    {
      this.refillIsLearning();
    }
    Collections.shuffle(this.isLearning); // shuffle learning vocabs
  }

  private void fillKnown()
  {
    ArrayList<Integer> knownVocabs = new ArrayList<Integer>();
    for(int i = 0; i < this.vocabs.size() ; i++ )
    {
      if(this.score.get(i) > 0 && this.score.get(i) < this.doneValue)
      {
        knownVocabs.add(i);
      }
    }
    Collections.shuffle(knownVocabs);
    int i = 0;
    while(this.isLearning.size() < this.maxIsLearning)
    {
      if(! isLearning.contains(knownVocabs.get(i)))
      {
        isLearning.add(knownVocabs.get(i)); 
      }
      i++;
    }

  }
  private void refillIsLearning() 
  {
    fillKnown();
    int i = 0;
    Integer[] shuffledOrder = shuffledOrder(this.vocabs.size());
    while(this.isLearning.size()< this.maxIsLearning)
    {
      int u = shuffledOrder[i] -1; // INDEX starts with 0!!!
      if(this.score.get(u) == 0)
      {
        this.isLearning.add(u);
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