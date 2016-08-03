package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BracketsApp {
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        String input = "(sjfakl_)sdfka{dfad{fkljasdf[[[[[[[[]]]]]]((()))]]}}";
        BracketChecker theChecker = new BracketChecker( input );
        theChecker.check();
    }
    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader br = new BufferedReader( isr );
        String s = br.readLine();
        System.out.println(s);
        return s;        
    }
}