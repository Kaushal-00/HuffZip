import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanEncoder {

    public void generateCompressedFile(String filePath,
                                       HashMap<Character, String> huffmanCodeMap,
                                       StringBuffer huffmanTreeCode,
                                       String outputFileName) {
        try (FileReader fr = new FileReader(filePath);
             FileOutputStream fos = new FileOutputStream(outputFileName)) {

            byte b = 0;
            int bitCounter = 0;

            // Write serialized tree: preorder with '1' + 8-bit char for leaf, '0' for internal.
            for (int i = 0; i < huffmanTreeCode.length(); i++) {
                char ch = huffmanTreeCode.charAt(i);

                if (ch == '1' || ch == '0') {
                    b = (byte) (b << 1);
                    if (ch == '1') {
                        b = (byte) (b | 1);
                    }
                    bitCounter++;
                    if (bitCounter == 8) {
                        fos.write(b);
                        b = 0;
                        bitCounter = 0;
                    }
                } else {
                    // write the character as 8 bits
                    String charBits = String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0');
                    for (int j = 0; j < charBits.length(); j++) {
                        b = (byte) (b << 1);
                        if (charBits.charAt(j) == '1') {
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
            }

            // Now write data bits
            int c;
            while ((c = fr.read()) != -1) {
                char ch = (char) c;
                String code = huffmanCodeMap.get(ch);
                if (code == null) {
                    throw new IOException("Missing code for character: " + (int) ch);
                }
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

            // pad remaining bits in last byte
            if (bitCounter > 0) {
                b = (byte) (b << (8 - bitCounter));
                fos.write(b);
            }

        } catch (IOException e) {
            System.out.println("Error: Failed to write compressed file - " + e.getMessage());
        }
    }
}
