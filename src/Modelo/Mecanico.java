package Modelo;

import Vista.frm_Mecanico;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Mecanico {
    private String Nombre;
    private int Edad;
    private int Peso;
    private String Correo;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public int getPeso() {
        return Peso;
    }

    public void setPeso(int Peso) {
        this.Peso = Peso;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement addMecanico = conexion.prepareStatement("INSERT INTO tbMecanico(UUID_Mecanico, Nombre, Edad, Peso, Correo) VALUES (?, ?, ?, ?, ?)");
            addMecanico.setString(1, UUID.randomUUID().toString());
            addMecanico.setString(2, getNombre());
            addMecanico.setInt(3, getEdad());
            addMecanico.setInt(4, getPeso());
            addMecanico.setString(5, getCorreo());
            addMecanico.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();  
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try { 
                PreparedStatement updateMecanico = conexion.prepareStatement("UPDATE tbMecanico set Nombre = ?, Edad = ?, Peso = ?, Correo = ? where UUID_Mecanico = ?");
                updateMecanico.setString(1, getNombre());
                updateMecanico.setInt(2, getEdad());
                updateMecanico.setInt(3, getPeso());
                updateMecanico.setString(4, miUUId);
                updateMecanico.setString(5, getCorreo());
                updateMecanico.executeUpdate();
 
            } catch (Exception e) {
                System.out.println("Este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            PreparedStatement deleteMecanico = conexion.prepareStatement("DELETE FROM tbMecanico WHERE UUID_Mecanico = ?");
            deleteMecanico.setString(1, miId);
            deleteMecanico.executeUpdate();
        } catch (Exception e) {
            System.out.println("Este es el error metodo de eliminar" + e);
        }
    }
    
    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modeloDeDatos = new DefaultTableModel();
        modeloDeDatos.setColumnIdentifiers(new Object[]{"UUID_codigo", "Nombre", "Edad", "Peso", "Correo"});
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tbMecanico");
            while (rs.next()) {
                modeloDeDatos.addRow(new Object[]{rs.getString("UUID_Mecanico"),  
                    rs.getString("Nombre"),
                    rs.getInt("Edad"),
                    rs.getInt("Peso"),
                    rs.getString("Correo")});
            }
            tabla.setModel(modeloDeDatos);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void cargarDatosTabla(frm_Mecanico vista) {
        int filaSeleccionada = vista.jb_Mecanico.getSelectedRow();
        if (filaSeleccionada != -1) {
            String UUIDDeTB = vista.jb_Mecanico.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jb_Mecanico.getValueAt(filaSeleccionada, 1).toString();
            String EdadTB = vista.jb_Mecanico.getValueAt(filaSeleccionada, 2).toString();
            String PesoTB = vista.jb_Mecanico.getValueAt(filaSeleccionada, 3).toString();
            String CorreoTB = vista.jb_Mecanico.getValueAt(filaSeleccionada, 3).toString();
 
            vista.txt_Nombre.setText(NombreDeTB);
            vista.txt_Edad.setText(EdadTB);
            vista.txt_Peso.setText(PesoTB);
            vista.txt_Correo.setText(CorreoTB);
        }
    }   
}
