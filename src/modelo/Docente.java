/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
/**
 *
 * @author hg259
 */
public class Docente extends Persona{
    private int id;
    private String cod_docente, salario, f_ingreso, f_igso_regis;
    Conexion cn;
    
    public Docente(){}
    public Docente(int id, String cod_docente, String salario, String f_ingreso, String f_igso_regis, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String nit) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento, nit);
        this.id = id;
        this.cod_docente = cod_docente;
        this.salario = salario;
        this.f_ingreso = f_ingreso;
        this.f_igso_regis = f_igso_regis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod_docente() {
        return cod_docente;
    }

    public void setCod_docente(String cod_docente) {
        this.cod_docente = cod_docente;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(String f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public String getF_igso_regis() {
        return f_igso_regis;
    }

    public void setF_igso_regis(String f_igso_regis) {
        this.f_igso_regis = f_igso_regis;
    }


    public DefaultTableModel leer (){
    DefaultTableModel tabla = new DefaultTableModel();
    try{
        cn = new Conexion();
        cn.abrir_conexion();
        String query;
        query = "Select id_docente as id,nit,codigo_docente,nombres,apellidos,direccion,telefono,fecha_nacimiento,fecha_ingreso,fecha_ingreso_registro,salario from bd_colegio.docente;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado[] = {"id","Nit","Codigo","Nombres","Apellidos","Direccion","Telefono","Nacimiento","Fecha Ingreso","Fecha Ingreso Registro","Salario"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[] = new String [11];
        
        while(consulta.next()){
        datos[0] = consulta.getString("id");
        datos[1] = consulta.getString("nit");
        datos[2] = consulta.getString("codigo_docente");
        datos[3] = consulta.getString("nombres");
        datos[4] = consulta.getString("apellidos");
        datos[5] = consulta.getString("direccion");
        datos[6] = consulta.getString("telefono");
        datos[7] = consulta.getString("fecha_nacimiento");
        datos[8] = consulta.getString("fecha_ingreso");
        datos[9] = consulta.getString("fecha_ingreso_registro");
        datos[10] = consulta.getString("salario");
        tabla.addRow(datos);
        }
        cn.cerrar_conexion();
        
    }catch(SQLException ex){
        cn.cerrar_conexion();
        System.out.println("Error: " + ex.getMessage());
    }
    return tabla;
    }
}
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            String query ="INSERT INTO docente (nit, codigo, nombres,apellidos,direccion,telefono,fecha_nacimiento, fecha_ingreso, fecha_ingreso_registro, salario) VALUES (?,?,?,?,?,?,?,?,?,?);";
        cn = new Conexion();
        cn.abrir_conexion();
        
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getNit());
        parametro.setString(2, getCodigo());
        parametro.setString(3, getNombres());
        parametro.setString(4, getApellidos());
        parametro.setString(5, getDireccion());
        parametro.setString(6, getTelefono());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setString(8, getFecha_ingreso());
        parametro.setString(9, getFecha_ingreso_registro());
        parametro.setString(10,getSalario());
        
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Ingresado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex){
            System.out.print("Error..."+ ex.getMessage());
        }
    
    }
@Override
    public void actualizar(){
    try{
         PreparedStatement parametro;
         String query ="update clientes set nit = ?, codigo = ?, nombres = ?,apellidos = ?,direccion = ?,telefono = ?,fecha_ingreso = ?, nombres = ?, fecha_ingreso_registro = ?, salario = ?" +
         "where id_docente = ?";
        cn = new Conexion();
        cn.abrir_conexion();
        
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getNit());
        parametro.setString(2, getCodigo());
        parametro.setString(3, getNombres());
        parametro.setString(4, getApellidos());
        parametro.setString(5, getDireccion());
        parametro.setString(6, getTelefono());
        parametro.setString(7, getFecha_nacimiento());
        parametro.setString(8, getFecha_ingreso());
        parametro.setString(9, getFecha_ingreso_registro());
        parametro.setString(10,getSalario());
        
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Actualizado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex){
            System.out.print("Error..."+ ex.getMessage());
        }
    
    }
    
    public void eliminar(){
    try{
            PreparedStatement parametro;
            String query = "delete from bd_colegio.docente where id_docente = ?";
        cn = new Conexion();
        cn.abrir_conexion();
        
        parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, getId());
        
        int executar = parametro.executeUpdate();
        cn.cerrar_conexion();
        JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Eliminado","Agregar",JOptionPane.INFORMATION_MESSAGE);
        
        }catch(HeadlessException | SQLException ex){
            System.out.print("Error..."+ ex.getMessage());
        }
    
    }
}

  
