# **HuffZip:**

- **HuffZip – Huffman Text Compressor** is a Java-based implementation of the Huffman Coding Algorithm for compressing and decompressing `.txt` files. 
- It helps reduce file size using binary encoding while maintaining lossless compression.
- This project demonstrates how compression can be achieved efficiently by encoding characters based on their frequencies in the input text.

---

## **How HuffZip Works:**

### **Compression Phase:**

- **Input:** A plain `.txt` file.
- **Steps:**

    - **Frequency Map Generation:** Count the occurrences of each character.
    - **Huffman Tree Construction:** Build a binary tree where less frequent characters go deeper.
    - **Code Assignment:** Traverse the tree and assign binary codes to each character.
    - **Tree Serialization:** Convert the tree to a binary-friendly format using preorder traversal (uses `'1'` for leaf nodes and ASCII for characters).
    - **File Encoding:**
        Encode the tree and the text into a binary stream.
        Save the compressed output to `Encoded.bin`.

---

### **Decompression Phas- **

- **Input:** The compressed `Encoded.bin` file.
- **Steps:**

  - **Tree Deserialization:** Reconstruct the Huffman tree from the encoded file.
  - **Text Decoding:** Decode the binary content using the tree.
  - **Output File:** Original text is recovered and written to `Decoded.txt`.

---

## **Project Structure:**

```
├── FrequencyMap.java           # Builds the frequency map
├── HuffmanNode.java            # Node structure for the Huffman Tree
├── HuffmanTree.java            # Generates the Huffman Tree
├── HuffmanCode.java            # Creates Huffman code map and tree serialization
├── HuffmanEncoder.java         # Handles compression and writing binary file
├── HuffmanDecoder.java         # Handles tree reconstruction and decompression
├── Main.java                   # Entry point to run compression
├── testFile.txt                # Sample file to test compression (~60 KB)
└── README.md                   # Project documentation
```

---

## **Compression Example**

- To demonstrate the effectiveness of HuffZip:

    - **Input File:** `testFile.txt` (≈ 60 KB)
    - **Output Compressed File:** `Encoded.bin` (≈ 28–29 KB)
    - **Compression Ratio:** \~48%

---

## **How to Use HuffZip:**

- **Compile the files:**

   ```bash
   javac *.java
   ```

- **Run the Main class:**

   ```bash
   java Main
   ```

- **Provide input:**

   * Enter the full path to a `.txt` file when prompted.

- **Generated Output:**

   - `Encoded.bin` — Compressed binary file.
   - `Decoded.txt` — Decompressed text file.

---