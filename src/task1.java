// Дана json строка {{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
//{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}}
//1. Дана json строка { { "фамилия":"Иванов","оценка":"5","предмет":"Математика"},
//{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},
//{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}}
//Задача написать метод(ы), который распарсить строку и выдаст ответ вида:
//Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по предмету Информатика.
//Студент Краснов получил 5 по предмету Физика. Используйте StringBuilder для подготовки ответа
public class task1 {
    public static void main(String[] args) throws Exception {
        String templateQuery = "Студент ";
        String content ="[{\"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"}," +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"}" +
                ",{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
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
