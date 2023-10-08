/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class Layout {

    private String name;
    private int rows;
    private int columns;
    private ArrayList<Pair> positionCanceled;
    private int numberCanceled;
    private int numberOfSeats;
    private boolean[][] map;
    private int counter=1;
    //public static ArrayList<Integer> emptyColumns;  // not implemented
    
    /**
     * Construct a Layout object
     * @param _name name of the layout
     * @param _rows number of rows
     * @param _columns number of columns 
     * @param _numberCanceled number of positions to be canceled
     */
    public Layout(String _name, int _rows, int _columns, int _numberCanceled) {
        name=_name;
        rows=_rows;
        columns=_columns;
        numberCanceled=_numberCanceled;
        positionCanceled=new ArrayList<Pair>();
        numberOfSeats=rows*columns-numberCanceled;
        map=new boolean[rows+1][columns+1];
        
        for(int i=1;i<=rows;i++)
            for(int j=1;j<=columns;j++) {
                map[i][j]=true;
            }
    }
    
    /**
     * Construct a Layout object
     * @param _name name of the layout
     * @param _rows number of rows
     * @param _columns number of columns
     * @param _numberCanceled number of positions to be canceled
     * @param _positionCanceled positions canceled in the layout 
     */
    public Layout(String _name, int _rows, int _columns, int _numberCanceled, ArrayList<Pair> _positionCanceled) {
        name=_name;
        rows=_rows;
        columns=_columns;
        positionCanceled=_positionCanceled;
        numberCanceled=_numberCanceled;
        numberOfSeats=rows*columns-numberCanceled;
        map=new boolean[rows+1][columns+1];
       
        for(int i=1;i<=rows;i++)
            for(int j=1;j<=columns;j++) {
                map[i][j]=true;
            }
        for(Pair pr:_positionCanceled) {
            map[pr.getX()][pr.getY()]=false;
        }
    }
    
    /**
     * Construct a Layout object by reading from a file
     * @param _file the Layout file
     * @throws FileNotFoundException 
     */
    public Layout(File _file) throws FileNotFoundException {
        Scanner scn=new Scanner(System.in);
        Scanner fileReader=new Scanner(_file);
        String fileName=_file.getName();
        name=fileName.substring(0,fileName.length()-4);
        rows=Integer.parseInt(fileReader.next());
        columns=Integer.parseInt(fileReader.next());
        numberCanceled=Integer.parseInt(fileReader.next());
        numberOfSeats=rows*columns-numberCanceled;
        positionCanceled=new ArrayList<Pair>();
        
        for(int i=0;i<numberCanceled;i++) {
            String str=fileReader.next();
            int pos=str.indexOf(",");
            int _x=Integer.parseInt(str.substring(1,pos));
            int _y=Integer.parseInt(str.substring(pos+1,str.length()-1));
            Pair newPair=new Pair(_x,_y);
            positionCanceled.add(newPair);
        }
        map=new boolean[rows+1][columns+1];
        for(int i=1;i<=rows;i++)
            for(int j=1;j<=columns;j++) {
                map[i][j]=true;
            }
        for(Pair pr:positionCanceled) {
            map[pr.getX()][pr.getY()]=false;
        }
        
        scn.close();
        fileReader.close();
    }
    
    /**
     * Get the name of the layout
     * @return the name of the layout
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the name to _name
     * @param _name name value assigned
     */
    public void setName(String _name) {
        name=_name;
    }
    
    /**
     * Get the row number of the layout
     * @return row number of the layout
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Set the row number of the layout to _rows
     * @param _rows row number assigned
     */
    public void setRows(int _rows) {
        rows=_rows;
    }
    
    /**
     * Get the column number of the layout
     * @return column number of the layout
     */
    public int getColumns() {
        return columns;
    }
    
    /**
     * Set the column number of the layout to _columns
     * @param _columns column number assigned
     */
    public void setColumns(int _columns) {
        columns=_columns;
    }
    
    /**
     * Get the positions of those canceled blocks
     * @return ArrayList of canceled positions
     */
    public ArrayList<Pair> getPositionCanceled() {
        return positionCanceled;
    }
    
    /**
     * Set the canceled positions to _posCanceled
     * @param _positionCanceled canceled positions assigned
     */
    public void setPositionCanceled(ArrayList<Pair> _positionCanceled) {
        positionCanceled=_positionCanceled;
    }
    
    /**
     * Add a new position canceled
     * @param _x x coordinate of the canceled position
     * @param _y y coordinate of the canceled position
     */
    public void addPositionCanceled(int _x, int _y) {
        positionCanceled.add(new Pair(_x,_y));
    }
    
    /**
     * Get the number of positions canceled
     * @return number of positions canceled
     */
    public int getNumberCanceled() {
        return numberCanceled;
    }
    
    /**
     * Set the number of positions canceled to _numberCanceled
     * @param _numberCanceled number of positions canceled
     */
    public void setNumberCanceled(int _numberCanceled) {
        numberCanceled=_numberCanceled;
    }
    
    /**
     * Get the number of seats in the layout
     * @return number of seats
     */
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    /**
     * Set the number of seats in the layout to _numSeats
     * @param _numberOfSeats number of seats assigned
     */
    public void setNumberOfSeats(int _numberOfSeats) {
        numberOfSeats=_numberOfSeats;
    }
    
    /**
     * Get the map display of the current layout
     * @return map display of the layout
     */
    public boolean[][] getMap() {
        return map;
    }
    
    /**
     * Set the map display of the current layout to _map
     * @param _map the given map layout
     */
    public void setMap(boolean[][] _map) {
        map=_map;
    }
    
    /**
     * Get the condition of seating at coordinate (_x,_y)
     * @param _x x coordinate of the point
     * @param _y y coordinate of the point
     * @return true if there is a seat at (_x,_y), false otherwise
     */
    public boolean getMapAtPosition(int _x, int _y) {
        return map[_x][_y];
    }
    
    /**
     * Set the condition of seating at coordinate (_x_,y) to be not seated
     * Currently it only supports setting a position to be canceled
     * @param _x
     * @param _y 
     */
    public void setMapAtPosition(int _x, int _y) {
        map[_x][_y]=false; 
    }
    
    /**
     * Get the counter value
     * @return counter value
     */
    public int getCounter() {
        return counter;
    }
    
    /**
     * Set the counter value to _counter
     * @param _counter the given counter value
     */
    public void setCounter(int _counter) {
        counter=_counter;
    }
    
    /**
     * Increase the counter value by 1
     */
    public void incrementCounter() {
        counter++;
    }
    
    /**
     * Display the current map layout in terminal
     */
    public void displayMap() {
        for(int i=1;i<=rows;i++) {
            for(int j=1;j<=columns;j++) {
                if(map[i][j]==true) {
                    System.out.print("________ ");
                }
                else {
                    System.out.print("xxxxxxxx ");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Get the map of the layout in String format
     * @return map of the layout in String format
     */
    public String getMapInString() {
        String returnStr="";
        for(int i=1;i<=rows;i++) {
            for(int j=1;j<=columns;j++) {
                if(map[i][j]==true) {
                    returnStr+="________ ";
                }
                else {
                    returnStr+="xxxxxxxx ";
                }
            }
            returnStr+="\n";
        }
        return returnStr;
    }
    
    /**
     * Get all layouts in ArrayList<Layout> format
     * @return all layouts in ArrayList<Layout> format
     */
    public static ArrayList<Layout> getLayoutsInArrayListLayout() {
        ArrayList<Layout> layouts = new ArrayList<Layout>();
        File folder = new File("Accounts/"+currentAccount.getUsername()+"/");
        File[] userFiles = folder.listFiles();
        for(File file : userFiles) {
            if(file.getName().contains(".txt")) {
                try {
                    layouts.add(new Layout(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return layouts;
    }
    
    /**
     * Get all layouts in String format
     * @return all layouts in String format
     */
    public static String getLayoutsInString() {
        String theNames = "";
        File folder = new File("Accounts/"+currentAccount.getUsername()+"/");
        File[] userFiles = folder.listFiles();
        for(File file : userFiles) {
            if(file.getName().contains(".txt")) {
                theNames+=file.getName().substring(0,file.getName().length()-4)+"\n";
            }
        }
        //theNames=theNames.substring(0,theNames.length()-2);
        return theNames;
    }
    
    /**
     * Get all layouts in ArrayList<String> format
     * @return all layouts in ArrayList<String> format
     */
    public static ArrayList<String> getLayoutsInArrayListString() {
        ArrayList<String> theNames = new ArrayList<String>();
        File folder = new File("Accounts/"+currentAccount.getUsername()+"/");
        File[] userFiles = folder.listFiles();
        for(File file : userFiles) {
            if(file.getName().contains(".txt")) {
                theNames.add(file.getName().substring(0,file.getName().length()-4));
            }
        }
        return theNames;
    }
    
    /**
     * Save the current layout in a txt file
     */
    public void saveLayout() {
        try {
            //write the configuration into a txt file
            String pathName = "Accounts/" + currentAccount.getUsername() + "/" + this.getName() + ".txt";
            File file1 = new File(pathName);
            file1.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathName));
            writer.write(this.getRows() + "\n");
            writer.write(this.getColumns() + "\n");
            writer.write(this.getNumberCanceled() + "\n");
            for(Pair pr:this.getPositionCanceled()) 
                writer.write(pr+"\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Layout.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     * Delete the layout of a given name
     * @param yourLayoutChoice name of the layout to be deleted
     * @return true if the layout name is found, false otherwise
     */
    public static boolean deleteLayout(String yourLayoutChoice) {
        ArrayList<String> currentLayoutsInArrayList = Layout.getLayoutsInArrayListString();
        for(String str:currentLayoutsInArrayList) {
            if(str.equals(yourLayoutChoice)) {
                String path = "Accounts/"+currentAccount.getUsername()+"/"+yourLayoutChoice+".txt";
                File file1 = new File(path);
                file1.delete();
                return true;
            }
        }
        return false;
    }
}
