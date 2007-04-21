
public class ArbolRN<T extends Comparable<? super T>> {
	
	public Nodo raiz;
	public static final boolean ROJO = false;
	public static final boolean NEGRO = true;

	public ArbolRN(){
		raiz = null;
	}
	
	@SuppressWarnings("unchecked")
	public ArbolRN(T dato){
		raiz = new Nodo(dato);
		raiz.color = NEGRO;
	}
	
	@SuppressWarnings("unchecked")
	public ArbolRN(T dato, boolean color) {
		raiz = new Nodo(dato);
		raiz.color = NEGRO;
	}

	public boolean vacio()
	{
		return (this.raiz == null);
	}
	
	@SuppressWarnings("unchecked")
	public void insertarABB(T dato){
		if(vacio())
		{
			raiz = new Nodo(dato);
		}else
		{
			if(raiz.dato.compareTo(dato) == 1){
				if(raiz.izq == null)
				{
					ArbolRN<T> ar_temp = new ArbolRN(dato, ROJO);
					ar_temp.raiz.setearPadre(this);
					raiz.setearIzq(ar_temp);
					ar_temp = null;
				}else
				{
					raiz.izq.insertarABB(dato);
				}
			}else if(raiz.dato.compareTo(dato) == -1){
				if(raiz.der == null)
				{
					ArbolRN<T> ar_temp = new ArbolRN(dato, ROJO);
					ar_temp.raiz.setearPadre(this);
					raiz.setearDer(ar_temp);
					ar_temp = null;
				}else
				{
					raiz.der.insertarABB(dato);
				}
			}else
			{
				System.out.println("EL ELEMENTO YA ESTA");		
			}
		}
	}
	
	@Override
	public String toString() {
		String res;
		res = " <";
		if(!vacio())
		{
			res += (raiz.izq).toString();
			res += raiz.dato;
			res += (raiz.der).toString();
		}
		res += "> ";
		return res;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArbolRN<Integer> arbol = new ArbolRN<Integer>();
		arbol.insertarABB(20);
		arbol.insertarABB(10);
		arbol.insertarABB(30);
		arbol.insertarABB(5);
		arbol.insertarABB(15);
		arbol.insertarABB(25);
		arbol.insertarABB(35);
		System.out.println(arbol.toString());
		ArbolRN<Integer>arbol2 = arbol.raiz.izq.raiz.izq;
		System.out.println(arbol2.toString());
		System.out.println(arbol2.raiz.padre.toString());
	}

}
