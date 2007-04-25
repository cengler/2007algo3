package ArbolDeIntervalos.appl;

/**
 * Descriptor de Imagen por sus coordenadas
 * 
 * @author Grupo 16 - Algo3 - 2007
 * 
 * @version 1.0
 */
public class Imagen {

	/**
	 * cordenada x0 de la imagen
	 * x_0 <= x_1
	 */
	int x_0;
	
	/**
	 * cordenada x1 de la imagen
	 * x_0 <= x_1
	 */
	int x_1;
	
	/**
	 * cordenada y0 de la imagen
	 * y_0 <= y_1
	 */
	int y_0;
	
	/**
	 * cordenada y1 de la imagen
	 * y_0 <= y_1
	 */
	int y_1;
	
	/**
	 * Constructor
	 */
	public Imagen(int x_0, int x_1, int y_0, int y_1) {
		this.x_0 = x_0;
		this.x_1 = x_1;
		this.y_0 = y_0;
		this.y_1 = y_1;
	}

	/**
	 * Sobrecargamos toString para poder imprimirlo
	 */
	@Override
	public String toString() {
		String res;
		res = "(" + x_0 + " " + x_1 + ", " + y_0 + " " + y_1 + ")";
		return res;
	}
}
