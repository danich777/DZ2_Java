//2. Создать метод, который запишет результат работы в файл
// Обработайте исключения и запишите ошибки в лог файл

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class task2 {
    public static void main(String[] args){
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.INFO, "Все хорошо");
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        String templateQuery = "Студент ";
        String query = null;
        String content ="[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}" +
                ",{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";

        content = content.substring(1, content.length() - 1);
        content = content.replaceAll("[\"\\s{]", "");
        content = content.replaceAll("},", "!");
        content = content.replaceAll("}", "");
        String[] lines = content.split("!");
        for (String line : lines) {
            query = new StringBuilder(templateQuery).append(parseLines(line)).toString();
            System.out.print(query);
        }
        try (FileWriter fileWriter = new FileWriter("task2.txt", false)) {
            fileWriter.write(query);
        } catch (Exception e){
            logger.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }



    }

    public static String parseLines(String myStr) {
        String[] myStrArr = myStr.split(",");
        StringBuilder sb = new StringBuilder();
        for (String criterion : myStrArr) {
            String[] keyValue = criterion.split(":");
            if (keyValue[0].equals("фамилия")) {
                sb.append(keyValue[1]);
                sb.append(" получил ");
            }
            if (keyValue[0].equals("оценка")) {
                sb.append("оценку ");
                sb.append(keyValue[1]);
            }
            if (keyValue[0].equals("предмет")) {
                sb.append(" по предмету ");
                sb.append(keyValue[1]);
                sb.append(". ");
            }
        }
        return sb.toString();
    }
}

