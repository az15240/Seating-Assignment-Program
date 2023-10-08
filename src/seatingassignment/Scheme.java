/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatingassignment;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import static seatingassignment.AccountManager.currentAccount;

/**
 * @author az15240
 */
public class Scheme {
    
    private Layout layout;
    private ArrayList<Student> students;
    private int numberOfStudents;
    private String[][] mapNames;
    private String outputText;
    
    /**
     * Constructor
     * Create a normal scheme
     * @param _layout layout of the scheme
     * @param _students List of students
     */
    public Scheme(Layout _layout, ArrayList<Student> _students) {
        layout=_layout;
        students=_students;
        numberOfStudents=students.size();
        mapNames=new String[_layout.getRows()+1][_layout.getColumns()+1];
        outputText="";
    }

    /**
     * Get the layout of the scheme
     * @return layout of the scheme
     */
    public Layout getLayout() {
        return layout;
    }
    
    /**
     * Set the layout of the scheme to _layout
     * @param _layout layout value assigned
     */
    public void setLayout(Layout _layout) {
        layout=_layout;
    }
    
    /**
     * Get the student list in the scheme
     * @return student list in the scheme
     */
    public ArrayList<Student> getStudentList() {
        return students;
    }
        
    /** 
     * Get the number of students in the scheme
     * @return number of students in the scheme
     */
    public int getNumberOfStudent() {
        return numberOfStudents;
    }
    
    /**
     * Set the student list in the scheme to _students
     * @param _students student list assigned
     */
    public void setStudentList(ArrayList<Student> _students) {
        students=_students;
    }
    
    /**
     * Get the outputText value
     * @return outputText value
     */
    public String getOutputText() {
        return outputText;
    }
    
    /**
     * Set the outputText value to be str
     * @param str outputText value assigned 
     */
    public void setOutputText(String str) {
        outputText=str;
    }
    
    /**
     * Start randomly assigning seats
     */
    public void RandomSeating() {
        int filled=0;
        boolean OK=false;
        ArrayList<String> seats=new ArrayList<String>();
        HashMap<String,Pair> pos=new HashMap<String,Pair>();
        HashMap<String,Integer> book=new HashMap<String,Integer>();
        while(OK==false) {// the purpose of having it is to allow other seating methods. e.g. when there is other requirements, at the end of the loop something will occur...
            outputText="";
            OK=true;
            filled=0;
            book.clear();
            seats.clear();
            pos.clear();
            while(filled<students.size()) {
                int randm=(int)(Math.random()*students.size());
                if(book.get(students.get(randm).getName())==null) {
                    book.put(students.get(randm).getName(),1);
                    seats.add(students.get(randm).getName());
                    filled++;
                }
            }
            
            int tempnum=0;
            for(int i=1;i<=layout.getRows();i++) {
                for(int j=1;j<=layout.getColumns();j++) {
                    if(layout.getMap()[i][j]) {
                        outputText+=GUI.padLeftZeros(seats.get(tempnum),GUI.maxLengthOfNames);
                        mapNames[i][j]=seats.get(tempnum);
                        pos.put(seats.get(tempnum),new Pair(i,j));
                        tempnum++;
                    }
                    else {
                        outputText+=GUI.padLeftZeros("",GUI.maxLengthOfNames);
                        mapNames[i][j]="";
                    }
                }
                outputText+="\n";
            }
            //check if every pair of adjacent students can seat together
            for(int i=1;i<=layout.getRows();i++) {
                for(int j=1;j<=layout.getColumns();j++) {
                    String studentAName = mapNames[i][j], studentBName;
                    if(i+1<=layout.getRows()) {
                        studentBName = mapNames[i+1][j];
                        if(StudentManager.checkIfCanSeatTogether(studentAName, studentBName) == false) {
                            OK=false;
                            break;
                        }
                    }
                    if(j+1<=layout.getColumns()) {
                        studentBName = mapNames[i][j+1];
                        if(StudentManager.checkIfCanSeatTogether(studentAName, studentBName) == false) {
                            OK=false;
                            break;
                        }
                    }
                    if(i+1<=layout.getRows() && j+1<=layout.getColumns()) {
                        studentBName = mapNames[i+1][j+1];
                        if(StudentManager.checkIfCanSeatTogether(studentAName, studentBName) == false) {
                            OK=false;
                            break;
                        }
                    }
                    if(OK==false) break;
                }
                if(OK==false) break;
            }
            if(OK==true) break;
        }
    }
    
    /**
     * Save current seating scheme to seating.csv
     */
    public void saveScheme() {
        String path = "Accounts/"+currentAccount.getUsername()+"/seating.csv";
        try {
            File file1 = new File(path);
            file1.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
                for(int i=1;i<=layout.getRows();i++) {
                    for(int j=1;j<=layout.getColumns();j++) {
                        writer.write(mapNames[i][j] + ",");
                    }
                    writer.write("\n");
                    writer.flush();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(path));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(false); //originally set to true; may change later
        //fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".csv",".csv")); // not working!!!
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
