package ArbolDeIntervalos.appl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Desde aqui se llamaran todos los test
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 */
public class Main {

	/**
	 * Desde aqui se llamarán a todas las funciones.
	 * Punto de partida del programa
	 * 
	 * @param args no espera recibir argumentos
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		try{
			if( args[0].compareToIgnoreCase("test") == 0 )
			{
				testInsercion();
			}
			else if ( args[0].compareToIgnoreCase("levantar") == 0 )
			{
				List<List<Imagen>> instancias = Parser.getListaDeInstancias("Tp2Ej1.in");
				
				for(int k=0; k<instancias.size(); k++)
				{
					ArbolDeIntervalos arbolX= construirArbolX(instancias.get(k));
					System.out.println(arbolX.toString());
					k=1000;
				}
				
				
				
				
				
				
				
				
				
				
				/*
				Set<IntervaloElemental> imagenes = Parser.intervalosElementales(instancias.get(0), Parser.xANCHO);
				ArbolRN<IntervaloElemental> arbol_x= new ArbolRN<IntervaloElemental>();
				System.out.println(arbol_x.toString());
				arbol_x.insertarMuchos(imagenes);
				System.out.println(arbol_x);
				*/
			}
			else
			{
				System.out.println("Para correr el test escriba: test");
				System.out.println("Para correr el test escriba: levantar");
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Para ver opciones escriba: ver");
		}
		
		/*ArbolRN<Integer> arbol = new ArbolRN<Integer>();
		// Integer[] lista= {200, 100, 300, 50, 150, 250, 350, 10, 3, 1, 2, 3, 8, 79, 90, 110};
		Integer[] lista= {1, 2, 3};
		arbol.insertarMuchos(lista);
		System.out.println(arbol.toString());

		ArbolRN<Integer> arbol2 = new ArbolRN<Integer>();
		Integer[] lista2= {1, 2, 3, 4};
		arbol2.insertarMuchos(lista2);
		System.out.println(arbol2.toString());
		
		print(instancias = Parser.getListaDeInstancias("Tp2Ej1.in"));*/

	}
	
	/**
	 * Construye el arbol Red-Black de intervalos con todos los intervalos elementales
	 * 
	 * @param imagenes
	 * @return
	 */
	public static ArbolDeIntervalos construirArbolX( List<Imagen> imagenes)
	{
		Set<IntervaloElemental> puntosX= Parser.intervalosElementalesX(imagenes);
		ArbolDeIntervalos res= new ArbolDeIntervalos();
		res.insertarMuchos(puntosX);
		for(int i=0; i<imagenes.size(); i++)
		{
			System.out.println("aca");
			res.insertarImagen(i, imagenes.get(i));
		}
		return res;
	}
	
	/**
	 * Test de Insercion
	 */
	public static void testInsercion()
	{
		ArbolRN<Integer> arbol1 = new ArbolRN<Integer>();
		arbol1.insertar(10);
		System.out.println(arbol1);
		
		arbol1.insertar(20);
		System.out.println(arbol1);
		
		arbol1.insertar(15);
		System.out.println(arbol1);
		
		arbol1.insertar(30);
		System.out.println(arbol1);
		
		arbol1.insertar(40);
		System.out.println(arbol1);
	}
	
	/**
	 * Me imprime por pantalla una lista de instancias de imagenes
	 * 
	 * @param l Lista de instancias con imagenes que quiero imprimir
	 */
	public static void print(List<List<Imagen>> l)
	{
		System.out.print("IMAGENES: {");
		for (int k = 0; k< l.size(); k++)
		{
			System.out.print(" [");
			for (int j = 0; j< l.get(k).size(); j++)
				if (j == l.get(k).size()-1)
					System.out.print(l.get(k).get(j).toString() + ",");
			System.out.print("] ");
		}
		System.out.println("}");
	}
	
	/**
	 * 
	 * @param y
	 * @param indexOfFotosEnX
	 * @param imagenes
	 * @return
	 */
	public List<Imagen> buscarImagenesQCoinciden(Integer y, List<Integer> indexOfFotosEnX, List<Imagen> imagenes)
	{
		List<Imagen> res = new ArrayList<Imagen>();
		for(int j=0; j<indexOfFotosEnX.size(); j++)
		{
			int index_j = indexOfFotosEnX.get(j);
			if(imagenes.get(index_j).y_0 <= j && imagenes.get(index_j).y_1 >= j )
			{
				res.add(imagenes.get(index_j));
			}
		}
		return res;
	}
}
