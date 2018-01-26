package com.company;

import java.text.ParseException;
import java.util.Scanner;

public class Main {
    private final static String holdFlag = "Pascal :> ";
    public static void main(String[] args) throws ParseException {
	// write your code here
        Interpreter ext;
        while (true) {
            String text = null;
            try {
                System.out.print(holdFlag);
                Scanner sc = new Scanner(System.in);
                text = sc.nextLine();
            } catch (Exception e) {
                System.out.println(holdFlag + "Wrong input");
            }
            ext = new Interpreter (text);
            String result = ext.getResult();
            System.out.println(holdFlag + result);
        }
    }
}
