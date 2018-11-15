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
    public void compilationUnit() throws IOException{
        switch(cnt.symbol){
            case ("package"):
                packageDeclaration();
                while(cnt.symbol.equals("import")){
                    importDeclaration();
                }
                while(cnt.symbol.equals("public") || cnt.symbol.equals("final") || cnt.symbol.equals("class") || cnt.symbol.equals("interface")){
                    classDeclaration();
                }
                break;
            case ("import"):
                while(cnt.symbol.equals("import")){
                    importDeclaration();
                }
                while(cnt.symbol.equals("public") || cnt.symbol.equals("final") || cnt.symbol.equals("class") || cnt.symbol.equals("interface")){
                    classDeclaration();
                }
                break;
        }
    }
    
    public void packageDeclaration() throws IOException{
        this.cnt.accept("package");
        packageName();
        this.cnt.accept(";");
    }
    
    public void importDeclaration() throws IOException{
        this.cnt.accept("import");
        packageName();
        importEnding();
    }
    
    public void packageName() throws IOException{
        identifier();
        while(cnt.symbol.equals(".")){
            this.cnt.accept(".");
            identifier();
        }
    }
    
    public void importEnding() throws IOException{
        switch(cnt.symbol){
            case ("."):
                this.cnt.accept(".");
                this.cnt.accept("*");
                this.cnt.accept(";");
                break;
            case (";"):
                this.cnt.accept(";");
                break;
        }
    }
    
    //Class
    public void classDeclaration() throws IOException{
        classModifier();
        classModifierExtension();
    }
    
    public void classModifierExtension() throws IOException{
        switch(cnt.symbol){
            case("final"):
                classModifier2();
                this.cnt.accept("class");
                classDeclarationExtension();
                break;
            case("class"):
                this.cnt.accept("class");
                classDeclarationExtension();
                break;
            case("abstract"):
                abstractModifier();
                typeDeclaration();
                break;
            case("interface"):
                this.cnt.accept("interface");
                interfaceDeclaration();
                break;
        }
    }
    
    public void typeDeclaration() throws IOException{
        switch(cnt.symbol){
            case("class"):
                this.cnt.accept("class");
                abstractClassDeclaration();
                break;
            case("interface"):
                this.cnt.accept("interface");
                interfaceDeclaration();
                break;
        }
    }
    
    public void classDeclarationExtension() throws IOException{
        identifier();
        super1();
        interfaces();
        classBody();
    }
    
    public void abstractClassDeclaration() throws IOException{
        identifier();
        super1();
        interfaces();
        abstractClassBody();
    }
    
    public void classModifier() throws IOException{
        if(cnt.symbol.equals("public")){
            this.cnt.accept("public");
        }
    }
    
    public void classModifier2() throws IOException{
        if(cnt.symbol.equals("final")){
            this.cnt.accept("final");
        }
    }
    
    public void abstractModifier() throws IOException{
        this.cnt.accept("abstract");
    }
    
    public void super1() throws IOException{
        if(cnt.symbol.equals("extends")){
            this.cnt.accept("extends");
            identifier();
        }
    }
    
    public void interfaces() throws IOException{
        if(cnt.symbol.equals("implements")){
            this.cnt.accept("implements");
            identifier();
            while(cnt.symbol.equals(",")){
                this.cnt.accept(",");
                identifier();
            }
        }
    }
    
    public void classBody() throws IOException{
        this.cnt.accept("{");
        while(cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("private") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile") 
                || cnt.symbol.equals("native") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            classBodyDeclaration();
        }
        this.cnt.accept("}");
    }
    
    public void classBodyDeclaration() throws IOException{
        if(cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile") 
                || cnt.symbol.equals("native") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            abstractMethodModifier();
            bodyDeclaration();
        } else if(cnt.symbol.equals("private")){
            this.cnt.accept("private");
            abstractBodyDeclaration();
        }
    }
    
    public void abstractMethodModifier() throws IOException{
        switch(cnt.symbol){
            case("public"):
                this.cnt.accept("public");
                break;
            case("private"):
                this.cnt.accept("private");
                break;
        }
    }
    
    public void abstractClassBody() throws IOException{
        this.cnt.accept("{");
        while(cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("private") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile") 
                || cnt.symbol.equals("native") || cnt.symbol.equals("abstract") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            abstractClassBodyDeclaration();
        }
        this.cnt.accept("}");
    }
    
    public void abstractClassBodyDeclaration() throws IOException{
        if(cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile") 
                || cnt.symbol.equals("native") || cnt.symbol.equals("abstract") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            abstractMethodModifier();
            bodyDeclaration();
        } else if(cnt.symbol.equals("private")){
            this.cnt.accept("private");
            abstractBodyDeclaration();
        }
    }
    
    public void bodyDeclaration() throws IOException{
        if(cnt.symbol.equals("transient") || cnt.symbol.equals("volatile")){
            fieldDeclaration();
        } else if(cnt.symbol.equals("native") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("void")){
            methodInitializer();
        } else if(cnt.symbol.equals("final")){
            this.cnt.accept("final");
            finalDeclaration();
        } else if(cnt.symbol.equals("static")){
            this.cnt.accept("static");
            staticOption();
        } else if(cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double") 
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int") 
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            dataTypeDeclaration();
        }
    }
    
    public void dataTypeDeclaration() throws IOException{
        if(cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double") 
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int") 
                || cnt.symbol.equals("long") || cnt.symbol.equals("char")){
            dataPrimitive();
            dataPrimitiveDeclaration();
        } else if(cnt.symbol.equals("_") || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            identifier();
            declarationType();
        }
    }
    
    public void dataPrimitiveDeclaration() throws IOException{
        if(cnt.symbol.equals("_") || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            identifier();
            variableOrMethodOption();
        } else if(cnt.symbol.equals("[")){
            arrayAfterDataType();
            variableLooping();
        }
    }
    
    public void declarationType() throws IOException{
        switch(cnt.symbol){
            case("("):
                constructorDeclaration();
                break;
            case("throws"):
                throws1();
                break;
            case("="):
                variableOperator();
                variableLooping();
                this.cnt.accept(";");
                break;
            case("["):
                arrayAfterDataType();
        }
    }
    
    public void abstractBodyDeclaration() throws IOException{
        if(cnt.symbol.equals("static") || cnt.symbol.equals("transient") || cnt.symbol.equals("final") 
                || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile") 
                || cnt.symbol.equals("native") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            bodyDeclaration();
        } else if(cnt.symbol.equals("abstract")){
            abstractMethodDeclaration();
        }
    }
    
    public void finalDeclaration() throws IOException{
        switch(cnt.symbol){
            case("transient"):
                fieldModifier3Declaration();
                break;
            case("synchronized"):
                finalAdditionalMod();
                break;
        }
    }
    
    public void staticOption() throws IOException{
        if(cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double") 
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int") 
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            dataTypeDeclaration();
        } else if(cnt.symbol.equals("final") || cnt.symbol.equals("void") || cnt.symbol.equals("synchronized") 
                || cnt.symbol.equals("transient") || cnt.symbol.equals("volatile") || cnt.symbol.equals("{")){
            staticDeclaration();
        }
    }
    
    public void staticDeclaration() throws IOException{
        if(cnt.symbol.equals("final") || cnt.symbol.equals("void")){
            staticMethodDeclaration();
        } else if(cnt.symbol.equals("{") || cnt.symbol.equals("transient") || cnt.symbol.equals("volatile")){
            staticInitializer();
        } else if(cnt.symbol.equals("synchronized")){
            synchronizedModifier();
            synchronizedMethodDeclaration();
        }
    }
    
    public void staticInitializer() throws IOException{
        if(cnt.symbol.equals("{")){
            block();
        } else if(cnt.symbol.equals("transient") || cnt.symbol.equals("volatile")){
            staticFieldDeclaration();
        }
    }
    
    //Interface
    public void interfaceDeclaration() throws IOException{
        identifier();
        extendInterfaces();
        interfaceBody();
    }
    
    public void extendsInterfaces() throws IOException{
        if(cnt.symbol.equals("extends")){
            this.cnt.accept("extends");
            identifier();
            while(cnt.symbol.equals(",")){
                this.cnt.accept(",");
                identifier();
            }
        }
    }
    
    public void interfaceBody() throws IOException{
        this.cnt.accept("{");
        while(cnt.symbol.equals("abstract") || cnt.symbol.equals("public")
                || cnt.symbol.equals("static") || cnt.symbol.equals("final")){
            interfaceMember();
        }
        this.cnt.accept("}");
    }
    
    public void interfaceMember() throws IOException{
        if(cnt.symbol.equals("abstract")){
            abstractMethodDeclaration();
        } else if(cnt.symbol.equals("public") || cnt.symbol.equals("static") || cnt.symbol.equals("final")){
            constantDeclaration();
        }
    }
    
    //Constructor
    public void constructorDeclaration() throws IOException{
        parameters();
        throws1();
        constructorBody();
    }
    
    public void parameters() throws IOException{
        this.cnt.accept("(");
        while(cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double") 
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int") 
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_") 
                || ((int)cnt.symbol.charAt(0)>=65 && (int)cnt.symbol.charAt(0)<=90)
                || ((int)cnt.symbol.charAt(0)>=97 && (int)cnt.symbol.charAt(0)<=122)){
            formalParameter();
        }
        this.cnt.accept(")");
    }
    
    public void formalParameter() throws IOException{
        dataType();
        identifier();
    }
    
    public void throws1() throws IOException
        if(cnt.symbol.equals("throws")){
            this.cnt.accept("throws");
            identifier();
            while(cnt.symbol.equals(",")){
                this.cnt.accept(",");
                identifier();
            }
        }
    }
    
    public void constructorBody() throws IOException{
        this.cnt.accept("{");
        
        this.cnt.accept("}");
    }
    
    //Field
    
    
    //Method
    
    
    //Data Type
    
    
    //Statement
    
    
    //Branching
    
    
    //Looping
    
    
    //Expression
    
    
    //Literal
}
