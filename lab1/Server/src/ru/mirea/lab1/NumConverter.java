package ru.mirea.lab1;

import java.math.BigInteger;
import java.util.*;

public class NumConverter implements Result {
    private ArrayList<BigInteger> inputnumbers;
    private int inputradix, outputradix;
    private Set<Character> delimset = new HashSet<>(Arrays.asList('.',',','|',';'));

    public NumConverter(ArrayList<BigInteger> userinput,  int inputradix, int outputradix) throws Exception{
        this.inputnumbers = userinput;
        this.inputradix=inputradix;
        this.outputradix=outputradix;
        if (inputnumbers.isEmpty()) {
            throw new Exception("There were no numbers in string!");
        }
    }

    public NumConverter(BigInteger[] userinput, int inputradix, int outputradix) throws Exception{
        inputnumbers = new ArrayList<>(Arrays.asList(userinput));
        this.inputradix=inputradix;
        this.outputradix=outputradix;
        if (inputnumbers.isEmpty()) {
            throw new Exception("There were no numbers in string!");
        }
    }

    public NumConverter(String userinput, int inputradix, int outputradix) throws Exception {
        StringBuilder numberstring = new StringBuilder("");
        inputnumbers = new ArrayList<BigInteger>();
        this.inputradix = inputradix;
        this.outputradix = outputradix;
        for (char symbol : userinput.toCharArray()) {
            if (Character.isDigit(symbol) || Character.isAlphabetic(symbol)) {
                try {
                    if (!(inputradix > Integer.parseInt(Character.toString(symbol)))) {
                        throw new Exception("Alphanum of bigger radix found!");
                    }
                } catch (Exception e) {
                    throw new Exception("Radix parse error!");
                }
                numberstring.append(symbol);
            } else if (delimset.contains(symbol)) {
                inputnumbers.add(convert(numberstring.toString(), inputradix));
                numberstring.setLength(0);
            } else if (!(Character.isWhitespace(symbol))) {
                throw new Exception("String format exception!");
            }
        }
        if (!numberstring.toString().isEmpty()) {
            inputnumbers.add(convert(numberstring.toString(), inputradix));
        }
        if (inputnumbers.isEmpty()) {
            throw new Exception("There were no numbers in string!");
        }
    }

    public NumConverter(String userinput) throws Exception {
        StringBuilder numberstring = new StringBuilder("");
        inputnumbers = new ArrayList<BigInteger>();
        this.inputradix = inputradix;
        this.outputradix = outputradix;
        for (char symbol : userinput.toCharArray()) {
            if (Character.isDigit(symbol) || Character.isAlphabetic(symbol)) {
                try {
                    if (!(inputradix > Integer.parseInt(Character.toString(symbol)))) {
                        throw new Exception("Alphanum of bigger radix found!");
                    }
                } catch (Exception e) {
                    throw new Exception("Radix parse error!");
                }
                numberstring.append(symbol);
            } else if (delimset.contains(symbol)) {
                inputnumbers.add(convert(numberstring.toString(), inputradix));
                numberstring.setLength(0);
            } else if (!(Character.isWhitespace(symbol))) {
                throw new Exception("String format exception!");
            }
        }
        if (!numberstring.toString().isEmpty()) {
            inputnumbers.add(convert(numberstring.toString(), inputradix));
        }
        if (inputnumbers.isEmpty()) {
            throw new Exception("There were no numbers in string!");
        }
    }


    private BigInteger convert(String numberstring, int radix) {
        return new BigInteger(numberstring,radix);
    }

    public StringBuilder getResult() {
        StringBuilder result = new StringBuilder("Исходная СС: " + inputradix + "\nНовая CC: " + outputradix + "\n");
        for (BigInteger num : inputnumbers) {
            result.append(num.toString(inputradix)).append(" => ").append(num.toString(outputradix)).append(",\n");
        }
        return result;
    }
}
