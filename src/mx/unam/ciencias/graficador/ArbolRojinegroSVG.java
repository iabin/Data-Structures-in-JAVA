package mx.unam.ciencias.graficador;
import mx.unam.ciencias.edd.*;
/**
 * Clase que permite dibujar estructas de tipo
 *ArbolRojinegr
 */
public class ArbolRojinegroSVG implements ImprimeSVG{
    /** Atributo Interno*/
    ArbolRojinegro<Integer> arbol;
    
    /**
     * Constructor único que recibe un elemento.
     * @param arbol El arbolRojinegro que se desea imprimir
     */
    public ArbolRojinegroSVG(ArbolRojinegro<Integer> arbol){
	this.arbol = arbol;
    } 
    

    /**
     * Metodo que SobreEscribe imprimeSVG de
     * la interfaz ImprimeSVG
     */
    @Override
    public void imprimeSVG(){
	VerticeArbolBinario<Integer> raiz = arbol.raiz();
	int f = (int) (Math.pow(2, arbol.profundidad()) * 90);
	imprimeRecursivo(new Vector2D(f/2,80),arbol.profundidad(),raiz);
  	
    }
    
    
    /**Metodo privado Para permitir la recursion e inpresion del arbol*/
    private void imprimeRecursivo(Vector2D centro,int n,VerticeArbolBinario<Integer> vertice){
	ArbolRojinegro<Integer> arbol = new ArbolRojinegro<>();
	Color color = arbol.getColor(vertice);
	CirculoSVG raiz = new CirculoSVG(25,centro,vertice.get().toString(),color);

	int k = (int)(Math.pow(2,n)*(20));
	int minimo = centro.getX() +k;
	int minimo2 = centro.getX() - k;
	
	
	if(minimo-minimo2<50){ 
	    minimo = centro.getX() + 30;
	    minimo2 = centro.getX() -30;
	}

	if(vertice.hayDerecho()){ 
	    int i = n-1;
	    VerticeArbolBinario<Integer> derecho = vertice.getDerecho();
	    Vector2D centroDerecho = new Vector2D(minimo,centro.getY()+150);
	    LineaSVG linea = new LineaSVG(centro,centroDerecho);
	    linea.imprimeSVG();
	    imprimeRecursivo(centroDerecho,i,derecho);
	    
	}
	
	if(vertice.hayIzquierdo()) {
	    int j = n-1;
	    VerticeArbolBinario<Integer> izquierdo = vertice.getIzquierdo();
	    Vector2D centroIzquierdo = new Vector2D(minimo2,centro.getY()+150);
	    LineaSVG linea = new LineaSVG(centro,centroIzquierdo);
	    linea.imprimeSVG();
	    imprimeRecursivo(centroIzquierdo,j,izquierdo);
	}
	
	
	raiz.imprimeSVG();
    }
}
