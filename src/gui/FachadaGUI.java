package gui;

import aplicacion.App;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class FachadaGUI implements GUI{

    private final App fa;
    private final JFrame ventana;
    private final Stack<Pantalla> historialPantallas;

    public void nuevaPantalla( Pantalla pantalla) {
        this.historialPantallas.push(pantalla);
        this.ventana.setContentPane(pantalla.getMainPanel());
        this.ventana.pack();
        this.ventana.setVisible(true);
    }

    public void volverPantalla() {
        Pantalla pantallaActual = this.historialPantallas.pop();
        //si esta era la última pantalla
        if(this.historialPantallas.isEmpty()){
            //volvemos a meterla (no pasaría nada)
            this.historialPantallas.push(pantallaActual);
        }else{
            //si no lo era asignamos a la ventana la siguiente pantalla en la pila
            this.ventana.setContentPane(this.historialPantallas.peek().getMainPanel());
        }
    }

    public FachadaGUI(App fa) {
        this.fa = fa;
        this.historialPantallas = new Stack<>();
        this.ventana = new JFrame("Kiosco");
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //para que la ventana aparezca en el centro
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.ventana.setLocation(dim.width/2-this.ventana.getSize().width/2, dim.height/2-this.ventana.getSize().height/2);
        this.ventana.setResizable(false);
    }

    public void iniciarGui() {
        this.fa.nuevaPantalla(new PantallaBienvenida(this.fa));
    }

    public void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(ventana, mensaje);
    }

    @Override
    public void initComponentesPantalla(JLabel telefono, JLabel hora) {
        telefono.setText("Teléfono: " + fa.getTelefono());
        hora.setText(fa.getHoraActual());
        telefono.setFont(telefono.getFont().deriveFont(15.0f));
        hora.setFont(hora.getFont().deriveFont(15.0f));
    }

    @Override
    public JButton generarImagenJButton(String pathImagen, int alto, int ancho) {
        JButton boton = null;
        try {
            BufferedImage img = ImageIO.read(new File(pathImagen));
            Image dimg = img.getScaledInstance(ancho, alto,
                    Image.SCALE_SMOOTH);
            boton =  new JButton(new ImageIcon(dimg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boton;
    }

    @Override
    public void cambiarTamText(JButton boton, float tamano) {
        boton.setFont(boton.getFont().deriveFont(tamano));
    }

    @Override
    public void cambiarTamText(JLabel texto, float tamano) {
        texto.setFont(texto.getFont().deriveFont(tamano));
    }

    @Override
    public void initComponentesPantalla(JLabel paso, JLabel telefono, JLabel hora, int numPaso) {
        initComponentesPantalla(telefono,hora);
        String descripcion;
        switch (numPaso){
            case 1:
                descripcion = "configura tu pedido";
                break;
            case 2:
                descripcion = "hora de recogida del pedido";
                break;
            case 3:
                descripcion = "pago del pedido";
                break;
            case 4:
                descripcion = "resumen del pedido";
                break;
            default:
                descripcion = "ERROR";
        }
        paso.setText("Paso " + numPaso + " de 4: " + descripcion);
        paso.setFont(paso.getFont().deriveFont(15.0f));
    }




}
