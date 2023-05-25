import java.io.*;
import java.nio.file.*;

public class CaesarCipher {

  // For the Part 1 Encode Method
  public String encode(String message, int shift) {
    shift = shift % 65536;
    char[] plaintextChars = message.toCharArray();

    StringBuilder ciphertext = new StringBuilder();

    for (char ch : plaintextChars) {
      if (ch != ' ') {
        int asnum = (int) ch;
        asnum += shift;
        ciphertext.append((char) asnum);
      } else {
        ciphertext.append(ch);
      }
    }
    return ciphertext.toString();
  }

  // For the Part 2 Decode Method
  public String decode(String message, int shift) {
    String newMessage = encode(message, -shift);
    return newMessage.toString().replaceAll("[^a-zA-Z] ", "");
  }

  // For the Part 3 Decoding
  public int freqAnalysis(String text) {
    int[] counts = new int[100000];

    char[] array = text.toCharArray();

    for (int i = 0; i < array.length; i++) {

      int ASNUM = (int) array[i];
      if (ASNUM > 33) {
        counts[ASNUM] += 1;
      }
    }

    int highIndex = -1;
    int highValue = 0;
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > highValue) {
        highIndex = i;
        highValue = counts[i];
      }
    }

    return highIndex - 101;
  }

  public void decodeFile(String inputFilePath, String outputFilePath, int shift) throws IOException {
    String content = new String(Files.readAllBytes(Paths.get(inputFilePath)));
    String decodedContent = decode(content, shift);
    Files.write(Paths.get(outputFilePath), decodedContent.getBytes());
  }
}