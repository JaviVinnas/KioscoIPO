package gui;

import aplicacion.App;
import aplicacion.ItemCarta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PantallaMenuDiaSiguiente implements Pantalla {
    private JTextArea infoItemMenu;
    private JList<String> listaPrimeros;
    private JList<String> listaSegundos;
    private JList<String> listaBebidas;
    private JList<String> listaPostres;
    private JButton volverButton;
    private JLabel telefonoLabel;
    private JLabel horaLabel;
    private JPanel mainPanel;
    //
    App app;

    public PantallaMenuDiaSiguiente(App app) {
        this.app = app;
        app.initComponentesPantalla(new ComponentesConstantesPantalla.ComponentesConstantesPantallaBuilder(app).setTelefono(telefonoLabel).build());
        volverButton.addActionListener(e -> app.volverPantalla());
        //listas
        ModeloListaStrings modeloPrimeros = new ModeloListaStrings();
        modeloPrimeros.addElemento("Crema de calabaza con jamón");
        modeloPrimeros.addElemento("Huevos rellenos de atún");
        modeloPrimeros.addElemento("Risotto de verduras");
        listaPrimeros.setModel(modeloPrimeros);
        ModeloListaStrings modeloSegundos = new ModeloListaStrings();
        modeloSegundos.addElemento("Pisto al vapor con filetes de cerdo");
        modeloSegundos.addElemento("Salmón a la plancha con puré de patata");
        modeloSegundos.addElemento("Macarrones al pesto");
        listaSegundos.setModel(modeloSegundos);
        ModeloListaStrings modeloBebidas = new ModeloListaStrings();
        modeloBebidas.addElemento("Agua Mineral");
        modeloBebidas.addElemento("Aquarius de naranja");
        modeloBebidas.addElemento("Estrella Galicia");
        listaBebidas.setModel(modeloBebidas);
        ModeloListaStrings modeloPostres = new ModeloListaStrings();
        modeloPostres.addElemento("Macedonia de frutas");
        modeloPostres.addElemento("Macedonia de frutas");
        modeloPostres.addElemento("Yogurt natural casero");
        listaPostres.setModel(modeloPostres);

        listaPrimeros.addMouseListener(new ItemMenuListaClicked(listaPrimeros));
        listaSegundos.addMouseListener(new ItemMenuListaClicked(listaSegundos));
        listaBebidas.addMouseListener(new ItemMenuListaClicked(listaBebidas));
        listaPostres.addMouseListener(new ItemMenuListaClicked(listaPostres));
    }

    private class ItemMenuListaClicked extends MouseAdapter{
        private final JList<String> lista;
        public ItemMenuListaClicked(JList<String> lista){
            this.lista = lista;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            String idItemMenu = lista.getModel().getElementAt(lista.getSelectedIndex());
            ItemCarta itemCartaProvisional = app.getItemCartabyNombre(idItemMenu);
            infoItemMenu.setText(itemCartaProvisional.toString());
            if(listaPrimeros != lista){
                listaPrimeros.clearSelection();
            }
            if(listaSegundos != lista){
                listaSegundos.clearSelection();
            }
            if(listaPostres != lista){
                listaPostres.clearSelection();
            }
            if(listaBebidas != lista){
                listaBebidas.clearSelection();
            }

        }
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
        listaPrimeros.clearSelection();
        listaSegundos.clearSelection();
        listaPostres.clearSelection();
        listaBebidas.clearSelection();
        infoItemMenu.setText("");
    }
}
