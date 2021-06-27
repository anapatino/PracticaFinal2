
package co.edu.unicesar.sistemas.p2.practica2.vista;

import co.edu.unicesar.sistemas.p2.practica2.dominio.Publicacion;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import co.edu.unicesar.sistemas.p2.practica2.negocio.RegistroPublicacion;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class VentanaConsulta extends JDialog {
    
private JPanel paneFiltro;
    private JScrollPane panelTabla;
    private Container contenedor;
    private JLabel lFiltro;
    private JTextField tFiltro;
    
    private JTable tabla;
    private DefaultTableModel  modelo;
    private String titulo[]={"  Titulo","Autor","Año","Isbn","Costo"};
    private RegistroPublicacion negocio;
    public VentanaConsulta(JFrame frame, boolean bln) {
        super(frame, bln);
        this.setTitle("Consulta de Publicaciones");
        this.negocio=new RegistroPublicacion();
        this.iniciarComponentes() ;
        this.llenarTabla();
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
     public void iniciarComponentes()   {
      this.contenedor=this.getContentPane();
      this.contenedor.setLayout(new BorderLayout());
      this.iniciarPanelFiltro();
      this.iniciarPanelTabla();
         
     }
    
      public void iniciarPanelFiltro() {
       this.paneFiltro=new JPanel(new FlowLayout(FlowLayout.LEFT))  ;
       
       this.lFiltro=new JLabel("Texto  de Filtro: ");
       this.tFiltro=new JTextField(10);
       
       this.paneFiltro.add(this.lFiltro);
       this.paneFiltro.add(this.tFiltro);
       
       this.contenedor.add(this.paneFiltro,BorderLayout.NORTH);
       
       
     }
    
    
     public void iniciarPanelTabla(){
        this.panelTabla=new JScrollPane();
        this.tabla=new JTable();
        this.modelo=new DefaultTableModel(null,this.titulo);
        this.tabla.setModel(this.modelo);
        this.panelTabla.setViewportView(this.tabla);
        
        this.contenedor.add(this.panelTabla,BorderLayout.CENTER);
         
         
     }
    
     public void actualizarTabla(java.util.List<Publicacion> lista){
       
            this.modelo.setNumRows(0);
            for(Publicacion pub: lista){
                String fila[] = {pub.getTitulo(),pub.getAutor(),pub.getAnio()+"",pub.getIsbn(),pub.getCosto()+""};
                this.modelo.addRow(fila);
            }
       
    }
     
      public void mostrarMsg(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }
    
     public void llenarTabla(){
    try {
        java.util.List<Publicacion> lis=this.negocio.consultarPublicaciones();
        this.actualizarTabla(lis);
    } catch (ExcepcionAccesoDatos ex) {
         this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
    }
         
         
         
     }
    
    
    
}
