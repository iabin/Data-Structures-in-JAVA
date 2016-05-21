package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase privada para iteradores de gráficas. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Grafica<T>.Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador() {
	    iterador = vertices.iteradorLista();
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
	    return iterador.next().getElemento();
        }

        /* No lo implementamos: siempre lanza una excepción. */
        @Override public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* Vertices para gráficas; implementan la interfaz VerticeGrafica */
    private class Vertice implements VerticeGrafica<T> {

        /* El elemento del vértice. */
        public T elemento;
        /* El color del vértice. */
        public Color color;
        /* Lista de vecinos. */
        public Lista<Grafica<T>.Vertice> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
	    this.elemento = elemento;
	    vecinos = new Lista<Grafica<T>.Vertice>();
	    this.color = Color.NINGUNO;
        }

        /* Regresa el elemento del vértice. */
        @Override public T getElemento() {
            return this.elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return vecinos.getLongitud();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return this.color;
        }

        /* Define el color del vértice. */
        @Override public void setColor(Color color) {
            this.color =  color;
        }

        /* Regresa un iterador para los vecinos. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vecinos;
        }
    }

    /* Vértices. */
    private Lista<Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
	vertices = new Lista<Vertice>();
	aristas = 0;
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    public int getElementos() {
        return vertices.getLongitud();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() {
        return aristas;
    }

    private Vertice verticeGrafica(T e){
	for(Vertice v : this.vertices){
	    if(v.elemento.equals(e))
		return v;
	} 
	return null;
	
    }

    
    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido agregado a
     *         la gráfica.
     */
    @Override public void agrega(T elemento) {
	if(verticeGrafica(elemento)!=null)
	    throw new IllegalArgumentException();
	Vertice vertice =  new Vertice(elemento);
	vertices.agrega(vertice);
        
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
	if(a.equals(b)||sonVecinos(a,b))
	    throw new IllegalArgumentException();
	Vertice bb =  verticeGrafica(a);
	Vertice aa = verticeGrafica(b);;

	if(aa==null||bb==null)
	    throw new NoSuchElementException();

	aa.vecinos.agrega(bb);
	bb.vecinos.agrega(aa);
	aristas++;
		}
    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
	if(!contiene(a)||!contiene(b))
	    throw new NoSuchElementException();
	if(!sonVecinos(a,b))
	    throw new IllegalArgumentException();
	
	verticeGrafica(a).vecinos.elimina(verticeGrafica(b));
	verticeGrafica(b).vecinos.elimina(verticeGrafica(a));
	aristas = (aristas-1);
	  
	   
        
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <tt>true</tt> si el elemento está contenido en la gráfica,
     *         <tt>false</tt> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
	return verticeGrafica(elemento)==null ? false:true;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
	if(!contiene(elemento))
	    throw new NoSuchElementException();
	Vertice v = verticeGrafica(elemento);
	for(Vertice c: v.vecinos) {
	    desconecta(v.elemento,c.elemento);
	}
	vertices.elimina(v);
      
       
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <tt>true</tt> si a y b son vecinos, <tt>false</tt> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
	if(!(contiene(a)&&contiene(b)))
	    throw new NoSuchElementException();
	Vertice aa = verticeGrafica(a);
	Vertice bb = verticeGrafica(b);

	for(Vertice v:aa.vecinos){
	    if(v.elemento.equals(bb.elemento))
		return true;
	}

	
	for(Vertice v:bb.vecinos){
	    if(v.elemento.equals(bb.elemento))
		return true;
	}
	return false;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
	if(verticeGrafica(elemento)==null)
	    throw new NoSuchElementException();
	    
	return verticeGrafica(elemento);
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
	for(Vertice v : vertices)
	    accion.actua(v);
        return ;
    }

     private void recorre(T elemento, AccionVerticeGrafica<T> accion, MeteSaca<Vertice> meteSaca) {
	 
	 if(verticeGrafica(elemento) == null)
	     throw new NoSuchElementException();
	 Vertice v = verticeGrafica(elemento);
	 meteSaca.mete(v);

	 
	 while(!meteSaca.esVacia()){
	     Vertice c = meteSaca.saca();
	     c.setColor(Color.ROJO);
	     accion.actua(c);
	    for(Vertice a:c.vecinos){
		if(a.color!=Color.ROJO) {
		    a.setColor(Color.ROJO);
		    meteSaca.mete(a);
		} 
	    } 
	}

	 
	 for(Vertice o : vertices){
	     o.setColor(Color.NINGUNO);
	}
     } 
        

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
	Cola<Vertice> cola = new Cola<>();
	recorre(elemento,accion,cola);	
	return;
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
	Pila<Vertice> pila = new Pila<>();
	recorre(elemento,accion,pila);	
        return ;
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacio() {
        if(vertices.getLongitud()==0)
	    return true;
	return false;
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }
}
