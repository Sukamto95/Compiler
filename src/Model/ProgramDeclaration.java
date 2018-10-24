package Model;

import Controller.Controller;
import java.io.IOException;

/**
 *
 * @author sukam, andre
 */
public class ProgramDeclaration {
    Controller controller;
    
    public ProgramDeclaration(Controller cont){
        this.controller = cont;
    }
    
    public void procedureA() throws IOException{
        procedureB();
        this.controller.accept('.');
    }
    
    public void procedureB() throws IOException{
        char symbol = this.controller.getSymbol();
        switch(symbol){
            case 'x':this.controller.accept('x');
                break;
            case '(':this.controller.accept('(');
                procedureC();
                this.controller.accept(')');
                break;
            case '[':this.controller.accept('[');
                procedureB();
                this.controller.accept(']');
                break;
        }
    }
    
    public void procedureC() throws IOException{
        procedureB();
        procedureD();
    }
    
    public void procedureD() throws IOException{
        char symbol = this.controller.getSymbol();
        while(symbol == '+'){
            this.controller.accept('+');
            symbol = this.controller.getSymbol();
            procedureB();
        }
    }
}
