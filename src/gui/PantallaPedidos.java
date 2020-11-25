package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPedidos implements Pantalla {
    private JPanel mainPanel;
    private JButton volverButton;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel pasoLabel;
    private JLabel horaLabel;
    private JLabel telefonoLabel;
    private JTabbedPane menuInfantilPanel;
    private JList listaItemsPedidoJList;
    private JButton borrarButton;
    private JButton borrarTodosButton;
    private JTextArea infoItemPedidoTextArea;
    private JPanel fueraDeCartaPanel;
    private JPanel dentroDeLaCartaPanel;
    private JList listaPrimerosJList;
    private JList listaSegundosJList;
    private JList listaBebidasJList;
    private JList listaPostresJList;
    private JTextArea InfoElementoCartaTextArea;
    private JButton añadirAlMenúButton;
    private JTextArea InfoMenuTextArea;
    private JButton borrarMenúActualButton;
    private JButton añadirMenúAlPedidoButton;
    private JLabel postresLabel;
    private JLabel bebidasLabel;
    private JLabel segundosLabel;
    private JLabel primerosLabel;
    private JList listaFueradelMenu;
    private JTextArea infoItemFueraDelMenu;
    private JButton añadirAlMenúButton1;
    private JTextArea infoMenuInfantilTextArea;
    private JButton añadirMenúAlPedidoButton1;
    private final App app;

    public PantallaPedidos(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 1).setTelefono(telefonoLabel).setHora(horaLabel).setBotonVolver(volverButton, true).setBotonCancelar(cancelarButton, true).setBotonConfirmar(confirmarButton, true, null).build());


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
