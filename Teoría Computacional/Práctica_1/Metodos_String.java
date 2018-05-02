 METODOS DE STRING EN JAVA
+ Esta propiedad nos devuelve el caracter que
  encuentra en una posición especifica.
	string.charAr(indice)

+ Sirve para concatenar. 
	string.concat(string2)
	System.out.println(string1+string2)

+ Conocer en que posición de un String se 
  encuentra determinado caracter. Devuelve un
  entero.
  string.indexOf(caracter); 

+ Eliges de donde quieres empezar a evaluar, y va 
  ignorar las posiciones anteriores, pero la 
  posición si sería absoluta en relación a la
  cadena evaluada.
  string.indexOf(caracter, posicionInicio);

+ Recibe un String y evalua en que parte de la 
  cadena a evaluar hay coincidencias con la otra
  cadena que le estamos enviando. 
  String nombre = "Typing Code";
  String buscar = "ping";
  nombre.indexOf(buscar);
  //Obtendriamos 2

+ Devuelve la longitud de la cadena. Toma en 
  cuenta los espacios.
  string.length();

+ Nos reemplaza todos los caracteres como el que
  le enviamos en la cadena en cuestion.
  string.replace(caracter);
  \\Ejemplo:
  String nombre = "Typing Code";
  String nombreCambiado = nombre.replace('y','a');
  \\Salida Taping Code

+ Reemplaza todas las coincidencias de un texto
  o expresión regular dentro de la cadena por un
  string que nosotros queramos definir.
  string.replaceAll(Cadena, Sustituto);
  \\Ejemplo:
  \\Remplazar todos los numeros por 'NUM'
  String cadena = "Guatemala 19 de marzo del 2013";
  String expresionRegular = "[0-9]";
  cadena.replaceAll(expresionRegular, "NUM");
  \\La salida sería:
  "Guatemala NUM de marzo del NUM"

+ Nos devuelve una cadena recortada a partir de 
  posición que le enviemos hasata el final de la
  misma.
  string.substring(posicion);
  \\Ejemplo
  String nombre = "Type Code Guatemala";
  String resultado = nombre.substring(11);
  \\Imprime: Guatemala

+ Enviamos 2 parametros, la posición inicial y la
  posición final, o sea, desde donde hasta donde 
  queremos que nos recorte.
  String.substring(posInicial, posFinal);

+ Devuelve la cadena en minusculas.
  string.toLowerCase();

+ Devuelve la cadena en mayusculas.
  string.ToUpperCase();

+ Compara una cadena con un expresion y devuelve
  si coincide o no.
  string.matches(expresion);

+ Separa una cadena.
  string.split(stringAseparar);

+ Devuelve un string eliminando todos los espacios
  en blanco que encuentre al pirncipio y al final
  de la cadena.
  string.trim();

+ Devuelve true si una cadena es igual a la otra
  y false en caso contrario. Hace disticion de 
  mayusculas y minusculas.
  strin.equals(stringAcomparar);

+ Devuelve true si una cadena es igual a la otra y
  false en caso contrario. NO hace disticion de 
  mayusculas y minusculas.
  string.equalsIgnoreCase(stringAcomparar)

