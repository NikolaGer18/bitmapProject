package com.company.converter;

import java.io.*;

public class PPMToMonochrome {

    public static void convertToMonochrome(String inputFilePath, String outputFilePath) throws IOException {
        try (DataInputStream reader = new DataInputStream(new FileInputStream(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            char magic1 = (char) reader.readByte();
            char magic2 = (char) reader.readByte();
            String magicNumber = "" + magic1 + magic2;

            if (magicNumber.equals("P6")) {
                reader.readLine();
                String header = reader.readLine();
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(reader.readLine());

                writer.write("P1\n");
                writer.write(width + " " + height + "\n");

                for (int i = 0; i < width * height; i++) {
                    int red = reader.readUnsignedByte();
                    int green = reader.readUnsignedByte();
                    int blue = reader.readUnsignedByte();

                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);

                    int threshold = 128;
                    int monochromeValue = (grayValue < threshold) ? 0 : 1;

                    writer.write(monochromeValue + " ");
                }
            } else if (magicNumber.equals("P3")) {
            } else {
                throw new IOException("Unsupported PPM format: " + magicNumber);
            }
        }
    }

    public static void convertToMonochrome2(String inputFilePath, String outputFilePath) throws IOException {

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
                writer.write("P1\n");
                reader.readLine();
                String header = reader.readLine();
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(reader.readLine());
                writer.write(width + " " + height + "\n");

                byte[] pixels = new byte[width * height * 3];
                reader.readFully(pixels);

                int threshold = 128;

                for (int i = 0; i < pixels.length; i += 3) {
                    int red = pixels[i] & 0xFF;
                    int green = pixels[i + 1] & 0xFF;
                    int blue = pixels[i + 2] & 0xFF;

                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);
                    int monochromeValue = (grayValue < threshold) ? 0 : 1;

                    writer.write(monochromeValue + " ");
                    if ((i / 3 + 1) % width == 0) {
                        writer.write("\n");
                    }
                }
            } else if (magicNumber.equals("P3")) {
                writer.write("P1\n");

                String header = reader.readLine();
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(reader.readLine());

                writer.write(width + " " + height + "\n");

                int threshold = 128;

                for (int i = 0; i < width * height; i++) {
                    int red = Integer.parseInt(reader.readLine());
                    int green = Integer.parseInt(reader.readLine());
                    int blue = Integer.parseInt(reader.readLine());

                    int grayValue = (int) (0.21 * red + 0.72 * green + 0.07 * blue);
                    int monochromeValue = (grayValue < threshold) ? 0 : 1;

                    writer.write(monochromeValue + " ");
                    if ((i + 1) % width == 0) {
                        writer.write("\n");
                    }
                }
            } else if (magicNumber.equals("P2")) {
                writer.write("P1\n");

                String header = readNonEmptyLine(reader);
                String[] dimensions = header.split("\\s+");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                int maxColorValue = Integer.parseInt(readNonEmptyLine(reader));

                writer.write(width + " " + height + "\n");

                int threshold = 128;

                for (int i = 0; i < width * height; i++) {
                    int grayValue = Integer.parseInt(readNonEmptyLine(reader));
                    int monochromeValue = (grayValue < threshold) ? 0 : 1;

                    writer.write(monochromeValue + " ");
                    if ((i + 1) % width == 0) {
                        writer.write("\n");
                    }
                }
            }else {
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