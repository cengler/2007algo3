package ArbolDeIntervalos.appl;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
		boolean porX= true;
		
		for(int k=0; k<instancias.size(); k++)
		{
			ArbolDeIntervalos arbolX = new ArbolDeIntervalos();
			
    		System.out.println("Comenzando Construccion del arbol de la instancia " + k + " en " + System.currentTimeMillis());
			arbolX.llenarArbol(instancias.get(k), porX);
			System.out.println("Termino Construccion del arbol de la instancia " + k + " en " + System.currentTimeMillis());
			
			System.out.println("    Comenzando Busquedas en " + System.currentTimeMillis());
			for(int i=0; i<consultas.size(); i+=2)
			{
				List<Imagen> res = arbolX.busqueda(consultas.get(i), consultas.get(i+1), instancias.get(k), true);
				Parser.guardar(res, archivo_salida);
				Parser.imprimirSeparador(archivo_salida, porX);
			}
			System.out.println("    Finalizando Busquedas en " + System.currentTimeMillis());
			
			Parser.imprimirSeparador(archivo_salida, false);
		}
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
