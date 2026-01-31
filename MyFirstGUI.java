import javax.swing.*;

//ABSTRACTION //
interface Operation {
    long apply(long a, long b);
}

// POLYMORPHISM
class Add implements Operation {
    public long apply(long a, long b) {
        return a + b;
    }
}

class Subtract implements Operation {
    public long apply(long a, long b) {
        return a - b;
    }
}

class Multiply implements Operation {
    public long apply(long a, long b) {
        return a * b;
    }
}

class Divide implements Operation {
    public long apply(long a, long b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }
}

//ENCAPSULATION//
class Calculator {

    private StringBuilder first = new StringBuilder();
    private StringBuilder second = new StringBuilder();
    private Operation op;
    private String operatorSymbol = "";

    public void addDigit(String d) {
        if (op == null) {
            first.append(d);
        } else {
            second.append(d);
        }
    }

    public void setOperation(Operation o, String symbol) {
        if (op == null) {
            op = o;
            operatorSymbol = symbol;
        }
    }

    public String getDisplay() {
        if (op == null) {
            return first.toString();
        }
        return first.toString() + " " + operatorSymbol + " " + second.toString();
    }

    public String calculate() {
        if (first.length() == 0 || second.length() == 0 || op == null) {
            return "error";
        }

        long a = Long.parseLong(first.toString(), 2);
        long b = Long.parseLong(second.toString(), 2);

        long result = op.apply(a, b);

        String answer = Long.toBinaryString(result);
        clear();
        return answer;
    }

    public void clear() {
        first.setLength(0);
        second.setLength(0);
        op = null;
        operatorSymbol = "";
    }

    public void deleteLast() {
        if (op == null && first.length() > 0) {
            first.deleteCharAt(first.length() - 1);
        } else if (op != null && second.length() > 0) {
            second.deleteCharAt(second.length() - 1);
        }
    }
}

//GUI PLACE//
public class MyFirstGUI extends JFrame {

    JLabel display;
    Calculator calc = new Calculator();

    public MyFirstGUI() {

        setTitle("Judy's Calculator");
        setSize(450, 500);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        display = new JLabel("", SwingConstants.RIGHT);
        display.setBounds(20, 20, 400, 50);
        display.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
        add(display);

        JButton bA = new JButton("A");
        bA.setBounds(20, 90, 60, 50);
        bA.setEnabled(false);
        add(bA);

        JButton bLeft = new JButton("<<");
        bLeft.setBounds(90, 90, 60, 50);
        add(bLeft);

        JButton bRight = new JButton(">>");
        bRight.setBounds(160, 90, 60, 50);
        add(bRight);

        JButton bClear = new JButton("C");
        bClear.setBounds(230, 90, 60, 50);
        bClear.addActionListener(e -> {
            calc.clear();
            display.setText("");
        });
        add(bClear);

        JButton bDel = new JButton("Del");
        bDel.setBounds(300, 90, 60, 50);
        bDel.addActionListener(e -> {
            calc.deleteLast();
            display.setText(calc.getDisplay());
        });
        add(bDel);

        JButton bB = new JButton("B");
        bB.setBounds(20, 150, 60, 50);
        bB.setEnabled(false);
        add(bB);

        JButton bOpen = new JButton("(");
        bOpen.setBounds(90, 150, 60, 50);
        add(bOpen);

        JButton bClose = new JButton(")");
        bClose.setBounds(160, 150, 60, 50);
        add(bClose);

        JButton bMod = new JButton("%");
        bMod.setBounds(230, 150, 60, 50);
        add(bMod);

        JButton bDiv = new JButton("/");
        bDiv.setBounds(300, 150, 60, 50);
        bDiv.addActionListener(e -> pressOperation(new Divide(), "/"));
        add(bDiv);

        JButton bC2 = new JButton("C");
        bC2.setBounds(20, 210, 60, 50);
        bC2.setEnabled(false);
        add(bC2);

        JButton b7 = new JButton("7");
        b7.setBounds(90, 210, 60, 50);
        b7.setEnabled(false);
        add(b7);

        JButton b8 = new JButton("8");
        b8.setBounds(160, 210, 60, 50);
        b8.setEnabled(false);
        add(b8);

        JButton b9 = new JButton("9");
        b9.setBounds(230, 210, 60, 50);
        b9.setEnabled(false);
        add(b9);

        JButton bMul = new JButton("*");
        bMul.setBounds(300, 210, 60, 50);
        bMul.addActionListener(e -> pressOperation(new Multiply(), "*"));
        add(bMul);

        JButton bD = new JButton("D");
        bD.setBounds(20, 270, 60, 50);
        bD.setEnabled(false);
        add(bD);

        JButton b4 = new JButton("4");
        b4.setBounds(90, 270, 60, 50);
        b4.setEnabled(false);
        add(b4);

        JButton b5 = new JButton("5");
        b5.setBounds(160, 270, 60, 50);
        b5.setEnabled(false);
        add(b5);

        JButton b6 = new JButton("6");
        b6.setBounds(230, 270, 60, 50);
        b6.setEnabled(false);
        add(b6);

        JButton bSub = new JButton("-");
        bSub.setBounds(300, 270, 60, 50);
        bSub.addActionListener(e -> pressOperation(new Subtract(), "-"));
        add(bSub);

        JButton bE = new JButton("E");
        bE.setBounds(20, 330, 60, 50);
        bE.setEnabled(false);
        add(bE);

        JButton b1 = new JButton("1");
        b1.setBounds(90, 330, 60, 50);
        b1.addActionListener(e -> pressDigit("1"));
        add(b1);

        JButton b2 = new JButton("2");
        b2.setBounds(160, 330, 60, 50);
        b2.setEnabled(false);
        add(b2);

        JButton b3 = new JButton("3");
        b3.setBounds(230, 330, 60, 50);
        b3.setEnabled(false);
        add(b3);

        JButton bAdd = new JButton("+");
        bAdd.setBounds(300, 330, 60, 50);
        bAdd.addActionListener(e -> pressOperation(new Add(), "+"));
        add(bAdd);

        JButton bF = new JButton("F");
        bF.setBounds(20, 390, 60, 50);
        bF.setEnabled(false);
        add(bF);

        JButton bPM = new JButton("<html>+<br>/<br>-</html>");
        bPM.setBounds(90, 390, 60, 50);
        add(bPM);

        JButton b0 = new JButton("0");
        b0.setBounds(160, 390, 60, 50);
        b0.addActionListener(e -> pressDigit("0"));
        add(b0);

        JButton bDot = new JButton(".");
        bDot.setBounds(230, 390, 60, 50);
        add(bDot);

        JButton bEq = new JButton("=");
        bEq.setBounds(300, 390, 60, 50);
        bEq.addActionListener(e -> pressEquals());
        add(bEq);

        setVisible(true);
    }

    private void pressDigit(String d) {
        calc.addDigit(d);
        display.setText(calc.getDisplay());
    }

    private void pressOperation(Operation o, String symbol) {
        calc.setOperation(o, symbol);
        display.setText(calc.getDisplay());
    }

    private void pressEquals() {
        try {
            display.setText(calc.calculate());
        } catch (Exception ex) {
            display.setText(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new MyFirstGUI(); //
    }
}
