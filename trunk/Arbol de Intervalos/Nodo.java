/**
 * Noda para arbol "Rojo Y Negro"
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 * 
 * @param <T> Recibe cualquier tipo de elementos que implementen Comparable
 */
public class Nodo<T extends Comparable<? super T>>
{ 
	/**
	 * DEFINICION ROJO == 1
	 */
	public final static byte ROJO = 1;
	/**
	 * DEFINICION NEGRO == 0
	 */
	public final static byte NEGRO = 0;

	/**
	 * Nodo superior
	 */
	protected Nodo padre;
	/**
	 * Nodo inferior izquierdo
	 */
	protected Nodo izq;
	/**
	 * Nodo inferior derecho
	 */
	protected Nodo der;
	/**
	 * Dato de tipo T (Debe implementar comparable)
	 */
	protected T dato;
	/**
	 * Color (Puede ser Rojo o Negro, ROJO = 1, Negro = 0 )
	 */
	protected byte color;

	/**
	 * Constructor de Nodo
	 * 
	 * @param dato recibe el dato a ingresar en el nodo
	 */
	public Nodo(T dato)
	{ 
		this.dato = dato; 
		izq = null; 
		der = null;
		padre = null; 
		color = ROJO;				
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
		if (color == ROJO)
		{
			return "R";
		}
		else
		{
			return "N";
		}
	}

	/**
	 * Sobrecargamos toString para poder imprimir el nodo
	 * para hacer los test que creamos necesarios
	 * 
	 */
	@Override
	public String toString() {
		String res;
		res = " (";
		if(this != null)
		{
			if(izq != null){res += (izq).toString();}else { res += ".";}
			res += "[" + this.Color2String() + " " + dato + "]";
			if(der != null){res += (der).toString();}else { res += ".";}
		}
		res += ") ";
		return res;
	}
}

