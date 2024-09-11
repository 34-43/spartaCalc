package com.example.lv3;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Stream;

public class Main {
    private JTabbedPane tabPane;
    private JPanel mainPanel;
    private JPanel calcTab;
    private JPanel historyTab;
    private JPanel btnGrid;
    private JTextField InputField;
    private JTextField conditionField;
    private JCheckBox conditionCheck;
    private JRadioButton radioBigger;
    private JRadioButton radioSmaller;
    private JScrollPane scrollReadPane;
    private JPanel readPanel;
    private JButton btn0;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btnL;
    private JButton btnR;
    private JButton btnDot;
    private JButton btnAdd;
    private JButton btnSub;
    private JButton btnMul;
    private JButton btnDiv;
    private JButton btnEnter;
    private JButton btnBack;
    private JButton btnRead;
    private JButton btnDel;

    private final CalculatorApp calcApp = new CalculatorApp();

    public Main() {
        readPanel.setLayout(new GridLayout(0,1));
        scrollReadPane.setViewportView(readPanel);
        ButtonGroup group = new ButtonGroup();
        group.add(radioSmaller);
        group.add(radioBigger);

        JButton[] btnNums = {btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnL,btnR,btnDot,btnAdd,btnSub,btnMul,btnDiv};
        for (JButton btn : btnNums) {
            btn.addActionListener(ev -> {
                String input = btn.getActionCommand();
                int caretPos = InputField.getCaretPosition();
                InputField.setText(new StringBuilder(InputField.getText()).insert(caretPos,input).toString());
            });
        }
        btnBack.addActionListener(ev -> {
            int caretPos = InputField.getCaretPosition();
            if (caretPos != 0 && !InputField.getText().isEmpty()) {
                InputField.setText(new StringBuilder(InputField.getText()).deleteCharAt(caretPos-1).toString());
            }
        });

        btnEnter.addActionListener(ev -> {
            String input = InputField.getText();
            try {
                InputField.setText(calcApp.evaluation(input).toString());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                InputField.setText("ERROR");
            }

        });

        btnRead.addActionListener(ev -> {
            Stream<Number> stream = Stream.empty();

            if (conditionCheck.isSelected()) {
                String str = conditionField.getText();
                try {
                    double inputVal = Double.parseDouble(str);
                    if (radioBigger.isSelected()) {
                        stream = calcApp.getResultStream().filter(a -> a.doubleValue() > inputVal);
                    } else if (radioSmaller.isSelected()) {
                        stream = calcApp.getResultStream().filter(a -> a.doubleValue() < inputVal);
                    } else {
                        readPanel.add(new JLabel("조건을 선택해 주세요."));
                    }
                } catch (NumberFormatException ex) {readPanel.add(new JLabel("잘못된 값입니다."));}
            } else {
                stream = calcApp.getResultStream();
            }
            readPanel.removeAll();
            stream.forEach((num)->{
                readPanel.add(new JLabel(num.toString()));
            });
            readPanel.revalidate();
            readPanel.repaint();
        });

        btnDel.addActionListener(ev -> {
            calcApp.removeResult();
            readPanel.removeAll();
            calcApp.getResultStream().forEach((num)->{
                readPanel.add(new JLabel(num.toString()));
            });
            readPanel.revalidate();
            readPanel.repaint();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("스파르타 계산기");
        frame.setContentPane(new Main().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
