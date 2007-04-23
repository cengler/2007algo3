import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Parser para levantar de un fichero, todas las instancias de "imagenes"
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 */
public class Parser {
	
	/**
	 * Fichero de entrada
	 */
	private static Reader fichero= null;
	/**
	 * Buffer de Lectura del fichero de entrada
	 */
	private static BufferedReader bufferDeLectura = null;
	/**
	 * Cantidad de instancias del fichero de entrada
	 */
	private static int numeroDeInstancias;
	/**
	 * Ancho de la pantalla
	 */
	public static final int ANCHO = 1000;
	/**
	 * Alto de la pantalla
	 */
	public static final int ALTO = 1000;
	/**
	 * byte para referenciar "x Ancho"
	 */
	public static final byte xANCHO = 0;
	/**
	 * byte para referenciar "x Alto"
	 */
	public static final byte xALTO = 1;
	
	/**
	 * Parser para levantar de un fichero, todas las instancias de "imagenes"
	 * 
	 * @param fich Fichero donde es encuentran todas las instancias de entrada
	 * @return Lista de Lista que contiene para cada instancia, su secuencia de imagenes
	 */
	public static List getListaDeInstancias(String fich)
	{
		
		try {
			fichero= new FileReader(fich);
			bufferDeLectura = new BufferedReader(fichero);
			numeroDeInstancias = new Integer(bufferDeLectura.readLine());
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error en lectura");
			e.printStackTrace();
		} 
		
		
		List<List<Imagen>> instancias = new ArrayList<List<Imagen>>();
		
		try {		
			for(int j=0; j<numeroDeInstancias; j++)
			{
				List<Imagen> imagenes = new ArrayList<Imagen>();
				int numeroDeFotos = new Integer(bufferDeLectura.readLine());
				for(int k=0; k<numeroDeFotos; k++)
				{
					
					StringTokenizer st = new StringTokenizer(bufferDeLectura.readLine(), "\n \r");
					int x0_foto = new Integer(st.nextToken());
					int x1_foto = new Integer(st.nextToken());
					int y0_foto = new Integer(st.nextToken());
					int y1_foto = new Integer(st.nextToken());
					imagenes.add(new Imagen(x0_foto, x1_foto, y0_foto, y1_foto));
				}
				instancias.add(imagenes);
			}
		}catch(IOException e)
		{
			System.err.println("Error en lectura");
			e.printStackTrace();		
		}

		try {
			bufferDeLectura.close();
			fichero.close();
			
		} catch (IOException e) {
			System.err.println("Error al cerrar el buffer o el fichero de entrada");
			e.printStackTrace();
		}
		
		return instancias;
	}
	
	public static Set<IntervaloElemental> intervalosElementales(List<Imagen> imagenes, byte porAnchoOporLargo){
		
		 Set<IntervaloElemental> res = new TreeSet<IntervaloElemental>();
		
		if(porAnchoOporLargo == xANCHO)
		{
			for(int k=0; k< imagenes.size(); k++)
			{
				res.add(new IntervaloElemental(imagenes.get(k).x_0));
				res.add(new IntervaloElemental(imagenes.get(k).x_1));
			}
		}
		else
		{
			for(int k=0; k< imagenes.size(); k++)
			{
				res.add(new IntervaloElemental(imagenes.get(k).y_0));
				res.add(new IntervaloElemental(imagenes.get(k).y_1));
			}
		}
		
		return res;
	}
}