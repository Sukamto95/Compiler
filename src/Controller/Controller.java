package Controller;


import Model.ProgramDeclaration;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sukam
 */
public class Controller {
    
    BufferedReader br;
    BufferedWriter bw;
    String isiFile = "";
    char symbol;
    int cursor;
    boolean isError = false;
    
    public Controller() throws FileNotFoundException{
        br = new BufferedReader(new FileReader("input.txt"));
    }
    
    public void start() throws IOException{
        bw = new BufferedWriter(new FileWriter(new File("output.txt")));
        cursor = 0;
        String currentLine;
        while((currentLine=br.readLine())!=null){
            isiFile += currentLine +"\n";
        }
        symbol = isiFile.charAt(cursor);
        new ProgramDeclaration(this).procedureA();
        if(cursor < isiFile.length()-2){
            isError = true;
            bw.write("(Error)"+isiFile.substring(cursor));
        } else if(!isError){
            bw.write("\nTidak ada error\n");
        }
        bw.close();
        br.close();
    }
    
    public void accept(char terminal) throws IOException{
        System.out.println("terminal - symbol = "+terminal+" - "+symbol);
        if(terminal == symbol){
            bw.write(symbol);
            readNextSymbol();
        } else{
            isError = true;
            bw.write("(Error)"+symbol);
            readNextSymbol();
        }
    }
    
    public void readNextSymbol(){
        cursor++;
        if(cursor < isiFile.length()){
            this.symbol = isiFile.charAt(cursor);
        }
    }
    
    public char getSymbol(){
        return this.symbol;
    }
}
