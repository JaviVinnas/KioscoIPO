package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTimeOut implements Pantalla {
    private JButton sigoAquiButton;
    private JPanel mainPanel;
    //
    App app;

    public PantallaTimeOut(App app) {
        this.app = app;
        sigoAquiButton.addActionListener(e -> {
            app.volverPantalla();
            app.empezarCuentaAtras();
        });
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
