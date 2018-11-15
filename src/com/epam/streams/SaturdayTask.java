package com.epam.streams;

import java.io.*;

public class SaturdayTask {
    public static void main(String[] args) {
        mainExercise();
    }

    public static void mainExercise() {

        StringBuilder sb = new StringBuilder();
        StringBuilder sbTwoThird = new StringBuilder();

        fileNotFoundCreate("input.txt");

        try (BufferedReader in = new BufferedReader(new FileReader("input.txt"))) {
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                }
                sb.append(returnTextFromConsole());
                sb.reverse();
                write(sb, "output.txt");
                sbTwoThird = sbTwoThird.append(sb.toString().substring((sb.length() / 3), (sb.length() / 3 * 2)));
                write(sbTwoThird, "test.txt");
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void write(StringBuilder sb, String fileName) {

        fileNotFoundCreate(fileName);

        try (PrintWriter out = new PrintWriter(fileName)) {
            out.print(sb.toString().toUpperCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fileNotFoundCreate(String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
                if (fileName.equals("input.txt")) {
                    String str = "Default text";
                    try (PrintWriter in = new PrintWriter("input.txt")) {
                        in.print(str);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String returnTextFromConsole() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        System.out.println("Enter file content to write in the file:");
        try {
            line = br.readLine();
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

