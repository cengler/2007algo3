package ArbolDeIntervalos.appl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
		arn.insertar(ie, new IntervaloElemental(-1), new IntervaloElemental(-1));
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
		//System.out.println(this.toString());
	}
	
	@SuppressWarnings("unchecked")
	public void insertarIntervalo(int indexOf, int x_0, int x_1, int min, int max, Nodo <IntervaloElemental> actual)
	{
		//el intervalo que quiero insertar es justo el donde estoy
		if (( x_0 == min) && ( max == x_1))
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
					insertarIntervalo(indexOf, x_0, actual.dato.numero, min, actual.dato.numero, actual.izq); 
				}else 
				{ 
					insertarIntervalo(indexOf, x_0, x_1, min, actual.dato.numero, actual.izq); 
				} 
			} 

			if(x_1 > actual.dato.numero) 
			{ 
				if( x_0 < actual.dato.numero) 
				{ 
					insertarIntervalo(indexOf, actual.dato.numero, x_1, actual.dato.numero, max, actual.der); 
				}else 
				{ 
					insertarIntervalo(indexOf, x_0, x_1, actual.dato.numero, max, actual.der); 
				} 
			}

		}

	}

	@SuppressWarnings("unchecked")
	private void buscarIndicesX(int x, Nodo<IntervaloElemental> actual, Set<Integer> res)
	{	
		if(actual != null)
		{
			for(int i=0; i<actual.dato.indexOfImg.size(); i++)
			{
				res.add(actual.dato.indexOfImg.get(i));
			}

			if( x <= actual.dato.numero)
			{	
				buscarIndicesX(x, actual.izq, res);	
			}
			
			if( x >= actual.dato.numero)
			{		
				buscarIndicesX(x, actual.der, res);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Imagen> busqueda(int x, int y, List<Imagen> Imagenes_levantadas)
	{
		List<Imagen> res = new ArrayList<Imagen>();
		
		Set<Integer> resX = new TreeSet<Integer>();			// creo un conjunto
		
		buscarIndicesX(x, this.getRaiz(), resX);
		
		Iterator<Integer> it = resX.iterator();
		
		while(it.hasNext())
		{
			Integer index = it.next();
			int pic_y_0 = Imagenes_levantadas.get(index).y_0;
			int pic_y_1	= Imagenes_levantadas.get(index).y_1;
			
			if ((pic_y_0 <= y) && (y <= pic_y_1))
			{
				res.add(Imagenes_levantadas.get(index));
			}
		}
		return res;		
	}

	@Override
	public String toString() {
		return this.arn.toString();
	}
	
	

}
