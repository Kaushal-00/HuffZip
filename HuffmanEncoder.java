import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {

    String encodeText(String filePath, HashMap<Character, String> huffmanCodeMap) {

        StringBuffer encodedText = new StringBuffer();

        try (FileReader fr = new FileReader(filePath)) {
            int c;
            while ((c = fr.read()) != -1) {
                char ch = (char) c;
                String code = huffmanCodeMap.get(ch);

                encodedText.append(code);
            }
        } catch (IOException e) {
            System.out.println("Error reading file for encoding: " + e.getMessage());
        }

        return encodedText.toString();
    }
}