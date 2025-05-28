import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {

    void generateCompressedFile(String filePath, HashMap<Character, String> huffmanCodeMap) {
        try {
            FileReader fr = new FileReader(filePath);
            FileOutputStream fos = new FileOutputStream("Encoded.bin");

            int c;
            byte b = 0;
            int bitCounter = 0;

            while ((c = fr.read()) != -1) {
                char ch = (char) c;
                String code = huffmanCodeMap.get(ch);

                for (int i = 0; i < code.length(); i++) {
                    b = (byte) (b << 1);
                    if (code.charAt(i) == '1') {
                        b = (byte) (b | 1);
                    }
                    bitCounter++;

                    if (bitCounter == 8) {
                        fos.write(b);
                        b = 0;
                        bitCounter = 0;
                    }
                }
            }

            if (bitCounter > 0) {
                b = (byte) (b << (8 - bitCounter));
                fos.write(b);
            }

            fr.close();
            fos.close();
            System.out.println("Encoded binary file written successfully as Encoded.bin");

        } catch (IOException e) {
            System.out.println("Error writing compressed file: " + e.getMessage());
        }
    }
}