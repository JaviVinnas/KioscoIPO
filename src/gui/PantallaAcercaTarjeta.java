package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaAcercaTarjeta implements Pantalla {
    private JPanel mainPanel;
    private JTextArea infoPedidoTextArea;
    private JButton simularLecturaButton;
    private JButton volverButton;
    private JButton cancelarButton;
    private JLabel intruccionesLabel;
    private JLabel pasoLabel;
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    //otras
    App app;


    public PantallaAcercaTarjeta(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 3).setTelefono(telefonoLabel).build());

        volverButton.addActionListener(e -> {
            app.volverPantalla();
            app.empezarCuentaAtras();
        });
        cancelarButton.addActionListener(e -> {
            app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR);
            app.empezarCuentaAtras();
        });
        simularLecturaButton.addActionListener(e -> {
            app.nuevaPantalla(Pantallas.PANTALLA_INTRODUCE_PIN);
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
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setHora(horaLabel).build());
        infoPedidoTextArea.setText(app.getPedido().toString());
    }
}
