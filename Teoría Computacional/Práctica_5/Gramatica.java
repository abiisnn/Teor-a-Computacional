/*	
	Autor(a): Abigail Nicolás Sayago
	Grupo: 2CM4
	Fecha: 01-Mayo-2018
	Práctica 5 - Limpieza de Gramática Libre de Contexto 
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
	//JTextField[] Pd;
	String[] Pd;
	List<List> Prods;

	// Num_Datos: 0-NoTerminales, 1-Terminales, 2-Producciones
	int[] Num_Datos = new int [3];
	List<String> Str_NT, Str_T, Str_P, NewGramatica;
	int i, j, k, l, m, contador;
	// Almacena los no terminales que producen "E"
	List<Character> Vacias = new ArrayList<Character>();
	List<Character> SimT = new ArrayList<Character>();
	List<Character> SimNT = new ArrayList<Character>();
	List<Character> SimNTVivos = new ArrayList<Character>();
	List<Character> SimNTMuertos = new ArrayList<Character>();
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
		Dato[1].setText("8");
		Dato[2].setText("9");
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
		//Pd = new JTextField[5];
		Etiquetas2[2] = new JLabel(Nombres[2]);
		Producciones.add(Etiquetas2[2]);
		/*for(i=0; i<5; i++)
		{
			Pd[i] = new JTextField();
			Pd[i].setText(Prod[i]);
			Producciones.add(Pd[i]);
		}*/
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
				//System.out.print("\n" + Num_Datos[j]); 
			}
			Pd = new String[Num_Datos[2]];
			Pd[0] = "S->aAB|A|G";
			Pd[1] = "A->cBd|H";
			Pd[2] = "B->e|fS|E";
			Pd[3] = "C->gD|hDt";
			Pd[4] = "D->x|y|z";
			Pd[5] = "O->AH|cB";
			Pd[6] = "F->AB|Ga";
			Pd[7] = "G->FG";
			Pd[8] = "H->Ha|bH|O";

			SimT = new ArrayList<Character>();
			SimT.add('a');
			SimT.add('b');
			SimT.add('c');
			SimT.add('d');
			SimT.add('e');
			SimT.add('f');
			SimT.add('g');
			SimT.add('h');
			SimT.add('x');
			SimT.add('y');
			SimT.add('z');
			
			/*Pd[0].setText("S->aAB|A|G");
			Pd[1].setText("A->cBd|H");
			Pd[2].setText("B->e|fS|E");
			Pd[3].setText("C->gD|hDt");
			Pd[4].setText("D->x|y|z");
			Pd[5].setText("O->AH|cB");
			Pd[6].setText("F->AB|Ga");
			Pd[7].setText("G->FG");
			Pd[8].setText("H->Ha|bH|O");
		*/
			//Pd[3].setText("C->bbBaa|aaBbb");
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

			/* LISTA DE LISTAS XD. Genera la lista de listas. Por cada producción 
				genera otra lista para guardar las subproducciones.*/
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
				Cadena = Pd[j];
				//System.out.println("\n" + Cadena);
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
				//System.out.println("\n------------SE HAN DIVIDIDO LAS PRODUCCIONES EN LA LISTA");
				//ImprimirGramaticaAuxiliar();
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
		int i,j,k;
		// ELIMINAR REGLAS NO GENERATIVAS
		// Primero verifica que existan cadenas vacias entre las producciones
		Vacias = ValidarRNG(Vacias);
		if(!Vacias.isEmpty())
		{
			System.out.print("\n\n\n\n ============ HAY reglas no Generativas ===========\n\n\n\n");
			ReglasNoGenerativas();
		}
		ReglasRedenominacion();
		SimbolosMuertos();
		
		System.out.println("\n\n\n\n------------------------------GRAMATICA LIMPIA-----------------------------------\n");
		ImprimirGramaticaAuxiliar();
		//ImprimirGramatica();
	}

	/* Validación de que existan cadenas vacias, agrega en un arreglo 
	   La primera letra de la regla no generativa para despues
	   Verificarlo
	 */
	
