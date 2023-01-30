//3*. Получить исходную json строку из файла, используя FileReader или Scanner

import java.io.*;

public class task3 {
    public static void main(String[] args) throws Exception {
        String templateQuery = "Студент ";
        String content = getString("task3.jsn");
        System.out.println(content);
        content = content.substring(1, content.length() - 1);
        content = content.replaceAll("[\"\\s{]", "");
        content = content.replaceAll("},", "!");
        content = content.replaceAll("}", "");
        String[] lines = content.split("!");
        for (String line : lines) {
            String query = new StringBuilder(templateQuery).append(parseLines(line)).toString();
            System.out.print(query);
        }
    }

    public static String getString(String fileName) {
        String content = null;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            content = reader.readLine();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
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
