package com.company.converter;

import java.io.*;

public class PPMToGrayscale {


    public static void convertToGrayscale(String inputFilePath, String outputFilePath) throws IOException {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            char magic1 = (char) reader.readByte();
            char magic2 = (char) reader.readByte();
            String magicNumber = "" + magic1 + magic2;

            if (magicNumber.equals("P6")) {
                writer.write("P2\n");
                reader.readLine();
                String header = reader.readLine();
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(reader.readLine());
                writer.write(width + " " + height + "\n");
                writer.write("255\n");

                byte[] pixels = new byte[width * height * 3];
                reader.readFully(pixels);

                for (int i = 0; i < pixels.length; i += 3) {
                    int red = pixels[i] & 0xFF;
                    int green = pixels[i + 1] & 0xFF;
                    int blue = pixels[i + 2] & 0xFF;

                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);

                    writer.write(grayValue + "\n");
                }
            } else if (magicNumber.equals("P3")) {
                writer.write("P2\n");


                String header = reader.readLine();
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(reader.readLine());


                writer.write(width + " " + height + "\n");
                writer.write("255\n");
                for (int i = 0; i < width * height; i++) {
                    int red = Integer.parseInt(reader.readLine());
                    int green = Integer.parseInt(reader.readLine());
                    int blue = Integer.parseInt(reader.readLine());
                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);
                    writer.write(grayValue + "\n");
                }
            } else {
                throw new IOException("Unsupported PPM format: " + magicNumber);
            }
        }
    }

    public static void convertToGrayscale2(String inputFilePath, String outputFilePath) throws IOException {

        byte[] fileContent;
        try (DataInputStream reader = new DataInputStream(new FileInputStream(inputFilePath))) {
            fileContent = reader.readAllBytes();
        }

        try (DataInputStream reader = new DataInputStream(new ByteArrayInputStream(fileContent));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            char magic1 = (char) reader.readByte();
            char magic2 = (char) reader.readByte();
            String magicNumber = "" + magic1 + magic2;

            if (magicNumber.equals("P6")) {
                writer.write("P2\n");
                reader.readLine();
                String header = readNonEmptyLine(reader);
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(readNonEmptyLine(reader));
                writer.write(width + " " + height + "\n");
                writer.write("255\n");

                byte[] pixels = new byte[width * height * 3];
                reader.readFully(pixels);

                for (int i = 0; i < pixels.length; i += 3) {
                    int red = pixels[i] & 0xFF;
                    int green = pixels[i + 1] & 0xFF;
                    int blue = pixels[i + 2] & 0xFF;

                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);

                    writer.write(grayValue + "\n");
                }
            } else if (magicNumber.equals("P3")) {
                writer.write("P2\n");

                String header = readNonEmptyLine(reader);
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(readNonEmptyLine(reader));

                writer.write(width + " " + height + "\n");
                writer.write("255\n");
                for (int i = 0; i < width * height; i++) {
                    int red = Integer.parseInt(readNonEmptyLine(reader));
                    int green = Integer.parseInt(readNonEmptyLine(reader));
                    int blue = Integer.parseInt(readNonEmptyLine(reader));
                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);
                    writer.write(grayValue + "\n");
                }
            } else if (magicNumber.equals("P1")) {
                writer.write("P2\n");

                String header = readNonEmptyLine(reader);
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);

                writer.write(width + " " + height + "\n");
                writer.write("255\n");

                int pixelCount = 0;
                while (pixelCount < width * height) {
                    String line = readNonEmptyLine(reader);
                    String[] tokens = line.split("\\s+");
                    for (String token : tokens) {
                        if (token.isEmpty()) continue;
                        int monochromeValue = Integer.parseInt(token);
                        int grayValue = monochromeValue == 0 ? 0 : 255;
                        writer.write(grayValue + "\n");
                        pixelCount++;
                    }
                }
            } else {
                throw new IOException("Unsupported PPM format: " + magicNumber);
            }
        }
    }

    private static String readNonEmptyLine(DataInputStream reader) throws IOException {
        String line;
        do {
            line = reader.readLine();
        } while (line != null && (line.isEmpty() || line.startsWith("#")));
        return line;
    }

}
