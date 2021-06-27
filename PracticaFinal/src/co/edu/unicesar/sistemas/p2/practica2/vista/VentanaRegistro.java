
package co.edu.unicesar.sistemas.p2.practica2.vista;

import co.edu.unicesar.sistemas.p2.practica2.dominio.*;
import co.edu.unicesar.sistemas.p2.practica2.excepciones.ExcepcionAccesoDatos;
import co.edu.unicesar.sistemas.p2.practica2.negocio.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


public class VentanaRegistro extends JDialog {
    
    private JLabel lPagina,lEdicion,lIsbn,lTitulo,lAutor,lAnio,lCosto,lTipoPublicacion,lDuracion,lPeso,lFormato;
    private JTextField tPagina,tEdicion,tIsbn,tAutor,tCosto,tTitulo,tDuracion,tPeso;
    private JComboBox cAnio,cFormato,cTipo;
    private ButtonGroup grupoRadio;
    private JButton bGuardar,bCancelar,bNuevo,bBuscar,bEliminar;
    
    private JPanel panelDatos,panelBotones;
    private Container contenedor;
    
   private RegistroPublicacion negocio;
    public VentanaRegistro(JFrame frame, boolean bln) {
        super(frame, bln);
        this.setTitle("Registro de Publicaciones");
        this.negocio = new RegistroPublicacion();
       this.iniciarComponentes();
       //this.setSize(600, 400);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
      
    } 
    
     public void iniciarComponentes(){
        this.contenedor=this.getContentPane();
        this.contenedor.setLayout(new BorderLayout());
        this.panelDatos();
        this.panelBotones();
    }
    
     public void panelDatos(){
       this.panelDatos=new JPanel();
       this.panelDatos.setLayout(new GridLayout(12,2,5,5) );  
      //Creando etiquetas 
       this.lTipoPublicacion=new JLabel("   Tipo Publicacion: ");
       this.lTitulo=new JLabel("   Titulo: ");
       this.lAutor=new JLabel("   Autor: ");
       this.lEdicion=new JLabel("   Edicion: ");
       this.lAnio=new JLabel("   Año: ");
       this.lPagina=new JLabel("   Nro Pagina: ");
       this.lIsbn=new JLabel("   Isbn: ");
       this.lCosto=new JLabel("   Costo: ");
       this.lDuracion=new JLabel("   Duracion: ");
       this.lPeso=new JLabel("   Peso: ");
       this.lFormato=new JLabel("   Formato: ");
       
       //creando los cuadros de textos
     
       this.tTitulo=new JTextField(null);
       this.tTitulo.setEnabled(false);/////
       this.tAutor=new JTextField(null);
       this.tAutor.setEnabled(false);/////
       this.tEdicion=new JTextField(null);
       this.tEdicion.setEnabled(false);/////
       this.tPagina=new JTextField(null);
       this.tPagina.setEnabled(false);/////
       this.tIsbn=new JTextField(null);
       this.tIsbn.setEnabled(false);/////
       this.tCosto=new JTextField(null);
       this.tCosto.setEnabled(false);/////
       this.tPeso=new JTextField(null);
       this.tPeso.setEnabled(false);/////
       this.tDuracion=new JTextField(null);
       this.tDuracion.setEnabled(false);/////
       
       //Creando barra 
       this.cAnio=new JComboBox();
       this.cAnio.setEnabled(false);/////
       this.cAnio.addItem("2021");
       this.cAnio.addItem("2020");
       this.cAnio.addItem("2019");   
       this.cAnio.addItem("2018"); 
       this.cAnio.addItem("Otro");
       
       this.cFormato=new JComboBox();
       this.cFormato.setEnabled(false);/////
       this.cFormato.addItem("MP3");
       this.cFormato.addItem("MP4");
       
        this.cTipo=new JComboBox();
        this.cTipo.setEnabled(false);/////
         this.cTipo.addItem("AudioLibro");
        this.cTipo.addItem("Libro");
        this.cTipo.addActionListener(new EventoClickComboTipo());
       
   
       
       this.bGuardar=new JButton("Guardar");
       this.bGuardar.addActionListener(this::guardar);
       this.bGuardar.setEnabled(false);///
       this.bCancelar=new JButton("Cancelar");
       this.bCancelar.setEnabled(false);///
       
       this.panelDatos.add(this.lTipoPublicacion);
       this.panelDatos.add(this.cTipo);
       
       this.panelDatos.add(this.lTitulo);
       this.panelDatos.add(this.tTitulo);
         
       this.panelDatos.add(this.lAutor);
       this.panelDatos.add(this.tAutor);
        
        this.panelDatos.add(this.lAnio);
        this.panelDatos.add(this.cAnio);
          
        this.panelDatos.add(this.lPagina);
        this.panelDatos.add(this.tPagina);
            
        this.panelDatos.add(this.lIsbn);
        this.panelDatos.add(this.tIsbn);
              
        this.panelDatos.add(this.lCosto);
        this.panelDatos.add(this.tCosto);
        
        this.panelDatos.add(this.lEdicion);
        this.panelDatos.add(this.tEdicion);
        
        this.panelDatos.add(this.lPagina);
        this.panelDatos.add(this.tPagina);
        
        this.panelDatos.add(this.lDuracion);
        this.panelDatos.add(this.tDuracion);
        
        this.panelDatos.add(this.lPeso);
        this.panelDatos.add(this.tPeso);
        
        this.panelDatos.add(this.lFormato);
        this.panelDatos.add(this.cFormato);
       
       this.panelDatos.add(this.bGuardar);
       this.panelDatos.add(this.bCancelar);
       
       this.contenedor.add(this.panelDatos,BorderLayout.CENTER);
       
     }
    
