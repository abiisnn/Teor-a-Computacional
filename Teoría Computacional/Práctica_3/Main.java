/*
	Autor(a): Abigail Nicolás Sayago
	Grupo: 2CM4
	Fecha: 13-Marzo-2018
	Práctica 3 - Automata
*/
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Main extends JFrame implements ActionListener
{
	JFrame Ventana;
	JTextArea AreaSalida;
	JScrollPane scroll;
	JTextField Cadena;
	JButton Enviar;
	JLabel Bienvenida;
	Boleta Automata;
	JPanel Entrada, Salida, ABotones;
	public Main()
	{
		Ventana =  new JFrame("Automata");
		Ventana.setLayout(null);
		Ventana.setSize(800,800);
		Ventana.setVisible(true);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Entrada = new JPanel(new GridLayout(2,2));
		Salida = new JPanel(new GridLayout(1,1));
		ABotones = new JPanel(new GridLayout(2,1));

		AreaSalida = new JTextArea(20,20);
		Automata = new Boleta("", AreaSalida);
		scroll = new JScrollPane(AreaSalida);
		Cadena = new JTextField(20);
		Bienvenida = new JLabel("Ingresa una cadena");
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
		new Main();
	}
	// Recibe los eventos al presionar el boton 
	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton)e.getSource();
		// Si se presiona el boton OK
		if(b == Enviar)
		{
			// Se limpia la entrada
			AreaSalida.setText("");
			// Se envía la boleta 
			Automata.setBoleta(Cadena.getText());
			// Se inicia el proceso de evaluar estados
			Automata.Iniciar();
			// Se verifica si la cadena es valida
			if(Automata.Verificar())
				AreaSalida.setText(AreaSalida.getText() + " Cadena Valida\r\n");
			else
				AreaSalida.setText(AreaSalida.getText() + " Cadena NO Valida\r\n");
		}
	}

}