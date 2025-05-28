import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanDecoder {

    static class DecodedNode {

        char ch;
        DecodedNode left, right;

        DecodedNode(char ch) {
            this.ch = ch;
        }

        DecodedNode() {
            this.ch = '\0';
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }

    int currentByte = 0;
    int bitPosition = 8;
    FileInputStream fis;

    int readBit() throws IOException {

        if (bitPosition == 8) {
            currentByte = fis.read();
            bitPosition = 0;
        }

        if (currentByte == -1) {
            return -1;
        }

        int bit = (currentByte >> (7 - bitPosition)) & 1;
        bitPosition++;

        return bit;
    }

    char readCharacter() throws IOException {

        int asciiValue = 0;

        for (int i = 0; i < 8; i++) {
            int bit = readBit();
            if (bit == -1) {
                return '\0';
            }
            asciiValue = (asciiValue << 1) | bit;
        }

        return (char) asciiValue;
    }

    DecodedNode rebuildTree() throws IOException {

        int bit = readBit();

        if (bit == -1) {
            return null;
        }

        if (bit == 1) {

            char character = readCharacter();
            return new DecodedNode(character);

        } else {

            DecodedNode node = new DecodedNode();
            node.left = rebuildTree();
            node.right = rebuildTree();

            return node;
        }
    }

    void decodeData(DecodedNode root, FileOutputStream fos) throws IOException {

        DecodedNode current = root;
        int bit;

        while ((bit = readBit()) != -1) {
            if (bit == 0) {
                current = current.left;
            } else {
                current = current.right;
            }

            if (current.isLeaf()) {
                fos.write(current.ch);
                current = root;
            }
        }
    }

    void decodeFile() {

        try {
            fis = new FileInputStream("Encoded.bin");
            FileOutputStream fos = new FileOutputStream("Decoded.txt");

            DecodedNode root = rebuildTree();
            decodeData(root, fos);

            fis.close();
            fos.close();

            System.out.println("Decoded file saved successfully as Decoded.txt");

        } catch (IOException e) {
            System.out.println("Error during decoding: " + e.getMessage());
        }
    }
}
