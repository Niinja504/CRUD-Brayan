package Controlador;

import Modelo.Mecanico;
import Vista.frm_Mecanico;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CtrlMecanico implements MouseListener  {
    private Mecanico modelo;
    private frm_Mecanico vista;
    
    public CtrlMecanico(Mecanico Modelo, frm_Mecanico vista){
        this.modelo = Modelo;
        this.vista = vista;
        
        vista.btn_agregar.addMouseListener(this);
        Modelo.Mostrar(vista.jb_Mecanico);
        vista.btn_Eliminar.addMouseListener(this);
        vista.jb_Mecanico.addMouseListener(this);
        vista.btn_Editar.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
