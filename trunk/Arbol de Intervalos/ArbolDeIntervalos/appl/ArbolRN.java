package ArbolDeIntervalos.appl;

import java.util.Iterator;
import java.util.Set;

/**
 * Arbol "Rojo Y Negro", con operaciones de insercion y busqueda
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 * 
 * @param <T> Recibe cualquier tipo de elementos que implementen Comparable
 */
public class ArbolRN<T extends Comparable<? super T>>
{
	/**
	 *  Raiz del Arbol Rojo Negro
	 */
	protected Nodo raiz;	

	/**
	 * Costructor de arbol que devuelve un arbol vacio
	 */
	public ArbolRN()
	{  
		raiz = null;				
	} 

	/**
	 * Metodo para averiguar si un arbol es vacio o no
	 * 
	 * @return devuelve TRUE si el arbol esta vacio 
	 * y FALSE si no lo esta
	 */
	public boolean vacio() 
	{
		if (raiz == null)
			return true;
		else
			return false;
	}

	/**
	 * Insertar en ABB, Inserta un nodo como si el arbol
	 * fuera un arbol binario de busqueda. <br>
	 * Gracias a que recibe el nodo como parametro
	 * el llamador conserva un referencia al nodo insertado
	 * de esta manera es posible in volviendo y restableciendo
	 * el invariante del RedBlackTree
	 * 
	 * @param nuevo Nodo que contiene el dato que queremos insertar
	 */
	@SuppressWarnings("unchecked")
	protected void insertarABB(Nodo nuevo)
	{	
											// bucaré "El padre" de mi nodo
		Nodo padre = null;					// será el padre
		Nodo hijo = this.raiz;				// será el hijo

		while (hijo != null)				// mientras haya hijos
		{
			padre = hijo;					// el padre es mi hijo
			if (nuevo.dato.compareTo(hijo.dato) == -1) 
				hijo = padre.izq;			// actualizo mi hijo
			else
				hijo = padre.der;			// actualizo mi hijo
		}
		nuevo.padre = padre;				// el padre es el conseguido

		if (padre == null) 					// si es huerfano, es la raiz del arbol
			raiz = nuevo;
		else 
			if (nuevo.dato.compareTo(padre.dato) == -1)
				padre.izq = nuevo;
			else  
				padre.der = nuevo;								
	}
	
	/**
	 * Insercion en el Arbol Red Black
	 * REQUIERE que el elemento a ingesar no este el el arbol...
	 * sino provoca resultados no esperados
	 * 
	 * @param dato dato a ingresar en el arbol red black
	 */
	@SuppressWarnings("unchecked")
	public void insertar(T dato)
	{
		Nodo actual = new Nodo(dato); 								// nodo con el elemento a ingresar		
		this.insertarABB(actual);									// lo inserto de la forma usual en un ABB

		while (actual != raiz && actual.padre.color == Nodo.ROJO)
			// mientras no haya reacomodado hasta la raiz
			// y solo se el padre es rojo (si fuera negro no habria que hacer nada)
		{
			if (actual.padre == actual.padre.padre.izq)				// si el padre de actual es hijo izquierdo
			{
				Nodo tio = actual.padre.padre.der;					// me quedo con el tio 																
				if (tio != null && tio.color == Nodo.ROJO) 			// si el tio existe
				{ 													// y es de color rojo
																	// ESTAMOS EN CASO 1 ver informe       
					actual.padre.color = Nodo.NEGRO;      			// pinto al padre de negro
					tio.color = Nodo.NEGRO;            				// pinto al tio de negro
					actual.padre.padre.color = Nodo.ROJO; 			// pinto al abuelo de rojo

					actual = actual.padre.padre;          		// listo por ahora, luego reacomodamos
				}
				else												// el tio es de color negro						
				{
					if (actual == actual.padre.der )				// hijo derecho del padre
																	// ESTAMOS EN CASO 2 ver informe
					{            
						actual = actual.padre;           			// vamos a rotar el padre
						rotarIzq(actual);           				// rotamos a izq
																	// hemos reducido del caso 2 al 3
					}
																	// ESTAMOS EN CASO 3 ver informe

					actual.padre.color = Nodo.NEGRO;        		// pintamos al padre de negro
					actual.padre.padre.color = Nodo.ROJO;   		// pintamos al abuelo de rojo
					rotarDer(actual.padre.padre);		  			// rotamos al abuelo a der
				}
			}
			else
			{ 
				if (actual.padre == actual.padre.padre.der ) 		// si el padre de actual es hijo derecho
				{
					Nodo tia = actual.padre.padre.izq;				// me quedo con la tia 	 																								
					if ((tia != null) && tia.color == Nodo.ROJO)	// si la tia existe y es de color rojo
					{ 												// ESTAMOS EN CASO 1 ver informe     
						actual.padre.color = Nodo.NEGRO;     		// pinto al padre de negro
						tia.color = Nodo.NEGRO;           			// pinto a la tia de negro
						actual.padre.padre.color = Nodo.ROJO;		// pinto al abuelo de negro

						actual = actual.padre.padre;         		// listo por ahora, luego reacomodamos
						System.out.println("hol");
					}
					else											// la tia es de color negro	
					{
						if (actual == actual.padre.izq)				// hijo derecho del padre
																	// ESTAMOS EN CASO 2 ver informe
						{	
							actual = actual.padre;           		// vamos a rotar al padre
							rotarDer(actual);         				// rotamos a der
																	// hemos reducido del caso 2 al 3
						}
																	// ESTAMOS EN CASO 3 ver informe

						actual.padre.color = Nodo.NEGRO;     		// pinto al padre de negro
						actual.padre.padre.color = Nodo.ROJO;		// pinto al abuelo de rojo
						rotarIzq(actual.padre.padre);	 			// rotamos al abuelo a izq
					}
				}
			}
		}
		raiz.color = Nodo.NEGRO;									// terminamos con el invariante
																	// poniendo la raiz en negro
	}

