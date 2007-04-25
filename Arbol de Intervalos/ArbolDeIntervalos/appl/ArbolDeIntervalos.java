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
	{/*
		Nodo <IntervaloElemental> actual = this.getRaiz();
		int min = 0;
		int max = ANCHO_ARBOL;
		
		while( actual != null)
		{
			if((min <= actual.dato.numero) && (max <= actual.dato.numero)) 
			{
				actual.dato.indexOfImg.add(indexOf);
				actual = null;
			}else{
				if()
				{
					//hasta aqui llegamos!!
				}
			}
		}
	 */
	}
}
