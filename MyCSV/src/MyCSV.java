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
                    int startIndex = 0;
                    int endIndex = line.indexOf(",", startIndex);
                    while (endIndex != -1) {
                        String substring = line.substring(startIndex, endIndex);
                        writer.println("<td>");
                        writer.println(substring);
                        writer.println("</td>");
                        startIndex = endIndex + 1;
                        endIndex = line.indexOf(",", startIndex);
                    }
                    endIndex = line.length();
                    String substring = line.substring(startIndex, endIndex);
                    writer.println("<td>");
                    writer.println(substring);
                    writer.println("</td>");

                    writer.println("</tr>");
                    line = bufferedReader.readLine();
                }
                writer.printf("</table>");
                writer.close();
            }
        }
    }
}
