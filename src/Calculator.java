import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * implamtion of Calculator Program in Java Swing/JFrame
 * using Shunting-yard algorithm
 *
 * @author Mulugeta Fanta
 */
public class Calculator implements ActionListener {
    //Declaring variables:
    private static JTextField input;
    private static JButton btn0, btn1, btn2, btn3, btn4,
            btn5, btn6, btn7, btn8, btn9, equal, btnMul,
            btnDiv, btnAdd, btnSub, btnxPow, sqrtx, btnPow, btnOff, btnSin,
            btnCos, btnTan, btnCr, btnLog, btnLn, btnDot, btnBracOpen, btnBracClose,
            btnMplus, btnMinus, btnMs, btnMr, btnMc, btnOn, btnDel, btnAc;

    private final JFrame Win;
    private final Map<String, Integer> precedence;
    private final int MAX_DIGITS = 18;
    private final JButton[] buttons;
    private double MemoryVal;

    public Calculator() {

        Win = new JFrame("Calculator");
        //initialize buttons
        init();

        Win.setSize(400, 700);
        Win.setLocationRelativeTo(null);//set JFrame to appear centered
        Win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Win.setLayout(null);
        Win.setResizable(false);
        Win.setVisible(true);
        Win.getContentPane().setBackground(new Color(96, 96, 96));

        precedence = new HashMap<>();
        precedence.put("+", 2);
        precedence.put("-", 2);
        precedence.put("*", 3);
        precedence.put("/", 3);
        precedence.put("^", 4);

        buttons = new JButton[]{btnMr, btnMs, btnMplus, btnMinus, btnOn,
                btnxPow, sqrtx, btnPow, btnCr, btnMc,
                btnLn, btnLog, btnTan, btnCos, btnSin,
                btn7, btn8, btn9, btnDel, btnAc,
                btn4, btn5, btn6, btnMul, btnDiv,
                btn1, btn2, btn3, btnAdd, btnSub,
                btn0, btnDot, btnBracOpen, btnBracClose, equal};
    }

