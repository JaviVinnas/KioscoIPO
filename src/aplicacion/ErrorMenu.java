package aplicacion;

public final class ErrorMenu extends Exception{
    public ErrorMenu(String mensajeError){
        super(mensajeError);
    }
}
