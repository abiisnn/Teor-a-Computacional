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
import java.util.Iterator;
import java.util.List;  

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

	List<List> Prods;

	// Num_Datos: 0-NoTerminales, 1-Terminales, 2-Producciones
	int[] Num_Datos = new int [3];
	List<String> Str_NT, Str_T, Str_P, NewGramatica;
	int i, j, k, l, m, contador;
	List<Character> AuxRN;
	// Método constructor
	public Gramatica()
	{
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
		Dato[0].setText("1");
		Dato[1].setText("1");
		Dato[2].setText("4");
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
		// Num_Datos: 0-NoTerminales, 1-Terminales, 2-Producciones
		//int[] Num_Datos = new int [3];
		int j;
		if(Baux == Numeros)
		{
			for(j=0; j<3; j++)
			{
				Num_Datos[j] = Integer.parseInt(Dato[j].getText());
				System.out.print("\n" + Num_Datos[j]); 
			}
			Pd[0].setText("S->bb");
			Pd[1].setText("A->aaaB|E");
			Pd[2].setText("B->E");
			Pd[3].setText("C->bbBaa|aaBbb");
			/*for (j=0; j<5; j++ ) 
			{
				Nd[j].setText("");
				Td[j].setText("");
				Pd[j].setText("");	
			}*/
		} // Fin if(Baux == Numeros)
		else if(Baux == AddDatos)
		{
			List<String> AuxiliarStr = new ArrayList<String>();
			Prods = new ArrayList<List>();
			String Cadena, AuxCadena;
			//AuxCadena = "";
			char Car;

			/* LISTA DE LISTAS XD
			   Genera la lista de listas 
			   Por cada producción genera otra lista para
			   guardar las subproducciones.
			*/
			for(j=0; j<Num_Datos[2]; j++)
			{
				List<String> Produccion;
				Produccion = new ArrayList<String>();
				Prods.add(Produccion);
			}
			/* DIVIDIR SUBPRODUCCIONES
			   De la lista principal de las producciones agarra un elemento y cada elemento
			   pertenece a una producción, de esa producción ya se genero otra lista, la 
			   cual contendrá la producción dividida en subproducciones:
			   Ejemplo: (Entre parentesis esta el índice al que corresponderá de la lista)
			   A[0] -[1]>[2] aaaB[3] |[4] bbbC[5] 
			*/ 
			for(j=0; j<Num_Datos[2]; j++)
			{
				// Obtiene una de las cadenas ingresadas
				Cadena = Pd[j].getText();
				System.out.println("\n" + Cadena);
				// Agrega los primeros 3 elementos a la lista [A->]
				for(k=0; k<3; k++)
				{
					// Agarra un caracter
					Car = Cadena.charAt(k);
					// Lo convierte a String
					AuxCadena = Character.toString(Car);
					// Y lo agrega a la lista de su produccion correspondiente
					(Prods.get(j)).add(AuxCadena);
				}
				AuxCadena = "";
				// Agrega los elementos sobrantes a la lista 
				for(k=3; k<Cadena.length(); k++)
				{
					Car = Cadena.charAt(k);
					if(Car == '|') // Si el caracter es "|", indica una nueva subproduccion
					{
						// Agrega la subproduccíon a la posicion correspondiente 
						(Prods.get(j)).add(AuxCadena);
						(Prods.get(j)).add("|"); // También agrega un "|"
						AuxCadena = ""; // Limpia la Cadena auxiliar
					}
					else
					{
						// Almacena en una cadena la subproduccion que despues sera almacenada
						AuxCadena = AuxCadena + Cadena.charAt(k);	
					}
				}
				// Agrega a la lista la ultima cadena que se ha generado
				(Prods.get(j)).add(AuxCadena);
				System.out.println("\n------------SE HAN DIVIDIDO LAS PRODUCCIONES EN LA LISTA");
				System.out.println(Prods.get(j));
			}
			LimpiarGramatica();
			/*System.out.print("\n------------------------------ADD DATOS");
			Str_NT = new ArrayList<String>();
			Str_T = new ArrayList<String>();
			Str_P = new ArrayList<String>();
			NewGramatica = new ArrayList<String>();

			for (j=0; j<Num_Datos[0]; j++) 
			{
				Str_NT.add(Nd[j].getText());
				//System.out.print("\n" + Str_NT[j]);
			}
			for (j=0; j<Num_Datos[1]; j++) 
			{
				Str_T.add(Td[j].getText());
				//System.out.print("\n" + Str_T[j]);
			}
			for (j=0; j<Num_Datos[2]; j++) 
			{
				Str_P.add(Pd[j].getText());
				NewGramatica.add(Pd[j].getText()); 
				//System.out.print("\n" + Str_P[j]);
			}*/
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

		int i,j,k;
		List<Character> Vacias = new ArrayList<Character>();
		AuxRN =  new ArrayList<Character>();
		// ELIMINAR REGLAS NO GENERATIVAS
		// Primero verifica que existan cadenas vacias entre las producciones
		Vacias = ValidarRNG(Vacias);
		if(Vacias.isEmpty())
		{
			System.out.print("No existen Reglas no Generativas\n");
		}
		else
		{
			System.out.print("\n ============ HAY reglas no Generativas ===========\n");
	
			ReglasNoGenerativas(Vacias);

/*
			List <String> Auxiliar = new ArrayList<String>();
			List <String> SubPro =  new ArrayList<String>();

			// Generamos las subproducciones que existen
			Auxiliar = GenerarSubproducciones(Auxiliar);
			String Cadena;
			for(i=0; i<Vacias.size(); i++)
			{
				System.out.println("\n El simbolo terminal " + Vacias.get(i) + "Contiene la cadena vacia");		
				for(j=0; j<Auxiliar.size(); j++)
				{
					Cadena = Auxiliar.get(j);
					System.out.println("\n *** Analiza " + Cadena);
					for(k=0; k<Cadena.length(); k++)
					{
						if(Cadena.charAt(k)==Vacias.get(i))
						{
							// Analizo la subproduccion
							System.out.println("\n" + Cadena);
							SubPro.add(Cadena);
						}
					}
				}
				System.out.println("\n"+SubPro);
			}
			SubPro = Analizar(SubPro);
	*/	
		}
	}

	/* Validación de que existan cadenas vacias, agrega en un arreglo 
	   La primera letra de la regla no generativa para despues
	   Verificarlo
	 */
	public List<Character> ValidarRNG(List<Character> Vacias)
	{
		String Produ, a;
		char C;
		Vacias = new ArrayList<Character>();
		List<String> Aux = new ArrayList<String>();
		for(i=0; i<Num_Datos[2]; i++)
		{
			Aux = Prods.get(i);
			for(j=3; j<Aux.size(); j++)
			{
				Produ = Aux.get(j);
				System.out.println("\n"+Produ);
				if(Produ != "|")
				{
					for(k=0; k<Produ.length(); k++)
					{
						if(Produ.charAt(k)=='E')
						{
							a = Aux.get(0);
							C = a.charAt(0);
							Vacias.add(C);
						}
					}
				}
				//for(k=0; k<)
			}
		}
		System.out.println("\n LA PRODUCCION QUE CONTIENE REGLAS GENERATIVAS ES " + Vacias);
		return Vacias;
	}

	public void ReglasNoGenerativas(List<Character> Vacias)
	{
		System.out.println("EMPIEZA A ANALIZAR REGLAS NO GENERATIVAS");
		Vacias = new ArrayList<Character>();
		List<String> Aux = new ArrayList<String>();
		String SubProdu, newcad;
		for(i=0; i<Num_Datos[2]; i++)
		{
			Aux = Prods.get(i);
			System.out.println("ANALIZA :    " + Aux);
			
			for(j=3; j<Aux.size(); j++)
			{
				SubProdu = Aux.get(j);
				System.out.println("\n COMPARA:  " + SubProdu);
				for(k=0; k<Vacias.size(); k++)
				{
					// AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII
					for(l=0; l<SubProdu.length(); l++)
					{
						System.out.println("                 Comparamos:" + SubProdu.charAt(l) + "busca  "+Vacias.get(k));
						if(SubProdu.charAt(l)==Vacias.get(k))
						{
							newcad = NuevaCadena(SubProdu, Vacias.get(k));
						}
					}
				}
			}
		}
	}
	public String NuevaCadena(String SubProdu, char vacia)
	{
		System.out.println("\n ---- ENTRA NUEVA CADENA ----");
		System.out.println("Analiza:" + SubProdu + "Quitando" + vacia);
		int m;
		String newcadena = "";
		for(m=0; m<SubProdu.length(); m++)
		{
			if(SubProdu.charAt(m) != vacia)
			{
				newcadena =  newcadena + SubProdu.charAt(m);
			}
		}
		System.out.println(newcadena);
		return newcadena;
	}

	public List<String> GenerarSubproducciones(List <String> Auxiliar)
	{
		String Produccion;
		Auxiliar = new ArrayList<String>();
		String Palabra = "";
		for (i=0; i<Num_Datos[2]; i++) 
		{
			// Elige una de las producciones
			Produccion = Str_P.get(i);
			System.out.print("\n-------------------ANALIZA LA PRODUCCION:   "+Produccion);			
			// Recorre la produccion a partir del 3 porque los primeros tres son: "A->"
			for(j=2; j<Produccion.length(); j++)
			{
				if(Produccion.charAt(j) != '|' && Produccion.charAt(j) != '>')
				{
					Palabra = Palabra + Produccion.charAt(j);
				}
				else
				{
					System.out.println("\n"+Palabra);
					Auxiliar.add(Palabra);
					Palabra = "";
				}
			}		
		}	
		Auxiliar.remove(0);
		System.out.println(Auxiliar);
		return Auxiliar;
	}

	/* Regresa la parte que debe ser concatenada a la produccion original.
	*/
	public List<String> Analizar(List <String> SubProConcatenar)
	{
		System.out.println("\n EMPIEZA A ANALIZAR LAS SUBPRODUCCIONES ");
		SubProConcatenar = new ArrayList<String>();
		List <Character> AuxSub = new ArrayList<Character>();
		int l,j,k,indice;
		//Arreglo que guardara la cadena que sera enviada para concatenar a la produccion principal
		String Palabra = "";
		String SubProd = "";
		indice = 0;
		System.out.println(SubProConcatenar);
		for(l=0; l<SubProConcatenar.size(); l++)
		{
			SubProd = SubProConcatenar.get(l);
			System.out.println("\n" + SubProd);
			/*for(j=0; j<SubProd.length(); j++)
			{
				AuxSub.add(SubProd.charAt(j));
			}
			System.out.println("\n" + AuxSub);
			for(j=0;j<AuxSub.size();j++)
			{
				if(AuxSub.get(j) == Vacia)
					indice = j;
			}
			if(AuxSub.contains(Vacia))
			{
				//System.out.println("Si contiene vacia");
				AuxSub.remove(indice);
				System.out.println(AuxSub);
				for(k=0; k<AuxSub.size();k++)
				{
					Palabra = Palabra + AuxSub.get(k); 
				}
				Auxiliar.set(i, Palabra);
			}*/
		}
		System.out.println(SubProConcatenar);
		return SubProConcatenar;
	}
}