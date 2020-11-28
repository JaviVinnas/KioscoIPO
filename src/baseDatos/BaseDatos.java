package baseDatos;

import aplicacion.ErrorMenu;
import aplicacion.ItemCarta;
import aplicacion.Pagable;
import aplicacion.Pedido;

import java.util.List;

public interface BaseDatos {
    //obtiene la hora actual del sistema en formato 24horas
    String getHoraActual();
    //obtiene el teléfono
    String getTelefono();
    //obtiene un item de la carta a partir de su nombre
    ItemCarta getItemCartabyNombre(String nombre);
    //añadir un item de la carta al menú provisional
    void addItemCartaAlMenu(ItemCarta item) throws ErrorMenu;
    //quita un item de la carta del menú provisional
    void quitarItemCartaAlMenu(ItemCarta item);
    //añade un menú o un elemento suelto al pedido
    void addPagableAlPedido(Pagable pagable);
    //quita uno o todos los elementos pagables del mismo tipo al pedido
    void quitarPagableAlPedido(Pagable pagable, boolean todos);
    //nos dice el precio del pedido
    float getPrecioPedido();
    //pone el menú preeliminar en blanco
    void reiniciarMenuPreeliminar();
    //pone el pedido en blanco
    void reiniciarPedido();
    //obtenemos el pedido
    Pedido getPedido();
}
