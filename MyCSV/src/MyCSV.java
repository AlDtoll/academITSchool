import java.io.*;
import java.util.Scanner;

public class MyCSV {

    public static void main(String[] args) throws IOException {

        System.out.println("CSV");

        System.out.println("Введите название файла");
        Scanner in = new Scanner(System.in);
        String path = "CSV_test";//in.nextLine();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + ".txt"));
             PrintWriter writer = new PrintWriter(path + ".html")) {
            // Формирование шляпы
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<meta charset=\"utf-8\"/>" + " <title> " + path + "</title>" + " </head>");
            writer.println("<body>");
            writer.println("<table>");
            String line = bufferedReader.readLine();// прочитали строку

            boolean isInQuotes = false;
            boolean isLineTransfer = false;
            boolean isDoubleQuotes = false;
            while (line != null) {
                if (!isLineTransfer) {
                    writer.print("<tr>");
                    writer.print("<td>");
                }
                for (int i = 0; i < line.length(); i++) {
                    if (isInQuotes) { // если мы в кавычках
                        if (line.charAt(i) == '\"') { // и встретилась кавычка...
                            if (i == line.length() - 1) { // .. в конце строки
                                if (line.charAt(i) == line.charAt(i - 1)) {// узнаем двойная ли она
                                    writer.print(line.charAt(i)); // тогда ппечатаем одну кавычку
                                    isLineTransfer = true;
                                    writer.print("<br/>"); // то у нас несколько строк в ячейке
                                } else { // если нет, то закрываем кавычки
                                    isInQuotes = false; // тогда закрываем кавычку
                                    isLineTransfer = false;
                                    writer.print("</td>");// закрываем ячейку
                                }
                            } else { // ... в середине
                                if (isDoubleQuotes) { // она двойная?
                                    writer.print(line.charAt(i));// напечатаем одну кавычку
                                    isDoubleQuotes = false;
                                } else {
                                    if (line.charAt(i) == line.charAt(i + 1)) {// узнаем двойная ли она
                                        isDoubleQuotes = true;
                                    } else { // если нет, то закрываем кавычки
                                        isInQuotes = false; // тогда закрываем кавычку
                                        isLineTransfer = false;
                                    }
                                }
                            }
                        } else { // кавычка не встретилась
                            writer.print(line.charAt(i)); // просто печатаем символ
                            if (i == line.length() - 1) { // как дошли до конца строки
                                isLineTransfer = true;
                                writer.print("<br/>"); // то у нас несколько строк в ячейке
                            }
                        }
                    } else { // если мы вне кавычек
                        if (line.charAt(i) == '\"') {// и встретилась кавычка
                            isInQuotes = true; // открываем кавычки
                        } else {
                            if (line.charAt(i) == ',') { // и встретилась запятая
                                writer.print("</td><td>");
                            } else { // ничего интересного
                                writer.print(line.charAt(i)); // просто печатаем символ
                                if (i == line.length() - 1) { // и дошли до конца строки
                                    writer.print("</td>");// закрываем ячейку
                                }
                            }
                        }
                    }
                }
                if (!isLineTransfer) {
                    writer.println("</tr>");
                }
                line = bufferedReader.readLine();
            }
            writer.println("</table>");
            writer.println("</body>");
            writer.println("</html>");
        }
    }
}

