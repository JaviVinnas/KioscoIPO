package aplicacion;

import java.time.LocalDateTime;

public class Hora {
    private Integer hora;
    private Integer minuto;

    public Hora() {
        hora = null;
        minuto = null;
    }

    public Hora(Integer hora, Integer minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public Hora horaActual() {
        this.hora = LocalDateTime.now().getHour();
        this.minuto = LocalDateTime.now().getMinute();
        return this;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void clear() {
        hora = null;
        minuto = null;
    }

    public String getMinutoFormatted() {
        String minutoString = String.valueOf(minuto);
        if (minutoString.length() == 1) minutoString = '0' + minutoString;
        return minutoString;
    }

    public String getHoraFormatted() {
        String horaString = String.valueOf(hora);
        if (horaString.length() == 1) horaString = '0' + horaString;
        return horaString;
    }

    public String porQueNoEsValido() {

        String motivo = null;
        if( hora == null ){
            return "Falta por configurar la hora";
        }
        if( minuto == null ){
            return "Faltan por configurar los minutos";
        }
        ///horas válidas: 12 -> 4 y de 7 -> 10
        if(!(hora < 16 && hora >= 12) && !(hora >= 19 && hora < 22)){
            return "La hora de recogida debe estar en el intervalo 12-16 o 19-22";
        }
        //tiene que haber un tiempo mínimo para hacer el pedido
        if (hora < new Hora().horaActual().hora+1 || (hora.equals(new Hora().horaActual().hora+1) && minuto < new Hora().horaActual().minuto)){
            Hora horaMinima = new Hora().horaActual();
            horaMinima.setHora(horaMinima.hora + 1);
            return "El pedido tiene que tardar una hora en cocinarse. (La hora mínima de recogida será " + horaMinima.toString() + ")";
        }
        //pide para una hora para una hora anterior a la actual
        if (hora < new Hora().horaActual().hora || (hora.equals(new Hora().horaActual().hora) && minuto < new Hora().horaActual().minuto)){
            return "No se puede pedir para una hora anterior a la actual (" + this.toString() + " < " + new Hora().horaActual().toString() + ")";
        }
        return null;
    }

    @Override
    public String toString() {
        return getHoraFormatted() + ":" + getMinutoFormatted();
    }
}
