package ArbolDeIntervalos.appl;

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
	public static int numeroDeInstancias;
	/**
	 * Ancho de la pantalla
	 */
	public static final int ANCHO = 100;
	/**
	 * Alto de la pantalla
	 */
	public static final int ALTO = 100;
	
	/**
	 * Parser para levantar de un fichero, todas las instancias de "imagenes"
	 * 
	 * @param fich Fichero donde es encuentran todas las instancias de entrada
	 * @return Lista de Lista que contiene para cada instancia, su secuencia de imagenes
	 */
	public static List<List<Imagen>> getListaDeInstancias(String fich)
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
	
	/**
	 * Consigue todos los inicios y comienzos de los intervalos elementales
	 * 
	 * @param imagenes Lista de imagenes de los que quiero los intervalos elementales
	 * @return devuelve un conjunto con todos los inicios y comienzos de los intervalos elementales
	 */
	public static Set<IntervaloElemental> intervalosElementalesX(List<Imagen> imagenes){

		Set<IntervaloElemental> res = new TreeSet<IntervaloElemental>();

		for(int k=0; k< imagenes.size(); k++)
		{
			res.add(new IntervaloElemental(imagenes.get(k).x_0));
			res.add(new IntervaloElemental(imagenes.get(k).x_1));
		}

		return res;
	}

	public static void guardar(List<Imagen> res, String ficheroSalida) {
		
		//System.out.println(res.size());
		
		FileWriter salida = null;
		BufferedWriter salida_buff = null;
		try {
			salida = new FileWriter(ficheroSalida, true);
			salida_buff = new BufferedWriter(salida);
		} catch (IOException e) {
			System.err.println("No se pudo crear " + ficheroSalida);
			e.printStackTrace();
		}

		for (int k = 0; k< res.size(); k++)
		{
			try {
				salida_buff.write(res.get(k).toString());
				salida_buff.newLine();
			} catch (IOException e) {
				System.err.println("No se pudo escribir en " + ficheroSalida);
				e.printStackTrace();
			}
		}
		try {
			salida_buff.close();
			salida.close();
		} catch (IOException e) {
			System.err.println("No se pudo cerrar el archivo " + ficheroSalida);
			e.printStackTrace();
		}
	}

	public static List<Integer> consultasArchivo(String archivo_consultas) {

		Integer numeroDeConsultas = 0;

		try {
			fichero= new FileReader(archivo_consultas);
			bufferDeLectura = new BufferedReader(fichero);
			numeroDeConsultas = new Integer(bufferDeLectura.readLine());
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println("Archivo no encontrado");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error en lectura");
			e.printStackTrace();
		} 

		List<Integer> res = new ArrayList<Integer>();

		try {		
			for(int j=0; j<numeroDeConsultas; j++)
			{

				StringTokenizer st = new StringTokenizer(bufferDeLectura.readLine(), "\n \r");
				int x = new Integer(st.nextToken());
				int y = new Integer(st.nextToken());
				res.add(x);
				res.add(y);
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

		return res;
	}

	public static void vaciarArchivo(String archivo_salida) {
		try {
			FileWriter fichero= new FileWriter(archivo_salida);
			fichero.close();
		} catch (Exception e) {
			System.out.println("Problemas al vaciar el archivo");
			e.printStackTrace();
		}
		
	}
	
	public static void imprimirCero(String archivo_salida) {
		try {
			FileWriter fichero= new FileWriter(archivo_salida, true);
			BufferedWriter salida_buff = new BufferedWriter(fichero);
			salida_buff.write("0");
			salida_buff.newLine();
			salida_buff.close();
			fichero.close();
		} catch (Exception e) {
			System.out.println("Problemas al imprimir 0");
			e.printStackTrace();
		}
		
	}
}