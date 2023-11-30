import java.io.IOException;

public class TarCompressor {
    public static void main(String[] args) {
        ProcessBuilder builder;
        for (int i = 0; i < args.length; i++) {
            // builder = new ProcessBuilder("tar","cvzf", args[i] + ".tar.gz", args[i]);
            // esto no siempre funciona por el nombre que se le da al archivo
            builder = new ProcessBuilder("tar", "cvzf", "carpeta" + (i + 1) + ".tar.gz", args[i]);
            try {
                Process process = builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}