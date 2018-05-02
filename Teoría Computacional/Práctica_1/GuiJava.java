import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
public class GuiJava implements ActionListener{//implementando el listener de eventos
 
    JPanel jp1, jp2, jp3;
    JButton jb1, jb2, jb3, jbP1, jbP2, jbP3;   
 
    public GuiJava(){
 
        JFrame jfM = new JFrame("JPanel En Java");  
        jfM.setLayout(null);
 
 
 //       gridJP(); bordJP(); flowJP(); //invocamos los metodos que contienen los paneles 
 
        jbP1 = new JButton("Panel 1"); jbP2 = new JButton("Panel 2"); jbP3 = new JButton("Panel 3");
 
        jp1.setBounds(10, 10, 200, 200); jp2.setBounds(240, 10, 400, 250); jp3.setBounds(10, 270, 400, 150);
 
        jbP1.setBounds(10, 430, 90, 20); jbP2.setBounds(110, 430, 90, 20); jbP3.setBounds(210, 430, 90, 20);
 
        jfM.add(jp1); jfM.add(jp2); jfM.add(jp3); 
        jfM.add(jbP1); jfM.add(jbP2); jfM.add(jbP3);
 
        jbP1.addActionListener(this); jbP2.addActionListener(this); jbP3.addActionListener(this);
 
        jfM.setLocation(100, 50);
        jfM.setResizable(false);
        jfM.setVisible(true);
        jfM.setSize(800, 600);
        jfM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    public void gridJP(){
 
        jp1 = new JPanel(new GridLayout(3, 1, 5, 7));//filas, columnas, espacio entre filas, espacio entre columnas
 
        jb1= new JButton("B1"); jb2= new JButton("B2"); jb3= new JButton("B3");//creamos los objetos para el panel
 
        jp1.add(jb1); jp1.add(jb2); jp1.add(jb3);//añadimos los objetos al jpanel
 
        jb1.addActionListener(this); jb2.addActionListener(this); jb3.addActionListener(this);
 
        jp1.setVisible(true);
    }
 
    public void bordJP(){
        jp2 = new JPanel(new BorderLayout(2, 3));//espacio entre las regiones, horizontal y vertical
 
        jb1= new JButton("B1"); jb2= new JButton("B2"); jb3= new JButton("B3");//añadiendo objetos al jpanel
 
        jp2.add(jb1, BorderLayout.NORTH);//boton al panel norte
        jp2.add(jb2, BorderLayout.WEST); //boton a la region oeste
        jp2.add(jb3, BorderLayout.CENTER); //boton a la region centro    
 
        jb1.addActionListener(this); jb2.addActionListener(this); jb3.addActionListener(this);
 
        jp2.setVisible(true);
    }
 
    public void flowJP(){
        jp3 = new JPanel(new FlowLayout());
 
        jb1= new JButton("B1"); jb2= new JButton("B2"); jb3= new JButton("B3");//añadiendo objetos al jpanel
 
        jp3.add(jb1); jp3.add(jb2); jp3.add(jb3);//añadimos los objetos al jpanel
 
        jb1.addActionListener(this); jb2.addActionListener(this); jb3.addActionListener(this);
 
        jp3.setVisible(true);
    }
 
    public static void main(String[] args) {        
        GuiJava gj = new GuiJava();//uso de constructor para la ventana
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {//sobreescribimos el metodo del listener
 
        if(e.getSource() == jbP1){
            if(jp1.isVisible()){
                jp1.setVisible(false);
            }else
                jp1.setVisible(true);
        }else if(e.getSource() == jbP2){
            if(jp2.isVisible()){
                jp2.setVisible(false);
            }else
                jp2.setVisible(true);
 
        }else if(e.getSource() == jbP3){
            if(jp3.isVisible()){
                jp3.setVisible(false);
            }else
                jp3.setVisible(true);
 
        }else{
            JOptionPane.showMessageDialog(null, e.getActionCommand());
        }
    }
}