	/**
	 * RotarIzq, rota el subarbol que comienza con el nodo "b"
	 * parametro de la funcion. Notese que no mantiene los colores
	 * 
	 * @param b Nodo a partir del cual se realiza la rotacion
	 */
	@SuppressWarnings("unchecked")
	private void rotarIzq(Nodo b)
	{
		Nodo a = b.der;					// me guardo el subarbol A
		b.der = a.izq;					// DER de B = y
		if( a.izq != null )				// si y no es nil
			a.izq.padre = b;			// el padre de y es B 
		a.padre = b.padre;				// el padre de A es el padre de B

		if( b.padre == null )			// si B era la raiz
			raiz = a;					// la raiz es ahora A

		else 							// si B no era raiz
			if( b == b.padre.izq )		// y si B era el hijo izq
				b.padre.izq = a;		// actualizo que A es hijo Izq
			else						// y si B era el hijo der
				b.padre.der = a;		// actualizo que A es hijo Der

		a.izq = b;						// el hijo izq de A es B
		b.padre = a;					// el padre de B es A
	}		

	/**
	 * RotarDer, rota el subarbol que comienza con el nodo "a"
	 * parametro de la funcion. Notese que no mantiene los colores
	 * 
	 * @param a Nodo a partir del cual se realiza la rotacion
	 */
	@SuppressWarnings("unchecked")
	private void rotarDer(Nodo a)
	{
		Nodo b = a.izq;					// me guardo el subarbol B
		a.izq = b.der;					// DER de B = y
		if( b.der != null )				// si y no es nil
			b.der.padre = a;			// el padre de y es A
		b.padre = a.padre;				// el padre de B es el padre de A

		if( a.padre == null )			// si A era la raiz
			raiz = b;					// la raiz es ahora B

		else 							// si A no era raiz
			if( a == a.padre.der )		// y si A era el hijo izq
				a.padre.der = b;		// actualizo que B es hijo Izq
			else						// y si A era el hijo der
				a.padre.izq = b;		// actualizo que B es hijo Der

		b.der = a;						// el hijo izq de B es A
		a.padre = b;					// el padre de A es B
	}

	/**
	 * Para lograr una impresion por pantalla mas o menos
	 * aceptable, traducimos el byte de decicion de color
	 * a una letra
	 * 
	 * @return "R" si la raiz es ROJA y "R" si la raiz es NEGRA
	 */
	public String Color2String()
	{
		if (raiz.color == Nodo.ROJO)
		{
			return "R";
		}
		else
		{
			return "N";
		}
	}

	/**
	 * Sobrecargamos toString para poder imprimir el arbol
	 * para hacer los test que creamos necesarios
	 * 
	 */
	@Override
	public String toString() {
		String res;
		res = "ARBOL RB: (";
		if(this.raiz != null)
		{
			if(raiz.izq != null){res += (raiz.izq).toString();}else { res += ".";}
			res += "[" + this.Color2String() + " " + raiz.dato + "]";
			if(raiz.der != null){res += (raiz.der).toString();}else { res += ".";}
		}
		res += ")";
		return res;
	}

	/**
	 * Simplifica la creacion de pruebas permitiendo la insercion
	 * de un array de valores a ingresar
	 * 
	 * @param datos array de datos a ingresar en el arbol
	 */
	public void insertarMuchos(Set<T> datos)
	{
		Iterator<T>  it = datos.iterator();
		while(it.hasNext())
		{
			insertar(it.next());
			System.out.println(this.toString());
		}
	}
	
}

