package gui;

import aplicacion.App;

import java.util.Timer;
import java.util.TimerTask;

public class InteraccionTimeoutTimer extends Timer {

    //tres minutos sin interacción y luego un minuto en la pantalla de advertencia

    private final App app;

    public InteraccionTimeoutTimer(App app){
        super(true);
        this.app = app;
    }

    public void empezarCuentaAtras(){
        this.schedule(new IrAPantallaTimeOut(), 1000*60*3); //3 minutos -> pantalla de advertencia
        //System.out.println("Iniciado");
    }

    private void iniciarSegundoTemporizador(){
        this.schedule(new CancelarPedidoTimeout(), 1000*60); //1 minuto -> pantalla principal con pedido borrado
        //System.out.println("Iniciado el segundo");
    }

    public void borrarCuentaAtras(){
        this.schedule(new CancelarPedidoTimeout(), 1000*6000);
        //System.out.println("borrado");
    }



    private class IrAPantallaTimeOut extends TimerTask{
        @Override
        public void run() {
            app.nuevaPantalla(Pantallas.PANTALLA_TIMEOUT_INTERACCION);
          //  System.out.println("Suena el primero");
            iniciarSegundoTemporizador();
        }
    }

    private class CancelarPedidoTimeout extends TimerTask{
        @Override
        public void run() {
            app.volverPantallaPrincipal();
            //System.out.println("Suena el segundo");
            borrarCuentaAtras();
        }
    }


}
