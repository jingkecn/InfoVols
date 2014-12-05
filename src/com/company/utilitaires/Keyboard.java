package com.company.utilitaires;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {

    // Author: M. Dennis Mickunas, June 9, 1997
// Primitive Keyboard input of integers, reals,
// strings, and characters.
/* Modification apport�e par l'�cole
 * - ajout de la m�thode readFirstChar, qui renvoie
 * le premier caract�re d'un texte, en n�gligeant
 * les caract�res de controle li�es � la touche 
 * Entree.
 * - la m�thode readChar est modifi�e :
 * Sous DOS : la touche Entree correspond � \r\n
 * Dans l'environnement Netbeans, � \n uniquement
 * C'est pourquoi la m�thode readChar relit un
 * deuxi�me caract�re si elle trouve \r !
 */
    static boolean iseof = false;
    static char c;
    static int i;
    static double d;
    static String s;

    /* WARNING:  THE BUFFER VALUE IS SET TO 1 HERE TO OVERCOME
    ** A KNOWN BUG IN WIN95 (WITH JDK 1.1.3 ONWARDS)
    */
    static BufferedReader input
            = new BufferedReader(
            new InputStreamReader(System.in), 1);

    public static int readInt() {
        if (iseof) return 0;
        System.out.flush();
        try {
            s = input.readLine();
        } catch (IOException e) {
            System.exit(-1);
        }
        if (s == null) {
            iseof = true;
            return 0;
        }
        i = new Integer(s.trim()).intValue();
        return i;
    }

    public static char readChar() {
        if (iseof) return (char) 0;
        System.out.flush();
        try {
            i = input.read();
        } catch (IOException e) {
            System.exit(-1);
        }
        if (i == -1) {
            iseof = true;
            return (char) 0;
        }
        // ajout personnel : si le caract�re est \r
        // on suppose que le systeme envoie ensuite \n
        // on lit donc un caract�re en plus et on renvoie
        // le second caract�re
        if (i == 13) readChar();
        return (char) i;
    }

    public static double readDouble() {
        if (iseof) return 0.0;
        System.out.flush();
        try {
            s = input.readLine();
        } catch (IOException e) {
            System.exit(-1);
        }
        if (s == null) {
            iseof = true;
            return 0.0;
        }
        d = new Double(s.trim()).doubleValue();
        return d;
    }

    public static String readString() {
        if (iseof) return null;
        System.out.flush();
        try {
            s = input.readLine();
        } catch (IOException e) {
            System.exit(-1);
        }
        if (s == null) {
            iseof = true;
            return null;
        }
        return s;
    }

    public static char readFirstChar() {
        if (iseof) return (char) 0;
        System.out.flush();
        try {
            s = input.readLine();
        } catch (IOException e) {
            System.exit(-1);
        }
        if (s == null) {
            iseof = true;
            return (char) 0;
        }
        return s.charAt(0);
    }

    public static boolean eof() {
        return iseof;
    }

}

