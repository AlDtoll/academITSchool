package ru.academits.tolmachev.csv.main;

import java.io.*;

public class MyCSV {

    public static void main(String[] args) throws IOException {

        System.out.println("CSV");

        System.out.println("Название входного файла: " + args[0]);
        System.out.println("Название выходного файла: " + args[1]);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            // Формирование шляпы
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head> <meta charset=\"utf-8\"/> <title> " + args[1].replace(".html", "") + "</title> </head>");
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
                                if (isDoubleQuotes) {// узнаем двойная ли она
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
                            printSymbol(writer, line, i);// печатаем символ
                            if (i == line.length() - 1) { // как дошли до конца строки
                                isLineTransfer = true;
                                writer.print("<br/>"); // то у нас несколько строк в ячейке
                            }
                        }
                    } else { // если мы вне кавычек
                        if (line.charAt(i) == '\"') {// и встретилась кавычка
                            isInQuotes = true; // открываем кавычки
                        } else {
                            if (line.charAt(i) == ',') { // и встретилась запятая...
                                if (i == line.length() - 1) { // .. в конце строки
                                    writer.print("</td><td></td>");
                                } else {// ..в середине строки
                                    writer.print("</td><td>");
                                }
                            } else { // ничего интересного
                                printSymbol(writer, line, i);// печатаем символ
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

    static private void printSymbol(PrintWriter writer, String line, int i) {
        switch (line.charAt(i)) {
            case '<':
                writer.print("&lt");
                break;
            case '>':
                writer.print("&gt");
                break;
            case '&':
                writer.print("&amp");
                break;
            default:
                writer.print(line.charAt(i));
        }
    }
}

