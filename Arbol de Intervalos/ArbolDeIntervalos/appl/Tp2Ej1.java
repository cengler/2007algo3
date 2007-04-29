package ArbolDeIntervalos.appl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Desde aqui se llamaran todos los test
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 */
public class Tp2Ej1 {

	private static String archivo_inst = "Tp2Ej1.in";
	private static String archivo_consultas = "Tp2Ej4.in";
	private static String archivo_salida = "Tp2Ej1.out";
	private static List<Integer> consultas = new ArrayList<Integer>();
	
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
			System.out.println("Corriendo Programa con archivos por defecto");
			
			System.out.println("Para correr el test escriba: test");
			System.out.println("***********");
			consultas = Parser.consultasArchivo(archivo_consultas);
			consultas();
			return;
		}
		
		else if( args[0].compareToIgnoreCase("test") == 0 )
		{ // para realizar las pruebas que querramos durante el desarrollo
			testInsercion();
		}
		
		else if( args[0].compareToIgnoreCase("in_in_out") == 0 )
		{
			try {
				archivo_inst = args[1];
				archivo_consultas = args[2];
				archivo_salida = args[3];
				consultas = Parser.consultasArchivo(archivo_consultas);
				consultas();
			} catch (ArrayIndexOutOfBoundsException e) {
				parametrosIncorrectos();
			}			
		}
		
		else if( args[0].compareToIgnoreCase("in_consultas_out") == 0 )
		{
			try {
				archivo_inst = args[1];
				for(int i=2; i<(args.length - 1); i++)
				{
					consultas.add(new Integer(args[i]));
				}
				archivo_salida = args[args.length-1];
				consultas();
			} catch (ArrayIndexOutOfBoundsException e) {
				parametrosIncorrectos();	
			}
		}else
		{
			parametrosIncorrectos();
		}
	}		
	
	public static void parametrosIncorrectos()
	{
		System.out.println("Parametros incorrectos");
		System.out.println("Para correr el programa con los archivos por defecto," +
		" no escriba parametros");
		System.out.println("Para correr el test escriba: test");
		System.out.println("***********");	
	}
				
	public static void consultas()
	{	
		List<List<Imagen>> instancias = Parser.getListaDeInstancias(archivo_inst);
		Parser.vaciarArchivo(archivo_salida);
		
		for(int k=0; k<instancias.size(); k++)
		{
			ArbolDeIntervalos arbolX= construirArbolX(instancias.get(k));
			for(int i=0; i<consultas.size(); i+=2)
			{
				List<Imagen> res = arbolX.busqueda(consultas.get(i), consultas.get(i+1), instancias.get(k));
				Parser.guardar(res, archivo_salida);
			}
			Parser.imprimirCero(archivo_salida);
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
		
		Iterator<IntervaloElemental>  it = puntosX.iterator();
		while(it.hasNext())
		{
			res.insertar(it.next());
		}
											//res.insertarMuchos(puntosX);
		
		for(int i=0; i<imagenes.size(); i++)
		{
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
		arbol1.insertar(10, null, null);
		System.out.println(arbol1);
		
		arbol1.insertar(20, null, null);
		System.out.println(arbol1);
		
		arbol1.insertar(15, null, null);
		System.out.println(arbol1);
		
		arbol1.insertar(30, null, null);
		System.out.println(arbol1);
		
		arbol1.insertar(40, null, null);
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
