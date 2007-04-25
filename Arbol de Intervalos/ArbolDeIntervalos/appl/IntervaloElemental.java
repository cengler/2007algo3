package ArbolDeIntervalos.appl;

import java.util.ArrayList;
import java.util.List;


public class IntervaloElemental implements Comparable<Object> {

	public int numero;
	public List<Integer> indexOfImg= new ArrayList<Integer>();
	
	public IntervaloElemental(int numero) {
		this.numero = numero;
	}

	public int compareTo(Object intElem) {
		if (this.numero < ((IntervaloElemental)intElem).numero){
			return -1;
		}else if (this.numero > ((IntervaloElemental)intElem).numero){
			return 1;
		}else
			return 0;
	}

	@Override
	public String toString() {
		return String.valueOf(numero);
	}	
	
	
	
}