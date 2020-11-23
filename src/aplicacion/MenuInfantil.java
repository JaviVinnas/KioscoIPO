package aplicacion;

import java.util.ArrayList;

public final class MenuInfantil extends Menu {
    public MenuInfantil() {
        super();
        try {
            //primero
            ArrayList<Alergeno> alergenos = new ArrayList<>();
            alergenos.add(Alergeno.HUEVOS);
            alergenos.add(Alergeno.LACTOSA);
            PrimerPlato primero = new PrimerPlato(3.0f, alergenos, "Ensaladilla rusa", "Plato típico ruso con mayonesa, huevos y verduras");
            addItem(primero);
            //segundo
            alergenos = new ArrayList<>();
            SegundoPlato segundo = new SegundoPlato(4.0f, alergenos, "Milanesas con patatas fritas", "Filete asado al horno de piedra acompañado de patatas fritas en aceite de oliva");
            addItem(segundo);
            //bebidas
            Bebida bebida = new Bebida(.25f, new ArrayList<>(), "Agua Mineral", "Agua mineral de la Mondariz");
            addItem(bebida);
            //postre
            alergenos = new ArrayList<>();
            alergenos.add(Alergeno.FRUTOS_SECOS);
            Postre postre = new Postre(2.0f, alergenos, "Brownie de chocolate", "Delicioso postre casero de chocolate y bizcocho");
            addItem(postre);
        } catch (ErrorMenu error) {
            error.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Menu infantil: " + super.toString();
    }
}
