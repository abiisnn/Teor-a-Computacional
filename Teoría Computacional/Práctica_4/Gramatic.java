/*
	Autor(a): Abigail Nicolás Sayago
	Grupo: 2CM4
	Fecha: 12-Abril-2018
	Práctica 4 - Gramatica
*/
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.UIManager.*;

public class Gramatic extends JFrame implements ActionListener
{
	JFrame Ventana;
	JTextArea AreaSalida;
	JScrollPane scroll;
	JTextField Cadena;
	JButton Enviar;
	JLabel Bienvenida;
	JPanel Entrada, Salida, ABotones;
	public Gramatic()
	{
		Ventana =  new JFrame("GRAMATICA");
		Ventana.setLayout(null);
		Ventana.setSize(800,800);
		Ventana.setVisible(true);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Entrada = new JPanel(new GridLayout(2,2));
		Salida = new JPanel(new GridLayout(1,1));
		ABotones = new JPanel(new GridLayout(2,1));

		AreaSalida = new JTextArea(20,20);
		scroll = new JScrollPane(AreaSalida);
		Cadena = new JTextField(20);
		Bienvenida = new JLabel("Ingresa el numero de cadenas que quieres ver:");
		Enviar = new JButton("OK");

		Entrada.add(Bienvenida);
		Entrada.add(Cadena);
		Entrada.setBounds(10,10,450,100);

		Salida.add(scroll);
		Salida.setBounds(10,150,450,350);
		Salida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(10, 15, 5, 15)));


		ABotones.add(Enviar);
		ABotones.setBounds(10,540,450,100);
		Enviar.addActionListener(this);
		Ventana.add(Entrada);
		Ventana.add(Salida);
		Ventana.add(ABotones);
	}

	public static void main(String[] args)
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
		new Gramatic();
	}
	// Recibe los eventos al presionar el boton 
	public void actionPerformed(ActionEvent e)
	{
		JButton bo = (JButton)e.getSource();
		// Si se presiona el boton OK
		if(bo == Enviar)
		{
			Gramatica();
		}
	}
	public void Gramatica()
	{
		AreaSalida.setText("SI FUNCIONA");
		int num = Integer.parseInt(Cadena.getText());
		int contador, i;
		String a, b;
		String auxa, auxb, cadena, gramatica;
		a = "a"; auxa = "";
		b = "b"; auxb = "";
		if(num > 0)
		{
			contador = 1;
			AreaSalida.setText("E");
			while (contador<=num) // Imprime el número de cadenas que se eligen
			{
				// Se crea una cadena que contenga s a
				auxa = GenerarA(contador);
				// Se crea una cadena que contenta s b
				auxb = GenerarB(contador);
				gramatica = auxa + "S" + auxb; 
				cadena = auxa + auxb;
				AreaSalida.setText(" "+AreaSalida.getText()+ "\n" + gramatica + "        " + cadena);
				cadena = ""; gramatica = "";
				contador++;
			}
		}
		else 
			AreaSalida.setText("El numero que ingresaste no es valido");
	}

	public String GenerarA(int N)
	{
		int i;
		String auxA = "", A = "a";
		for (i=0; i<N; i++)
			auxA = auxA + A;
		return auxA;
	}

	public String GenerarB(int N)
	{
		int i;
		String auxB = "", B = "b";
		for (i=0; i<N; i++)
			auxB = auxB + B;
		return auxB;
	}
}