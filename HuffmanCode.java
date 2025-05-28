import java.util.HashMap;

public class HuffmanCode {

    HashMap<Character, String> generateHuffmanCodeMap(HuffmanNode root) {

        HashMap<Character, String> huffmanCodeMap = new HashMap<>();
        generateHuffmanCode(root, "", huffmanCodeMap);

        return huffmanCodeMap;
    }

    void generateHuffmanCode(HuffmanNode node, String code, HashMap<Character, String> huffmanCodeMap) {
        
        if (node == null) {
            return;
        }

        if (node.ch != '\0') {
            huffmanCodeMap.put(node.ch, code);
            return;
        }

        generateHuffmanCode(node.left, code + "0", huffmanCodeMap);
        generateHuffmanCode(node.right, code + "1", huffmanCodeMap);
    }

    StringBuffer generateHuffmanTreeCode (HuffmanNode root) {

        StringBuffer huffmanTreeCode = new StringBuffer();

        generateTreeCode(root, huffmanTreeCode);

        return huffmanTreeCode;
    }

    void generateTreeCode(HuffmanNode root, StringBuffer huffmanTreeCode) {

        if (root == null) {
            return;
        }

        if(root.left == null && root.right == null) {

            huffmanTreeCode.append('1');
            huffmanTreeCode.append(root.ch);

        } else {

            huffmanTreeCode.append('0');
            generateTreeCode(root.left, huffmanTreeCode);
            generateTreeCode(root.right, huffmanTreeCode);

        }
    }
}
