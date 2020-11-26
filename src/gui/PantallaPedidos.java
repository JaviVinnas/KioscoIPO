package gui;

import aplicacion.App;

import javax.swing.*;

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
    private JList<String> listaPrimerosJList;
    private JList listaSegundosJList;
    private JList listaBebidasJList;
    private JList listaPostresJList;
    private JTextArea InfoElementoCartaTextArea;
    private JButton anadirAlMenuButton;
    private JTextArea InfoMenuTextArea;
    private JButton borrarMenuActualButton;
    private JButton anadirMenuAlPedidoButton;
    private JLabel postresLabel;
    private JLabel bebidasLabel;
    private JLabel segundosLabel;
    private JLabel primerosLabel;
    private JList listaFueradelMenu;
    private JTextArea infoItemFueraDelMenu;
    private JButton anadirAlMenuButton1;
    private JTextArea infoMenuInfantilTextArea;
    private JButton anadirMenuAlPedidoButton1;
    private final App app;

    public PantallaPedidos(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setPaso(pasoLabel, 1).setTelefono(telefonoLabel).build());
        //barra de navegación
        volverButton.addActionListener(e -> app.volverPantalla());
        cancelarButton.addActionListener(e -> app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR));

        //listas
        ModeloListaStrings modeloLista = new ModeloListaStrings();
        modeloLista.addElemento("Alcachofas");
        modeloLista.addElemento("Berenjenas");
        modeloLista.addElemento("Boniatos");
        this.listaPrimerosJList.setModel(modeloLista);


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
    }
}
