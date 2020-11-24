package gui;

import javax.swing.*;

public class ComponentesConstantesPantalla {
    private JLabel paso;
    private int numPaso;
    private JLabel telefono;
    private JLabel hora;
    private JButton botonVolver;
    private JButton botonCancelar;
    private JButton botonConfirmar;
    private boolean botonVolverVisible;
    private boolean botonCancelarVisible;
    private boolean botonConfirmarVisible;

    public static class ComponentesConstantesPantallaBuilder{
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

        public ComponentesConstantesPantallaBuilder(){}

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

        public ComponentesConstantesPantallaBuilder setBotonConfirmar(JButton botonConfirmar, boolean botonConfirmarVisible) {
            this.botonConfirmar = botonConfirmar;
            this.botonConfirmarVisible = botonConfirmarVisible;
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
    }

    public void construir(){
        //todo: pasar la lógica de montar los componentes de la fachada GUI aquí
    }
}
