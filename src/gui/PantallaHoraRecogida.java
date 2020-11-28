package gui;

import aplicacion.App;

import javax.swing.*;
import java.time.LocalDateTime;

public class PantallaHoraRecogida implements Pantalla{
    private JPanel mainPanel;
    private JButton volverButton;
    private JButton cancelarButton;
    private JButton confirmarButton;
    private JTextArea erroresTextArea;
    private JTextArea infoHorarioTextArea;
    private JTextArea infoPedidoTextArea;
    private JComboBox<Integer> horasComboBox;
    private JComboBox<Integer> minutosComboBox;
    private JLabel pasoLabel;
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    private JLabel tituloLabel;
    //variables propias
    App app;

    public PantallaHoraRecogida(App app){
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 2).setTelefono(telefonoLabel).build());
        app.cambiarTamText(tituloLabel, 35);
        //barra de navegación
        volverButton.addActionListener(e -> app.volverPantalla());
        cancelarButton.addActionListener(e -> app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR));
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
        //iniciamos los comboboxes de las horas
        int hora = LocalDateTime.now().getHour();
        for( int i = hora; i <= 24; i++){
            horasComboBox.addItem(i);
        }
        for( int i = 0; i <= 60; i++){
            minutosComboBox.addItem(i);
        }
        infoPedidoTextArea.setText(app.getPedido().toString());
    }
}
