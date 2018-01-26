package com.company;

import java.text.ParseException;

public class Interpreter {
    private String result;
    private int pos = 0;
    private String text;
    private token currentToken;
    private final static String INTEGER = "INTEGER";
    private final static String OPER = "OPER";
    private final static String EOF = "EOF";

    public Interpreter (String s) {
        this.text = s;
    }

    public String getResult() throws ParseException {
        int flag = 0;
        int[] cacu = new int[2];
        String oper = "";
        while (!(currentToken = getNextToken()).type.equals(EOF)) {
            token t = currentToken;
            switch (t.type) {
                case INTEGER:
                    cacu[flag] = cacu[flag] * 10 + Integer.valueOf(t.value);
                    break;
                case OPER:
                    if (flag > 0) error();
                    flag ++;
                    oper = t.value;
                    break;
                case EOF:
                    break;
            }
        }
        switch (oper) {
            case "+" :
                result = String.valueOf(cacu[0] + cacu[1]);
                break;
            case "-" :
                result = String.valueOf(cacu[0] - cacu[1]);
                break;
            case "*" :
                result = String.valueOf(cacu[0] * cacu[1]);
                break;
            case "/" :
                result = String.valueOf(cacu[0] / cacu[1]);
                break;
        }

        return result;
    }
    private void eat(String t) throws ParseException {
        if (this.currentToken.type.equals(t)) {
            this.currentToken = getNextToken();
        } else {
            error();
        }
    }
    private token getNextToken() throws ParseException {
        if (this.pos >= this.text.length())
            return new token(EOF, null);
        char currentChar = text.charAt(pos);
        if (Character.isDigit(currentChar)) {
            this.pos += 1;
            return new token(INTEGER, String.valueOf(currentChar));
        }
        if (currentChar == '+' | currentChar == '-' | currentChar == '*' | currentChar == '/') {
            this.pos += 1;
            return new token(OPER, String.valueOf(currentChar));
        }
        if (currentChar == ' ') {
            this.pos += 1;
            return getNextToken();
        }
        throw new ParseException(text, pos);
    }
    private void error() throws ParseException {
        throw  new ParseException(text, pos);
    }
    class token {
        String type;
        String value;
        public token (String type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Token("+ type + ", "+ value + ")";
        }
    }
    private enum arithmeticOperaton {
        PLUS("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");
        String oper;
        arithmeticOperaton(String oper) {
            this.oper = oper;
        }

        String getOper() {
            return oper;
        }
    }
}
