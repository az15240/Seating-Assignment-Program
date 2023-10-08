/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatingassignment;

/**
 * @author az15240
 */
public class Account {
    private String username;
    private String password;
    
    /**
     * @param _username username of the account
     * @param _password password of the account
     */
    public Account(String _username, String _password) {
        username=_username;
        password=_password;
    }
    
    /**
     * @param _username username of the account
     * @param _password password of the account in char[] type
     */
    public Account(String _username, char[] _password) {
        username=_username;
        password=String.valueOf(_password);
    }
    
    /**
     * Get the value of the username
     * @return username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Set the value of the username to _username
     * @param _username username value assigned
     */
    public void setUsername(String _username) {
        username=_username;    
    }
    
    /**
     * Get the value of the password
     * @return password
     */
    public String getPassword() {
        return password;
    } 
    
    /**
     * Set the value of the password to _password
     * @param _password password value assigned
     */
    public void setPassword(String _password) {
        password=_password;
    } 
    
    /**
     * Overrides the toString method, return the String format of the Account
     * @return String format of the Account
     */
    @Override
    public String toString() {
        return username+","+password;
    }
    
    /**
     * Return the String of the Account in csv format
     * @return String of the Account in csv format
     */
    public String toCSV(){
        return String.format("%s,%s", username, password);
    }
}
