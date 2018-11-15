package Model;

import Controller.Controller;
import java.io.IOException;

/**
 * @author 
 * Sukamto 23518017
 * Andreas Novian 23518002
 */
public class ProgramDeclaration {
    Controller cnt;
    
    public ProgramDeclaration(Controller cont){
        this.cnt = cont;
    }
    
    public void procedureA() throws IOException{
        procedureB();
        this.cnt.accept(".");
    }
    
    public void procedureB() throws IOException{
        switch(cnt.symbol){
            case ("x"):this.cnt.accept("x");
                break;
            case "(":this.cnt.accept("(");
                procedureC();
                this.cnt.accept(")");
                break;
            case "[":this.cnt.accept("[");
                procedureB();
                this.cnt.accept("]");
                break;
        }
    }
    
    public void procedureC() throws IOException{
        procedureB();
        procedureD();
    }
    
    public void procedureD() throws IOException{
        while(cnt.symbol.equals("+")){
            cnt.accept("+");
            procedureB();
        }
    }
    
    //Program Declaration
    
    
    //Class
    
    
    //Interface
    
    
    //Constructor
    
    
    //Field
    
    
    //Method
    
    
    //Data Type
    
    
    //Statement
    
    
    //Branching
    
    
    //Looping
    
    
    //Expression
    
    
    //Literal
}
