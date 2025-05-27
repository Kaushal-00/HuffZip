public class HuffmanNode{
    char ch;
    int frequency;

    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char ch, int frequency){
        this.ch = ch;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right){
        this.ch = '\0';
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }
}