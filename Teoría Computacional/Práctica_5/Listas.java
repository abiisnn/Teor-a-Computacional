
¿Cómo usar Listas en Java?

// Declarar una lista
List ListName = new ArrayList();

// Podemos declarar cualquier tipo de dato
// Byte, Short, Integer, Long, Float, Double, Character, Boolean
List<String> ListName = new ArrayList<String>();
List<Integer> ListNameInt = new ArrayList<Integer>();

// Para agregar a una lista
ListName.add("Hola");

// Para obtener la cantidad de elementos que tiene la lista
ListName.size();

// Para consultar un elemento se usa
ListName.get(0);

// Para eliminar determinado elemento 
ListName.remove(0);

// Para eliminar cierto elemento 
ListName.remove("Loquequieras");

// Para imprimir la lista tenemos dos opciones

// OPCION 1
System.out.println(ListName);

// OPCION 2
for(int i=0; i<ListName.size()-1; i++)
{
	System.out.println(ListName.get(i));
}

// Para eliminar todos los elementos de la lista
ListName.clear();

// Saber si la lista contiene al menos un elemento. Devuelve true o false
ListName.isEmpty();

// Para saber un elemento especifico
ListName.contains("Loquequieras");

// Para modificar cierto elemento
ListName.set(1, "Loquequieras"); 

