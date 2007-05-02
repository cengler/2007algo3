package ArbolDeIntervalos.appl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ArbolDeIntervalos {
	
	/**
	 * Este es el ancho del arbol de intervalos
	*/
	private ArbolRN<IntervaloElemental> arn = null;

	private int ANCHO_ARBOL= 0;
	
	private List<Boolean> estanImagenes = new ArrayList<Boolean>();
	
	public ArbolDeIntervalos()
	{
		arn = new ArbolRN<IntervaloElemental>();
	}	
	
	public void llenarArbol(List<Imagen> imagenes, boolean X)
	{
		prepararArregloEstanImagenes(imagenes.size());
		llenarIntervalosElementales(imagenes, X);
		insertarImagenes(imagenes, X);
	}
	
	private void prepararArregloEstanImagenes(int cantImagenes)
	{
		for(int i=0; i<cantImagenes; i++)
		{
			estanImagenes.add(new Boolean(false));
		}
	}	

	private void llenarIntervalosElementales(List<Imagen> imagenes, boolean X)
	{
		Set<IntervaloElemental> puntos= intervalosElementales(imagenes, X);

		Iterator<IntervaloElemental>  it = puntos.iterator();
		while(it.hasNext())
		{
			insertar(it.next());
		}
	}	
	
	private Set<IntervaloElemental> intervalosElementales(List<Imagen> imagenes, boolean X){

		Set<IntervaloElemental> res = new TreeSet<IntervaloElemental>();
		if(X)
		{
			for(int k=0; k< imagenes.size(); k++)
			{
				res.add(new IntervaloElemental(imagenes.get(k).x_0));
				res.add(new IntervaloElemental(imagenes.get(k).x_1));
				if(imagenes.get(k).x_1 > ANCHO_ARBOL)
				{
					ANCHO_ARBOL = imagenes.get(k).x_1;
				}
			}
		}
		else
		{
			for(int k=0; k< imagenes.size(); k++)
			{
				res.add(new IntervaloElemental(imagenes.get(k).y_0));
				res.add(new IntervaloElemental(imagenes.get(k).y_1));
				if(imagenes.get(k).y_1 > ANCHO_ARBOL)
				{
					ANCHO_ARBOL = imagenes.get(k).y_1;
				}
			}
		}
		return res;
	}

	private void insertar(IntervaloElemental ie)
	{
		arn.insertar(ie, new IntervaloElemental(-1), new IntervaloElemental(-1));
	}	
	
	private void insertarImagenes(List<Imagen> imagenes, boolean X)
	{
		if(X)
		{
			for(int i=0; i<imagenes.size(); i++)
			{
				Imagen img = imagenes.get(i);
				insertarImagen(i, img.x_0, img.x_1, 0, ANCHO_ARBOL, getRaiz());
			}
		}else
		{
			for(int i=0; i<imagenes.size(); i++)
			{
				Imagen img = imagenes.get(i);
				insertarImagen(i, img.y_0, img.y_1, 0, ANCHO_ARBOL, getRaiz());
			}			
		}

	}
	
	@SuppressWarnings("unchecked")
	private void insertarImagen(int indexOf, int inicio, int finaL, int min, int max, Nodo <IntervaloElemental> actual)
	{
		//el intervalo que quiero insertar es justo el donde estoy
		if (( inicio == min) && ( max == finaL))
		{
			//guardo la imagen 
			actual.dato.indexOfImg.add(indexOf);
		}else{
			//sino quiere decir que debo bajar por una de sus hojas
			//decido si bajo por una y/o por otra

			if(inicio < actual.dato.numero) 
			{ 
				if( finaL > actual.dato.numero) 
				{ 
					insertarImagen(indexOf, inicio, actual.dato.numero, min, actual.dato.numero, actual.izq); 
				}else 
				{ 
					insertarImagen(indexOf, inicio, finaL, min, actual.dato.numero, actual.izq); 
				} 
			} 

			if(finaL > actual.dato.numero) 
			{ 
				if( inicio < actual.dato.numero) 
				{ 
					insertarImagen(indexOf, actual.dato.numero, finaL, actual.dato.numero, max, actual.der); 
				}else 
				{ 
					insertarImagen(indexOf, inicio, finaL, actual.dato.numero, max, actual.der); 
				} 
			}
		}
	}	

	@SuppressWarnings("unchecked")
	private Nodo<IntervaloElemental> getRaiz()
	{
		return arn.raiz;
	}
	
	@SuppressWarnings("unchecked")
	public List<Imagen> busqueda(int x, int y, List<Imagen> Imagenes_levantadas, boolean X)
	{
		List<Integer> resIndices = new ArrayList<Integer>();
		List<Imagen> res = new ArrayList<Imagen>();
		
		if(X)
		{
			buscarIndices(x, this.getRaiz(), resIndices);
			for(int j=0; j<resIndices.size(); j++)
			{
				int indice = resIndices.get(j);
				int pic0 = Imagenes_levantadas.get(indice).y_0;
				int pic1 = Imagenes_levantadas.get(indice).y_1;
				
				if ((pic0 <= y) && (y <= pic1))
				{
					res.add(Imagenes_levantadas.get(indice));
				}
				estanImagenes.set(indice, new Boolean(false));
			}
		}
		else
		{
			buscarIndices(y, this.getRaiz(), resIndices);
			for(int i=0; i<resIndices.size(); i++)
			{
				int indice = resIndices.get(i);
				int pic0 = Imagenes_levantadas.get(indice).x_0;
				int pic1 = Imagenes_levantadas.get(indice).x_1;
				
				if ((pic0 <= x) && (x <= pic1))
				{
					res.add(Imagenes_levantadas.get(indice));
				}
				estanImagenes.set(indice, new Boolean(false));
			}
		}
		return res;		
	}

	@SuppressWarnings("unchecked")
	private void buscarIndices(int valor, Nodo<IntervaloElemental> actual, List<Integer> res)
	{	
		if(actual != null)
		{
			for(int i=0; i<actual.dato.indexOfImg.size(); i++)
			{
				int numeroDeImagen = actual.dato.indexOfImg.get(i);
				if(!estanImagenes.get(numeroDeImagen))
				{
					estanImagenes.set(numeroDeImagen, new Boolean(true));
					res.add(actual.dato.indexOfImg.get(i));
				}
			}

			if( valor <= actual.dato.numero)
			{	
				buscarIndices(valor, actual.izq, res);	
			}
			
			if( valor >= actual.dato.numero)
			{		
				buscarIndices(valor, actual.der, res);
			}
		}
	}	
	
	@Override
	public String toString() {
		return this.arn.toString();
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	

}
