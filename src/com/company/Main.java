package com.company;

import java.util.Scanner;

public class Main {
    private final static String holdFlag = "Pascal :> ";
    public static void main(String[] args) {
	// write your code here
        while (true) {
            String text = null;
            try {
                System.out.print(holdFlag);
                Scanner sc = new Scanner(System.in);
                text = sc.nextLine();
            } catch (Exception e) {
                System.out.println(holdFlag + "Wrong input");
            }
            Interpreter ext = new Interpreter (text);
            String result = ext.getResult();
            System.out.println(holdFlag + result);
        }
    }
}
