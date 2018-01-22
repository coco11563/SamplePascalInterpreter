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
        currentToken = getNextToken();

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
        if (this.pos > this.text.length())
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
