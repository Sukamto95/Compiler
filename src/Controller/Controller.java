package Controller;

import Model.ProgramDeclaration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Sukamto 23518017 Andreas Novian 23518002
 */
public class Controller {

    BufferedReader br, brSymbol;
    BufferedWriter bw;
    String isiFile = ""; //isi file murni, belum diapa-apain
    String[] listOfSymbols; //kumpulan simbol setelah dipisahin
    String symbol; //simbol yang sedang diproses
    int cursor;
    boolean isError = false;

    public Controller() throws FileNotFoundException {
        br = new BufferedReader(new FileReader("input.txt"));
        brSymbol = new BufferedReader(new FileReader("symbols.txt"));
    }

    public void start() throws IOException {
        bw = new BufferedWriter(new FileWriter(new File("output.txt")));
        cursor = 0;
        String currentLine;

        while ((currentLine = br.readLine()) != null) {
            isiFile += currentLine + "\n";
        }

        listOfSymbols = preprocess(isiFile);
        symbol = listOfSymbols[0];

        new ProgramDeclaration(this).procedureA();
        if (cursor < listOfSymbols.length - 2) {
            if (!isError) {
                bw.write("(Error)");
            }
            for (int i = cursor; i < listOfSymbols.length; i++) {
                bw.write(listOfSymbols[i]);
            }
            isError = true;
        }
        if (!isError) {
            bw.write("\nTidak ada error\n");
        }
        bw.close();
        br.close();
    }

    public void accept(String terminal) throws IOException {
        System.out.println("terminal - symbol = " + terminal + " - " + symbol);
        if (terminal.equalsIgnoreCase(symbol)) {
            bw.write(symbol);
            readNextSymbol();
        } else {
            isError = true;
            bw.write("(Error)" + symbol);
            readNextSymbol();
        }
    }

    public void readNextSymbol() {
        cursor++;
        if (cursor < listOfSymbols.length) {
            this.symbol = listOfSymbols[cursor];
        }
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String[] preprocess(String isiFile) throws IOException {
        int counter = 0;
        String temp = "";
        char c;

        isiFile = isiFile.replaceAll("\\s+", "");
        String[] result = new String[isiFile.length()];

        for (int i = 0; i < isiFile.length(); i++) {
            c = isiFile.charAt(i);
            if (Character.isLetter(c)) {
                temp += c;
            }
            if (isSymbol(temp)) {
                result[counter] = temp;
                temp = "";
                counter++;
            } else {
                result[counter] = c + "";
                counter++;
            }
        }

        return result;
    }

    public boolean isSymbol(String in) throws IOException {
        String currentLine;

        while ((currentLine = brSymbol.readLine()) != null) {
            if (in.equalsIgnoreCase(currentLine)) {
                return true;
            }
        }

        return false;
    }
}