    public double applyOperator(double b, char op, double a) {

        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            ArithmeticException(
                            "Cannot divide by zero");
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException(op + " is not an operator");
        }
    }


    //set all buttons enable
    public void enable() {
        for (JButton button : buttons)
            button.setEnabled(true);
    }

    //set all buttons disable except ON button
    public void disable() {
        System.out.println("dis");
        for (JButton button : buttons)
            button.setEnabled(false);

        btnOn.setEnabled(true);
    }

    /**
     * @param str
     * @return length of the input
     */
    public int countDigits(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            //check if charAt(i) is number
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                count++;
                if (count > MAX_DIGITS) return count;
            } else count = 0;

        }
        return count;
    }

    /**
     * initialize all buttons,
     * set font,color,width,height,x-coordinate,y-coordinate
     */
    public void init() {
        input = new JTextField("");
        input.setBackground(Color.white);
        input.setEditable(false);
        input.setFont(new Font("SansSerif ", Font.PLAIN, 25));
        input.setBounds(26, 30, 340, 70);

        btn0 = initButton("0", new int[]{25, 540, 60, 60}, Color.white, Color.black, this);
        btnDot = initButton("•", new int[]{95, 540, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnBracOpen = initButton("(", new int[]{165, 540, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnBracClose = initButton(")", new int[]{235, 540, 60, 60}, new Color(25, 25, 25), Color.white, this);
        equal = initButton("=", new int[]{305, 540, 60, 60}, new Color(25, 25, 25), Color.white, this);
        equal.setFont(new Font("SansSerif ", Font.PLAIN, 25));

        btn1 = initButton("1", new int[]{25, 470, 60, 60}, Color.white, Color.black, this);
        btn2 = initButton("2", new int[]{95, 470, 60, 60}, Color.white, Color.black, this);
        btn3 = initButton("3", new int[]{165, 470, 60, 60}, Color.white, Color.black, this);
        btnAdd = initButton("+", new int[]{235, 470, 60, 60}, new Color(0, 128, 255), Color.white, this);
        btnAdd.setFont(new Font("SansSerif ", Font.PLAIN, 25));
        btnSub = initButton("-", new int[]{305, 470, 60, 60}, new Color(0, 128, 255), Color.white, this);
        btnSub.setFont(new Font("SansSerif ", Font.PLAIN, 25));

        btn4 = initButton("4", new int[]{25, 400, 60, 60}, Color.white, Color.black, this);
        btn5 = initButton("5", new int[]{95, 400, 60, 60}, Color.white, Color.black, this);
        btn6 = initButton("6", new int[]{165, 400, 60, 60}, Color.white, Color.black, this);
        btnMul = initButton("×", new int[]{235, 400, 60, 60}, new Color(0, 128, 255), Color.white, this);
        btnMul.setFont(new Font("SansSerif ", Font.PLAIN, 25));
        btnDiv = initButton("÷", new int[]{305, 400, 60, 60}, new Color(0, 128, 255), Color.white, this);
        btnDiv.setFont(new Font("SansSerif ", Font.PLAIN, 25));

        btn7 = initButton("7", new int[]{25, 330, 60, 60}, Color.white, Color.black, this);
        btn8 = initButton("8", new int[]{95, 330, 60, 60}, Color.white, Color.black, this);
        btn9 = initButton("9", new int[]{165, 330, 60, 60}, Color.white, Color.black, this);
        btnDel = initButton("DEL", new int[]{235, 330, 60, 60}, new Color(100, 0, 0), Color.white, this);
        btnAc = initButton("AC", new int[]{305, 330, 60, 60}, new Color(0, 100, 0), Color.white, this);

        btnxPow = initButton("x^2", new int[]{25, 190, 60, 60}, new Color(25, 25, 25), Color.white, this);
        sqrtx = initButton("√", new int[]{95, 190, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnPow = initButton("^", new int[]{165, 190, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnCr = initButton("nCr", new int[]{235, 190, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnMc = initButton("MC", new int[]{305, 190, 60, 60}, new Color(100, 0, 0), Color.white, this);

        btnSin = initButton("Sin", new int[]{305, 260, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnCos = initButton("Cos", new int[]{235, 260, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnTan = initButton("Tan", new int[]{165, 260, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnLog = initButton("Log", new int[]{95, 260, 60, 60}, new Color(25, 25, 25), Color.white, this);
        btnLn = initButton("Ln", new int[]{25, 260, 60, 60}, new Color(25, 25, 25), Color.white, this);


        btnMr = initButton("MR", new int[]{25, 120, 60, 60}, new Color(255, 255, 153), Color.black, this);
        btnMs = initButton("MS", new int[]{95, 120, 60, 60}, new Color(255, 255, 153), Color.black, this);
        btnMplus = initButton("M+", new int[]{165, 120, 60, 60}, new Color(255, 255, 153), Color.black, this);
        btnMinus = initButton("M-", new int[]{235, 120, 60, 60}, new Color(255, 255, 153), Color.black, this);
        btnOn = initButton("OFF", new int[]{305, 120, 60, 60}, Color.red, Color.white, this);

        Win.add(input);
    }

    /**
     * Perform action
     *
     * @param ae ActionEvent
     */
    public void actionPerformed(ActionEvent ae) {
        String e = ae.getActionCommand();
        String in = input.getText();
        //regular expression to split input
        String splitReg = "(?<=[-+*/()^√]|(?<=Cos)|(?<=Sin)|(?<=Tan)|(?<=Log)|(?<=Ln)" +
                "|(?=[-+*/()^√]))";
        String[] expToStr = (input.getText()).replaceAll("\\s+", "").split(splitReg);

        if (input.getText().equals("0.0")) in = "";
        else in = input.getText();
        switch (e) {
            case "0":
                input.setText(in + "0");
                break;
            case "1":
                input.setText(in + "1");
                break;
            case "2":
                input.setText(in + "2");
                break;
            case "3":
                input.setText(in + "3");
                break;
            case "4":
                input.setText(in + "4");
                break;
            case "5":
                input.setText(in + "5");
                break;
            case "6":
                input.setText(in + "6");
                break;
            case "7":
                input.setText(in + "7");
                break;
            case "8":
                input.setText(in + "8");
                break;
            case "9":
                input.setText(in + "9");
                break;
            case "•":
                input.setText(in + ".");
                break;
            case "+":
                input.setText(in + "+");
                break;
            case "-":
                input.setText(in + "-");
                break;
            case "*":
            case "×":
                input.setText(in + "*");
                break;
            case "÷":
            case "/":
                input.setText(in + "/");
                break;
            case "^":
                input.setText(in + "^");
                break;
            case "(":
                input.setText(in + "(");
                break;
            case ")":
                input.setText(in + ")");
                break;
            case "C":
                input.setText("");
                break;
            case "√":
                input.setText(in + "√");
                break;
            case "Log":
                input.setText(in + "Log");
                break;
            case "Sin":
                input.setText(in + "Sin");
                break;
            case "Cos":
                input.setText(in + "Cos");
                break;
            case "Tan":
                input.setText(in + "Tan");
                break;
            case "nCr": {
                input.setText(in + "(___)");
                int count = 0;
                String userInput = "";
                double n = 0, k = 0;
                ImageIcon icon = new ImageIcon("images/binom.PNG");
                try {
                    while (count < 2) {
                        if (count == 0) {

                            userInput = (String) JOptionPane.showInputDialog(null, "Please enter a number", null, JOptionPane.INFORMATION_MESSAGE,
                                    icon, null, "");

                            n = Double.parseDouble(userInput);
                            input.setText(in + n + "C");
                            count++;
                        } else {
                            userInput = (String) JOptionPane.showInputDialog(null, "Please enter a number",
                                    null, JOptionPane.INFORMATION_MESSAGE, icon, null, "");

                            k = Double.parseDouble(userInput);
                            input.setText(in + n + "C" + k);
                            count++;
                        }
                    }
                    //nCk={n! / k!(n-k)!}
                    String nCr = "" + Binomial(n, k);
                    input.setText(in + nCr);
                } catch (IllegalArgumentException ex) {
                    input.setText(ex.getMessage());
                    //if user didn't input any value
                } catch (NullPointerException ex) {
                    input.setText(in);
                }
                break;
            }
            case "x^2": {
                input.setText(in + "__^2    ");
                String userInput = (String) JOptionPane.showInputDialog("Please enter a number");
                input.setText(in + userInput + "^2");
                break;
            }
            case "AC":
                input.setText("");
                break;
            case "DEL":
                String str = input.getText();
                int index = str.length() - 1;
                while (index > 0) {
                    if ((str.charAt(index) >= '0' && str.charAt(index) <= '9') || (precedence.containsKey("" + str.charAt(index)))) {
                        str = str.substring(0, index);
                        break;
                    } else {
                        index--;
                    }

                }
                str = str.substring(0, index);

                if (str.equals("")) {
                    input.setText("0");
                } else {
                    input.setText(str);
                }
                break;
            case "=":
                if (countDigits(in) > MAX_DIGITS) {
                    input.setText("Error: number too big");
                } else {
                    String ans = "";
                    try {
                        ans = "" + Eval(in);
                        input.setText(new DecimalFormat("##.####").format(Double.parseDouble(ans)));
                    } catch (Exception ex) {
                        ans = ex.getMessage();
                        input.setText(ans);
                    }
                }
                break;
            case "MS":
            case "MR":
            case "M+":
            case "M-":
            case "MC":
                MemoryManager(ae);
                break;
            case "ON":
                input.setText("0");
                enable();
                btnOn.setText("OFF");
                btnOn.setBackground(Color.red);
                break;
            case "OFF":
                input.setText("");
                disable();
                btnOn.setBackground(Color.GREEN);
                btnOn.setText("ON");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + e);
        }
    }

    /**
     * method to find factorial of given number
     *
     * @param n given number
     * @return factorial value of n
     */
    public double Factorial(double n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        return n * Factorial(n - 1);
    }

    /**
     * method to calculating the binomial coefficient of two integers
     *
     * @param n first given number
     * @param k second given number
     * @return n choose k (binomial coefficient of two integers)
     */
    public double Binomial(double n, double k) {
        if (n < k || k < 0 || n < 0) throw new IllegalArgumentException("Error! 0 ≤ k ≤ n");
        double x = applyOperator(Factorial(k), '*', Factorial(n - k));
        double y = Factorial(n);
        return applyOperator(x, '/', y);
    }

    /**
     * use Shunting-yard algorithm to parsing mathematical expressions
     * specified in infix notation.
     * source:https://en.wikipedia.org/wiki/Shunting-yard_algorithm
     *
     * @param expression The given mathematical expression
     * @return The given expression result
     * @throws IllegalArgumentException invalid expression exception
     * @throws ArithmeticException      error while try to divide by zero
     * @throws NumberFormatException    error while try to parsing a number (string to double)
     */
    public double Eval(String expression) throws IllegalArgumentException, ArithmeticException, NumberFormatException {
        if (expression.length() == 0) return 0;

        if (expression.charAt(0) == '-') expression = "0" + expression;
        expression = expression.replace("(-", "(0-");

        //regular expression to split input
        String splitReg = "(?<=[-+*/()^√]|(?<=Cos)|(?<=Sin)|(?<=Tan)|(?<=Log)|(?<=Ln)" +
                "|(?=[-+*/()^√]))";
        //regex to chek if value is type double
        String reg = "([0-9]*[.])?[0-9]+";
        String[] expToStr = expression.replaceAll("\\s+", "").split(splitReg);
        //Stack for numbers
        Stack<Double> values = new Stack<>();
        //Stack for Operators
        Stack<String> operator = new Stack<>();
        //List for output
        List<String> output = new ArrayList<>();

        for (int i = 0; i < expToStr.length; i++) {
            String token = expToStr[i];
            //check if  token is operator
            if (precedence.containsKey(token)) {
                // if token is operator and  token precedence is less or equal  than the top of stack
                //and it is not '^' (that has left associated). then we add it to output
                //or token precedence is less then the top of the stack we add it if toke is '^'
                while (!operator.isEmpty() && precedence.containsKey(operator.peek())
                        && ((precedence.get(token) <= precedence.get(operator.peek()) && !token.equals("^"))
                        || (precedence.get(token) < precedence.get(operator.peek()) && token.equals("^")))) {
                    output.add(operator.pop());
                }
                operator.push(token);
                //if token is not operator
            } else if (token.equals("(")) {
                operator.push(token);
            } else if (token.equals(")")) {
                while (!operator.isEmpty()) {
                    if (!operator.peek().equals("(")) {
                        output.add(operator.pop());
                    } else {
                        break;
                    }
                }
                if (!operator.isEmpty()) {
                    operator.pop();
                }
            } else if (token.equals("√")) {
                String tmp = "";
                //take next value and make sqrt
                if (i + 1 < expToStr.length && expToStr[i + 1].matches(reg)) {
                    tmp += expToStr[i + 1];
                }
                if (!tmp.equals("")) {
                    try {
                        output.add("" + Math.sqrt(Double.parseDouble(tmp)));
                    } catch (NumberFormatException e) {
                        input.setText(e.getMessage());
                    }
                } else {
                    //throw exception if there operator stand alone
                    throw new IllegalArgumentException("Invalid Expression:" + token);
                }
                i = i + 1;
            }
            //if token is number
            else if (token.matches(reg)) {
                output.add(token);
            } else {
                int index = 0;
                if (i + 1 < expToStr.length && expToStr[i + 1].matches(reg)) {
                    index = 1;
                    String tmp = expToStr[i + 1];
                    try {
                        double x = Double.parseDouble(tmp);
                        switch (token) {
                            case "Cos":
                                //conver x to radian
                                x = Math.toRadians(x);
                                output.add("" + Math.cos(x));
                                break;
                            case "Sin":
                                x = Math.toRadians(x);
                                output.add("" + Math.sin(x));
                                break;
                            case "Tan":
                                x = Math.toRadians(x);
                                if (x < -1.5 || x > 1.5) {
                                    throw new ArithmeticException("Tan(90) is Undefined");
                                } else output.add("" + Math.tan(x));
                                break;
                            case "Log":
                                output.add("" + Math.log10(x));
                                break;
                            case "Ln":
                                output.add("" + Math.log(x));
                                break;
                        }
                    } catch (NumberFormatException e) {
                        input.setText(e.getMessage());
                    }
                } else {
                    //if cos,sin,tan,ln,log  standalone (with no number after)
                    throw new IllegalArgumentException("Invalid Expression:" + token);
                }
                i = i + index;
            }
        }
        //finally add all operators left to output
        while (!operator.isEmpty()) {
            output.add(operator.pop());
        }

        for (String token : output) {
            // if token is number we add it to values
            if (!precedence.containsKey(token) && token.matches(reg)) {
                try {
                    values.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    input.setText(e.getMessage());
                    throw e;
                }
            } else {
                if (values.size() > 1) {
                    char op = token.charAt(0);
                    values.push(applyOperator(values.pop(), op, values.pop()));
                } else throw new IllegalArgumentException("Invalid expression");
            }

        }
        if (1 != values.size()) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return values.peek();
    }

    /**
     * @param name    the name of a JButton
     * @param Bounds  the bounds of a JButton
     * @param bgColor the background color of a JButton
     * @param fgColor the foreground color of a JButton
     * @param e       the actionListener to a button.
     * @return
     */
    public JButton initButton(String name, int[] Bounds, Color bgColor, Color fgColor, ActionListener e) {
        JButton button = new JButton(name);
        button.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.addActionListener(e);
        //if button is for numbers set font to Bold
        if (name.charAt(0) >= '0' && name.charAt(0) <= '9') {
            button.setFont(new Font("Arial", Font.BOLD, 18));
        }

        Win.add(button);
        return button;
    }

    /**
     * memory manager when clicked MS,MR.MC.M+,M-
     *
     * @param e the actionListener to a button.
     */
    public void MemoryManager(ActionEvent e) {
        String ac = e.getActionCommand();
        String in = input.getText();
        try {
            switch (ac) {
                case "MS":
                    MemoryVal = Double.parseDouble(input.getText());
                    break;
                case "MR":
                    input.setText("" + MemoryVal);
                    break;
                case "MC":
                    MemoryVal = 0;
                    break;
                case "M+":
                    double num2 = Double.parseDouble(in);
                    if (MemoryVal != 0) {
                        MemoryVal = applyOperator(MemoryVal, '+', num2);
                        input.setText("" + MemoryVal);
                    } else {
                        MemoryVal = num2;
                    }
                    break;
                case "M-":
                    num2 = Double.parseDouble(in);
                    if (MemoryVal != 0) {
                        MemoryVal = applyOperator(num2, '-', MemoryVal);
                        input.setText("" + MemoryVal);
                    } else {
                        MemoryVal = -num2;
                    }
                    break;
            }
        } catch (NumberFormatException ex) {
        }
    }
}