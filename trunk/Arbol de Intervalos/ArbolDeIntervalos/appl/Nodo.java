package ArbolDeIntervalos.appl;


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
	public Nodo padre;
	
	/**
	 * Nodo inferior izquierdo
	 */
	public Nodo izq;
	
	/**
	 * Nodo inferior derecho
	 */
	public Nodo der;
	
	/**
	 * Dato de tipo T (Debe implementar comparable)
	 */
	public T dato;
	
	/**
	 * Color (Puede ser Rojo o Negro, ROJO = 1, Negro = 0 )
	 */
	public byte color;

	/**
	 * Indentificador de HOJA
	 */
	public byte esHoja;
	
	/**
	 * Constructor de Nodo (Hoja)
	 * 
	 * Construye un Nodo que es Hoja
	 */
	@SuppressWarnings("unchecked")
	protected Nodo()
	{ 
		dato = null;
		izq = null; 
		der = null;
		padre = null; 
		color = NEGRO;
		esHoja = 1;
	}
	
	/**
	 * Constructor de Nodo
	 * 
	 * @param dato recibe el dato a ingresar en el nodo
	 * @param datoHi Espera un dato vacio para setear a la hoja izq
	 * @param datoHd Espera un dato vacio para setear a la hoja der
	 */
	@SuppressWarnings("unchecked")
	protected Nodo(T dato, T datoHi, T datoHd)
	{ 
		this.dato = dato; 
		izq = new Nodo(); 
		der = new Nodo();
		padre = null; 
		color = ROJO;
		esHoja = 0;
		
		izq.padre = this;
		der.padre = this;
		izq.dato = datoHi;
		der.dato = datoHd;
	}

	/**
	 * Para lograr una impresion por pantalla mas o menos
	 * aceptable, traducimos el byte de decicion de color
	 * a una letra
	 * 
	 * @return "R" si la raiz es ROJA y "R" si la raiz es NEGRA
	 */
	private String Color2String() //tick
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
	public String toString()
	{
		String res;
		res = " (";
		if(this != null)
		{
			if(!this.esHoja())
			{
				res += (izq).toString();
				res += "[" + this.Color2String() + " " + dato + "]";
				res += (der).toString();
			}else{
				res += ".";
				res += "[" + this.Color2String() + " H " + dato + "]";
				res += ".";
			}
		}
		res += ") ";
		return res;
	}
	
	/**
	 * Nos dice cuando un nodo tiene dos punteros a null
	 * es decir que es nil (Hoja del Arbol Rojo y Negro)
	 * 
	 * @return True si el nodo es hoja, falso si no lo es
	 */
	protected boolean esHoja() //tick
	{
		return (this.esHoja == 1);
	}

}

