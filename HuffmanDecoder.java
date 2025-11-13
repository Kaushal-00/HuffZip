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

    private int currentByte = 0;
    private int bitPosition = 8;
    private FileInputStream fis;

    private int readBit() throws IOException {
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

    private char readCharacter() throws IOException {
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

    private DecodedNode rebuildTree() throws IOException {
        int bit = readBit();
        if (bit == -1) return null;

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

    private void decodeData(DecodedNode root, FileOutputStream fos) throws IOException {
        DecodedNode current = root;
        int bit;
        while ((bit = readBit()) != -1) {
            current = (bit == 0) ? current.left : current.right;
            if (current == null) {
                throw new IOException("Invalid bitstream: corrupted data detected.");
            }
            if (current.isLeaf()) {
                fos.write(current.ch);
                current = root;
            }
        }
    }

    public boolean decodeFile(String compressedFilePath, String outputFilePath) {
        try (FileInputStream in = new FileInputStream(compressedFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath)) {

            this.fis = in;
            this.currentByte = 0;
            this.bitPosition = 8;

            DecodedNode root = rebuildTree();
            if (root == null) {
                throw new IOException("Failed to rebuild Huffman tree.");
            }
            decodeData(root, fos);
            return true;
        } catch (IOException e) {
            System.out.println("Error: Decoding failed - " + e.getMessage());
            return false;
        }
    }
}
