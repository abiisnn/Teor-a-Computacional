/*	
	Autor(a): Abigail Nicolás Sayago
	Grupo: 2CM4
	Fecha: 22-Febrero-2018
	Práctica 1 - Operaciones con cadenas 
*/
import java.util.*;
import java.lang.reflect.Field;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager.*;

public class Cadena implements ActionListener{
	JFrame Ventana;
	JPanel Entrada, Salida, Botones;
	JTextField[] campos;
	JLabel[] etiquetas;
	String[] columnas = {"CADENA 1", "CADENA 2", "SELECCIONA", "POTENCIA"};
	JButton palindromo, longitud, concatena, potencia;
	JButton inverso, prefijo, sufijo, subcadena;
	JTextArea AreaSalida;
	JScrollPane scrollPane;
	String cadenasel;
	
	//Método constructor
	public Cadena()
	{
		int i;
		Ventana = new JFrame("Operaciones con cadenas");
		//Caracteristicas de la ventana
		Ventana.setLayout(null);
		Ventana.setSize(800,800);
		Ventana.setVisible(true);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// PANELES
		Entrada = new JPanel(new GridLayout(4,2));
		Botones = new JPanel(new GridLayout(4,2));
		Salida = new JPanel(new GridLayout(1,1));
		
		//Para el Panel de ENTRADA
		campos = new JTextField[4];
		etiquetas = new JLabel[2];
		//Agrega las etiquetas y campos a el panel ENTRADA
		for(i=0; i<2; i++)
		{
			etiquetas[i] = new JLabel(columnas[i]);
			Entrada.add(etiquetas[i]);
		}
		for(i=0; i<4; i++)
		{
			campos[i] = new JTextField();
			Entrada.add(campos[i]);	
		}
		campos[2].setText("ELIGE LA CADENA 1 o 2");
		campos[3].setText("ESCRIBE UNA POTENCIA");
		// setBounds(X,Y,ANCHO,ALTO)
		Entrada.setBounds(10,10,450,100);

		//Para el panel de SALIDA
		AreaSalida = new JTextArea("BIENVENIDO");
		scrollPane = new JScrollPane(AreaSalida);
		Salida.setPreferredSize(new Dimension(400, 100));
		Salida.add(scrollPane, BorderLayout.CENTER);
		Salida.setBounds(10,150,450,350);
		//la siguiente linea es para dar margen interior y color al jpanel 
		Salida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(10, 15, 5, 15)));
		
		//Para el panel de BOTONES
		palindromo = new JButton("Palindromo");
		longitud = new JButton("Longitud"); 
		concatena = new JButton("Concatenar");
		potencia = new JButton("Potencia");
		inverso = new JButton("Inverso");
		sufijo = new JButton("Sufijo");
		prefijo = new JButton("Prefijo");
		subcadena = new JButton("Subcadena");

		palindromo.addActionListener(this);
		longitud.addActionListener(this);
		concatena.addActionListener(this);
		potencia.addActionListener(this);
		inverso.addActionListener(this);
		sufijo.addActionListener(this);
		prefijo.addActionListener(this);
		subcadena.addActionListener(this);

		palindromo.setEnabled(true);
		longitud.setEnabled(true);
		concatena.setEnabled(true);
		potencia.setEnabled(true);
		inverso.setEnabled(true);
		sufijo.setEnabled(true);
		prefijo.setEnabled(true);
		subcadena.setEnabled(true);

		Botones.add(palindromo); Botones.add(longitud); 
		Botones.add(concatena); Botones.add(potencia); 
		Botones.add(inverso); Botones.add(prefijo); 
		Botones.add(sufijo); Botones.add(subcadena);  
		Botones.setBounds(10,540,450,100);

		//Agrega los paneles a la ventana
		Ventana.add(Entrada); Ventana.add(Salida); Ventana.add(Botones);
	
	}
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		AreaSalida.setText("");
		if(b == longitud)
			longi(Seleccionar());
		else if(b == concatena)
			Concatenar();
		else if(b == palindromo)
			Palindromo(Seleccionar());
		else if(b == potencia)
			Potencia(Seleccionar());
		else if(b == inverso)
			Inverso(Seleccionar());
		else if(b == sufijo)
			Sufijo(Seleccionar());
		else if(b == prefijo)
			Prefijo(Seleccionar());
		else if(b == subcadena)
			Subcadena(Seleccionar());
	}
	public static void main(String s[])
	{
		try
		{
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if("Nimbus".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch(Exception e)
		{
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}
		new Cadena();
	}
	public void longi(int sel)
	{
		String cad = campos[sel].getText(); //Obtenemos la cadena
		String mensaje = "LA LONGITUD DE LA CADENA ES: \n\n";
		if (cad.equalsIgnoreCase("E")) // Para la cadena vacía
			AreaSalida.setText(mensaje + "|"+ cad +"|" +" = "+ "0" +"\n"); //La longitud es cero
		else // Para cualquier otra cadena
			AreaSalida.setText(mensaje + "|"+ cad + "|" +" = "+ cad.length() +"\n");
	}
	public void Concatenar()
	{
		String cad1 = campos[0].getText(); //Obtenemos la primera cadena
		String cad2 = campos[1].getText(); //Obtenemos la segunda cadena
		String mensaje = "CONCATENACION DE LA CADENA 1 Y CADENA 2: \n\n";
		AreaSalida.setText( mensaje + "\n\n"+ cad1 + cad2 ); // Se concatenan
	}
	public void Palindromo(int sel)
	{
		int i;
		Boolean p = true; // Bandera
		String cad = campos[sel].getText(); //Obtenemos los datos de la cadena seleccionada
		String aux=""; //Cadena auxiliar para guardar el inverso
		for (i = cad.length()-1; i>=0; i--)
			aux = aux + cad.charAt(i); //Invertimos la cadena
		p = cad.equalsIgnoreCase(aux); // Comparamos la original con la invertida
		if(p == true) // Si la bandera no ha sido modificada
			AreaSalida.setText("LA CADENA ES PALINDROMO");
		else 
			AreaSalida.setText("LA CADENA NO ES PALINDROMO");
	}
	public void Inverso(int sel)
	{
		String cad = campos[sel].getText(); //Se obtiene la cadena
		String aux=""; // Cadena auxiliar para guardar el inverso
		int i, tam; 
		tam = cad.length(); // Tamaño de la cadena
		//Invertimos la cadena
		for (i = tam-1; i>=0; i--)
			aux = aux + cad.charAt(i);
		// Se muestra la cadena auxiliar
		AreaSalida.setText("LA CADENA INVERTIDA ES:" +"\n\n" + aux);
	}
	public void Potencia(int sel)
	{
		int tam, i;
		String cad = campos[sel].getText(); // Se obtiene la cadena
		int n = Integer.parseInt(campos[3].getText()); // Se obtiene la potencia
		String aux=""; // Cadena auxilar para guardar la nueva cadena
		tam = cad.length(); // Tamaño de la cadena
		// CASO: La potencia es positiva
		if(n > 0)
		{
			// Concatenamos la cadena consigo misma n veces
			for (i=0; i<=n-1; i++)
				aux = cad + aux;
		}
		// CASO: La potencia es negativa
		else if(n<0) 
		{
			// Invertimos la cadena
			n=-1*n;
			for (i = tam-1; i>=0; i--)
				aux = aux + cad.charAt(i);
			cad = aux;
			// Concatenamos la cadena consigo misma n veces
			for (i=0; i<=n-2; i++)
				aux = cad + aux;
		}
		// CASO: La potencia es 0
		else if(n == 0)
			aux = "E";	// Cadena vacía
		// Se muestra la nueva cadena
		AreaSalida.setText("LA POTENCIA SEGUN EL NUMERO DADO ES:\n\n" + aux +"\n");
	}
	public void Sufijo(int sel)
	{
		int tam, i;
		String cad = campos[sel].getText(); // Se obtiene la cadena
		String aux = ""; // Cadena auxilar para guardar la nueva cadena
		tam = cad.length(); // Tamaño de la cadena
		AreaSalida.setText("E"); // Se imprime la cadena vacía
		// Se imprimen los sufijos, recorriendo cada simbolo de la cadena
		for(i=tam-1; i>=0; i--) // Iniciamos de último simbolo
		{
			aux = cad.charAt(i) + aux; // Nueva cadena
			AreaSalida.setText(" " + AreaSalida.getText() + "\n" +  aux);
		}
	}
	public void Prefijo(int sel)
	{
		int tam, i;
		String cad = campos[sel].getText(); // Obtenemos a cadena
		String aux = "";  // Cadena auxiliar para guardar la nueva cadena
		tam = cad.length(); // Tamaño de la cadena
		AreaSalida.setText("E"); // Se imprime la cadena vacía
		// Se imprimen los prefijos, recorriendo cadda simbolo de la cadena
		for (i=0; i<tam; i++) // Iniciamos del primer simbolo
		{
			aux = aux + cad.charAt(i); //Nueva cadena
			AreaSalida.setText(" " + AreaSalida.getText() + "\n" +  aux);
		}
	}
	public void Subcadena(int sel)
	{
		String cad = campos[sel].getText(); // Obtenemos la cadena
		String aux = ""; // Cadena auxiliar para guardar cada subcadena
		String aux2 = ""; // Cadena auxiliar para imprimir cada subadena
		int i, tam, n; 
		tam = cad.length(); // Tamaño de la cadena
		n = 0; // Variable para iniciar desde cierto simbolo
		while(n<=tam) // Para que no se rebase el limite 
		{
			for (i=n; i<tam; i++) // Se inicia en el simbolo indicado
			{
				aux = aux + cad.charAt(i); // Generamos la nueva cadena
				aux2 = aux; // Guardamos en otra cadena para imprimir
				// Mostramos todo lo que ya teníamos y la nueva cadena
				AreaSalida.setText(AreaSalida.getText() + "\n" +  aux2); 
			}
			aux = " "; // Limpiamos la cadena auxiliar
			n++; // Se recorre el indice de inicio
		}
	}
	/* Metodo que permite seleccionar la cadena con la que se trabajara*/
	public int Seleccionar()
	{
		int a = 0;
		int s = Integer.parseInt(campos[2].getText());
		if(s == 2)
			a = 1;
		return a; 
	}
}
