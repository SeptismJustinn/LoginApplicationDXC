package com.septismjustinn.dxc.loginapp.services;

public class Encoder {
    private final char characterOffset;
    private final int offset;
    // ASCII A-Z is 65-90, 0-9 is 48-57, remaining chars are 40-47
    public Encoder(char c) {
        this.characterOffset = c;
        this.offset = getOffset(c);
    }

    private static int getOffset(char c) {
        if (c < 47) {
            // Special characters 101-108 => 36-43
            return c - 4;
        } else if (c < 10) {
            // Numbers 0-9: 91-100 => 26-35
            return c - 22;
        } else {
            // Alphabets A-Z: 65-90 => 0-25
            return c - 65;
        }
    }

    /**
     * Encode character based on offset
     * @param numerizedChar 65-108
     * @return If number falls between 91-100, return a number; 101-108 return a special character;
     * 65-90 return an alphabet; <65 loop back from 108.
     */
    private char encodeChar(int numerizedChar) {
        int targetChar = numerizedChar - this.offset;
        if (targetChar < 65) {
            //Special character
            targetChar += 44;
        }
        if (targetChar > 100) {
            targetChar -= 61;
        } else if (targetChar > 90) {
            targetChar -= 43;
        }
        return (char) targetChar;
    }

    /**
     * Encodes plainText based on char offset when instantiated
     * @param plainText String to be encoded
     * @return Encoded String, shifted based on this.offset
     */
    public String encode(String plainText) {
        String output = Character.toString(this.characterOffset);
        for (int i = 0; i < plainText.length(); i++) {
            char target = plainText.charAt(i);
            if (target == ' ') {
                output += " ";
                continue;
            }
            int targetValue = target;
            if (targetValue < 48) {
                //Special char 40-47 => 101-108
                targetValue += 61;
            } else if (targetValue < 58) {
                //Numbers 48-57 => 91-100
                targetValue += 43;
            }
            output += encodeChar(targetValue);

        }
        return output;
    }

    /**
     * Decode character based on offset
     * @param numerizedChar 65-108
     * @return If number falls between 91-100, return a number; 101-108 return a special character;
     * 65-90 return an alphabet; >108 loop back from 65.
     */
    private static char decodeChar(int offset, int numerizedChar) {
        int targetChar = numerizedChar + offset;
        if (targetChar > 108) {
            //Special character
            targetChar -= 44;
        }
        if (targetChar > 90) {
            targetChar -= 43;
        } else if (targetChar > 100) {
            targetChar -= 61;
        }
        return (char) targetChar;
    }

    /**
     * Decodes plainText encoded by any Encoder
     * @param plainText Text to be decoded, first char will be used as offset
     * @return Decoded plainText
     */
    public static String decode(String plainText) {
        String output = "";
        int offset = getOffset(plainText.charAt(0));
        for (int i = 1; i < plainText.length(); i++) {
            char target = plainText.charAt(i);
            if (target == ' ') {
                output += " ";
                continue;
            }
            int targetValue = target;
            if (targetValue < 48) {
                //Special char 40-47 => 101-108
                targetValue += 61;
            } else if (targetValue < 58) {
                //Numbers 48-57 => 91-100
                targetValue += 43;
            }
            output += decodeChar(offset, targetValue);
        }
        return output;
    }

}