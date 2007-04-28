package ArbolDeIntervalos.appl;

import java.util.Set;

public class ArbolDeIntervalos {
	
	private ArbolRN<IntervaloElemental> arn = null;
	/**
	 * Este es el ancho del arbol de intervalos
	 */
	private final int ANCHO_ARBOL= Parser.ANCHO;
		
	public ArbolDeIntervalos()
	{
		arn = new ArbolRN<IntervaloElemental>();
	}
	
	public void insertar(IntervaloElemental ie)
	{
		arn.insertar(ie);
	}
	
	public void insertarMuchos(Set<IntervaloElemental> intervalos)
	{
		arn.insertarMuchos(intervalos);
	}
	
	@SuppressWarnings("unchecked")
	public Nodo<IntervaloElemental> getRaiz()
	{
		return arn.raiz;
	}
	
	public void insertarImagen(int indexOf, Imagen img)
	{
		//tenemos en img las coordenadas que vamos a meter en la imagen
		//Nombre de intervalo: indexOf
		insertarIntervalo(indexOf, img.x_0, img.x_1, 0, ANCHO_ARBOL, this.getRaiz());
		System.out.println(this.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void insertarIntervalo(int indexOf, int x_0, int x_1, int min, int max, Nodo <IntervaloElemental> actual)
	{
		//el intervalo que quiero insertar es justo el donde estoy
		if (( x_0 <= min) && ( max<= x_1))
		{
			//guardo la imagen 
			actual.dato.indexOfImg.add(indexOf);
		}else{
			//sino quiere decir que debo bajar por una de sus hojas
			//decido si bajo por una y/o por otra
				
			if(x_0 < actual.dato.numero)
			{
				if( x_1 > actual.dato.numero)
				{
					insertarIntervalo(indexOf, x_0, x_1, min, x_0, actual.izq);
				}else
				{
					insertarIntervalo(indexOf, x_0, x_1, min, actual.dato.numero, actual.izq);
				}
			}

			/*if(actual.dato.numero < x_1)
			{
				insertarIntervalo(indexOf, x_0, x_1, actual.dato.numero, max, actual.der);
			}*/
			
		}
		
	}

	@Override
	public String toString() {
		return this.arn.toString();
	}
	
	

}
