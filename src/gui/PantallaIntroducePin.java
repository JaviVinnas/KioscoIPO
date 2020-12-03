package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaIntroducePin implements Pantalla {
    private JPanel panel1;
    private JTextField pinField;
    private JButton borrarButton;
    private JButton unoBoton;
    private JButton dosBoton;
    private JButton tresBoton;
    private JButton cuatroBoton;
    private JButton cincoBoton;
    private JButton seisBoton;
    private JButton sieteBoton;
    private JButton ochoBoton;
    private JButton nueveBoton;
    private JButton ceroBoton;
    private JTextArea infoPedido;
    private JButton volverButton;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JLabel pasoLabel;
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    private JLabel instruccionesPinJLabel;
    private JButton BORRARTODOButton;
    //otros
    private App app;

    public PantallaIntroducePin(App app) {
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
        confirmarButton.addActionListener(e -> {
            app.getPedido().generarId();
            app.nuevaPantalla(Pantallas.PANTALLA_RESUMEN_PEDIDO);
            app.empezarCuentaAtras();
        });

        unoBoton.addActionListener(new BotonDigitoClicked());
        dosBoton.addActionListener(new BotonDigitoClicked());
        tresBoton.addActionListener(new BotonDigitoClicked());
        cuatroBoton.addActionListener(new BotonDigitoClicked());
        cincoBoton.addActionListener(new BotonDigitoClicked());
        seisBoton.addActionListener(new BotonDigitoClicked());
        sieteBoton.addActionListener(new BotonDigitoClicked());
        ochoBoton.addActionListener(new BotonDigitoClicked());
        nueveBoton.addActionListener(new BotonDigitoClicked());
        ceroBoton.addActionListener(new BotonDigitoClicked());
        borrarButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            if (pinField.getText().length() > 0) {
                pinField.setText(pinField.getText().substring(0, pinField.getText().length() - 1));
                instruccionesPinJLabel.setText("Introduce los 4 dígitos del pin");
            }

        });
        BORRARTODOButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            pinField.setText("");
            instruccionesPinJLabel.setText("Introduce los 4 dígitos del pin");
        });
    }

    private class BotonDigitoClicked implements ActionListener {

        public BotonDigitoClicked() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            app.empezarCuentaAtras();
            if (pinField.getText().length() < 4) {
                pinField.setText(pinField.getText() + '*');
                if(pinField.getText().length() == 4){
                    confirmarButton.setEnabled(true);
                    instruccionesPinJLabel.setText("PIN de longitud correcta");
                }
            } else {
                instruccionesPinJLabel.setText("El código PIN tiene 4 cifras como máximo (no se pueden introducir más números)");
            }
        }
    }

    @Override
    public JPanel getMainPanel() {
        return panel1;
    }

    @Override
    public App getApp() {
        return null;
    }

    @Override
    public void init() {
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setHora(horaLabel).build());
        infoPedido.setText(app.getPedido().toString());
        pinField.setText("");
        confirmarButton.setEnabled(false);
        instruccionesPinJLabel.setText("Introduce los 4 dígitos del pin");
    }
}
