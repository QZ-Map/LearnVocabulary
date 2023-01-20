public class Raw { // File / Class unused
  public static char Read()
  {
    char in = 0;
    try
    {
    kickInRaw();
    in = (char)System.in.read();
    kickInBaked();
    System.out.print("\n\r");
    }catch(Exception e)
    {
      e.printStackTrace();
    }
    return in;
  }
  private static void kickInRaw() throws Exception
  {
    if(System.getProperty("os.name").equals("Linux"))
    {
      String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"}; // Pretty unsafe
      Runtime.getRuntime().exec(cmd);
      
    }
    else 
    {
      System.out.println("Error");
    }
  }
  private static void kickInBaked() throws Exception
  {
    if(System.getProperty("os.name").equals("Linux"))
    {
      String[] cmd = {"/bin/sh", "-c", "stty -raw </dev/tty"};
      Runtime.getRuntime().exec(cmd);
    }
  }
}
