import java.io.*;

public class MyCSV {

    public static void main(String[] args) throws IOException {

        System.out.println("CVS");

        try (FileReader fileReader = new FileReader("CVS_test.txt")) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                PrintWriter writer = new PrintWriter("CVS_output.html");
                writer.println("<table>");
                String line = bufferedReader.readLine();
                while (line != null) {
                    writer.println("<tr>");
                    int startCell = 0;
                    boolean inQuotes = false;
                    int quote = line.indexOf("\"", startCell); // Смотрим начинается ли таблица с кавычек
                    if (quote == 0) { // Если да, то заходим внутрь..
                        inQuotes = true;
                        startCell = quote + 1;
                    }
                    int endCell;
                    if (inQuotes) { // .. и разделяем по запятой за второй кавычкой
                        endCell = line.indexOf("\"", startCell) + 1;
                        inQuotes = false;
                    } else { // Если нет, то разделяем по запятой
                        endCell = line.indexOf(",", startCell);
                    }
                    String substring;
                    while (endCell != -1 && endCell != line.length()) { // Пока есть запятая и пока кавычка не последняя
                        writer.println("<td>");
                        substring = line.substring(startCell, endCell);
                        substring = substring.replace("\"", "");
                        writer.println(substring);
                        writer.println("</td>");
                        startCell = endCell + 1; // Перевели каретку через запятую - началась следующая ячейка
                        quote = line.indexOf("\"", startCell); // Смотрим есть ли еще кавычка
                        if (quote != -1) { // Если есть, то смотрим где она
                            if (quote == startCell + 1) { // Если через отступ, то снова заходим в кавычки
                                inQuotes = true;
                                startCell = quote + 1;
                            }
                            if (inQuotes) {
                                endCell = line.indexOf("\"", startCell) + 1;
                                inQuotes = false;
                            } else { // Если нет, то смотрим на запятые
                                endCell = line.indexOf(",", startCell);
                            }
                        } else { // Если нет, то смотрим на запятые
                            endCell = line.indexOf(",", startCell);
                        }
                        System.out.println(line.length());
                    } // Для последней ячейки
                    if (line.indexOf("\"", startCell) == line.length() - 1) {// если последняя кавычка, то убираем ее
                        endCell = line.length() - 1;
                    } else { // иначе рисуем последний символ
                        endCell = line.length();
                    }
                    substring = line.substring(startCell, endCell);
                    writer.println("<td>");
                    writer.println(substring);
                    writer.println("</td>");

                    writer.println("</tr>");
                    line = bufferedReader.readLine(); // Перешли к следующей строке

                }

                writer.printf("</table>");
                writer.close();
            }
        }
    }
}
