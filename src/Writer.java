import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    public static void writeTableOnFile(String path, double value, String title) throws IOException {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(title + " ");
            writer.write(String.format("%.2f", value));
            writer.write(" ");
            writer.write("\n");
        }
    }

    public static void writeTableOnFile(String path, double[] table, String title) throws IOException {
        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(title);
            for (double v : table) {
                writer.write(String.format("%.3f", v));
                writer.write(" ");
            }
            writer.write("\n");
        }
    }

    public static void clearFile(String path) throws IOException{
        try(FileWriter writer = new FileWriter(path, false)){
            writer.write("");
        }
    }
}
