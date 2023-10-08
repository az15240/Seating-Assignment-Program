/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatingassignment;

/**
 * @author az15240
 */
public class Student {
    
    private String name;
    private Pair position;
    private int notSeatWithNumber;
    
    /**
     * Create a Student object with a name
     * @param _name name of the student
     */
    public Student(String _name) {
        name=_name;
    }
    
    /**
     * Create a Student object with the name and the position
     * @param _name name of the student
     * @param _position position of the student
     */
    public Student(String _name, Pair _position) {
       name=_name;
       position=_position;
       notSeatWithNumber=-1;//default
    }
    
    /**
     * Create a Student object with the name and the "not seat with" number
     * @param _name name of the student
     * @param _notSeatWithNumber "not seat with" number of the student
     */
    public Student(String _name, int _notSeatWithNumber) {
       name=_name;
       notSeatWithNumber=_notSeatWithNumber;
    }
    
    /**
     * Create a Student object with the name, the position, and the "not seat with" number
     * @param _name name of the student
     * @param _position position of the student
     * @param _notSeatWithNumber "not seat with" number of the student
     */
    public Student(String _name, Pair _position, int _notSeatWithNumber) {
       name=_name;
       position=_position;
       notSeatWithNumber=_notSeatWithNumber;
    }
    
    /**
     * Get the name of the student
     * @return name of the student
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the name of the student to _name
     * @param _name name assigned
     */
    public void setName(String _name) {
        name=_name;
    }
    
    /**
     * Get the position of the student
     * @return position of the student
     */
    public Pair getPos() {
        return position;
    }
    
    /**
     * Set the position of the student to _pos
     * @param _pos position assigned
     */
    public void setPos(Pair _pos) {
        position=_pos;
    }
    
    /**
     * Get the advanced feature of the student
     * @return advanced feature of the student
     */
    public int getNotSeatWithNumber() {
        return notSeatWithNumber;
    }
    
    /** 
     * Set the advanced feature of the student
     * @param _notSeatWithNumber advanced feature assigned
     */
    public void setNotSeatWithNumber(int _notSeatWithNumber) {
        notSeatWithNumber=_notSeatWithNumber;
    }
}
