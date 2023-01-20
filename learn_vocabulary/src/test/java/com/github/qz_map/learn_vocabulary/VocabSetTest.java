package com.github.qz_map.learn_vocabulary;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class VocabSetTest 
{
    /**
    * Rigorous Test :-)
    */

    @Test
    public void testAddwithScore()
    {
      Integer testSize = 100000;
      VocabSet testSet = new VocabSet();
      for(Integer i = 0; i<testSize; i++)
      {
        Integer definition = i + 5;
        Integer score  = testSize - i;
        testSet.add(i.toString(), definition.toString(), score);
      }
      for(Integer i = 0; i<testSize; i++)
      {
        Integer b = i + 5;
        Integer score  = testSize - i;
        assertEquals(testSet.vocabs.get(i), (i.toString()));
        assertEquals(testSet.definitions.get(i), (b.toString()));
        assertEquals(testSet.score.get(i), score);
      }
    }


    @Test
    public void testAdd()
    {
      VocabSet testSet = new VocabSet();
      for(Integer i = 0; i<4; i++)
      {
        Integer b = i + 5;
        testSet.add(i.toString(), b.toString());
      }
      for(Integer i = 0; i<4; i++)
      {
        Integer b = i + 5;
        assertEquals(testSet.vocabs.get(i), (i.toString()));
        assertEquals(testSet.definitions.get(i), (b.toString()));
      }
    }
    @Test
    public void normalDistribution()
    {
      Integer[][] distribution = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}}; // [place] [VocabNrCount]
      VocabSet testSet = new VocabSet();
      for(Integer i = 0; i<4; i++)
      {
        testSet.add(i.toString(), i.toString());
      }
      testSet.initLearn();
      int interations = 10000;
      for(int i = 0; i < interations; i++)
      {
        String[] possibleAnswers = testSet.populatePossibleAnswers(1);
        int j = 0;
        for (String string : possibleAnswers) {
          distribution[j][Integer.parseInt(string)]++;
          j++;
        }
      }

      double min = interations/4 * 0.85;
      double max = interations/4 * 1.15;

      boolean normalDistribution=true;
      for (int i = 0 ; i<4; i++)
      {
        for (int j = 0 ; j<4; j++)
      {
        int test = distribution[i][j];
        if(test <min | test > max)
        {
          normalDistribution = false;
          break;
        }
      }
      }
      assertTrue( normalDistribution );
    }
}
