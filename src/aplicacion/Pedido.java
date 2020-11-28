package aplicacion;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    Map<Menu, Integer> menus;
    Map<ItemCarta, Integer> itemsCarta;

    public Pedido() {
        menus = new HashMap<>();
        itemsCarta = new HashMap<>();
    }

    public Map<Menu, Integer> getMenus() {
        return menus;
    }

    public Map<ItemCarta, Integer> getItemsCarta() {
        return itemsCarta;
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
    public void quitarItem(Pagable item, boolean todos){
        if (item instanceof Menu) {
            if(menus.get(item) == 1 || todos){
                menus.remove(item);
            } else {
                menus.put((Menu) item, menus.get(item) - 1);
            }
        }
        if (item instanceof ItemCarta) {
            if(itemsCarta.get(item) == 1 || todos){
                itemsCarta.remove(item);
            } else {
                itemsCarta.put((ItemCarta) item, itemsCarta.get(item) - 1);
            }
        }
    }

    //dice si el pedido tiene o no un item pagable en concreto
    public boolean contains(Pagable item){
        if (item instanceof Menu) {
            return menus.containsKey(item);
        }
        if (item instanceof ItemCarta) {
            return itemsCarta.containsKey(item);
        }
        return false;
    }

    public Pagable getItemPedidoByDescripcionCorta(String descripcionCorta){
        for(Map.Entry<Menu, Integer> menu : menus.entrySet()){
            if(descripcionCorta.equals(menu.getValue().toString() + " -> " + menu.getKey().getDescripcionCorta())){
                return menu.getKey();
            }
        }
        for(Map.Entry<ItemCarta, Integer> itemCarta : itemsCarta.entrySet()){
            if(descripcionCorta.equals(itemCarta.getValue().toString() + " -> " + itemCarta.getKey().getDescripcionCorta())){
                return itemCarta.getKey();
            }
        }
        return null;
    }

    public float getPrecio(){
        float precio = 0.0f;
        for(Map.Entry<Menu, Integer> menu : menus.entrySet()){
            precio+= menu.getKey().getPrecio() * Float.parseFloat(menu.getValue().toString());
        }
        for(Map.Entry<ItemCarta, Integer> itemCarta : itemsCarta.entrySet()){
            precio+= itemCarta.getKey().getPrecio() * Float.parseFloat(itemCarta.getValue().toString());
        }
        return precio;
    }



}
