package ArbolDeIntervalos.appl;

import java.util.ArrayList;
import java.util.List;


public class IntervaloElemental implements Comparable<Object> {

	public int numero;
	public List<Integer> indexOfImg= new ArrayList<Integer>();
	
	public IntervaloElemental(int numero) {
		this.numero = numero;
	}

	public IntervaloElemental() {}

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
		String res;
		if( numero != -1)
		{
			res = String.valueOf(numero) + "[ ";
			for(int i=0; i<indexOfImg.size(); i++)
			{
				res += indexOfImg.get(i).toString() + " ";
			}
			res += "]";
		}
		else
		{
			res = "[ ";
			for(int i=0; i<indexOfImg.size(); i++)
			{
				res += indexOfImg.get(i).toString() + " ";
			}
			res += "]";	
		}
		return res;
	}	
	
	
	
}