package com.company;

import com.company.Control.TestUnit;
import com.company.utilitaires.Keyboard;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        // write your code here
        final String filename = "departs.txt";
        TestUnit tu = new TestUnit(filename);
        tu.showChoices();
        int nQ = Keyboard.readInt();
        switch (nQ) {
            case 1:
                tu.exeQ1();
                break;
            case 2:
                tu.exeQ2();
                break;
            case 3:
                tu.exeQ3();
                break;
            default:
                break;
        }
    }
}
