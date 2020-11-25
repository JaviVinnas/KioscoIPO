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
        //inicializamos
        this.nuevaPantalla(Pantallas.PANTALLA_BIENVENIDA);
    }

    //mete el jpanel principal del tope de la pila como ventana actual iniciándolo
    private void setPantalla(){
        Pantalla pantalla = this.historialPantallas.peek();
        pantalla.init();
        this.ventana.setContentPane(pantalla.getMainPanel());
    }

    public void nuevaPantalla(Pantallas pantalla){
        Pantalla nuevaPantalla = this.pantallasDisponibles.get(pantalla);
        //la inicializamos
        nuevaPantalla.init();
        //la metemos en el tope de la pila
        this.historialPantallas.push(nuevaPantalla);
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
    public void volverAlInicio(){
        Pantalla pantallaEnElTope = pantallasDisponibles.get(Pantallas.PANTALLA_BIENVENIDA);
        while (!this.historialPantallas.isEmpty()){
            pantallaEnElTope = this.historialPantallas.pop();
        }
        this.historialPantallas.push(pantallaEnElTope);
        setPantalla();
    }
}
