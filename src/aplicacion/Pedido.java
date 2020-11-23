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
