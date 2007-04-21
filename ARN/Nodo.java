
public class Nodo<T extends Comparable<? super T>> {

	public T dato;
	public ArbolRN padre;
	public ArbolRN izq;
	public ArbolRN der;
	boolean color;
	
	public Nodo(T dato) {
		this.dato = dato;
		this.padre = null;
		this.izq = new ArbolRN<T>();
		this.der = new ArbolRN<T>();
		this.color = false;
	}
	
	public void setearIzq(ArbolRN ar)
	{
		this.izq = ar;
	} 
	
	public void setearDer(ArbolRN ar)
	{
		this.der = ar;
	} 
	
	public void setearPadre(ArbolRN ar)
	{
		this.padre = ar;
	} 
}