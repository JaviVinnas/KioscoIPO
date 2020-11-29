package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Navigator {
    private final App app;
    private final JFrame ventana;
    private final Stack<Pantalla> historialPantallas;
    private final Map<Pantallas,Pantalla> pantallasDisponibles;

    public Navigator(App app){
        this.app = app;
        this.ventana = new JFrame("Kiosko");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //para que la ventana aparezca en el centro
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.ventana.setLocation(dim.width/2-this.ventana.getSize().width/2, dim.height/2-this.ventana.getSize().height/2);
        this.ventana.setResizable(false);
        this.historialPantallas = new Stack<>();
        this.pantallasDisponibles = new HashMap<>();
        //introducimos las posibles pantallas
        this.pantallasDisponibles.put(Pantallas.PANTALLA_BIENVENIDA, new PantallaBienvenida(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_PEDIDOS, new PantallaPedidos(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_QUIERES_SALIR, new PantallaQuieresSalir(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_HORA_RECOGIDA, new PantallaHoraRecogida(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_ACERCA_TARJETA, new PantallaAcercaTarjeta(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_INTRODUCE_PIN, new PantallaIntroducePin(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_RESUMEN_PEDIDO, new PantallaResumenPedido(this.app));
        this.pantallasDisponibles.put(Pantallas.PANTALLA_MENU_MANANA, new PantallaMenuDiaSiguiente(this.app));
    }

    //mete el jpanel principal del tope de la pila como ventana actual iniciándolo
    private void setPantalla(){
        Pantalla pantalla = this.historialPantallas.peek();
        pantalla.init();
        this.ventana.setContentPane(pantalla.getMainPanel());
    }

    public void nuevaPantalla(Pantallas pantalla){
        //la metemos en el tope de la pila
        this.historialPantallas.push(this.pantallasDisponibles.get(pantalla));
        setPantalla();
        this.ventana.pack();
        this.ventana.setVisible(true);
    }
    public void volverPantalla(){
        Pantalla pantallaActual = this.historialPantallas.pop();
        //si esta era la última pantalla
        if(this.historialPantallas.isEmpty()){
            //volvemos a meterla (no pasaría nada)
            this.historialPantallas.push(pantallaActual);
        }else{
            //si no lo era asignamos a la ventana la siguiente pantalla en la pila inicializándola previamente
            setPantalla();
        }
    }

    public void volverPantallaPrincipal(){
        while (!this.historialPantallas.isEmpty()){
            this.historialPantallas.pop();
        }
        //estará vacía
        nuevaPantalla(Pantallas.PANTALLA_BIENVENIDA);
    }

    public JFrame getVentana() {
        return ventana;
    }
}
