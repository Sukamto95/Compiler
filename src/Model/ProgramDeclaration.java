package Model;

import Controller.Controller;
import java.io.IOException;

/**
 * @author Sukamto 23518017 Andreas Novian 23518002
 */
public class ProgramDeclaration {

    Controller cnt;

    public ProgramDeclaration(Controller cont) {
        this.cnt = cont;
    }

    public void procedureA() throws IOException {
        procedureB();
        this.cnt.accept(".");
    }

    public void procedureB() throws IOException {
        switch (cnt.symbol) {
            case ("x"):
                this.cnt.accept("x");
                break;
            case "(":
                this.cnt.accept("(");
                procedureC();
                this.cnt.accept(")");
                break;
            case "[":
                this.cnt.accept("[");
                procedureB();
                this.cnt.accept("]");
                break;
        }
    }

    public void procedureC() throws IOException {
        procedureB();
        procedureD();
    }

    public void procedureD() throws IOException {
        while (cnt.symbol.equals("+")) {
            cnt.accept("+");
            procedureB();
        }
    }

    //Program Declaration
    public void compilationUnit() throws IOException {
        switch (cnt.symbol) {
            case ("package"):
                packageDeclaration();
                while (cnt.symbol.equals("import")) {
                    importDeclaration();
                }
                while (cnt.symbol.equals("public") || cnt.symbol.equals("final") || cnt.symbol.equals("class") || cnt.symbol.equals("interface")) {
                    classDeclaration();
                }
                break;
            case ("import"):
                while (cnt.symbol.equals("import")) {
                    importDeclaration();
                }
                while (cnt.symbol.equals("public") || cnt.symbol.equals("final") || cnt.symbol.equals("class") || cnt.symbol.equals("interface")) {
                    classDeclaration();
                }
                break;
        }
    }

    public void packageDeclaration() throws IOException {
        this.cnt.accept("package");
        packageName();
        this.cnt.accept(";");
    }

    public void importDeclaration() throws IOException {
        this.cnt.accept("import");
        packageName();
        importEnding();
    }

    public void packageName() throws IOException {
        identifier();
        while (cnt.symbol.equals(".")) {
            this.cnt.accept(".");
            identifier();
        }
    }

