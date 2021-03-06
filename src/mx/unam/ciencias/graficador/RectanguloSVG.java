package mx.unam.ciencias.graficador;

/**
 * Clase que modela Rectangulos con medidas en enteros y puede imprimir en SVG
 */
public class RectanguloSVG extends DibujaSVG {
    
    private int altura;
    private int largo;
    private TextoSVG texto;
    private String color;

    /**
     * Constructor vacio por defecto inicializa el rectangulo en 0
     */
    public RectanguloSVG() {
	altura = 0;
	largo  = 0;
	centro = new Vector2D();
	color = "none";
    }

    /**
     * Constructor que inicaliza con un largo, ancho y un centro
     * @param largo	Largo del rectangulo
     * @param altura altura del rectangulo
     * @param centro centro del rectangulo
     */
    public RectanguloSVG(int largo,int altura,Vector2D centro) {
	this.largo  = largo;
	this.altura = altura;
	this.centro = centro;
	color =  "white";
	texto = new TextoSVG();
	
    }

    /**
     * Constructor que inicaliza con un largo, ancho , centro y un String
     * @param largo Largo del rectangulo
     * @param altura altura del rectangulo
     * @param centro centro del rectangulo
     * @param tex texto del rectangulo
     */
    public RectanguloSVG(int largo,int altura,Vector2D centro,String tex) {	   
	this.largo  = largo;
	this.altura = altura;
	this.centro = centro;             
	
	this.texto = tex.length()<7 ?  new TextoSVG(tex,altura/2,new Vector2D(centro.getX(),centro.getY()+altura/6)) :
	    new TextoSVG(tex,altura/4,new Vector2D(centro.getX(),centro.getY()+altura/8));
	//If line, para reescalar el texto dentro del rectangulo
	
	color = "white";
    }


    /**
     *  Constructor que inicaliza con un largo, ancho , centro,un String y un color
     * @param largo Largo del rectangulo
     * @param altura altura del rectangulo
     * @param centro centro del rectangulo
     * @param tex texto del rectangulo
     * @param color color del rectangulo
     */
    public RectanguloSVG(int largo,int altura,Vector2D centro,String tex,String color) {	   
	this.largo  = largo;
	this.altura = altura;
	this.centro = centro;             
	
	this.texto = tex.length()<7 ?  new TextoSVG(tex,altura/2,new Vector2D(centro.getX(),centro.getY()+altura/6),"white") :
	    new TextoSVG(tex,altura/4,new Vector2D(centro.getX(),centro.getY()+altura/8),"white");
	//If line, para reescalar el texto dentro del rectangulo
	
	this.color = color;
    }

    /**
     *Regresa el lado izquierdo del rectangulo
     * @return Vector2D lado izquierdo
     */
    @Override
    public Vector2D getIzquierdo(){ 
	int x1 = (centro.getX()-(largo/2));
    	return new Vector2D(x1,(centro.getY()));
    }
    
    
    /**
     *Regresa el lado derecho del rectangulo
     * @return Vector2D lado derecho
     */
    @Override
    public Vector2D getDerecho() {
	return new Vector2D((centro.getX()+(largo/2)),(centro.getY()));
	
    }

    /**
     * ImprimeSVG imprime en la salida estandard un SVG
     */
    @Override
    public void imprimeSVG(){ 
	int izq1 = getIzquierdo().getX();
	int izq2 = centro.getY()-(altura/2);
	int n;
	if(largo +altura < 400)
	    n = 1;
	else {if(largo+altura>1000){ //Vamos a darle una proporcionalidad a la linea 
		n = 3;
	    }else{ 
		n = 2;
	    }
	}
	System.out.println("<rect x='"+izq1+ "' y='"+izq2+ "' width='"+largo+"' height='"+altura+ 
			   "' fill='"+color+"' stroke='black' stroke-width='"+n+ "'/>");
	
	this.texto.imprimeSVG();
    }
    
    
    
}