     public void panelBotones(){
        this.panelBotones=new JPanel();
        
        this.bNuevo=new JButton("Nuevo");
        this.bNuevo.addActionListener(new EventoClikBotonNuevo());
        this.bBuscar=new JButton("Buscar");
        this.bBuscar.addActionListener(new EventoClikBotonBuscar());
        this.bBuscar.setEnabled(false);////
        this.bEliminar=new JButton("Eliminar");
        this.bEliminar.addActionListener(new EventoClickBotonEliminar());
        this.bEliminar.setEnabled(false);///
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3,1,5,5));
        
        panel.add(this.bNuevo);
        panel.add(this.bBuscar);
        panel.add(this.bEliminar);
        
        this.panelBotones.add(panel);
        
        this.contenedor.add(this.panelBotones,BorderLayout.EAST);
        
    }
    
    
   //escuchador de eventos para el click
    class EventoClikBotonNuevo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
          activarComponentes();  
        }
    
}  
    
    class EventoClikBotonBuscar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
           buscarPublicacion();
        }
        
    }
    
    class EventoClickBotonEliminar implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
          eliminarPublicacion();
        }
        
    }
    
     public void eliminarPublicacion() {

        String isb= this.tIsbn.getText();
        int confirmacion = JOptionPane.showConfirmDialog(this, "Desea eliminar el elemento", "Confirmacion", JOptionPane.OK_CANCEL_OPTION);
        if (confirmacion == 0) {
            try {
               this.negocio.eliminarPublicacion(isb);
               this.mostrarMsg("Exito", "Elemento eliminado con exito", JOptionPane.INFORMATION_MESSAGE);
               this.limpiarComponentes();
            } catch (ExcepcionAccesoDatos | NullPointerException ex) {
                this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    
    public void buscarPublicacion(){
     try{
         String isbn=this.tIsbn.getText();
         Publicacion pub=this.negocio.buscarPublicacion(isbn);
         
         if(pub==null){
            throw new NullPointerException("El isbn no se encuentra registrado");  
         }
         this.tTitulo.setText(pub.getTitulo());
         this.tAutor.setText(pub.getAutor());
         this.tCosto.setText(pub.getCosto()+"");
         this.cAnio.setSelectedItem(pub.getAnio()+"");
         this.tIsbn.setText(pub.getIsbn());
         if(pub instanceof AudioLibro ){
             this.cTipo.setSelectedItem("AudioLibro");
             AudioLibro au=(AudioLibro)pub;
             this.tDuracion.setText(au.getDuracion()+"");
             this.tPeso.setText(au.getPeso()+"");
             this.cFormato.setSelectedItem(au.getFormato());
         }else {
             if( pub instanceof Libro){
               this.cTipo.setSelectedItem("Libro"); 
               Libro lib=(Libro)pub;
               this.tPagina.setText(lib.getnPaginas()+"");
               this.tEdicion.setText(lib.getEdicion()+"");
             }
             
         }
          
         
     } catch(ExcepcionAccesoDatos eo) {
         this.mostrarMsg("Error", eo.getMessage(),JOptionPane.ERROR_MESSAGE);
     }
     
    }
    
    
    //clase para habilitar los componentes 
    public void activarComponentes(){
        //campos habilitarlos
       
       this.cTipo.setEnabled(true);
       this.tTitulo.setEnabled(true);
       this.tAutor.setEnabled(true);
       this.tEdicion.setEnabled(true);
       this.tPagina.setEnabled(true);
       this.tIsbn.setEnabled(true);
       this.tCosto.setEnabled(true);
       this.cAnio.setEnabled(true); 
       this.tDuracion.setEnabled(true); 
       this.tPeso.setEnabled(true); 
       this.cFormato.setEnabled(true); 
       //botones
       this.bGuardar.setEnabled(true);
       this.bCancelar.setEnabled(true);
       this.bBuscar.setEnabled(true);
       this.bEliminar.setEnabled(true); 
        
       this.cTipo.grabFocus();
    }
    
     public void mostrarMsg(String titulo, String msg, int tipo) {
        JOptionPane.showMessageDialog(this, msg, titulo, tipo);
    }
    
    public void guardar(ActionEvent ae) {

        try {
              Publicacion pub = this.leerDatos();
            this.negocio.adicionarPublicacion(pub);
            this.mostrarMsg("Exito", "Registro almacenado con exito", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarComponentes();
        } catch (ExcepcionAccesoDatos | NullPointerException ex) {
            //ex.printStackTrace();
            this.mostrarMsg("Error", ex.getMessage(), JOptionPane.ERROR_MESSAGE);

        }
        
    }
      public Publicacion leerDatos(){
       
        String titulo=this.tTitulo.getText();
        String autor=this.tAutor.getText();
        int edicion=Integer.parseInt(this.tEdicion.getText());
        int anio=Integer.parseInt(this.cAnio.getSelectedItem().toString());
        int pagina = Integer.parseInt(this.tPagina.getText());
        String isbn=this.tIsbn.getText();
        int costo= Integer.parseInt(this.tCosto.getText());
        double dura=Double.parseDouble(this.tDuracion.getText());
        double pes=Double.parseDouble(this.tPeso.getText());
        String form=this.cFormato.getSelectedItem().toString();
         String tipo= this.cTipo.getSelectedItem().toString();
         
        if(tipo.equalsIgnoreCase("AudioLibro")){
            
            return new AudioLibro(dura,pes,form,isbn,titulo,autor,anio,costo);
        }else{
            
            return new Libro(pagina,edicion,isbn,titulo,autor,anio,costo);
        }
       
     }
   
     public void limpiarComponentes(){
         
         this.tTitulo.setText(null);
         this.tAutor.setText(null);
         this.tEdicion.setText(null);
         this.cAnio.setSelectedIndex(0);
         this.tPagina.setText(null);
         this.tIsbn.setText(null);
         this.tCosto.setText(null);
         this.cTipo.grabFocus();
         
         
     }   
     
      class EventoClickComboTipo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            ocultarComponentes();
        }
    }
     
      public void ocultarComponentes(){
         String tipo = this.cTipo.getSelectedItem().toString();

        if(tipo.equalsIgnoreCase("AudioLibro")){
           this.lDuracion.setVisible(true);
           this.tDuracion.setVisible(true);
           this.lPeso.setVisible(true);
           this.tPeso.setVisible(true); 
           this.lFormato.setVisible(true);  
           this.cFormato.setVisible(true); 
           this.lEdicion.setVisible(false); 
           this.tEdicion.setVisible(false); 
           this.lPagina.setVisible(false); 
           this.tPagina.setVisible(false); 
           
        }else{
           this.lEdicion.setVisible(true); 
           this.tEdicion.setVisible(true); 
           this.lPagina.setVisible(true); 
           this.tPagina.setVisible(true);  
           this.lDuracion.setVisible(false);
           this.tDuracion.setVisible(false);
           this.lPeso.setVisible(false);
           this.tPeso.setVisible(false); 
           this.lFormato.setVisible(false);  
           this.cFormato.setVisible(false);
        }
      }
        
        
        

    }
 
