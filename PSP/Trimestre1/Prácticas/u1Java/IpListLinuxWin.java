import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IpListLinuxWin {
  public static void main(String[] args) {
    final String OS_NAME_PROPERTY = "os.name", LX_NAME = "linux", WS_NAME = "win";
    final String REGEX = "\\b(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b";
    //final String ALT_REGEX = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b"; //shorter but would validate 999.999.999.999
    final String SEPARATOR = "\\s+";
    final String UNSOPPORTED_OS = "Execution ended: Unsupported operating system.";
    final String[] COMMAND_LX = {"ip", "-a"};
    final String[] COMMAND_WS = {"ipconfig"};

    InputStream generatedInputStream;

    if(System.getProperty(OS_NAME_PROPERTY).toLowerCase().contains(LX_NAME)){
      generatedInputStream = generateProcess(COMMAND_LX);
      printResult(generatedInputStream, REGEX, SEPARATOR);
    } else if (System.getProperty(OS_NAME_PROPERTY).toLowerCase().contains(WS_NAME)){
      generatedInputStream = generateProcess(COMMAND_WS);
      printResult(generatedInputStream, REGEX, SEPARATOR);
    } else {
      throw new UnsupportedOperationException(UNSOPPORTED_OS);
    }  
  }

  public static InputStream generateProcess(String[] command){
    ProcessBuilder builder = null;
    Process process = null;
    try{
      builder = new ProcessBuilder(command);
      process = builder.start();
    } catch (Exception e) {}

    return process.getInputStream();    
  }

  public static void printResult(InputStream inputStream, String regex, String separator){
    try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
      String line = "";
      while((line = reader.readLine()) != null){
        String[] splitted = line.split(separator);
        for(String split : splitted){
          if(split.matches(regex)){
            System.out.println(split);
          }
        }        
      }
    } catch (Exception e) {}
  }
}