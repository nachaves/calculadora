
package com.mycompany.calculadora;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField pantalla;
    private double operando1, operando2, resultado;
    private String operacion;

    public Calculadora() {
        operando1 = operando2 = resultado = 0;
        operacion = "";
        
        pantalla = new JTextField(15);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        pantalla.setFont(new Font("Arial", Font.PLAIN, 24));

        JButton[] botones = new JButton[20];
        String[] etiquetas = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", ".", "=", "/",
            "C", "sin", "cos", "tan"
        };

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        for (int i = 0; i < 20; i++) {
            botones[i] = new JButton(etiquetas[i]);
            botones[i].setFont(new Font("Arial", Font.PLAIN, 18));
            botones[i].addActionListener(this);
            panel.add(botones[i]);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(pantalla, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculadora");
        setSize(300, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/") || comando.equals("=")) {
            if (!operacion.equals("")) {
                operando2 = Double.parseDouble(pantalla.getText());
                switch (operacion) {
                    case "+":
                        resultado = operando1 + operando2;
                        break;
                    case "-":
                        resultado = operando1 - operando2;
                        break;
                    case "*":
                        resultado = operando1 * operando2;
                        break;
                    case "/":
                        resultado = operando1 / operando2;
                        break;
                }
                pantalla.setText(String.valueOf(resultado));
            }
            if (!comando.equals("=")) {
                operando1 = Double.parseDouble(pantalla.getText());
                operacion = comando;
                pantalla.setText("");
            }
        } else if (comando.equals("C")) {
            pantalla.setText("");
            operando1 = operando2 = resultado = 0;
            operacion = "";
        } else if (comando.equals("sin")) {
            double num = Double.parseDouble(pantalla.getText());
            resultado = Math.sin(Math.toRadians(num));
            pantalla.setText(String.valueOf(resultado));
        } else if (comando.equals("cos")) {
            double num = Double.parseDouble(pantalla.getText());
            resultado = Math.cos(Math.toRadians(num));
            pantalla.setText(String.valueOf(resultado));
        } else if (comando.equals("tan")) {
            double num = Double.parseDouble(pantalla.getText());
            resultado = Math.tan(Math.toRadians(num));
            pantalla.setText(String.valueOf(resultado));
        } else {
            pantalla.setText(pantalla.getText() + comando);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}
