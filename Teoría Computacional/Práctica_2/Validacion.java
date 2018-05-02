/*
	Autor(a): Abigail Nicolás Sayago
	Grupo: 2CM4
	Fecha: 13-Marzo-2018
	Práctica 2 - Expresión Regular
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
import java.util.regex.Pattern;

public class Validacion implements ActionListener
{
	JFrame Ventana;
	JTextField Cadena;
	JLabel Valida;
	JButton Enviar;
	JPanel Principal, Salida;
	JTextArea AreaSalida;
	JScrollPane scrollPane;

	public Validacion()
	{
		// Para la ventana
		Ventana = new JFrame("Validacion de una cadena");
		Ventana.setLayout(null);
		Ventana.setSize(800,800);
		Ventana.setVisible(true);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Para el panel
		Principal = new JPanel(new GridLayout(3,1));

		Cadena = new JTextField("Introduce una cadena");
		Valida =  new JLabel("*****");
		Enviar = new JButton("OK");

		Enviar.addActionListener(this);
		Enviar.setEnabled(true);

		Principal.setBounds(10,10,200,100);
		Principal.add(Cadena);
		Principal.add(Enviar);
		Principal.add(Valida);
		
		// Para la salida
		AreaSalida = new JTextArea("BIENVENIDO");
		scrollPane = new JScrollPane(AreaSalida);
		Salida.setPreferredSize(new Dimension(400, 100));
		Salida.add(scrollPane, BorderLayout.CENTER);
		Salida.setBounds(10,150,450,350);
		//la siguiente linea es para dar margen interior y color al jpanel 
		Salida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(10, 15, 5, 15)));

		Ventana.add(Principal);
		Ventana.add(Salida);
	}

	public void actionPerformed(ActionEvent e)
	{
		// Recibimos un boton
		JButton b = (JButton)e.getSource();
		String aux="";
		Boolean a; // Bandera
		int aux2, i;
		// Si el boton es el de enviar
		if(b == Enviar)
		{	
			a = true;
			// Obtenemos la cadena a validar
			String cad = Cadena.getText(); 
			// Si la cadena es de longitud 16 
			if(cad.length() == 16)
			{	
				// Obtenemos el número de municipio
				for(i=6; i<9; i++) 
					aux = aux + cad.charAt(i);
				aux2 = Integer.parseInt(aux);
				// Si el número de municipio esta en el rango
				if (aux2 < 126) 
				{
					// Finalmente mandamos la cadena 
					if(a = validarExpresion(cad)) 
						Valida.setText("CADENA VALIDA");
					else // Si no cumple con la E.R
						Valida.setText("CADENA NO VALIDA");	
				}
				else // Si el municipio no esta en el rango
					Valida.setText("CADENA NO VALIDA");
			} 
			else // Si la cadena no es de longitud 16
				Valida.setText("CADENA NO VALIDA");
		}
	}
	
	public static void main(String s[])
	{
		new Validacion();
	}
	private boolean validarExpresion(String CAD)
	{
		// Generamos la expresión regular
		String regex = "([2]{1}[0]{1})(1[4-7])" + 
						"1" + "0" + 
						"([0-1][0-9][0-9])" + 
						"([0-9][1-9])" + 
						"[AD]{1}" + 
						"[0-9][0-9][0-9][A-Z]{1}" ;
		// Se compila la expresión regular
		Pattern patron = Pattern.compile(regex);
		// Si la cadena no coincide con la expresión regular
		if(!patron.matcher(CAD).matches()) 
			return false;
		else
			return true;
	}
}

