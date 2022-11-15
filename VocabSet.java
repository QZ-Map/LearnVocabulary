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