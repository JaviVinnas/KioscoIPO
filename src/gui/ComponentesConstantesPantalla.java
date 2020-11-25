package gui;

import aplicacion.App;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentesConstantesPantalla {
    private final App app;
    private final JLabel paso;
    private final int numPaso;
    private final JLabel telefono;
    private final JLabel hora;
    private final JButton botonVolver;
    private final JButton botonCancelar;
    private final JButton botonConfirmar;
    private final boolean botonVolverVisible;
    private final boolean botonCancelarVisible;
    private final boolean botonConfirmarVisible;
    private final Pantallas siguientePantalla;

    public static class ComponentesConstantesPantallaBuilder{
        private final App app;
        private JLabel paso;
        private int numPaso;
        private JLabel telefono;
        private JLabel hora;
        private JButton botonVolver;
        private boolean botonVolverVisible;
        private JButton botonCancelar;
        private boolean botonCancelarVisible;
        private JButton botonConfirmar;
        private boolean botonConfirmarVisible;
        private Pantallas siguientePantalla;

        public ComponentesConstantesPantallaBuilder(App app){
            this.app=app;
        }

        public ComponentesConstantesPantallaBuilder setPaso( JLabel paso, int numPaso){
            this.paso = paso;
            this.numPaso = numPaso;
            return this;
        }

        public ComponentesConstantesPantallaBuilder setTelefono(JLabel telefono) {
            this.telefono = telefono;
            return this;
        }

        public ComponentesConstantesPantallaBuilder setHora(JLabel hora) {
            this.hora = hora;
            return this;
        }

        public ComponentesConstantesPantallaBuilder setBotonCancelar(JButton botonCancelar, boolean botonCancelarVisible) {
            this.botonCancelar = botonCancelar;
            this.botonCancelarVisible = botonCancelarVisible;
            return this;
        }

        public ComponentesConstantesPantallaBuilder setBotonConfirmar(JButton botonConfirmar, boolean botonConfirmarVisible, Pantallas siguientePantalla) {
            this.botonConfirmar = botonConfirmar;
            this.botonConfirmarVisible = botonConfirmarVisible;
            this.siguientePantalla = siguientePantalla;
            return this;
        }

        public ComponentesConstantesPantallaBuilder setBotonVolver(JButton botonVolver, boolean botonVolverVisible) {
            this.botonVolver = botonVolver;
            this.botonVolverVisible = botonVolverVisible;
            return this;
        }

        public ComponentesConstantesPantalla build(){
            return new ComponentesConstantesPantalla(this);
        }
    }

    private ComponentesConstantesPantalla(ComponentesConstantesPantallaBuilder builder){
        this.app= builder.app;
        this.paso=builder.paso;
        this.numPaso= builder.numPaso;
        this.telefono= builder.telefono;
        this.hora= builder.hora;
        this.botonVolver= builder.botonVolver;
        this.botonCancelar= builder.botonCancelar;
        this.botonConfirmar= builder.botonConfirmar;
        this.botonVolverVisible= builder.botonVolverVisible;
        this.botonCancelarVisible= builder.botonCancelarVisible;
        this.botonConfirmarVisible= builder.botonConfirmarVisible;
        this.siguientePantalla= builder.siguientePantalla;
    }

    public void construir(){
        //todo: pasar la lógica de montar los componentes de la fachada GUI aquí
        if(this.telefono!=null){
            telefono.setText("Teléfono: " + app.getTelefono());
            app.cambiarTamText(telefono, 15.0f);
        }
        if(this.hora!=null){
            hora.setText(app.getHoraActual());
            app.cambiarTamText(hora, 15.0f);
        }
        if(this.paso!=null && this.numPaso >= 0){
            String descripcion;
            switch (this.numPaso){
                case 1:
                    descripcion = "configura tu pedido";
                    break;
                case 2:
                    descripcion = "hora de recogida del pedido";
                    break;
                case 3:
                    descripcion = "pago del pedido";
                    break;
                case 4:
                    descripcion = "resumen del pedido";
                    break;
                default:
                    descripcion = "ERROR";
            }
            paso.setText("Paso " + numPaso + " de 4: " + descripcion);
            app.cambiarTamText(paso, 15.0f);
        }
        //barra de navegación
        //botón de volver
        if(botonCancelar != null){
            botonCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.nuevaPantalla(Pantallas.PANTALLA_QUIERES_SALIR);
                }
            });
        }
        //botón de confirmar
        if(botonConfirmar!=null){
            botonCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.nuevaPantalla(siguientePantalla);
                }
            });
        }
        if(botonVolver!=null){
            botonVolver.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    app.volverPantalla();
                }
            });
        }

    }
}
