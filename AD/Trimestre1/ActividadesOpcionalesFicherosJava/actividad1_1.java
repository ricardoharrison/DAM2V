import java.io.File;

public class actividad1_1{
    public static void main(String[] args) {
        try{  
            String dir = args[0];
            
            File file = new File(dir);
            File[] arrayFiles = file.listFiles();

            for(File singleFile : arrayFiles){
                System.out.println(singleFile);
            }

            
        } catch (Exception e) {
            System.out.println("No has introducido ningún fichero como argumento");
        }
    }    
}