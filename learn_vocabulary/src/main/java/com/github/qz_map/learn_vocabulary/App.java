package com.github.qz_map.learn_vocabulary;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try
            {Learn_And_stuff.main(args);}
        catch(Exception e) // not proper exeption handeling
        {
            System.out.println( "Something went wrong..." );
            System.out.print(e);
        }
        finally
        {
            System.out.println( "Bye Bye" );
        }
    }
}
