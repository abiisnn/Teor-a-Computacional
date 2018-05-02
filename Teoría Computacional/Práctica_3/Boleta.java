/*
	Autor(a): Abigail Nicolás Sayago
	Grupo: 2CM4
	Fecha: 13-Marzo-2018
	Práctica 3 - Automata
*/
import java.util.*;
import javax.swing.*;
import java.lang.Integer.*;

/*	Clase BOLETA, verifica cada estado por el que pasa la cadena.
	Permite decir si la cadena es correcta o no.*/
public class Boleta
{
	String NumBoleta;
	JTextArea auxText;
	int i; // Variable que permitira contar la longitud
	boolean Correcto;

	// Metodo constructor
	public Boleta(String NumBoleta, JTextArea auxText)
	{
		this.NumBoleta = NumBoleta;
		this.auxText = auxText;
	}
	// Obtiene la boleta
	public void setBoleta(String NumBoleta)
	{
		this.NumBoleta = NumBoleta;
	}

	/* Método que permite verificar si la cadena cumple 
	   con la longitud requerida, en este caso 16. */
	public boolean Verificar()
	{
		if(i==16)
			return true;
		else
			return false;
	}

	/* Método que inicia la verificación de estados */
	public void Iniciar()
	{
		i = 0; // Se inicializa la variable contadora
		Correcto = true;
		qo(); // Empieza el estado 0
	}
	// Método del ESTADO 0 
	public void qo()
	{
		auxText.setText(auxText.getText()+"Q0\r\n");
		if(i< NumBoleta.length())
		{
			// Verifica que sea un número 2
			if('2'==NumBoleta.charAt(i))
			{
				i++; q1();
			}
		}
	}
	// Método del ESTADO 1
	public void q1()
	{
		auxText.setText(auxText.getText()+"Q1\r\n");
		if(i< NumBoleta.length())
		{
			// Verifica que sea un número 0
			if('0'==NumBoleta.charAt(i))
			{
				i++; q2();
			}
		}
	}
	// Método del ESTADO 2
	public void q2()
	{
		auxText.setText(auxText.getText() + "Q2\r\n");
		if(i < NumBoleta.length())
		{
			// Verifica que sea un número 1
			if('1'==NumBoleta.charAt(i))
			{
				i++; q3();
			}
		}	
	}
	// Método del ESTADO 3
	public void q3()
	{
		auxText.setText(auxText.getText() + "Q3\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango de [4-7]
			if( (num > 3) && (num < 8) )
			{
				i++; 
				q4();
			}
		}
	}
	// Método del ESTADO 4
	public void q4()
	{
		auxText.setText(auxText.getText() + "Q4\r\n");
		if(i < NumBoleta.length())
		{
			// Verifica que sea un número 1
			if('1'==NumBoleta.charAt(i))
			{
				i++; q5();
			}
		}	
	}
	// Método del ESTADO 5
	public void q5()
	{
		auxText.setText(auxText.getText() + "Q5\r\n");
		if(i < NumBoleta.length())
		{
			// Verifica que sea un número 0
			if('0'==NumBoleta.charAt(i))
			{
				i++; q6();
			}
		}	
	}
	// Método del ESTADO 6
	public void q6()
	{
		auxText.setText(auxText.getText() + "Q6\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango de [0-1]
			if( (num >= 0) && (num < 2))
			{
				i++; q7();
			}
		}
	}
	// Método del ESTADO 7
	public void q7()
	{
		auxText.setText(auxText.getText() + "Q7\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			int numAnt = Integer.parseInt(""+NumBoleta.charAt(i-1));
			if(numAnt == 0)
			{
				// Cumple el rango de [0-9]
				if( (num >= 0) && (num < 10))
				{
					i++; q8();
				}
			}
			else 
			{
				// Cumple el rango de [0-2]
				if( (num >= 0) && (num < 3))
				{
					i++; q8();
				}
			}
		}	
	}
	// Método del ESTADO 8
	public void q8()
	{
		auxText.setText(auxText.getText() + "Q8\r\n");
		if(i < NumBoleta.length() )
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango de [0-9]
			if( (num >= 0) && (num < 10))
			{
				i++; q9();
			}
		}	
	}
	// Método del ESTADO 9
	public void q9()
	{
		auxText.setText(auxText.getText() + "Q9\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango de [0-9]
			if( (num >= 0) && (num < 10))
			{
				i++; q10();
			}
		}	
	}
	// Método del ESTADO 10
	public void q10()
	{
		auxText.setText(auxText.getText() + "Q10\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango de [1-9]
			if( (num >= 1) && (num < 10))
			{
				i++; q11();
			}
		}	
	}
	// Método del ESTADO 11
	public void q11()
	{
		String letra1 = "A", letra2 = "D";
		char car = NumBoleta.charAt(i);
		auxText.setText(auxText.getText() + "Q11\r\n");
		if(i < NumBoleta.length())
		{
			if(String.valueOf(car).equals(letra1) || String.valueOf(car).equals(letra2))
			{
				i++; q12();	
			}
		}
	}
	// Método del ESTADO 12
	public void q12()
	{
		auxText.setText(auxText.getText() + "Q12\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango [0-9]
			if( (num >= 0) && (num < 10))
			{
				i++; q13();
			}
		}	
	}
	// Método del ESTADO 13
	public void q13()
	{
		auxText.setText(auxText.getText() + "Q13\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango [0-9]
			if( (num >= 0) && (num < 10))
			{
				i++; q14();
			}
		}	
	}
	// Método del ESTADO 14
	public void q14()
	{
		auxText.setText(auxText.getText() + "Q14\r\n");
		if(i < NumBoleta.length())
		{
			int num = Integer.parseInt(""+NumBoleta.charAt(i));
			// Cumple el rango [0-9]
			if( (num >= 0) && (num < 10))
			{
				i++; q15();
			}
		}	
	}
	// Método del ESTADO 15
	public void q15()
	{
		auxText.setText(auxText.getText() + "Q15\r\n");
		char[] abecedario;
		int j;
		if (i< NumBoleta.length())
		{
			abecedario = new char[26];
			for(j=0; j<26; j++)
				abecedario[j] = (char)('A' + j);
			for(j=0; j<26; j++)
			{
				if(NumBoleta.charAt(i) == abecedario[j])
				{
					j=26; q16(); i++;	
				}
			}
		}
	}
	// Método del ESTADO 16 - FIN -
	public void q16()
	{
		auxText.setText(auxText.getText() + "Q16\r\n");
		if(NumBoleta.length()>16)
			i=17;
	}
}