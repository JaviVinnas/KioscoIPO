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
    private JList<String> listaItemsPedidoJList;
    private JButton borrarButton;
    private JButton borrarTodosButton;
    private JTextArea infoItemPedidoTextArea;
    private JPanel fueraDeCartaPanel;
    private JPanel dentroDeLaCartaPanel;
    private JList<String> listaPrimerosJList;
    private JList<String> listaSegundosJList;
    private JList<String> listaBebidasJList;
    private JList<String> listaPostresJList;
    private JTextArea infoElementoCartaTextArea;
    private JButton anadirAlMenuButton;
    private JTextArea infoMenuTextArea;
    private JButton borrarMenuActualButton;
    private JButton anadirMenuAlPedidoButton;
    private JLabel postresLabel;
    private JLabel bebidasLabel;
    private JLabel segundosLabel;
    private JLabel primerosLabel;
    private JList<String> listaFueradelMenu;
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
        ModeloListaStrings modeloListaPrimeros = new ModeloListaStrings();
        modeloListaPrimeros.addElemento("Sopa de pescado");
        modeloListaPrimeros.addElemento("Pincho de tortilla");
        modeloListaPrimeros.addElemento("Tostas de pulpo con queso");
        this.listaPrimerosJList.setModel(modeloListaPrimeros);
        ModeloListaStrings modeloListaSegundos = new ModeloListaStrings();
        modeloListaSegundos.addElemento("Pota de berberechos");
        modeloListaSegundos.addElemento("Filetes con salsa de castañas");
        modeloListaSegundos.addElemento("Albóndigas con champiñones");
        this.listaSegundosJList.setModel(modeloListaSegundos);
        ModeloListaStrings modeloListaBebidas = new ModeloListaStrings();
        modeloListaBebidas.addElemento("Agua Mineral");
        modeloListaBebidas.addElemento("Coca-Cola");
        modeloListaBebidas.addElemento("Estrella Galicia");
        this.listaBebidasJList.setModel(modeloListaBebidas);
        ModeloListaStrings modeloListaPostres = new ModeloListaStrings();
        modeloListaPostres.addElemento("Yogurt natural casero");
        modeloListaPostres.addElemento("Mandarina");
        modeloListaPostres.addElemento("Brownie de chocolate");
        this.listaPostresJList.setModel(modeloListaPostres);




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
        listaPrimerosJList.clearSelection();
        listaSegundosJList.clearSelection();
        listaBebidasJList.clearSelection();
        listaPostresJList.clearSelection();
        infoElementoCartaTextArea.setText("");
        anadirAlMenuButton.setEnabled(false);
        app.reiniciarMenuPreeliminar();
        infoMenuTextArea.setText("");
        borrarMenuActualButton.setEnabled(false);
        anadirMenuAlPedidoButton.setEnabled(false);

    }
}
