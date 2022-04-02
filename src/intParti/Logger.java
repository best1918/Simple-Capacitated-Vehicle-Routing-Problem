/*
 * Logger.java
 *
 * Created on 9 April 2006, 16:57
 *
 * This class writes string to a file, whose name is given when this class
 * is constructed.
 * 
 * Everytime the new pw is created, the existing file, if ever, will be reset
 * to an empty file.
 */

package intParti;

import java.io.*;

/**
 *
 * @author cs50
 */
public class Logger {
    
    String fname = null;
    PrintWriter pw = null;
    /** Creates a new instance of Logger */
    public Logger(String f) {
        fname = f;
        try {
            pw = new PrintWriter(fname);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void logFile(String s) {
        try {
            pw.write(s);
            pw.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void log(String m) {
    	logFile(m);
    	logOut(m);
    }
    public static void logOut(String m) {
    	System.out.print(m);
    	
    }
    public static void out(String m) {
    	System.out.print(m);
    }
    public static void outl(String m) {
    	System.out.println(m);
    }
    public static void err(String m) {
    	System.err.print(m);
    }
    public static void errl(String m) {
    	System.err.println(m);
    }
    public static void logErr(String m) {
    	System.err.print(m);
    }
    public void close() {
        pw.close();
    }
}
