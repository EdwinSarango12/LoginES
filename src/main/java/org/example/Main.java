package org.example;

import org.example.login;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Login");
        frame.setContentPane(new login().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setPreferredSize(new Dimension(600,400));
        frame.pack();
        frame.setVisible(true);
    }
}