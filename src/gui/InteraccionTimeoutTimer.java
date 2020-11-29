package gui;

import aplicacion.App;

import java.util.Timer;
import java.util.TimerTask;

public class InteraccionTimeoutTimer {

    //tres minutos sin interacción y luego un minuto en la pantalla de advertencia

    private final App app;
    private Timer temporizador;

    public InteraccionTimeoutTimer(App app){
        this.app = app;
        temporizador = new Timer();
    }

    public void empezarCuentaAtras(){
        temporizador.cancel();
        temporizador = new Timer();
        temporizador.schedule(new IrAPantallaTimeOut(), 1000*60*3); //3 minutos -> pantalla de advertencia
    }

    private void iniciarSegundoTemporizador(){
        temporizador.cancel();
        temporizador = new Timer();
        temporizador.schedule(new CancelarPedidoTimeout(), 1000*30); //30 secs -> pantalla principal con pedido borrado
    }

    public void borrarCuentaAtras(){
        temporizador.cancel();
        temporizador = new Timer();
    }



    private class IrAPantallaTimeOut extends TimerTask{
        @Override
        public void run() {
            app.nuevaPantalla(Pantallas.PANTALLA_TIMEOUT_INTERACCION);
            iniciarSegundoTemporizador();
        }
    }

    private class CancelarPedidoTimeout extends TimerTask{
        @Override
        public void run() {
            app.volverPantallaPrincipal();
            borrarCuentaAtras();
        }
    }


}
