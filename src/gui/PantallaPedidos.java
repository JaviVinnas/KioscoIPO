package gui;

import aplicacion.App;

import javax.swing.*;

public class PantallaPedidos implements Pantalla{
    private JPanel mainPanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel pasoLabel;
    private JLabel horaLabel;
    private JLabel telefonoLabel;
    private App app;

    public PantallaPedidos(App app){
        this.app = app;
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public App getApp() {
        return app;
    }
}
