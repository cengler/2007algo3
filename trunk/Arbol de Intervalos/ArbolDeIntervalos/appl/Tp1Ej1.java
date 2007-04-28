package ArbolDeIntervalos.appl;
import java.util.List;
import java.util.Set;

/**
 * Desde aqui se llamaran todos los test
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 */
public class Tp1Ej1 {

	private static final String NOMBRE_ARCHIVO = "Tp2Ej1.in";
	private static final String ARCHIVO_SALIDA = "Tp2Ej1.out";
	
	/**
	 * Desde aqui se llamarán a todas las funciones.
	 * Punto de partida del programa
	 * 
	 * @param args no espera recibir argumentos
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		if (args.length == 0)
		{
			System.out.println("Para correr el test escriba: test");
			System.out.println("Para correr el test escriba: \"Tp2Ej1.in\" ");	
			return;
		}
		else if( args[0].compareToIgnoreCase("test") == 0 )
		{ // para realizar las pruebas que querramos durante el desarrollo
			testInsercion();
		}
		else if( args[0].compareToIgnoreCase(NOMBRE_ARCHIVO) == 0 )
		{
			List<List<Imagen>> instancias = Parser.getListaDeInstancias(NOMBRE_ARCHIVO);
			
			if(Parser.numeroDeInstancias + 1 == args.length)
			{
				for(int k=0; k<instancias.size(); k++)
				{
					ArbolDeIntervalos arbolX= construirArbolX(instancias.get(k));
					List<Imagen> res = arbolX.busqueda(Integer.valueOf(args[k+1]).intValue(), Integer.valueOf(args[k+2]).intValue(), instancias.get(k));
					Parser.guardar(res, ARCHIVO_SALIDA);
				}
			}
			else{
				System.out.println("Fueron levantadas " + Parser.numeroDeInstancias + " instancias.");
				System.out.println("Los parametros fueron incorrectos \n");
				System.out.println("El formato correcto es: ");
				System.out.println("    >java Tp2Ej1.in <x1> <y1> <x2> <y2> ... <xn> <yn>");
				System.out.println("Con n la cantidad de instancias");
			}
		}
	}
	

	/**
	 * Construye el arbol Red-Black de intervalos con todos los intervalos elementales
	 * con las fotos ingrasadas, listo para que se realicen las busquedas
	 * 
	 * @param imagenes Imagenes que se encuentran en la pantalla
	 * @return arbol de intervalos preparado para la busqueda
	 */
	private static ArbolDeIntervalos construirArbolX( List<Imagen> imagenes)
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
	private static void testInsercion()
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
	@SuppressWarnings("unused")
	private static void print(List<List<Imagen>> l)
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
}
