package mx.unam.ciencias.graficador;

/**
 * Clase Flecha, Genera codigo en SVG para dibujar una flecha
 */
public class Flecha extends LineaSVG{

	/**
	 * Constructor genera una flecha con 2 extremos
	 * @param izquierda	extremo izquierdo
	 * @param derecha	extremo derecho
     */
	public Flecha(Vector2D izquierda, Vector2D derecha){
		super(izquierda,derecha);
	}


	/**
	 * Metodo que genera el codigo SVG de la flecha actual
	 */
	@Override
		public void imprimeSVG(){ 

			super.imprimeSVG();
			int x1,x2,x3,y1,y2,y3,x4,y4,x5,y5,x6,y6;
			x1 = izquierdo.getX();
			y1 = izquierdo.getY();
			x3 = x2 = izquierdo.getX()+7;
			y2 = izquierdo.getY()-7;
			y3 = izquierdo.getY()+7;


			x4 = derecho.getX()	;
			y4 = derecho.getY();
			x5 = x6 = derecho.getX()-7;
			y5 = derecho.getY()-7;
			y6 = derecho.getY()+7;

			System.out.println("<polygon points='" +x1+ "," +y1+ " " +x2+ "," +y2+ " " +x3+ "," +y3+ "' stroke='" +color+ "' stroke-width='0' fill='" +color+ "'/>");
			System.out.println("<polygon points='" +x4+ "," +y4+ " " +x5+ "," +y5+ " " +x6+ "," +y6+ "' stroke='" +color+ "' stroke-width='0' fill='" +color+ "'/>");
		
	} 

 }
