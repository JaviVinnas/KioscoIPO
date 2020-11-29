package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PantallaResumenPedido implements Pantalla {
    private JPanel panel1;
    private JTextArea infoPedido;
    private JButton imprimirReciboButton;
    private JButton finalizarRevisiónButton;
    private JTextField correoCampo;
    private JButton enviarButton;
    private JButton consultaNuestroMenúParaButton;
    private JLabel pasoLabel;
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    private JLabel correoGuardadoLabel;
    private JLabel infoImpresión;
    //
    App app;

    public PantallaResumenPedido(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setTelefono(telefonoLabel).setPaso(pasoLabel,4).build());
        finalizarRevisiónButton.addActionListener(e -> app.volverPantallaPrincipal());
        imprimirReciboButton.addActionListener(e -> infoImpresión.setText("Espere a que se imprima el recibo..."));
        enviarButton.addActionListener(e -> {
            Pattern patronValidoEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matscher = patronValidoEmail.matcher(correoCampo.getText());
            if(matscher.find()){
                correoGuardadoLabel.setText("Correo enviado satisfactoriamente a " + correoCampo.getText());
            }else {
                correoGuardadoLabel.setText("Introduce una dirección de correo válida");
            }

        });
        consultaNuestroMenúParaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.nuevaPantalla(Pantallas.PANTALLA_MENU_MANANA);
            }
        });
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
        infoImpresión.setText("");
        correoGuardadoLabel.setText("");
        correoCampo.setText("");
    }
}
