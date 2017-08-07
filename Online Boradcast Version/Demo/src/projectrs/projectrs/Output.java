/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectrs;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kakas
 */
public class Output extends Thread{
    private DataOutputStream out;
    private Scanner sc = new Scanner(System.in);
    private String name;
    Output(String s) {
        name = s;
    }
    @Override
    public void run(){
        try {
            takeOutput();
        } catch (IOException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void setOutput(DataOutputStream a){
        out = a;
    }
    
    void takeOutput() throws IOException{
        //while(true){
            //s = sc.nextLine();
            out.writeUTF(name);
        
    }
}
