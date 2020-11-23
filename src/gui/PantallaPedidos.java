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
    private JTabbedPane tabbedPane1;
    private JList list1;
    private JButton button4;
    private JButton button5;
    private JTextArea textArea1;
    private final App app;

    public PantallaPedidos(App app){
        this.app = app;
        app.initComponentesPantalla(pasoLabel, telefonoLabel, horaLabel, 1);
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
