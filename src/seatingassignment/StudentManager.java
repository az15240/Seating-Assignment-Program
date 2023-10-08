/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seatingassignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static seatingassignment.AccountManager.currentAccount;

/**
 * @author az15240
 */
public class StudentManager {
    
    public static ArrayList<Student> students;
    
    /**
     * Load student list from namelist.csv
     * @return an ArrayList of students
     */
    public static ArrayList<Student> loadStudentList() {
        students = new ArrayList<Student>();
        String thePath="Accounts/"+currentAccount.getUsername()+"/namelist.csv";
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File(thePath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        //read student namelist
        while(fileReader.hasNext()) {
            String str=fileReader.nextLine();
            String studentName;
            int index=str.indexOf(",");
            int notSeatWithNumber;
            if(index == -1) {
                studentName = str;
                notSeatWithNumber=0;
                students.add(new Student(studentName,notSeatWithNumber));
            }
            else {
                studentName = str.substring(0,index);
                notSeatWithNumber = Integer.parseInt(str.substring(str.indexOf(",")+1));
                students.add(new Student(studentName,notSeatWithNumber));
            }
        }
        fileReader.close();
        return students;
    }
    
    /**
     * Save students' properties
     */
    public static void saveStudentsProperties() {
        String path = "Accounts/"+currentAccount.getUsername()+"/namelist.csv";
        try {
            File file1 = new File(path);
            file1.createNewFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
                for(Student stu:students) {
                    writer.write(stu.getName() + "," + stu.getNotSeatWithNumber() + "\n");
                    writer.flush();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Find the ancestor of student of index A
     * @param A index of the student
     * @return ancestor of the student
     */
    public static int find(int A) {
        Student stuA=students.get(A);
        if(A==stuA.getNotSeatWithNumber()) return A;
        stuA.setNotSeatWithNumber(find(stuA.getNotSeatWithNumber()));
        return stuA.getNotSeatWithNumber();
    }
    
    /**
     * Join students of index A and B together in the same set
     * @param A index of the first student
     * @param B index of the second student
     */
    public static void join(int A, int B) {
        int a=find(A),b=find(B);
        Student stuA=students.get(a), stuB=students.get(b);
        stuB.setNotSeatWithNumber(a);
    }
    
    /**
     * Set students named studentAName and studentBName to be in the same set
     * @param studentAName the first name
     * @param studentBName the second name
     * @return false if an error occurred, true otherwise
     */
    public static boolean setCannotSeatTogether(String studentAName, String studentBName) {
        if(studentAName.equals(studentBName)) {
            return false;
        }
        Student stuA = null, stuB = null;
        StudentManager.loadStudentList();
        
        for(Student stu : students) {
            if(stu.getName().equals(studentAName)) stuA=stu;
            else if(stu.getName().equals(studentBName)) stuB=stu;
        }
        if(stuA==null || stuB==null) return false;
        
        int A=students.indexOf(stuA), B=students.indexOf(stuB);      
        join(A,B);
        StudentManager.saveStudentsProperties();
        return true;
    }
    
    /**
     * Check if students named studentAName and studentBName can seat together
     * @param studentAName the first name
     * @param studentBName the second name
     * @return true if their ancestor are different (to be in different sets), false otherwise
     */
    public static boolean checkIfCanSeatTogether(String studentAName, String studentBName) {
        if(studentAName.equals("")) 
            return true;
        if(studentBName.equals(""))
            return true;
        
        if(studentAName.equals(studentBName)) {
            return false;
        }
        Student stuA = null, stuB = null;
        StudentManager.loadStudentList();
        
        for(Student stu : students) {
            if(stu.getName().equals(studentAName)) stuA=stu;
            else if(stu.getName().equals(studentBName)) stuB=stu;
        }
        if(stuA==null || stuB==null) return false;
        
        int A=students.indexOf(stuA), B=students.indexOf(stuB);
        
        int fatherA=find(A), fatherB=find(B);
        if(fatherA == fatherB) 
            return false; // cannot seat together
        return true; //can seat together
    }
}