//////////////////////////////////////////////////////////////
// 			     REGLAS NO GENERATIVAS
/////////////////////////////////////////////////////////////

	public List<Character> ValidarRNG(List<Character> Vacias)
	{
		String Produ, a;
		char C;
		Vacias = new ArrayList<Character>();
		List<String> Aux = new ArrayList<String>();
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			for(j=3; j<Aux.size(); j++)
			{
				Produ = Aux.get(j);
				//System.out.println("\n"+Produ);
				if(Produ != "|")
				{
					for(k=0; k<Produ.length(); k++)
					{
						if(Produ.charAt(k)=='E')
						{
							a = Aux.get(0);
							C = a.charAt(0);
							Vacias.add(C);
							Aux.remove(j);
							Aux.remove(Aux.size()-1);
						}
					}
				}
			}
		}
		//System.out.println("\n LA PRODUCCION QUE CONTIENE REGLAS GENERATIVAS ES " + Vacias);
		return Vacias;
	}

	public void ReglasNoGenerativas()
	{
		//System.out.println("EMPIEZA A ANALIZAR REGLAS NO GENERATIVAS");
		//System.out.println("\n VACIAS ------------ "+Vacias);
		List<String> Aux = new ArrayList<String>();
		String SubProdu, newcad;
		// AGREGAR CADENAS
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			//System.out.println("ANALIZA :    " + Aux);
			for(j=3; j<Aux.size(); j++)
			{
				SubProdu = Aux.get(j);
				//System.out.println("\n COMPARA:  " + SubProdu);
				for(k=0; k<Vacias.size(); k++)
				{
					for(l=0; l<SubProdu.length(); l++)
					{
						//System.out.println("                 Comparamos: " + SubProdu.charAt(l) + "  busca  "+Vacias.get(k));
						if(SubProdu.charAt(l)==Vacias.get(k))
						{
							//Aux.remove(j);
							newcad = NuevaCadena(SubProdu, Vacias.get(k));
							//System.out.println("CADENA : "+newcad);
							Aux.add("|");
							Aux.add(newcad);
							//System.out.println("RESULTA :    " + Aux);
						}
					}
				}
			}
		}
	}
	public String NuevaCadena(String SubProdu, char vacia)
	{
		//System.out.println("\n ---- ENTRA NUEVA CADENA ----");
		//System.out.println("Analiza:  " + SubProdu + "   Quitando   " + vacia);
		int m;
		String newcadena = "";
		for(m=0; m<SubProdu.length(); m++)
		{
			if(SubProdu.charAt(m) != vacia)
			{
				newcadena =  newcadena + SubProdu.charAt(m);
			}
		}
		//System.out.println("\n FINALMENTE TENEMOS LA CADENA:" + newcadena);
		return newcadena;
	}

//////////////////////////////////////////////////////////////
// 			     REGLAS DE REDOMINACION
/////////////////////////////////////////////////////////////
	
	public void ReglasRedenominacion()
	{
		ObtenerSimbolosNT();
		List<String> Aux = new ArrayList<String>();
		List<String> P = new ArrayList<String>();
		String Produ;
		int num = 0;
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			for(j=3; j<Aux.size(); j++)
			{
				Produ = Aux.get(j);
				//System.out.println("\n"+Produ);
				if(Produ != "|")
				{
					if(Produ.length()<2)
					{
						for(l=0; l<SimNT.size(); l++)
						{ // Encuentra las reglas de redenominación
							if(Produ.charAt(0) == SimNT.get(l))
							{
								Aux.remove(j);
								//System.out.println("En la produccion: " + Aux.get(0) + " Encuentra:  " + SimNT.get(l));
								P = Prods.get(l);
								Aux.add("|");
								for(k=3; k<P.size(); k++)
								{
									Aux.add(P.get(k));	
								}
							}
						}
						
					}
					
				}
			}
		}	
	}

	public void ObtenerSimbolosNT()
	{
		SimNT = new ArrayList<Character>();
		List<String> Aux = new ArrayList<String>();
		String a;
		char C;
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			a = Aux.get(0);
			C = a.charAt(0);
			SimNT.add(C);
			a = "";
		}	
		//System.out.println(SimNT);
	}

