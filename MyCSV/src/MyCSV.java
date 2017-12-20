import java.io.*;

public class MyCSV {

    public static void main(String[] args) throws IOException {

        System.out.println("CSV");

        try (FileReader fileReader = new FileReader("CSV_test.txt")) {
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                PrintWriter writer = new PrintWriter("CSV_output.html");
                writer.println("<table>");
                String line = bufferedReader.readLine();// прочитали строку

                String substring;
                boolean isInQuotes = false;
                boolean isTransfer = false;
                while (line != null) {
                    int startCell = 0;
                    int cursor = 0;
                    int endCell = 0;
                    if (!isTransfer) {
                        writer.println("<tr>");
                    }
                    while (endCell != line.length() && endCell != line.length() - 1) {
                        int quote = line.indexOf("\"", cursor);
                        if (isInQuotes) { // если мы в кавычках, то
                            if (quote == -1) { // ищем кавычки
                                endCell = line.length();// значит вся строка - часть ячейки
                                if (!isTransfer) {
                                    writer.println("<td>");
                                }
                                substring = line.substring(startCell, endCell);
                                substring = substring.replace("\"\"", "\"");
                                writer.println(substring);
                                writer.println("<br/>");
                                isTransfer = true;
                            } else { // если кавчка есть, то надо проверить двойная она или нет
                                if (quote == line.length() - 1) { // кавычка в конце строки
                                    isInQuotes = false;
                                    endCell = quote; // index = ine.length() - 1 значит полезет в цикл еще раз
                                    if (!isTransfer) {
                                        writer.println("<td>");
                                    }
                                    substring = line.substring(startCell, endCell);
                                    substring = substring.replace("\"\"", "\"");
                                    writer.println(substring);
                                    writer.println("</td>");
                                    writer.println("</tr>");
                                    isTransfer = false;
                                } else {
                                    if (line.charAt(quote) != line.charAt(quote + 1)) {// одинарная кавычка - закрываем по ней
                                        endCell = quote;
                                        if (!isTransfer) {
                                            writer.println("<td>");
                                        }
                                        substring = line.substring(startCell, endCell);
                                        substring = substring.replace("\"\"", "\"");
                                        writer.println(substring);
                                        writer.println("</td>");
                                        startCell = endCell + 2;// переводим каретку через кавычку и запятую за ней
                                        cursor = startCell;
                                        isInQuotes = false;
                                        isTransfer = false;
                                    } else {
                                        cursor = quote;
                                        cursor += 2;
                                    }
                                }
                            }
                        } else {// если мы вне кавычек, то
                            int comma = line.indexOf(",", startCell); // ищем запятые
                            if (comma == -1) { // если запятых нет, то ищем кавычки
                                if (quote == -1) {// если и их нет, то это конец строки
                                    endCell = line.length();
                                    writer.println("<td>");
                                    substring = line.substring(startCell, endCell);
                                    writer.println(substring);
                                    writer.println("</td>");
                                    writer.println("</tr>");
                                } else {// первая идет кавчка
                                    startCell = quote + 1; // перешагиваем через нее
                                    cursor = startCell;
                                    isInQuotes = true;// открываем кавычки
                                }
                            } else { // если запятые есть
                                if (quote == -1) { // а кавыче нет..
                                    endCell = comma; // то делим по запятым
                                    writer.println("<td>");
                                    substring = line.substring(startCell, endCell);
                                    writer.println(substring);
                                    writer.println("</td>");
                                    startCell = endCell + 1; // перевели каретку через запятую
                                } else { // если и кавычки есть, то вопрос, что раньше
                                    if (comma < quote) { // первая идет запятая
                                        endCell = comma; // то делим по запятым
                                        writer.println("<td>");
                                        substring = line.substring(startCell, endCell);
                                        writer.println(substring);
                                        writer.println("</td>");
                                        startCell = endCell + 1; // перевели каретку через запятую
                                        cursor = startCell;
                                    } else { // первая идет кавчка
                                        startCell = quote + 1;
                                        cursor = startCell;
                                        isInQuotes = true;// открываем кавычки
                                    }
                                }
                            }
                        }
                    }
                    line = bufferedReader.readLine();
                }
                if (!isTransfer) {
                    writer.println("</tr>");
                }
                writer.printf("</table>");
                writer.close();
            }
        }
    }

}

