import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws Exception {
        String encodedFilePath = "lib/input.txt";
        String decodedFilePath = "lib/output.txt";
        CaesarCipher cipher = new CaesarCipher();
        try {
            String encodedContent = new String(Files.readAllBytes(Paths.get(encodedFilePath)));

            // Estimate the shift value
            int estimatedShift = cipher.freqAnalysis(encodedContent);
            System.out.println("Estimated shift: " + estimatedShift);

            // Decode the file
            cipher.decodeFile(encodedFilePath, decodedFilePath, estimatedShift);
            System.out.println("File decoded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
}
