import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorUI extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0;
    private String operator = "";

    private CalculatorLogic logic = new CalculatorLogic();

    public CalculatorUI() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","C","=","+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            display.setText(display.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            num1 = Double.parseDouble(display.getText());
            operator = cmd;
            display.setText("");
        } else if (cmd.equals("=")) {
            double num2 = Double.parseDouble(display.getText());
            double result = logic.calculate(num1, num2, operator);
            display.setText(String.valueOf(result));
        } else if (cmd.equals("C")) {
            display.setText("");
        }
    }
}