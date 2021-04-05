import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Lab3 extends JFrame implements ActionListener {
    static final long serialVersionUID = 1;
    JLabel stringLabel = new JLabel("String");
    JTextField stringText = new JTextField(10);
    JButton findButton = new JButton("Find");
    JTextArea result = new JTextArea(20, 40);
    JScrollPane scroller = new JScrollPane();
    RandomAccessFile indexFile = null;
    RandomAccessFile wordsFile = null;

    public Lab3() {
        setTitle("Word Check");
        setLayout(new java.awt.FlowLayout());
        setSize(500, 430);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(stringLabel);
        add(stringText);
        add(findButton);
        findButton.addActionListener(this);
        scroller.getViewport().add(result);
        add(scroller);
    }

    public static void main(String[] args) {
        Lab3 display = new Lab3();
        display.setVisible(true);
    }

    void getFiles() {
        String indexName = getFileName("Index File");
        if (indexName == null) {
            result.setText("No index file chosen");
            return;
        }
        String wordsName = getFileName("Words File");
        if (wordsName == null) {
            result.setText("No words file chosen");
            return;
        }
        try {
            indexFile = new RandomAccessFile(indexName, "r");
        } catch (FileNotFoundException e) {
            result.setText("Index file not found");
            return;
        }
        try {
            wordsFile = new RandomAccessFile(wordsName, "r");
        } catch (FileNotFoundException e) {
            result.setText("Words file not found");
            return;
        }
    }

    String getFileName(String title) {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle(title);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            return fc.getSelectedFile().getPath();
        else
            return null;
    }

    public void actionPerformed(ActionEvent evt) {
        if (indexFile == null || wordsFile == null)
            getFiles();
        try {
            search();
        } catch (IOException e) {
            result.setText("I/O eror occurred");
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    /* Do not change anything above this line */
    /* You must implement the search method as described in */
    /* the assignment */
    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * */
    
    /**
     * This searches the file
     * @throws IOException
     */
    void search() throws IOException {
        //Retrieve a file to search
        if (this.indexFile != null && this.wordsFile != null) {

        }
        else {this.getFiles();}
        //Initializes the word to find. 
        String wordToFind = this.stringText.getText().trim().toLowerCase();


        //Total number of words in words 

        int numWords = (int)(this.indexFile.length() / 4);
        
        System.out.println(numWords);

        //Is the word in the dictionary?
        boolean isInDictionary = this.findInDictionary(wordToFind, 0, numWords - 1) >= 0; 

        if (isInDictionary) {
            this.result.setText(this.result.getText()  + wordToFind + " is in the dictionary\n");
         } else {
            this.result.setText(this.result.getText()  + wordToFind + " is NOT in the dictionary\n");
         }   
    }
    /**
     * This finds an item in a dictionary recursively using a binary search. 
     * @param toFind this is the word to find
     * @param lowIndex the lowest index, by default 0
     * @param maxIndex the maximum index to find, by default the length of the index file (over 4 since there are 4 bytes to an index)
     * @return The index of the word. Or -1 if it doesn't exist. 
     */
    int findInDictionary(String toFind, int lowIndex, int maxIndex) throws IOException
    { 
        if (lowIndex >= maxIndex) {
            return -1;
        }
        else {
            try{

                if (maxIndex - lowIndex <=1)
                {
                    return -1;
                }

                int average = (maxIndex + lowIndex) / 2;

                //This is the word we are comparing the user's word to.

                String foundWord = findWordGivenIndex(average);

                //compareTo lexiographically compares 2 different strings,  if one is greater than we know it is later in the alphabet.
                //if s is earlier n the alphabet to compared, then it returns a negative number
                if (foundWord.compareTo(toFind) < 0)
                {
                    return findInDictionary(toFind, average, maxIndex);
                }
                if (foundWord.compareTo(toFind) == 0)
                {
                   //They are the same word, so we return the index of the word
                    return average;
                }
                if (foundWord.compareTo(toFind) > 0)
                {
                    return findInDictionary(toFind, lowIndex, average);
                }
            }
            catch (Error e) {
                System.out.println("Something went wrong with your program.");
                return -1;
            }
            return -1;
        }
        
    }
        
    /**
     * Finds a word given the index of the word. 
     * @param index the index of the word
     * @return the word that is found inside the words file.
     */
    String findWordGivenIndex (int index) throws IOException 
    {   
        
        
        // get byte at index * 4
        // convert bytes to int. 
        // get byte at index *4 + 4
        // convert vytes to int.

        // seek to the first index
        
        // substring of the first index to second index
        // reutrn the substring




        
        indexFile.seek((long)(index * 4));
        int startingIndex = this.indexFile.readInt();
        int endingIndex = this.indexFile.readInt();
        System.out.println("Starting Index: " + String.valueOf(startingIndex));
        System.out.println("Ending Index: " + String.valueOf(endingIndex));
        wordsFile.seek((long)startingIndex);

        String wordToReturn = parseWord((endingIndex - startingIndex)/2);
       

        System.out.println(wordToReturn);
        return wordToReturn;


        // String wordToReturn = (wordsFile.readUTF()).substring(0, endingIndex - startingIndex);
        // System.out.println(wordToReturn);
        // return wordToReturn;
    
    }
    String parseWord(int distance) throws IOException
    {
        if (distance < 1) {
            return "";
        }
        else {
            return wordsFile.readChar() + parseWord(distance - 1);
        }

    }



}