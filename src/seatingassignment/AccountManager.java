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

/**
 * @author az15240
 */
public class AccountManager {
    public static final String SEPARATOR = ",";
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    public static Account currentAccount;
    
    /**
     * Add a new account to accounts
     * @param _username username of the account
     * @param _password password of the account
     */
    public static void addAccount(String _username, String _password) {
        Account _acc=new Account(_username,_password);
        accounts.add(_acc);
    }
    
    /**
     * Add a new account to accounts
     * @param _acc the account
     */
    public static void addAccount(Account _acc) {
        accounts.add(_acc);
    }
    
    /**
     * The user logs in to the system
     * @throws FileNotFoundException
     * @throws Exception 
     */
    public static void initAccount() throws FileNotFoundException, Exception {
        accounts=new ArrayList<Account>();
        
        //file input
        Scanner csvScanner = new Scanner(new File("Accounts.csv"));
        while(csvScanner.hasNext()) {
            String[] nextUserInfo=csvScanner.nextLine().split(SEPARATOR);
            AccountManager.addAccount(nextUserInfo[0], nextUserInfo[1]);
        }
        csvScanner.close();
    }
    
    /**
     * Get all accounts in a String format
     * @return all accounts in String format
     */
    public static String getAccountsInString() {
        String res="";
        for(Account acc:accounts) 
            res+=acc.getUsername()+", ";
        res=res.substring(0,res.length()-2);
        return res;
    }
    
    /**
     * Create a new account using client-selected username and password
     * @param myUsername client-selected username
     * @param myPassword client-selected password
     */
    public static void createNewAccount(String myUsername, String myPassword) {
        Account myAcc=new Account(myUsername,myPassword);
        AccountManager.addAccount(myAcc);

        try {
            //add the new account to Accounts.csv
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(new File("Accounts.csv")));
            for(Account _acc:accounts) {
                csvWriter.write(_acc.toCSV() + "\n");
                csvWriter.flush();
            }
            csvWriter.close();
            //add a new directory containing the Accounts information
            File f=new File("Accounts/"+myUsername);
            f.mkdir();
        } catch (IOException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        currentAccount=myAcc;

        try {
            //input student namelist.csv
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(new File("Accounts/"+myUsername+"/namelist.csv")));
            csvWriter.write("<Input student namelist here. One student per line.>\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Login to the system using client-selected username and password 
     * @param myUsername client-selected username
     * @param myPassword client-selected password
     * @return true if the account is found in database, false otherwise
     */
    public static boolean login(String myUsername, String myPassword) {
        for(Account acc:accounts) {
            if(acc.getUsername().equals(myUsername) && acc.getPassword().equals(myPassword)) {
                currentAccount=acc;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Delete an account using client-selected username and password
     * @param myUsername client-selected username
     * @param myPassword client-selected password
     * @return true if the account is found in database, false otherwise
     */
    public static boolean deleteAccount(String myUsername, String myPassword) {
        for(int i=0;i<accounts.size();i++) {
            if(accounts.get(i).getUsername().equals(myUsername) && accounts.get(i).getPassword().equals(myPassword)) {
                accounts.remove(i);

                try {
                    BufferedWriter csvWriter = new BufferedWriter(new FileWriter(new File("Accounts.csv")));
                    for(Account _acc:accounts) {
                        csvWriter.write(_acc.toCSV() + "\n");
                        csvWriter.flush();
                    }
                    csvWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }

                String path="Accounts/"+myUsername+"/";
                File folder = new File(path);
                File[] userFiles = folder.listFiles();
                for(File file : userFiles) {
                    file.delete();
                }
                folder.delete();
                return true;
            }
        }
        return false;
    }
}
