package com.github.qz_map.learn_vocabulary;

//import java.util.concurrent.ExecutionException;
//import java.io.*;

//import javax.lang.model.util.ElementScanner14;
//import javax.print.attribute.standard.RequestingUserName;
//import javax.xml.*;
import java.util.*;
//import java.io.*;
//import org.jline.terminal.*;
//import org.fusesource.jansi.*;


public class TerminalIO {
  static Scanner cmdScanner; // A scanner shared in the whole programm, I hope
  public static Boolean acceptDeny_Jn(char denykey) //throws Exception
  {
    //Scanner cmdScanner = new Scanner(System.in);
    //while(cmdScanner.hasNext() == false);
    String input = cmdScanner.nextLine();
    Character denyKey = denykey;
    if(input.equals(denyKey.toString()))
      return false;
    else
      return true;
    /*if((char) System.in.read() == denykey)
      return false;
    else
      return true;*/
  }

  public static char awaitKeyPress(Character[] validKeys)
  {
    //Scanner cmdScanner = new Scanner(System.in);
    char pressed;
    while (true)
    {
      while(cmdScanner.hasNextLine() == false); 
      String input = cmdScanner.nextLine();
      pressed = input.charAt(0);
      for(Character validKey : validKeys)
      {
        if(validKey.equals(pressed))
        {
          //while(scanner.hasNextLine() == false);
          return pressed;
        }
      }
    }
  }

  /*private static boolean acceptDeny_Nj(char acceptkey) throws Exception
  {
    return !acceptDeny_Jn(acceptkey);
  }*/
}