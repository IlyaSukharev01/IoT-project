import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public interface LogerInterface {
    File file = new File("logs.txt");
    default void writeLogs(String log) {
        try{
            file.setWritable(true);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write("\n" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd___HH:mm:ss___")) + log);
            writer.flush();
            writer.close();
            file.setReadOnly();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
