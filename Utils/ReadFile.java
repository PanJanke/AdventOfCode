package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static String[] getRows(String filePath){
        String[] rows = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            String input = stringBuilder.toString();
            rows = input.split("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

}
