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
    void search() throws IOException {


        
    }
}