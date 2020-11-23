package aplicacion;

import gui.FachadaGUI;
import gui.Pantalla;

import javax.swing.*;

public class FachadaAplicacion implements App {

    private final FachadaGUI fgui;
    private final baseDatos.FachadaBaseDatos fbd;

    @Override
    public void nuevaPantalla(Pantalla pantalla) {
        this.fgui.nuevaPantalla(pantalla);
    }

    @Override
    public void volverPantalla() {
        this.fgui.volverPantalla();
    }

    @Override
    public void mostrarAlerta(String mensaje) {
        this.fgui.mostrarAlerta(mensaje);
    }

    @Override
    public void initComponentesPantalla(JLabel paso, JLabel telefono, JLabel hora, int numPaso) {
        this.fgui.initComponentesPantalla(paso, telefono, hora, numPaso);
    }

    @Override
    public void initComponentesPantalla(JLabel telefono, JLabel hora) {
        this.fgui.initComponentesPantalla(telefono, hora);
    }

    @Override
    public JButton generarImagenJButton(String pathImagen, int alto, int ancho) {
        return this.fgui.generarImagenJButton(pathImagen, alto, ancho);
    }

    @Override
    public void cambiarTamText(JButton boton, float tamano) {
        this.fgui.cambiarTamText(boton, tamano);
    }

    @Override
    public void cambiarTamText(JLabel texto, float tamano) {
        this.fgui.cambiarTamText(texto, tamano);
    }

    @Override
    public String getHoraActual() {
        return fbd.getHoraActual();
    }

    @Override
    public String getTelefono() {
        return fbd.getTelefono();
    }

    @Override
    public ItemCarta getItemCartabyNombre(String nombre) {
        return fbd.getItemCartabyNombre(nombre);
    }

    @Override
    public void addItemCartaAlMenu(ItemCarta item) throws ErrorMenu {
        fbd.addItemCartaAlMenu(item);
    }

    @Override
    public void quitarItemCartaAlMenu(ItemCarta item) {
        fbd.quitarItemCartaAlMenu(item);
    }

    @Override
    public void addPagableAlPedido(Pagable pagable) {
        fbd.addPagableAlPedido(pagable);
    }

    @Override
    public void quitarPagableAlPedido(Pagable pagable, boolean todos) {
        fbd.quitarPagableAlPedido(pagable, todos);
    }

    @Override
    public float getPrecioPedido() {
        return fbd.getPrecioPedido();
    }

    public void iniciarGui() {
        this.fgui.iniciarGui();
    }

    public FachadaAplicacion() {
        this.fgui = new FachadaGUI(this);
        this.fbd = new baseDatos.FachadaBaseDatos(this);
    }

    public static void main(String[] args) {
        FachadaAplicacion fa = new FachadaAplicacion();
        fa.iniciarGui();
    }

}