    public void importEnding() throws IOException {
        switch (cnt.symbol) {
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
    public void classDeclaration() throws IOException {
        classModifier();
        classModifierExtension();
    }

    public void classModifierExtension() throws IOException {
        switch (cnt.symbol) {
            case ("final"):
                classModifier2();
                this.cnt.accept("class");
                classDeclarationExtension();
                break;
            case ("class"):
                this.cnt.accept("class");
                classDeclarationExtension();
                break;
            case ("abstract"):
                abstractModifier();
                typeDeclaration();
                break;
            case ("interface"):
                this.cnt.accept("interface");
                interfaceDeclaration();
                break;
        }
    }

    public void typeDeclaration() throws IOException {
        switch (cnt.symbol) {
            case ("class"):
                this.cnt.accept("class");
                abstractClassDeclaration();
                break;
            case ("interface"):
                this.cnt.accept("interface");
                interfaceDeclaration();
                break;
        }
    }

    public void classDeclarationExtension() throws IOException {
        identifier();
        super1();
        interfaces();
        classBody();
    }

    public void abstractClassDeclaration() throws IOException {
        identifier();
        super1();
        interfaces();
        abstractClassBody();
    }

    public void classModifier() throws IOException {
        if (cnt.symbol.equals("public")) {
            this.cnt.accept("public");
        }
    }

    public void classModifier2() throws IOException {
        if (cnt.symbol.equals("final")) {
            this.cnt.accept("final");
        }
    }

    public void abstractModifier() throws IOException {
        this.cnt.accept("abstract");
    }

    public void super1() throws IOException {
        if (cnt.symbol.equals("extends")) {
            this.cnt.accept("extends");
            identifier();
        }
    }

    public void interfaces() throws IOException {
        if (cnt.symbol.equals("implements")) {
            this.cnt.accept("implements");
            identifier();
            while (cnt.symbol.equals(",")) {
                this.cnt.accept(",");
                identifier();
            }
        }
    }

    public void classBody() throws IOException {
        this.cnt.accept("{");
        while (cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("private") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile")
                || cnt.symbol.equals("native") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            classBodyDeclaration();
        }
        this.cnt.accept("}");
    }

    public void classBodyDeclaration() throws IOException {
        if (cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile")
                || cnt.symbol.equals("native") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            abstractMethodModifier();
            bodyDeclaration();
        } else if (cnt.symbol.equals("private")) {
            this.cnt.accept("private");
            abstractBodyDeclaration();
        }
    }

    public void abstractMethodModifier() throws IOException {
        switch (cnt.symbol) {
            case ("public"):
                this.cnt.accept("public");
                break;
            case ("private"):
                this.cnt.accept("private");
                break;
        }
    }

    public void abstractClassBody() throws IOException {
        this.cnt.accept("{");
        while (cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("private") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile")
                || cnt.symbol.equals("native") || cnt.symbol.equals("abstract") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            abstractClassBodyDeclaration();
        }
        this.cnt.accept("}");
    }

    public void abstractClassBodyDeclaration() throws IOException {
        if (cnt.symbol.equals("public") || cnt.symbol.equals("protected") || cnt.symbol.equals("static")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("final") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile")
                || cnt.symbol.equals("native") || cnt.symbol.equals("abstract") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            abstractMethodModifier();
            bodyDeclaration();
        } else if (cnt.symbol.equals("private")) {
            this.cnt.accept("private");
            abstractBodyDeclaration();
        }
    }

    public void bodyDeclaration() throws IOException {
        if (cnt.symbol.equals("transient") || cnt.symbol.equals("volatile")) {
            fieldDeclaration();
        } else if (cnt.symbol.equals("native") || cnt.symbol.equals("synchronized") || cnt.symbol.equals("void")) {
            methodInitializer();
        } else if (cnt.symbol.equals("final")) {
            this.cnt.accept("final");
            finalDeclaration();
        } else if (cnt.symbol.equals("static")) {
            this.cnt.accept("static");
            staticOption();
        } else if (cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double")
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int")
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            dataTypeDeclaration();
        }
    }

    public void dataTypeDeclaration() throws IOException {
        if (cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double")
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int")
                || cnt.symbol.equals("long") || cnt.symbol.equals("char")) {
            dataPrimitive();
            dataPrimitiveDeclaration();
        } else if (cnt.symbol.equals("_") || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            identifier();
            declarationType();
        }
    }

    public void dataPrimitiveDeclaration() throws IOException {
        if (cnt.symbol.equals("_") || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            identifier();
            variableOrMethodOption();
        } else if (cnt.symbol.equals("[")) {
            arrayAfterDataType();
            variableLooping();
        }
    }

    public void declarationType() throws IOException {
        switch (cnt.symbol) {
            case ("("):
                constructorDeclaration();
                break;
            case ("throws"):
                throws1();
                break;
            case ("="):
                variableOperator();
                variableLooping();
                this.cnt.accept(";");
                break;
            case ("["):
                arrayAfterDataType();
        }
    }

    public void abstractBodyDeclaration() throws IOException {
        if (cnt.symbol.equals("static") || cnt.symbol.equals("transient") || cnt.symbol.equals("final")
                || cnt.symbol.equals("synchronized") || cnt.symbol.equals("volatile")
                || cnt.symbol.equals("native") || cnt.symbol.equals("void") || cnt.symbol.equals("boolean")
                || cnt.symbol.equals("float") || cnt.symbol.equals("double") || cnt.symbol.equals("byte") || cnt.symbol.equals("short")
                || cnt.symbol.equals("int") || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            bodyDeclaration();
        } else if (cnt.symbol.equals("abstract")) {
            abstractMethodDeclaration();
        }
    }

    public void finalDeclaration() throws IOException {
        switch (cnt.symbol) {
            case ("transient"):
                fieldModifier3Declaration();
                break;
            case ("synchronized"):
                finalAdditionalMod();
                break;
        }
    }

    public void staticOption() throws IOException {
        if (cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double")
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int")
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            dataTypeDeclaration();
        } else if (cnt.symbol.equals("final") || cnt.symbol.equals("void") || cnt.symbol.equals("synchronized")
                || cnt.symbol.equals("transient") || cnt.symbol.equals("volatile") || cnt.symbol.equals("{")) {
            staticDeclaration();
        }
    }

    public void staticDeclaration() throws IOException {
        if (cnt.symbol.equals("final") || cnt.symbol.equals("void")) {
            staticMethodDeclaration();
        } else if (cnt.symbol.equals("{") || cnt.symbol.equals("transient") || cnt.symbol.equals("volatile")) {
            staticInitializer();
        } else if (cnt.symbol.equals("synchronized")) {
            synchronizedModifier();
            synchronizedMethodDeclaration();
        }
    }

    public void staticInitializer() throws IOException {
        if (cnt.symbol.equals("{")) {
            block();
        } else if (cnt.symbol.equals("transient") || cnt.symbol.equals("volatile")) {
            staticFieldDeclaration();
        }
    }

    //Interface
    public void interfaceDeclaration() throws IOException {
        identifier();
        extendInterfaces();
        interfaceBody();
    }

    public void extendsInterfaces() throws IOException {
        if (cnt.symbol.equals("extends")) {
            this.cnt.accept("extends");
            identifier();
            while (cnt.symbol.equals(",")) {
                this.cnt.accept(",");
                identifier();
            }
        }
    }

    public void interfaceBody() throws IOException {
        this.cnt.accept("{");
        while (cnt.symbol.equals("abstract") || cnt.symbol.equals("public")
                || cnt.symbol.equals("static") || cnt.symbol.equals("final")) {
            interfaceMember();
        }
        this.cnt.accept("}");
    }

    public void interfaceMember() throws IOException {
        if (cnt.symbol.equals("abstract")) {
            abstractMethodDeclaration();
        } else if (cnt.symbol.equals("public") || cnt.symbol.equals("static") || cnt.symbol.equals("final")) {
            constantDeclaration();
        }
    }

    //Constructor
    public void constructorDeclaration() throws IOException {
        parameters();
        throws1();
        constructorBody();
    }

    public void parameters() throws IOException {
        this.cnt.accept("(");
        while (cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double")
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int")
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            formalParameter();
        }
        this.cnt.accept(")");
    }

    public void formalParameter() throws IOException {
        dataType();
        identifier();
    }

    public void throws1() throws IOException {
        if (cnt.symbol.equals("throws")) {
            this.cnt.accept("throws");
            identifier();
            while (cnt.symbol.equals(",")) {
                this.cnt.accept(",");
                identifier();
            }
        }
    }

    public void constructorBody() throws IOException {
        this.cnt.accept("{");
        while (cnt.symbol.equals("this") || cnt.symbol.equals("super") || cnt.symbol.equals("new")
                || cnt.symbol.equals("++") || cnt.symbol.equals("--") || cnt.symbol.equals("{")
                || cnt.symbol.equals(";") || cnt.symbol.equals("switch") || cnt.symbol.equals("do")
                || cnt.symbol.equals("break") || cnt.symbol.equals("continue") || cnt.symbol.equals("return")
                || cnt.symbol.equals("synchronized") || cnt.symbol.equals("throws") || cnt.symbol.equals("try")
                || cnt.symbol.equals("if") || cnt.symbol.equals("while") || cnt.symbol.equals("for")
                || cnt.symbol.equals("boolean") || cnt.symbol.equals("float") || cnt.symbol.equals("double")
                || cnt.symbol.equals("byte") || cnt.symbol.equals("short") || cnt.symbol.equals("int")
                || cnt.symbol.equals("long") || cnt.symbol.equals("char") || cnt.symbol.equals("_")
                || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            blockStatement();
        }
        this.cnt.accept("}");
    }

    public void explicitConstructorInvocation() throws IOException {
        switch (cnt.symbol) {
            case ("this"):
                this.cnt.accept("this");
                this.cnt.accept("(");
                argumentList();
                this.cnt.accept(")");
                this.cnt.accept(";");
                break;
            case ("super"):
                this.cnt.accept("super");
                this.cnt.accept("(");
                argumentList();
                this.cnt.accept(")");
                this.cnt.accept(";");
                break;
        }
    }

    //Field
    public void fieldDeclaration() throws IOException {
        fieldAdditionalModifiers();
        dataType();
        variableDeclarators();
        this.cnt.accept(";");
    }

    public void staticFieldDeclaration() throws IOException {
        fieldModifier2Initializer();
        dataType();
        variableDeclarators();
        this.cnt.accept(";");
    }

    public void fieldModifier2() throws IOException {
        this.cnt.accept("transient");
    }

    public void fieldModifier3() throws IOException {
        this.cnt.accept("volatile");
    }

    public void staticModifier() throws IOException {
        this.cnt.accept("static");
    }

    public void fieldAdditionalModifiers() throws IOException {
        switch (cnt.symbol) {
            case ("transient"):
                fieldModifier2();
                staticModifierInitializer();
                break;
            case ("volatile"):
                fieldModifier3();
                fieldModifier3Declaration();
                break;
        }
    }

    public void staticModifierInitializer() throws IOException {
        switch (cnt.symbol) {
            case ("volatile"):
                fieldModifier3();
                staticModifier();
                break;
            case ("static"):
                staticModifier();
                fieldModifier3Initializer();
                break;
        }
    }

    public void fieldModifier2Initializer() throws IOException {
        switch (cnt.symbol) {
            case ("volatile"):
                fieldModifier3();
                fieldModifier2();
                break;
            case ("transient"):
                fieldModifier2();
                fieldModifier3Initializer();
                break;
        }
    }

    public void fieldModifier3Initializer() throws IOException {
        if (cnt.symbol.equals("volatile")) {
            fieldModifier3();
        }
    }

    public void fieldModifier3Declaration() throws IOException {
        fieldModifier2();
        staticModifier();
    }

    public void variableOrMethodOption() throws IOException {
        if (cnt.symbol.equals("(")) {
            parameters();
            throws1();
        } else if (cnt.symbol.equals("[") || cnt.symbol.equals("=")) {
            variableOption();
            variableLooping();
            this.cnt.accept(";");
        }
    }

    public void variableDeclarators() throws IOException {
        variableDeclarator();
        while (cnt.symbol.equals(",")) {
            this.cnt.accept(",");
            variableDeclarator();
        }
    }

    public void variableDeclarator() throws IOException {
        if (cnt.symbol.equals("_") || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            identifier();
            variableOption();
        } else if (cnt.symbol.equals("[")) {
            arrayAfterDataType();
        }
    }

    public void arrayAfterDataType() throws IOException {
        this.cnt.accept("[");
        this.cnt.accept("]");
        while (cnt.symbol.equals("[")) {
            this.cnt.accept("[");
            this.cnt.accept("]");
        }
        arrayDeclaration();
    }

    public void arrayDeclaration() throws IOException {
        if (cnt.symbol.equals("_") || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            identifier();
            arrayInitializer();
        }
    }

    public void variableOption() throws IOException {
        switch (cnt.symbol) {
            case ("="):
                variableOperator();
                break;
            case ("["):
                this.cnt.accept("[");
                this.cnt.accept("]");
                while (cnt.symbol.equals("[")) {
                    this.cnt.accept("[");
                    this.cnt.accept("]");
                }
                arrayInitializer();
                break;
        }
    }

    public void variableOperator() throws IOException {
        if (cnt.symbol.equals("=")) {
            this.cnt.accept("=");
            variableInitializer();
        }
    }

    public void variableInitializers() throws IOException {
        if (cnt.symbol.equals("_") || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122) || cnt.symbol.equals("--")
                || cnt.symbol.equals("++") || cnt.symbol.equals("(") || cnt.symbol.equals("+")
                || cnt.symbol.equals("-") || cnt.symbol.equals("~") || cnt.symbol.equals("new")
                || cnt.symbol.equals("super") || cnt.symbol.equals("this") || cnt.symbol.equals("true")
                || cnt.symbol.equals("false") || cnt.symbol.equals("null") || (int) cnt.symbol.charAt(0) == 34
                || (int) cnt.symbol.charAt(0) == 39 || ((int) cnt.symbol.charAt(0) >= 48 && (int) cnt.symbol.charAt(0) <= 57)) {
            variableInitializer();
            while (cnt.symbol.equals(",")) {
                this.cnt.accept(",");
                variableInitializer();
            }
        }
    }

    public void variableInitializer() throws IOException {
        expression();
    }

    public void variableLooping() throws IOException {
        while (cnt.symbol.equals(",")) {
            this.cnt.accept(",");
            identifier();
            variableOption();
        }
    }

    public void arrayInitializer() throws IOException {
        this.cnt.accept("=");
        this.cnt.accept("{");
        arrayTypeInitializer();
        this.cnt.accept("}");
    }

    public void arrayTypeInitializer() throws IOException {
        if (cnt.symbol.equals("_") || ((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122) || cnt.symbol.equals("--")
                || cnt.symbol.equals("++") || cnt.symbol.equals("(") || cnt.symbol.equals("+")
                || cnt.symbol.equals("-") || cnt.symbol.equals("~") || cnt.symbol.equals("new")
                || cnt.symbol.equals("super") || cnt.symbol.equals("this") || cnt.symbol.equals("true")
                || cnt.symbol.equals("false") || cnt.symbol.equals("null") || (int) cnt.symbol.charAt(0) == 34
                || (int) cnt.symbol.charAt(0) == 39 || ((int) cnt.symbol.charAt(0) >= 48 && (int) cnt.symbol.charAt(0) <= 57)) {
            variableInitializers();
        } else if (cnt.symbol.equals("{")) {
            this.cnt.accept("{");
            variableInitializers();
            this.cnt.accept("}");
            while (cnt.symbol.equals(",")) {
                this.cnt.accept(",");
                this.cnt.accept("{");
                variableInitializers();
                this.cnt.accept("}");
            }
        }
    }

    public void constantDeclaration() throws IOException {
        constantModifiers();
        dataType();
        variableDeclarator();
        this.cnt.accept(";");
    }

    public void constantModifiers() throws IOException {
        switch (cnt.symbol) {
            case ("public"):
                this.cnt.accept("public");
                break;
            case ("static"):
                this.cnt.accept("static");
                break;
            case ("final"):
                this.cnt.accept("final");
                break;
        }
    }

    //Method
    public void methodInitializer() throws IOException {
    }

    public void methodDeclaration() throws IOException {
    }

    public void staticMethodDeclaration() throws IOException {
    }

    public void resultType() throws IOException {
    }

    public void finalModifier() throws IOException {
    }

    public void synchronizedModifier() throws IOException {
    }

    public void methodAdditionalModifier() throws IOException {
    }

    public void staticAdditionalMod() throws IOException {
    }

    public void synchronizedMethodDeclaration() throws IOException {
    }

    public void finalAdditionalMod() throws IOException {
    }

    public void synchronizedAdditionalMod() throws IOException {
    }

    public void staticModInitializer() throws IOException {
    }

    public void finalModInitializer() throws IOException {
    }

    public void synchronizedModInitializer() throws IOException {
    }

    public void methodDeclarator() throws IOException {
    }

    public void nativeMethodDeclaration() throws IOException {
    }

    public void nativeModifier() throws IOException {
    }

    public void abstractMethodDeclaration() throws IOException {
    }

    public void methodInvocation() throws IOException {
    }

    //Data Type
    public void dataType() throws IOException {
    }

    public void dataPrimitive() throws IOException {
    }

    public void primitiveType() throws IOException {
    }

    //Statement
    public void block() throws IOException {
    }

    public void blockStatement() throws IOException {
    }

    public void blockStatementOption() throws IOException {
    }

    public void statementWithoutExpressionStatement() throws IOException {
    }

    public void statement() throws IOException {
    }

    public void emptyStatement() throws IOException {
    }

    public void labeledStatement() throws IOException {
    }

    public void expressionStatementWithoutIdentifier() throws IOException {
    }

    public void expressionStatement() throws IOException {
    }

    public void identifierStatement() throws IOException {
    }

    public void statementExpression() throws IOException {
    }

    public void statementExpr() throws IOException {
    }

    public void postIdentifier() throws IOException {
    }

    //Branching
    public void ifStatement() throws IOException {
    }

    public void elseStatement() throws IOException {
    }

    public void switchStatement() throws IOException {
    }

    public void switchBlock() throws IOException {
    }

    public void switchBlockStatementGroup() throws IOException {
    }

    public void switchLabel() throws IOException {
    }

    //Looping
    public void whileStatement() throws IOException {
    }

    public void doStatement() throws IOException {
    }

    public void forStatement() throws IOException {
    }

    public void localVariableDeclaration() throws IOException {
    }

    public void forInit() throws IOException {
    }

    public void forUpdate() throws IOException {
    }

    public void statementExpressionList() throws IOException {
    }

    public void breakStatement() throws IOException {
    }

    public void continueStatement() throws IOException {
    }

    public void breakContinueIdentifier() throws IOException {
    }

    public void returnStatement() throws IOException {
    }

    public void throwsStatement() throws IOException {
    }

    public void synchronizedStatement() throws IOException {
    }

    public void tryStatement() throws IOException {
    }

    public void catchStatement() throws IOException {
    }

    public void finallyInitializer() throws IOException {
    }

    public void catches() throws IOException {
    }

    public void catchClause() throws IOException {
    }

    public void finallyStatement() throws IOException {
    }

    //Expression
    public void assignmentExpression() throws IOException {
    }

    public void expression() throws IOException {
    }

    public void multiplicativeOperator() throws IOException {
    }

    public void additiveExpression() throws IOException {
    }

    public void additiveOperator() throws IOException {
    }

    public void shiftExpression() throws IOException {
    }

    public void shiftOperator() throws IOException {
    }

    public void relationalExpression() throws IOException {
    }

    public void relationalOperator() throws IOException {
    }

    public void equalityExpression() throws IOException {
    }

    public void equalityOperator() throws IOException {
    }

    public void andExpression() throws IOException {
    }

    public void andOperator() throws IOException {
    }

    public void exclusiveOr() throws IOException {
    }

    public void exclusiveOperator() throws IOException {
    }

    public void inclusiveOr() throws IOException {
    }

    public void inclusiveOrOperator() throws IOException {
    }

    public void conditionalAnd() throws IOException {
    }

    public void conditionalAndOperator() throws IOException {
    }

    public void conditionalOr() throws IOException {
    }

    public void conditionalOrOperator() throws IOException {
    }

    public void castType() throws IOException {
    }

    public void unaryExpression() throws IOException {
    }

    public void incrementDecrement() throws IOException {
    }

    public void postExpressionName() throws IOException {
    }

    public void arrayOrAssignment() throws IOException {
    }

    public void primary() throws IOException {
    }

    public void instanceCreationExpression() throws IOException {
    }

    public void creationExpression() throws IOException {
    }

    public void classInstanceCreationExpression() throws IOException {
    }

    public void argumentList() throws IOException {
    }

    public void arrayCreation() throws IOException {
    }

    public void dimExpressions() throws IOException {
    }

    public void dimExpression() throws IOException {
    }

    public void dims() throws IOException {
    }

    public void assignment() throws IOException {
    }

    public void assignmentOperator() throws IOException {
    }

    public void fieldAccess() throws IOException {
    }

    public void arrayAccess() throws IOException {
    }

    public void expressionName() throws IOException {
    }

    public void identifier() throws IOException {
        if (((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
            alphabet();
            while (((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                    || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)
                    || ((int) cnt.symbol.charAt(0) >= 48 && (int) cnt.symbol.charAt(0) <= 57)
                    || cnt.symbol.equals("_")) {
                while (((int) cnt.symbol.charAt(0) >= 48 && (int) cnt.symbol.charAt(0) <= 57)) {
                    digit();
                }
                while (((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                        || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
                    alphabet();
                }
                while (cnt.symbol.equals("_")) {
                    this.cnt.accept("_");
                }
            }
        } else if (cnt.symbol.equals("_")) {
            this.cnt.accept("_");
            while (((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                    || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)
                    || ((int) cnt.symbol.charAt(0) >= 48 && (int) cnt.symbol.charAt(0) <= 57)
                    || cnt.symbol.equals("_")) {
                while (((int) cnt.symbol.charAt(0) >= 48 && (int) cnt.symbol.charAt(0) <= 57)) {
                    digit();
                }
                while (((int) cnt.symbol.charAt(0) >= 65 && (int) cnt.symbol.charAt(0) <= 90)
                        || ((int) cnt.symbol.charAt(0) >= 97 && (int) cnt.symbol.charAt(0) <= 122)) {
                    alphabet();
                }
                while (cnt.symbol.equals("_")) {
                    this.cnt.accept("_");
                }
            }
        }
    }

    //Literal
    public void literal() throws IOException {
    }

    public void numberLiteral() throws IOException {
    }

    public void zeroNumberOption() throws IOException {
    }

    public void nonZeroOption() throws IOException {
    }

    public void integerTypeSuffix() throws IOException {
    }

    public void digit() throws IOException {
        if (cnt.symbol.equals("0")) {
            this.cnt.accept("0");
        } else if ((int) cnt.symbol.charAt(0) >= 49 && (int) cnt.symbol.charAt(0) <= 57) {
            nonZeroDigit();
        }
    }

    public void nonZeroDigit() throws IOException {
        switch (cnt.symbol) {
            case "1":
                this.cnt.accept("1");
                break;
            case "2":
                this.cnt.accept("2");
                break;
            case "3":
                this.cnt.accept("3");
                break;
            case "4":
                this.cnt.accept("4");
                break;
            case "5":
                this.cnt.accept("5");
                break;
            case "6":
                this.cnt.accept("6");
                break;
            case "7":
                this.cnt.accept("7");
                break;
            case "8":
                this.cnt.accept("8");
                break;
            case "9":
                this.cnt.accept("9");
                break;
        }
    }

    public void hexNumeral() throws IOException {
    }

    public void hexDigit() throws IOException {
    }

    public void octalNumeral() throws IOException {
    }

    public void octalDigit() throws IOException {
    }

    public void floatingPointLiteral() throws IOException {
    }

    public void exponentPart() throws IOException {
    }

    public void exponentIndicator() throws IOException {
    }

    public void signedInteger() throws IOException {
    }

    public void sign() throws IOException {
    }

    public void floatTypeSuffix() throws IOException {
    }

    public void booleanLiteral() throws IOException {
    }

    public void characterLiteral() throws IOException {
    }

    public void singleCharacter() throws IOException {
    }

    public void stringLiteral() throws IOException {
    }

    public void alphabet() throws IOException {
        switch (cnt.symbol) {
            case "A":
                this.cnt.accept("A");
                break;
            case "B":
                this.cnt.accept("B");
                break;
            case "C":
                this.cnt.accept("C");
                break;
            case "D":
                this.cnt.accept("D");
                break;
            case "E":
                this.cnt.accept("E");
                break;
            case "F":
                this.cnt.accept("F");
                break;
            case "G":
                this.cnt.accept("G");
                break;
            case "H":
                this.cnt.accept("H");
                break;
            case "I":
                this.cnt.accept("I");
                break;
            case "J":
                this.cnt.accept("J");
                break;
            case "K":
                this.cnt.accept("K");
                break;
            case "L":
                this.cnt.accept("L");
                break;
            case "M":
                this.cnt.accept("M");
                break;
            case "N":
                this.cnt.accept("N");
                break;
            case "O":
                this.cnt.accept("O");
                break;
            case "P":
                this.cnt.accept("P");
                break;
            case "Q":
                this.cnt.accept("Q");
                break;
            case "R":
                this.cnt.accept("R");
                break;
            case "S":
                this.cnt.accept("S");
                break;
            case "T":
                this.cnt.accept("T");
                break;
            case "U":
                this.cnt.accept("U");
                break;
            case "V":
                this.cnt.accept("V");
                break;
            case "W":
                this.cnt.accept("W");
                break;
            case "X":
                this.cnt.accept("X");
                break;
            case "Y":
                this.cnt.accept("Y");
                break;
            case "Z":
                this.cnt.accept("Z");
                break;
            case "a":
                this.cnt.accept("a");
                break;
            case "b":
                this.cnt.accept("b");
                break;
            case "c":
                this.cnt.accept("c");
                break;
            case "d":
                this.cnt.accept("d");
                break;
            case "e":
                this.cnt.accept("e");
                break;
            case "f":
                this.cnt.accept("f");
                break;
            case "g":
                this.cnt.accept("g");
                break;
            case "h":
                this.cnt.accept("h");
                break;
            case "i":
                this.cnt.accept("i");
                break;
            case "j":
                this.cnt.accept("j");
                break;
            case "k":
                this.cnt.accept("k");
                break;
            case "l":
                this.cnt.accept("l");
                break;
            case "m":
                this.cnt.accept("m");
                break;
            case "n":
                this.cnt.accept("n");
                break;
            case "o":
                this.cnt.accept("o");
                break;
            case "p":
                this.cnt.accept("p");
                break;
            case "q":
                this.cnt.accept("q");
                break;
            case "r":
                this.cnt.accept("r");
                break;
            case "s":
                this.cnt.accept("s");
                break;
            case "t":
                this.cnt.accept("t");
                break;
            case "u":
                this.cnt.accept("u");
                break;
            case "v":
                this.cnt.accept("v");
                break;
            case "w":
                this.cnt.accept("w");
                break;
            case "x":
                this.cnt.accept("x");
                break;
            case "y":
                this.cnt.accept("y");
                break;
            case "z":
                this.cnt.accept("z");
                break;
        }
    }

    public void stringCharacter() throws IOException {
    }

    public void inputCharacter() throws IOException {
    }

    public void allInputCharacter() throws IOException {
    }

    public void escapeCharacter() throws IOException {
    }

    public void nullLiteral() throws IOException {
    }

    public void keyword() throws IOException {
    }

}
