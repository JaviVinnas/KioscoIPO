package aplicacion;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    Map<Menu, Integer> menus;
    Map<ItemCarta, Integer> itemsCarta;
    Hora horaRecogida;
    String idPedido;

    public Pedido() {
        menus = new HashMap<>();
        itemsCarta = new HashMap<>();
        idPedido = null;
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }

    public Map<ItemCarta, Integer> getItemsCarta() {
        return itemsCarta;
    }

    public void setHoraRecogida(Hora horaRecogida) {
        this.horaRecogida = horaRecogida;
    }

    //añade un item al pedido
    public void addItem(Pagable item) {
        if (item instanceof Menu) {
            menus.put((Menu) item, menus.getOrDefault(item, 0) + 1);
        } else if (item instanceof ItemCarta) {
            itemsCarta.put((ItemCarta) item, itemsCarta.getOrDefault(item, 0) + 1);
        }
    }

    //quita un item al pedido o todos si hiciera falta
    public void quitarItem(Pagable item, boolean todos) {
        if (item instanceof Menu) {
            if (menus.get(item) == 1 || todos) {
                menus.remove(item);
            } else {
                menus.put((Menu) item, menus.get(item) - 1);
            }
        }
        if (item instanceof ItemCarta) {
            if (itemsCarta.get(item) == 1 || todos) {
                itemsCarta.remove(item);
            } else {
                itemsCarta.put((ItemCarta) item, itemsCarta.get(item) - 1);
            }
        }
    }

    //dice si el pedido tiene o no un item pagable en concreto
    public boolean contains(Pagable item) {
        if (item instanceof Menu) {
            return menus.containsKey(item);
        }
        if (item instanceof ItemCarta) {
            return itemsCarta.containsKey(item);
        }
        return false;
    }

    public boolean isEmpty() {
        return menus.isEmpty() && itemsCarta.isEmpty();
    }

    public Pagable getItemPedidoByDescripcionCorta(String descripcionCorta) {
        for (Map.Entry<Menu, Integer> menu : menus.entrySet()) {
            if (descripcionCorta.equals(menu.getValue().toString() + " -> " + menu.getKey().getDescripcionCorta())) {
                return menu.getKey();
            }
        }
        for (Map.Entry<ItemCarta, Integer> itemCarta : itemsCarta.entrySet()) {
            if (descripcionCorta.equals(itemCarta.getValue().toString() + " -> " + itemCarta.getKey().getDescripcionCorta())) {
                return itemCarta.getKey();
            }
        }
        return null;
    }

    public float getPrecio() {
        float precio = 0.0f;
        for (Map.Entry<Menu, Integer> menu : menus.entrySet()) {
            precio += menu.getKey().getPrecio() * Float.parseFloat(menu.getValue().toString());
        }
        for (Map.Entry<ItemCarta, Integer> itemCarta : itemsCarta.entrySet()) {
            precio += itemCarta.getKey().getPrecio() * Float.parseFloat(itemCarta.getValue().toString());
        }
        return precio;
    }

    public void generarId() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        StringBuilder sb = new StringBuilder(8); //8 caracteres tendrá el id
        for (int i = 0; i < 8; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        idPedido = sb.toString();
    }

    public void borrarId(){
        idPedido = null;
    }

    @Override
    public String toString() {

        StringBuilder out = new StringBuilder("Informacion del pedido:\n");
        for (Map.Entry<Menu, Integer> menu : menus.entrySet()) {
            out.append("* ").append(menu.getValue().toString()).append(" -> ").append(menu.getKey().getDescripcionCorta()).append('\n');
        }
        for (Map.Entry<ItemCarta, Integer> itemCarta : itemsCarta.entrySet()) {
            out.append("* ").append(itemCarta.getValue().toString()).append(" -> ").append(itemCarta.getKey().getDescripcionCorta()).append('\n');
        }
        if (horaRecogida != null) {
            out.append("Hora de recogida: ").append(horaRecogida.toString()).append('\n');
        }
        if(idPedido != null){
            out.append("Identificador del pedido : ").append(idPedido).append('\n');
        }
        out.append("Precio total => ").append(getPrecio()).append('€');
        return out.toString();
    }
}
