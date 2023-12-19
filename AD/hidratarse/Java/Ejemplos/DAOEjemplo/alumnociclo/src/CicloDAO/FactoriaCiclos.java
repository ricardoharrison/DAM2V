package CicloDAO;

public class FactoriaCiclos {
    public static CicloInterface getCicloDao(){
    
        
        return new CicloBean();
    }
}
