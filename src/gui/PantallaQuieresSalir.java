package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaQuieresSalir implements Pantalla{
    private final App app;
    private JPanel mainPanel;
    private JButton siButton;
    private JButton noButton;

    public PantallaQuieresSalir(App app){
        this.app =  app;
        siButton.addActionListener(e -> app.volverPantallaPrincipal());
        noButton.addActionListener(e -> app.volverPantalla());
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public App getApp() {
        return app;
    }

    @Override
    public void init() {

    }
}
