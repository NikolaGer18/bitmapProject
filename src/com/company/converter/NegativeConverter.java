package com.company.converter;

import java.io.*;

public class NegativeConverter {
    public static void convertToNegative(String inputFile, String outputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            String line;

            line = reader.readLine();
            writer.write(line + "\n");

            line = reader.readLine();
            writer.write(line + "\n");

            line = reader.readLine();
            writer.write(line + "\n");

            while ((line = reader.readLine()) != null) {
                String[] pixels = line.trim().split("\\s+");
                for (String pixel : pixels) {
                    try {
                        int pixelValue = Integer.parseInt(pixel);
                        int negativePixelValue = 255 - pixelValue;
                        writer.write(negativePixelValue + " ");
                    } catch (NumberFormatException e) {

                        writer.write(pixel + " ");
                    }
                }
                writer.write("\n");
            }

            reader.close();
            writer.close();

            System.out.println("Conversion to negative completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertToNegative(String inputOutputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputOutputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(inputOutputFile + "_negative.ppm"));

            String format = reader.readLine();
            writer.write(format + "\n");

            String line;

            if (format.equals("P1") || format.equals("P2")) {
                writer.write(reader.readLine() + "\n");
                writer.write(reader.readLine() + "\n");
            } else if (format.equals("P3") || format.equals("P6")) {
                writer.write(reader.readLine() + "\n");
                writer.write(reader.readLine() + "\n");
                writer.write(reader.readLine() + "\n");
            }

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    String[] pixels = line.trim().split("\\s+");
                    for (String pixel : pixels) {
                        try {
                            int pixelValue = Integer.parseInt(pixel);
                            int negativePixelValue = 255 - pixelValue;
                            writer.write(negativePixelValue + " ");
                        } catch (NumberFormatException e) {
                            writer.write(pixel + " ");
                        }
                    }
                    writer.write("\n");
                } else {
                    writer.write(line + "\n");
                }
            }

            reader.close();
            writer.close();

            System.out.println("Conversion to negative completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