//////////////////////////////////////////////////////////////
// 			    BUSQUEDA DE SIMBOLOS MUERTOS
/////////////////////////////////////////////////////////////

	public void SimbolosMuertos()
	{
		System.out.println("\n\n---------------- SIMBOLOS MUERTOS");
		ObtenerSimbolosNT();
		GenerarListaVIVOSInicial();
		ActualizarListaVIVOS();
	}
	/* Hace una lista de No termianles que tengan al menos una producción con sólo 
	símbolos termianles en la parte derecha*/
	public void GenerarListaVIVOSInicial()
	{
		String Produ, a;
		char C;
		SimNTVivos = new ArrayList<Character>();	
		List<String> Aux = new ArrayList<String>();
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			//System.out.println("LA PRODUCCION QUE ANALIZA ES:   " + Aux);
			for(j=3; j<Aux.size(); j++)
			{
				Produ = Aux.get(j);
				//System.out.println("\n Elige una SUBPRODUCCION:   " +Produ);
				if(Produ != "|")
				{
					if(Produ.length() <2)
					{
						//System.out.println("\n La subproduccion tiene longitud <2:   " + Produ);		
						for(l=0; l<SimT.size(); l++)
						{ // Guarda en la lista los simbolos vivos
							if(Produ.charAt(0) == SimT.get(l))
							{
								a = Aux.get(0);
								C = a.charAt(0);
								SimNTVivos.add(C);
								j = Aux.size();
							}
						}
					}
				}
			}
		}
		System.out.println("Lista de simbolos vivos INICIAL   " + SimNTVivos);
	}
	/*Dada una producción si todos los no termianles de la parte derecha pertecenen
	a a la lista, entonces podemos incluir en la lista al no terminal de la parte
	izquierda d ela producción.*/
	public void ActualizarListaVIVOS()
	{
		String Produ, a;
		char C;
		int numero;
		List<Character>AuxVivos = new ArrayList<Character>();	
		List<Character>AuxVivos2 = new ArrayList<Character>();	
		List<String> Aux = new ArrayList<String>();
		List<Character>AuxNoVivos = new ArrayList<Character>();	
		AuxVivos = SimNTVivos;
		AuxVivos2 = SimNTVivos;
		// GUARDA LOS QUE NO HAN SIDO ANALIZADOS
		AuxNoVivos = SimNT;
		for(i=0; i<AuxNoVivos.size(); i++)
		{
			for(j=0; j<SimNTVivos.size(); j++)
			{
				if(AuxNoVivos.get(i)==SimNTVivos.get(j))
				{
					AuxNoVivos.remove(i);
				}
			}
		}
		System.out.println("\n\n\nNo terminales que faltan por analizar" + AuxNoVivos);

		int num_NT = 0;
		int aux_NT = 0;
		ObtenerSimbolosNT();
		//System.out.println(SimNT);
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			//System.out.println("\n\n\n\nLA PRODUCCION QUE ANALIZA ES:              " + Aux);
			for(int h=0; h<AuxNoVivos.size(); h++)
			{
				a = Aux.get(0);
				C = a.charAt(0);				
				//System.out.println("Compara:  " + a + "con el simbolo VIVO:  "+ AuxNoVivos.get(h));
				if(C == AuxNoVivos.get(h))
				{
					num_NT = ContarNT(Aux);
					//System.out.println("\n\n\n  ------------ ANALIZA   "+ Aux);
					for(j=3; j<Aux.size(); j++)
					{
						Produ = Aux.get(j);
						//System.out.println("\n Elige una SUBPRODUCCION:   " +Produ);
						if(Produ != "|")
						{
							for(k=0; k<Produ.length(); k++)
							{
								for(l=0; l<AuxVivos2.size(); l++)
								{ // Guarda en la lista los simbolos vivos
									//System.out.println("COMPARA::: "+ Produ.charAt(k) + "con" + AuxVivos2.get(l));
									if(Produ.charAt(k) == AuxVivos2.get(l))
									{
										aux_NT ++;	
									}
								}
							}
						}
					}
				}
			}
			//System.out.println("Numero total de NT:  " + num_NT + "   Numero encontrado:  " + aux_NT);
			if(aux_NT>0 && num_NT>0)
			{
				if(aux_NT == num_NT)
				{
					a = Aux.get(0);
					C = a.charAt(0);				
					SimNTVivos.add(C);	
					aux_NT = 0;
				}	
			}
		}
		System.out.println("Lista final de simbolos vivos" + SimNTVivos);
		// GUARDA LOS QUE NO HAN SIDO ANALIZADOS
		AuxNoVivos = SimNT;
	
		System.out.println("TAM de AUX:"+AuxNoVivos.size());
		for(j=0; j<SimNTVivos.size(); j++)
		{
			for(i=0; i<AuxNoVivos.size(); i++)
			{
				if(AuxNoVivos.get(i)==SimNTVivos.get(j))
				{
					//System.out.println(""+ i + "J== "+j);
					AuxNoVivos.remove(i);
				}	
			}	
		}		
		System.out.println("\n\nSIMBOLOS MUERTOS"+AuxNoVivos+"\n\n\n");

		//ELIMINA SIMBOLOS MUERTOS

		System.out.println(" \n\n\n------------>>>>>> ELIMINANDO SIMBOLOS MUERTOS\n\n");
		for(i=0; i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			System.out.println("\n\n\n\nLA PRODUCCION QUE ANALIZA ES:              " + Aux);
			for(k=0; k<AuxNoVivos.size(); k++)
			{
				a = Aux.get(0);
				C = a.charAt(0);				
				System.out.println("Compara elemento:" + C + "Con:  "+ AuxNoVivos.get(k));
				if(C==AuxNoVivos.get(k))
				{
					System.out.println("SON IGUALEEEEES");
					Prods.remove(i);
				}
				for(j=3; j<Aux.size(); j++)
				{
					Produ = Aux.get(j);
					System.out.println("Usa la produccion:  " + Produ);
					for(l=0; l<Produ.length(); l++)
					{
						if(Produ.charAt(l) ==AuxNoVivos.get(k))
						{
							Aux.remove(j);
						}
					}
				}
			}
		}

	}
	public int ContarNT(List<String> Aux)
	{
		//System.out.println("                                              + + + + + + Contandooooo");
		String Produ, a;
		char C;
		int o,n;
		n = 0;
		for(o=3; o<Aux.size(); o++)
		{
			Produ = Aux.get(o);
			//System.out.println("\n Elige una SUBPRODUCCION:   " +Produ);
			if(Produ != "|")
			{
				for(k=0; k<Produ.length(); k++)
				{
					//System.out.println("\n La subproduccion tiene longitud <2:   " + Produ);		
					for(l=0; l<SimNT.size(); l++)
					{ // Guarda en la lista los simbolos vivos
						if(Produ.charAt(k) == SimNT.get(l))
						{
							n++;
						}
					}
				}
			}
		}
		//System.out.println("Numero de Simbolos No terminales en la produccion:  " + n);
		return n;
	}

//////////////////////////////////////////////////////////////
// 			              GENERAL
/////////////////////////////////////////////////////////////
	public void ImprimirGramaticaAuxiliar()
	{
		for(i=0;  i<Prods.size(); i++)
		{
			System.out.println(Prods.get(i));
		}
	}
	public void Actualizar()
	{
		for(i=0; i<Prods.size(); i++)
		{
			if((Prods.get(i)).get(3)=="|")
			{
				Prods.remove(i);
				i--;
			}
		}
	}

	public void ImprimirGramatica()
	{
		AreaSalida.setText("LA GRAMATICA LIMPIA Y BIEN FORMADA ES:\n");
		String Pro = "";
		List<String> Aux = new ArrayList<String>();
		for(i=0;  i<Prods.size(); i++)
		{
			Aux = Prods.get(i);
			for(j=0; j<Aux.size(); j++)
			{
				Pro = Pro + Aux.get(j);
			}
			AreaSalida.setText(AreaSalida.getText() + "\n" + Pro + "\n");
			Pro = "";
		}	
	}
}
