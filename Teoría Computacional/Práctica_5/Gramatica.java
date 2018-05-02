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

public class Gramatica implements ActionListener
{
	JFrame Ventana;
	JPanel Instruccion, Entrada, NoTerminales, Terminales, Producciones, Salida;
	JLabel Gramatica, ins;
	JLabel[] Etiquetas;
	JLabel[] Etiquetas2;
	String[] Nombres = {"NO TERMINALES", "TERMINALES", "PRODUCCIONES"};
	String[] NoTer = {"NT1", "NT2", "NT3", "NT4", "NT5"};
	String[] Ter = {"TER1", "TER2", "TER3", "TER4", "TER5"};
	String[] Prod = {"1", "2", "3", "4", "5"};

	JTextField N, T, P;
	JTextField[] Dato;
	JButton Numeros, AddDatos;
	JTextArea AreaSalida;
	JScrollPane scrollPane;
	JTextField[] Nd;
	JTextField[] Td;
	JTextField[] Pd;

	// Num_Datos: 0-NoTerminales, 1-Terminales, 2-Producciones
	int[] Num_Datos = new int [3];
	String[] Str_NT, Str_T, Str_P, NewGramatica;
	int i, j, k, l, m, contador;	
	int IndVacias = 0;
	char[] AuxRN;
	// Método constructor
	public Gramatica()
	{
		int i;
		Ventana = new JFrame("Gramaticas Libres de Contexto");
		// Caracteristicas de la ventana
		Ventana.setLayout(null);
		Ventana.setSize(700,800);
		Ventana.setVisible(true);
		Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Panel de instrucciones - INSTRUCCION
		Instruccion = new JPanel(new GridLayout(2,1));
		Gramatica = new JLabel("G = (N, T, P, S)");
		ins = new JLabel("Coloca el numero que corresponda a los datos que se piden\n La letra E representa la cadena vacia");
		Instruccion.add(Gramatica);
		Instruccion.add(ins);
		Instruccion.setBounds(10,10,550,100);

		// Panel de entrada de numeros -  ENTRADA
		Entrada = new JPanel(new GridLayout(3,3));
		Dato = new JTextField[3];
		Etiquetas = new JLabel[3];
		Numeros = new JButton("OK");
		for(i=0; i<3; i++)
		{
			Etiquetas[i] = new JLabel(Nombres[i]);
			Entrada.add(Etiquetas[i]);
		}
		for(i=0; i<3; i++)
		{
			Dato[i] = new JTextField();
			Entrada.add(Dato[i]);
		}
		/*Dato[0].setText("NO TERMINALES");
		Dato[1].setText("TERMINALES");
		Dato[2].setText("PRODUCCIONES");*/
		Numeros.addActionListener(this);
		Numeros.setEnabled(true);
		Entrada.add(Numeros);
		Entrada.setBounds(10,120,550,100);

		Etiquetas2 = new JLabel[3];

		// Panel de datos - NoTerminales
		NoTerminales = new JPanel(new GridLayout(1,6));
		Nd = new JTextField[5];
		Etiquetas2[0] = new JLabel(Nombres[0]);
		NoTerminales.add(Etiquetas2[0]);
		for(i=0; i<5; i++)
		{
			Nd[i] = new JTextField();
			Nd[i].setText(NoTer[i]);
			NoTerminales.add(Nd[i]);
		}
		NoTerminales.setBounds(10,230,550,30);

		// Panel de datos - Terminales
		Terminales = new JPanel(new GridLayout(1,6));
		Td = new JTextField[5];
		Etiquetas2[1] = new JLabel(Nombres[1]);
		Terminales.add(Etiquetas2[1]);
		for(i=0; i<5; i++)
		{
			Td[i] = new JTextField();
			Td[i].setText(Ter[i]);
			Terminales.add(Td[i]);
		}
		Terminales.setBounds(10,270,550,30);

		// Panel de datos - Producciones
		Producciones = new JPanel(new GridLayout(1,6));
		Pd = new JTextField[5];
		Etiquetas2[2] = new JLabel(Nombres[2]);
		Producciones.add(Etiquetas2[2]);
		for(i=0; i<5; i++)
		{
			Pd[i] = new JTextField();
			Pd[i].setText(Prod[i]);
			Producciones.add(Pd[i]);
		}
		Producciones.setBounds(10,310,550,30);
		
		AddDatos = new JButton("Agregar Datos");
		AddDatos.addActionListener(this);
		AddDatos.setEnabled(true);
		AddDatos.setBounds(10,350, 200, 30);
	
		// Panel de SALIDA
		Salida = new JPanel(new GridLayout(1,1));
		AreaSalida = new JTextArea("BIENVENIDO");
		scrollPane = new JScrollPane(AreaSalida);
		Salida.setPreferredSize(new Dimension(400, 100));
		Salida.add(scrollPane, BorderLayout.CENTER);
		Salida.setBounds(10,390,550,200);
		//la siguiente linea es para dar margen interior y color al jpanel 
		Salida.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black),BorderFactory.createEmptyBorder(10, 15, 5, 15)));

		// Agregar paneles a la ventana
		Ventana.add(Instruccion); Ventana.add(Entrada);
		Ventana.add(NoTerminales); Ventana.add(Terminales);
		Ventana.add(Producciones); Ventana.add(AddDatos);
		Ventana.add(Salida);
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton Baux = (JButton)e.getSource();
		int j;
		// Num_Datos: 0-NoTerminales, 1-Terminales, 2-Producciones
		//int[] Num_Datos = new int [3];
		//String[] Str_NT, Str_T, Str_P; 
		if(Baux == Numeros)
		{
			for(j=0; j<3; j++)
			{
				Num_Datos[j] = Integer.parseInt(Dato[j].getText());
				System.out.print("\n" + Num_Datos[j]); 
			}
			Pd[0].setText("S->bb|");
			Pd[1].setText("A->aaaB|bb");
			Pd[2].setText("B->E");
			/*for (j=0; j<5; j++ ) 
			{
				Nd[j].setText("");
				Td[j].setText("");
				Pd[j].setText("");	
			}*/
		} // Fin if(Baux == Numeros)
		else if(Baux == AddDatos)
		{
			System.out.print("ADD DATOS\n");
			Str_NT = new String [Num_Datos[0]];
			Str_T = new String [Num_Datos[1]];
			Str_P = new String [Num_Datos[2]];
			NewGramatica = new String[10];

			for (j=0; j<Num_Datos[0]; j++) 
			{
				Str_NT[j] = Nd[j].getText();
				//System.out.print("\n" + Str_NT[j]);
			}
			for (j=0; j<Num_Datos[1]; j++) 
			{
				Str_T[j] = Td[j].getText();
				//System.out.print("\n" + Str_T[j]);
			}
			for (j=0; j<Num_Datos[2]; j++) 
			{
				Str_P[j] = Pd[j].getText();
				NewGramatica[j] = Pd[j].getText(); 
				//System.out.print("\n" + Str_P[j]);
			}
			LimpiarGramatica();

		} // Fin else if (Baux == AddDatos)
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
		new Gramatica();
	}

	public void LimpiarGramatica()
	{
		String Produccion;
		char[] Vacias = new char[5];
		AuxRN =  new char[5];
		char[] Cadena = new char[20];

		// ELIMINAR REGLAS NO GENERATIVAS
		// Primero verifica que existan cadenas vacias entre las producciones
		Vacias = ValidarRNG(Vacias);
		if(Vacias[0] == '\0')
		{
			System.out.print("No existen Reglas no Generativas\n");
		}
		else
		{
			System.out.print("HAY reglas Generativas\n");
			// Primero recorre el arreglo que contiene las producciones
			for (i=0; i<Num_Datos[2]; i++) 
			{
				// Elige una de las producciones
				Produccion = Str_P[i];
				//System.out.print("\n"+Produccion);
				// Recorre el arreglo que contiene las reglas no generativas
				for (j=0; j<IndVacias; j++) 
				{
					//System.out.print("Entra al for de las reglas no generativas\n");
					// Recorre la produccion a partir del 3 porque los primeros tres son: "A->"
					for(k=3; k<Produccion.length(); k++)
					{
						//System.out.print("Entra al for que recorre la produccion\n");
						// Analiza la primera 
						if(Produccion.charAt(k) == Vacias[j])
						{
							System.out.print("\n"+Produccion);
							Cadena = AnalizarProduccion(Produccion, Vacias[j]);
							for(l=0; l<contador; l++)
							{
								NewGramatica[0] = NewGramatica[0] + Cadena[l];
								System.out.print("\n" + NewGramatica[0]);
							}
							NewGramatica[0] = NewGramatica[0] + "|";
							System.out.print("\n" + NewGramatica[0]);
							//System.out.print("\n" + Str_P[i]);
						}	
					}
				}
			}
		}

	}
	/* Validación de que existan cadenas vacias, agrega en un arreglo 
	   La primera letra de la regla no generativa para despues
	   Verificarlo
	 */
	public char[] ValidarRNG(char[] Vacias)
	{
		String Produccion;
		Vacias = new char[5];
		for(i=0; i<Num_Datos[2]; i++)
		{
			Produccion = Str_P[i];
			//System.out.print(Produccion);
			for(k=0; k<Produccion.length(); k++)
			{
				//System.out.print("\nENTRA AL FOR");
				if(Produccion.charAt(k) == 'E')
				{
					Vacias[IndVacias] = Produccion.charAt(0);
					//System.out.print("\n" + Produccion + " " + Vacias[IndVacias]);
					IndVacias++;
				}	
			}	
		}
		return Vacias;
	}
	/* Regresa la parte que debe ser concatenada a la produccion original.
	*/
	public char[] AnalizarProduccion(String Produccion, char Vacia)
	{
		//Arreglo que guardara la cadena que sera enviada para concatenar a la produccion principal
		char[] cadena = new char[10];
		i = 0; j = 0; k = 0; contador = 0;
		// De la Produccion que recibe empieza a analizar
		for(j=3; j<Produccion.length(); j++)
		{
			// Guarda la parte de cadena que se va a pegar
			AuxRN[i] = Produccion.charAt(j);
			if(AuxRN[i] != '|')
			{
				if(AuxRN[i] != Vacia)
				{
					cadena[k] =  AuxRN[i];
					k++; contador++;
				}			
			}
			i++; j++;
		}
		return cadena;
	}
}