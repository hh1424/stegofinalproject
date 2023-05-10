package edu.guilford;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;

import javax.imageio.ImageIO;

public class DecryptionLSB {
    
    public static void Decrypt() {
        String path = "C:/stegofinalproject/src/main/EncodedImages/Used";
        String newImageFileString = path + "\\export.png";
        File newImageFile = new File(newImageFileString);
        try {
            BufferedImage image = ImageIO.read(newImageFile);
            Pixel[] pixels = GetPixelArray(image);
            System.out.println("Message: " + DecodeMessageBinaryFromPixels(pixels));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Pixel[] GetPixelArray(BufferedImage imageToEmbed) {
        int width = imageToEmbed.getWidth();
        int height = imageToEmbed.getHeight();
        Pixel[] pixels = new Pixel[width * height];
        int count = 0;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                Color colorToAdd = new Color(imageToEmbed.getRGB(x, y));
                pixels[count] = new Pixel(x, y, colorToAdd);
                count++;
            }
        }
        return pixels;
    }

    private static String DecodeMessageBinaryFromPixels(Pixel[] pixels) {
        boolean completed = false;
        int pixelIndex = 0;
        StringBuilder messageBuilder = new StringBuilder("");
        while(completed == false) {
            Pixel[] pixelsToRead = new Pixel[3];
            for(int i = 0; i < 3; i++) {
                pixelsToRead[i] = pixels[pixelIndex];
                pixelIndex++;
            }
            messageBuilder.append(ConvertPixelsToCharacter(pixelsToRead));
            if(IsEndOfMessage(pixelsToRead[2]) == true) {
                completed = true;
            }
        }
        return messageBuilder.toString();
    }

    private static char ConvertPixelsToCharacter(Pixel[] pixelsToRead) {
        ArrayList<String> binaryValues = new ArrayList<String>();
        for(int i = 0; i < pixelsToRead.length; i++) {
            String[] currentBinary = TurnPixelIntergersToBinary(pixelsToRead[i]);
            binaryValues.add(currentBinary[0]);
            binaryValues.add(currentBinary[1]);
            binaryValues.add(currentBinary[2]);
        }
        return ConvertBinaryValuesToCharacter(binaryValues);
    }

    private static String[] TurnPixelIntergersToBinary(Pixel pixel) {
        String[] values = new String[3];
        values[0] = Integer.toBinaryString(pixel.getColor().getRed());
        values[1] = Integer.toBinaryString(pixel.getColor().getGreen());
        values[2] = Integer.toBinaryString(pixel.getColor().getBlue());
        return values;
    }

    private static char ConvertBinaryValuesToCharacter(ArrayList<String> binaryValues) {
        StringBuilder endbinary = new StringBuilder("");
        for(int i = 0; i < binaryValues.size(); i++) {
            endbinary.append(binaryValues.get(i).charAt(binaryValues.get(i).length() - 1));
        }
        String endBinaryString = endbinary.toString();
        String noZeros = RemovePaddedZeros(endBinaryString);
        int ascii = Integer.parseInt(noZeros, 2);
        return (char)ascii;
    }

    private static String RemovePaddedZeros(String endBinaryString) {
        StringBuilder builder = new StringBuilder(endBinaryString);
        int paddedZeros = 0;
        for(int i = 0; i < builder.length(); i++) {
            if(builder.charAt(i) == '0') {
                paddedZeros++;
            } else {
                break;
            }
        }
        for(int i = 0; i < paddedZeros; i++) {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    private static boolean IsEndOfMessage(Pixel pixel) {
        if(TurnPixelIntergersToBinary(pixel)[2].endsWith("1")) {
            return false;
        } else {
            return true;
        }
    }
}
