package ArbolDeIntervalos.appl;

import java.util.ArrayList;
import java.util.List;
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

	@SuppressWarnings("unchecked")
	public List<Imagen> busqueda(int x, int y, List<Imagen> Imagenes_levantadas)
	{
		//creo la lista q voy a devolver
		List<Imagen> res= new ArrayList<Imagen>();
		
		Nodo<IntervaloElemental> actual = this.getRaiz();
		//referencia a la raiz
		
		//Vamos a recorrer el arbol de intervalos para obtener X
		
		//EsHoja hay q hacerlo
		while(!actual.esHoja())
		{
			//Sigo el camino q correspone
			if( x <= actual.dato.numero)
			{
				actual = actual.izq;				
			}else{
			//  actual.dato.numero < x  		
				actual = actual.der;				
			}
		}
		
		//Joya llege a la Hoja, ahora busco en su lista a Y
		List<Integer> Lista= new ArrayList<Integer>();
		Lista = actual.dato.indexOfImg;
		
		for(int i= 0; i< Lista.size(); i++)
		{
			int index = Lista.get(i);
			int pic_y_0 = Imagenes_levantadas.get(index).y_0;
			int pic_y_1	= Imagenes_levantadas.get(index).y_1;
			
			if ((pic_y_0 <= y) && (y <= pic_y_1))
			{
				//agrego el intervalo a la solucion
				res.add(Imagenes_levantadas.get(index));				
			}
			//Lo hice asi para q se entienda lo mas facil posible
			
			//Igual buscar en esta lista habria que mantenerla ordenada 
			//o algo para mejorar la busqueda
		}
		
		return res;		
	}

	@Override
	public String toString() {
		return this.arn.toString();
	}
	
	

}
