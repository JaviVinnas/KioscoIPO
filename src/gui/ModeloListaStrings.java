package gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloListaStrings extends AbstractListModel<String> {

    private final List<String> elementos;

    public ModeloListaStrings(){
        this.elementos = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.elementos.size();
    }

    @Override
    public String getElementAt(int index) {
        return elementos.get(index);
    }


    public void addElemento(String elemento){
        this.elementos.add(elemento);
    }

}
