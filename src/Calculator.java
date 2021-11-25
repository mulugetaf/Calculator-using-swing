import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    private final JFrame Win;
    private final Map<String, Integer> precedence;
    private JTextField input;
    private JButton btn0, btn1, btn2, btn3, btn4,
            btn5, btn6, btn7, btn8, btn9, equal, btnMul,
            btnDiv, btnAdd, btnSub, btnxPow, sqrtx, btnPow, btnDel, btnSin,
            btnCos, btnTan, btnCr, btnLog, btnLn, btnDot, btnBracOpen, btnBracClose,
            btnMplus, btnMs, btnMr, btnMc, shiftRig, shiftLef, btnAc;
    private boolean isEqual = false;
//    private Stack<Integer> value, operator;

    public Calculator() {
        Win = new JFrame("Calculator");
        this.init();
        Win.setSize(400, 700);
        Win.setLocationRelativeTo(null);//set JFrame to appear centered
        Win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Win.setLayout(null);
        Win.setResizable(false);
        Win.setVisible(true);

        precedence = new HashMap<>();
        precedence.put("+", 2);
        precedence.put("-", 2);
        precedence.put("*", 3);
        precedence.put("/", 3);
        precedence.put("^", 4);

    }


    public static double applyOperator(double b, char op, double a) {

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

    /*
    use Shunting-yard algorithm to parsing mathematical expressions
    specified in infix notation.
    source:https://en.wikipedia.org/wiki/Shunting-yard_algorithm
     */
    public static void main(String[] args) throws Exception {
//        JFrame frame = new JFrame();
//        ImageIcon icon = new ImageIcon("src/binom.PNG");
//        JLabel label = new JLabel(icon);
//        frame.add(label);
//        frame.setDefaultCloseOperation
//                (JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//        String str = "nCr";
//        String str2 = "1+4^2^3*2+2-2";
//        String s = "÷";
//        str=str.replace("÷","/");
//        System.out.println(str);
        Calculator cal = new Calculator();

//        double n = 8,k=4;
//        double ans = applyOperator(applyOperator(cal.Factorial(k), '*', cal.Factorial(n - k)), '/', cal.Factorial(n));
//        System.out.println(ans);
//        double ans = cal.Eval(str);
//        System.out.println(ans);
    }

    private void init() {
        input = new JTextField("");
        input.setBackground(Color.white);
        input.setEditable(false);
        input.setFont(new Font("SansSerif ", Font.PLAIN, 25));
        input.setBounds(25, 30, 330, 70);

        btn0 = initButton("0", new int[]{25, 470, 60, 60}, this);
        btn1 = initButton("1", new int[]{25, 400, 60, 60}, this);
        btn2 = initButton("2", new int[]{95, 400, 60, 60}, this);
        btn3 = initButton("3", new int[]{165, 400, 60, 60}, this);
        btn4 = initButton("4", new int[]{25, 330, 60, 60}, this);
        btn5 = initButton("5", new int[]{95, 330, 60, 60}, this);
        btn6 = initButton("6", new int[]{165, 330, 60, 60}, this);
        btn7 = initButton("7", new int[]{25, 260, 60, 60}, this);
        btn8 = initButton("8", new int[]{95, 260, 60, 60}, this);
        btn9 = initButton("9", new int[]{165, 260, 60, 60}, this);
        btnAdd = initButton("+", new int[]{235, 190, 60, 60}, this);
        btnSub = initButton("-", new int[]{235, 260, 60, 60}, this);
        btnMul = initButton("*", new int[]{235, 330, 60, 60}, this);
        btnDiv = initButton("÷", new int[]{235, 400, 60, 60}, this);
        btnxPow = initButton("x^2", new int[]{25, 190, 60, 60}, this);
        sqrtx = initButton("√", new int[]{95, 190, 60, 60}, this);
        btnPow = initButton("^", new int[]{165, 190, 60, 60}, this);
        btnDel = initButton("C", new int[]{305, 190, 60, 60}, this);
        btnSin = initButton("Sin", new int[]{305, 260, 60, 60}, this);
        btnCos = initButton("Cos", new int[]{305, 330, 60, 60}, this);
        btnTan = initButton("Tan", new int[]{305, 400, 60, 60}, this);
        btnCr = initButton("nCr", new int[]{25, 540, 60, 60}, this);
        btnLog = initButton("Log", new int[]{165, 470, 60, 60}, this);
        btnLn = initButton("ln", new int[]{95, 470, 60, 60}, this);
        btnDot = initButton("•", new int[]{305, 470, 60, 60}, this);
        btnBracOpen = initButton("(", new int[]{95, 540, 60, 60}, this);
        btnBracClose = initButton(")", new int[]{165, 540, 60, 60}, this);
        btnMplus = initButton("M+", new int[]{235, 120, 60, 60}, this);
        btnMs = initButton("MS", new int[]{165, 120, 60, 60}, this);
        btnMr = initButton("MR", new int[]{95, 120, 60, 60}, this);
        btnMc = initButton("MC", new int[]{25, 120, 60, 60}, this);
        shiftRig = initButton("»", new int[]{305, 540, 60, 60}, this);
        shiftLef = initButton("«", new int[]{235, 540, 60, 60}, this);
        btnAc = initButton("AC", new int[]{305, 120, 60, 60}, this);
        equal = initButton("=", new int[]{235, 470, 60, 60}, this);

        Win.add(input);
    }

    public void actionPerformed(ActionEvent ae) {
        String e = ae.getActionCommand();
        String in = input.getText();
        if (e.equals("0")) input.setText(in + "0");
        if (e.equals("1")) input.setText(in + "1");
        if (e.equals("2")) input.setText(in + "2");
        if (e.equals("3")) input.setText(in + "3");
        if (e.equals("4")) input.setText(in + "4");
        if (e.equals("5")) input.setText(in + "5");
        if (e.equals("6")) input.setText(in + "6");
        if (e.equals("7")) input.setText(in + "7");
        if (e.equals("8")) input.setText(in + "8");
        if (e.equals("9")) input.setText(in + "9");
        if (e.equals(".")) input.setText(in + ".");

        if (e.equals("+")) input.setText(in + "+");
        if (e.equals("-")) input.setText(in + "-");
        if (e.equals("*") || e.equals("×")) input.setText(in + "*");
        if (e.equals("÷") || e.equals("/")) input.setText(in + "/");
        if (e.equals("^")) input.setText(in + "^");

        if (e.equals("C")) input.setText("");
        if (e.equals("√")) input.setText(in + "√");
        if (e.equals("=")) {
            System.out.println("=");
            String ans = "" + Eval(in);
            try {
                input.setText(new DecimalFormat("##.####").format(Double.parseDouble(ans)));
            } catch (Exception ex) {
                ans = ex.getMessage();
                input.setText(ans);
            }
        }
        if (e.equals("^")) input.setText(in + "");
        if (e.equals("Log")) input.setText(in + "Log");
        if (e.equals("Sin")) input.setText(in + "Sin");
        if (e.equals("Cos")) input.setText(in + "Cos");
        if (e.equals("Tan")) input.setText(in + "Tan");
        if (e.equals("nCr")) {
            input.setText(in + "(___)");
            int count = 0;
            String userInput = "";
            double n = 0, k = 0;
            ImageIcon icon = new ImageIcon("src/binom.PNG");
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
                if(n < k) throw new IllegalArgumentException("Error! 0 < k < n");
                //nCk={n! / k!(n-k)!}
                String nCr = "" + applyOperator(applyOperator(Factorial(k), '*', Factorial(n - k)), '/', Factorial(n));
                input.setText(in + nCr);
            }
            catch (IllegalArgumentException ex){input.setText(ex.getMessage());}

        }
        if (e.equals("x^2")) {
            input.setText(in + "__^2    ");
            String userInput = (String) JOptionPane.showInputDialog("Please enter a number");
            input.setText(in + userInput + "^2");
        }
        if (e.equals("AC")) input.setText("");
    }

    public double Factorial(double n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        return n * Factorial(n - 1);
    }

    public double Eval(String expression) throws IllegalArgumentException, ArithmeticException {
        if (expression.charAt(0) == '-') expression = "0" + expression;
        expression = expression.replace("(-", "(0-");
        //(?<=Cos)|" +
        //                "(?<=Sin)|(?<=Cos)|(?<=Tan)|(?<=Lan)|(?<=Ln)//|(?=Cos)|(?=Sin)|(?=Tan)|(?=Log)|(?=Ln)
        String splitReg = "(?<=[-+*/()^√]|(?<=Cos)|(?<=Sin)|(?<=Tan)|(?<=Log)|(?<=Ln)" +
                "|(?=[-+*/()^√]))";
        String chek = "((?<=Cos.()))";
        String[] expToStr = expression.replaceAll("\\s+", "").split(splitReg);
        //Stack for numbers
        Stack<Double> values = new Stack();
        //Stack for Operators
        Stack<String> operator = new Stack();
        //output
        List<String> output = new ArrayList();
        //regex to chek if value is type double
        String reg = "([0-9]*[.])?[0-9]+";


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
            } else if (token.matches(reg)) {
                //if token is number
                output.add(token);
            } else {
                int index = 0;
                if (i + 1 < expToStr.length && expToStr[i + 1].matches(reg)) {
                    index = 1;
                    String tmp = expToStr[i + 1];
                    try {
                        double x = Double.valueOf(tmp);
                        x = Math.toRadians(x);
                        switch (token) {
                            case "Cos":
                                output.add("" + Math.cos(x));
                                break;
                            case "Sin":
                                output.add("" + Math.sin(x));
                                break;
                            case "Tan":
                                if (x < -1.5 || x > 1.5) {
                                    input.setText("Error! Tan(90) is Undefined");
                                    throw new ArithmeticException("Tan(90) is Undefined");

                                } else output.add("" + Math.tan(x));
                                break;
                            case "Log":
                                output.add("" + Math.log(x));
                                break;
                            case "Ln":
                                output.add("" + Math.log10(x));
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("here");
                        input.setText(e.getMessage());
                    }
                } else {
                    throw new IllegalArgumentException();
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
                    //not a double
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

        return values.peek();
    }

    private JButton initButton(String name, int[] Bounds, ActionListener e) {
        JButton button = new JButton(name);
        button.setBounds(Bounds[0], Bounds[1], Bounds[2], Bounds[3]);
        button.addActionListener(e);

        Win.add(button);
        return button;
    }


}
