import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {

    String generateEncodedText(String filePath, HashMap<Character, String> huffmanCodeMap) {
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

    void generateCompressedFile(String encodedText) {
        try {
            FileOutputStream fos = new FileOutputStream("Encoded.bin");
            int i = 0;

            while (i < encodedText.length()) {
                byte b = 0;

                for (int j = 0; j < 8; j++) {
                    b = (byte) (b << 1);

                    if (i < encodedText.length() && encodedText.charAt(i) == '1') {
                        b = (byte) (b | 1);
                    }

                    i++;
                }

                fos.write(b);
            }

            fos.close();
            System.out.println("Encoded binary file written successfully as Encoded.bin");

        } catch (IOException e) {
            System.out.println("Error writing compressed file: " + e.getMessage());
        }
    }
}
