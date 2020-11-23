package gui;

import aplicacion.App;

import javax.swing.*;

public class PantallaPedidos implements Pantalla{
    private JPanel mainPanel;
    private JButton volverButton;
    private JButton confirmarButton;
    private JButton cancelarButton;
    private JLabel pasoLabel;
    private JLabel horaLabel;
    private JLabel telefonoLabel;
    private JTabbedPane panelConTablasMenuTabbedPane;
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
