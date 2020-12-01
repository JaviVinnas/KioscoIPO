package gui;

import aplicacion.App;
import aplicacion.Hora;

import javax.swing.*;
import java.awt.event.*;

public class PantallaHoraRecogida implements Pantalla {
    private JPanel mainPanel;
    private JButton volverButton;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextArea infoHorarioTextArea;
    private JTextArea infoPedidoTextArea;
    private JComboBox<String> horasComboBox;
    private JComboBox<String> minutosComboBox;
    private JLabel pasoLabel;
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    private JLabel tituloLabel;
    private JTextArea errorHoraTextArea;
    //variables propias
    App app;
    Hora horaProvisional;

    public PantallaHoraRecogida(App app) {
        this.app = app;
        this.horaProvisional = new Hora();
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 2).setTelefono(telefonoLabel).build());
        app.cambiarTamText(tituloLabel, 35);
        //barra de navegación
        volverButton.addActionListener(e -> {
            app.volverPantalla();
            app.empezarCuentaAtras();
        });
        cancelarButton.addActionListener(e -> {
            app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR);
            app.empezarCuentaAtras();
        });
        infoHorarioTextArea.setText("Recuerda que estamos abiertos para puedas recoger tus pedidos de 12 a 16 horas y de 19 a 22 horas ");

        minutosComboBox.addActionListener(e -> {
            app.empezarCuentaAtras();
            try {
                String item = minutosComboBox.getSelectedItem().toString();
                horaProvisional.setMinuto((Integer.parseInt(item)));
                errorHoraTextArea.setText(horaProvisional.toString());
                String motivoErrorHora = horaProvisional.porQueNoEsValido();

                if (motivoErrorHora == null) {
                    errorHoraTextArea.setText("Hora de recogida válida (" + horaProvisional.toString() + ")");
                    confirmarButton.setEnabled(true);
                } else {
                    errorHoraTextArea.setText(motivoErrorHora);
                    confirmarButton.setEnabled(false);
                }

            } catch (NullPointerException ignore) {
                ignore.printStackTrace();
            }
        });


        horasComboBox.addActionListener(e -> {
            app.empezarCuentaAtras();
            try {
                String item = horasComboBox.getSelectedItem().toString();
                horaProvisional.setHora((Integer.parseInt(item)));
                System.out.println(horaProvisional);
                errorHoraTextArea.setText(horaProvisional.toString());
                String motivoErrorHora = horaProvisional.porQueNoEsValido();

                if (motivoErrorHora == null) {
                    errorHoraTextArea.setText("Hora de recogida válida (" + horaProvisional.toString() + ")");
                    confirmarButton.setEnabled(true);
                } else {
                    errorHoraTextArea.setText(motivoErrorHora);
                    confirmarButton.setEnabled(false);
                }

            } catch (NullPointerException ignore) {
                ignore.printStackTrace();
            }
        });
        confirmarButton.addActionListener(e -> {
            app.empezarCuentaAtras();
            app.getPedido().setHoraRecogida(horaProvisional);
            app.nuevaPantalla(Pantallas.PANTALLA_ACERCA_TARJETA);
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
