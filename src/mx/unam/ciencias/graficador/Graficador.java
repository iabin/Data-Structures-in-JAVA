package mx.unam.ciencias.graficador;
import mx.unam.ciencias.edd.*;
import java.io.*;

/**
 * Main del programa, Graficador, ingresas una estructura de datos y su contenido
 * en la segunda linea y por la salida standard sacara una representacion de el en SVG
 *
 */
public class Graficador {

    /**
     * Main del programa
     * @param args String[] arreglo de string de la entrada
     */
    public static void main(String[] args) {
    	final String fin = "</svg>";
    	int i = 0;
    	BufferedReader br;
	String[] estructuras = {"Lista",
				"ArbolRojinegro",
				"ArbolAVL","ArbolBinarioCompleto","ArbolBinarioOrdenado",
				"Pila","Cola","Grafica","MonticuloMinimo","ArregloRojinegro"};
	
	
        if (args.length == 0){ //Intento leer una entrada de archivo
	    
            br = new BufferedReader(new InputStreamReader(System.in));
	} else{ 
	    try{ 
		br = new BufferedReader(new FileReader(args[0]));
	    }catch(IOException e) {
        	System.err.println("ERROR DE LECTURA DEL ARCHIVO");
        	return;
	    }
	} 
	
    	String texto,texto2,texto3;
    	texto = texto2 = texto3= " ";
    	try{ 
	    texto = br.readLine();
	    texto = texto.trim();
	    if(texto.equals("")){  //SI NO METO NADA EN LA PRIMERA LINEA
	    	System.err.println("INGRESA UNA CLASE A GRAFICAR");
	    	return;
	    }
	    
	}catch(IOException e){ 
	    System.err.println("ERROR DE LECTURA DE LA PRIMERA LINEA");
	    return;
	}
	
	
	if(texto.charAt(0)!='#'){ //ELIMINO El # SOLO SI HAY SI NO ME SALGO
	    try{ 
    	  	br.close();
		System.err.println("EL PRIMER CARACTER DEBE SER #");
		return;
	    }catch(IOException e){ 
    		System.err.println("ERROR DE LECTURA DE LA PRIMERA LINEA");
		return;
	    }
	    
	    
	}else { 
	    texto = texto.replace('#',' '); //LO REMPLAZO
	    texto = texto.trim();
	    
	    for(String es:estructuras){
		if(es.equals(texto)){ 
		    i = -1;
		    break;
		}
	    }
	    
	    if(i==-1) {	//SI TODO FUE BIEN Y LA PRIMERA LINEA PASA EL TEST AVANZAMOS
		try{
		    
		    texto2 = br.readLine();
		    
		    
		    
		} catch(IOException e){ 
		    System.err.println("ERROR EN LA LECTURA DE LA SEGUNDA LINEA");
		}
	    }else {
		System.err.println("ESTRUCTURA NO VALIDA");
		return;
	    }
	    if(texto2.length()==0) {
		System.err.println("INGRESE NUMEROS DE LA FORMA '1,2,...,n'");
		return;
	    }
	    
	    for(int y = 0;y<texto2.length()-1;y++){ 
		if(texto2.charAt(y)==','&&texto2.charAt(y+1)==','){ 
		    System.err.println("ERROR EN LA ENTRADA DE ELEMENTOS, \n INGRESE NUMEROS DE LA FORMA '1,2,...,n' ");
		    return;
		}
		
	    }
	    
	    
	    if(!texto.equals("Grafica")) { //CASO DE LA 3RA LINEA SOLO SE LEERA SI SE PIDE GRAFICA
		try{ 
		    br.close();
		}catch(IOException e){ System.err.println("ERROR EN EL MANEJO DEL BUFFER");
		    return;
		}
	    }else { 
		try{ 
		    texto3 = br.readLine();
		    
		    
		    br.close();
		    
		    
		    
		    
		}catch(IOException e){ 
		    System.err.println("ERROR EN LA ENTRADA DE RELACIONES");
		    return;
		}
		
		

		if(texto3.length()==0) {
		    System.err.println("DEBE INGRESAR UNA RELACION");
		    return;
	    	}

	    } 

	    
	    
	    String[] numeros = texto2.split(",");
	    Integer[] enteros = new Integer[numeros.length]; //SEPARO POR ENTEROS LA PRIMERA LINEA
	    int ayu = 0;
	    for(int ss = 0;ss<numeros.length;ss++) {
		try{
		    String g = numeros[ss];	
		    Integer num = Integer.parseInt(g);
		    enteros[ss] = num;
			    
		}catch(NumberFormatException e) {
		    System.err.println("INGRESE NUMEROS DE LA FORMA '1,2,...,n'");
		    return;
		}
											     
	    }


	    

	    EstructurasSVG<Integer> imprimidor = new EstructurasSVG<>(); //DECLARO UNA NUEVA ESTRUCTURA
	    
	    
	    if(texto.equals("Lista")){ 	//LISTA
		
		Lista<Integer> lista = new Lista<>();
		for(Integer e : enteros){ 
		    
		    lista.agrega(e);
		}
		int n = lista.getLongitud()*120;
		System.out.println("<svg width='"+n+ "' height='40'>");
		imprimidor.listaSVG(lista);
		System.out.println(fin);
	    }
	    
	    if(texto.equals("Pila")||texto.equals("Cola")){  	//METESACA
		
		MeteSaca<Integer> meteSaca;
		if(texto.equals("Pila")){ 
		    meteSaca = new Pila<>();
		}else{ 
		    meteSaca = new Cola<>();
		}
		
		
		int tam = enteros.length;
		for(Integer e : enteros){ 
		    meteSaca.mete(e);
		}
			
		int n = tam*25;
		System.out.println("<svg width='250' height='"+ n +"'>");
		imprimidor.meteSacaSVG(meteSaca);
		System.out.println(fin);
			
	    }
	    
	    

	    if(texto.equals("ArbolBinarioOrdenado")||texto.equals("ArbolBinarioCompleto")){ //Arboles
		ArbolBinario<Integer> arbol;
		arbol =	texto.equals("ArbolBinarioOrdenado")? new ArbolBinarioOrdenado<>():new ArbolBinarioCompleto<>();
       			
		for(Integer e : enteros){ 
		    arbol.agrega(e);
		}
		int f = (int) (Math.pow(2, arbol.profundidad()) * 90);
		int ff = arbol.profundidad()*200;
		System.out.println("<svg width='"+f+"' height='"+ ff +"'>");
		imprimidor.arbolBinarioSVG(arbol);
		System.out.println(fin);

	    } 
	    
	    
	
	    
	    

	    
	    if(texto.equals("ArbolRojinegro")){ //ArbolRojinegro
		ArbolRojinegro<Integer> arbol = new ArbolRojinegro<>();
       		
		for(Integer e : enteros){ 
		    arbol.agrega(e);
		}
		
		int f = (int) (Math.pow(2, arbol.profundidad()) * 90);
		int ff = arbol.profundidad()*200;
		System.out.println("<svg width='"+f+"' height='"+ ff +"'>");
		imprimidor.arbolRojinegroSVG(arbol);
		System.out.println(fin);
		
	    } 

	    
	    
	    if(texto.equals("ArbolAVL")){ //ArboleAVL
		ArbolAVL<Integer> arbol = new ArbolAVL<>();
       		
		for(Integer e : enteros){ 
		    arbol.agrega(e);
		}
		
		int f = (int) (Math.pow(2, arbol.profundidad()) * 90);
		int ff = arbol.profundidad()*200;
		System.out.println("<svg width='"+f+"' height='"+ ff +"'>");
		imprimidor.arbolAVL_SVG(arbol);
		System.out.println(fin);
		
	    }
	    
	    if(texto.equals("MonticuloMinimo")){ //MinHeap
		MonticuloMinimo<Indexable<Integer>> monticulo= new MonticuloMinimo<>();
       		
		for(Integer e : enteros){ 
		    Indexable<Integer> e2 = new Indexable<>(e,(double)e);
		    monticulo.agrega(e2);
		}
		
		int f = (int) (Math.pow(2, Math.log(monticulo.getElementos())) * 180);
		int ff = (int) (Math.log(monticulo.getElementos())*400
       				);
		System.out.println("<svg width='"+f+"' height='"+ ff +"'>");
		imprimidor.monticuloMinimoSVG(monticulo);
		System.out.println(fin);
		
	    }
	    
	    if(texto.equals("ArregloRojinegro")){ 	//LA ESTRUCTURA MAS EPICA DEL UNIVERSO
		
		int n = enteros.length*120;
		System.out.println("<svg width='"+n+ "' height='40'>");
		imprimidor.arregloRojinegroSVG(enteros);
		System.out.println(fin);
	    }
	    
	    
	    
	    
	    
	    if(texto.equals("Grafica")){ 	//Cuanta sangre llore por esta nena mala
		
       		
		Grafica<Integer> grafica = new Grafica<>();
		for(Integer e:enteros){ 
		    if(grafica.contiene(e)){ 
			System.err.println("ELEMENTO REPETIDO EN LA LISTA DE VERTICES");
			return;
		    }
		    grafica.agrega(e);
		}
		
		String[] relaciones = texto3.split(";");
		for(String relacion: relaciones){ 
		    
		    String[] relacionBinaria = relacion.split(",");
		    if(relacionBinaria.length!=2||
		       relacionBinaria[0]==null||relacionBinaria[1]==null){ 
			System.err.println("ERROR EN EL INGRESO DE RELACIONES");
			return;
		    }
		    try{
			String derecho = relacionBinaria[0];
			String izquierdo = relacionBinaria[1];
			Integer num = Integer.parseInt(derecho);
			Integer num2 = Integer.parseInt(izquierdo);
			    
			if(!(grafica.contiene(num)&&grafica.contiene(num2))) {
			    System.err.println("EROR EN EL INGRESO DE RELACIONES, POR ELEMENTOS NO CONTENIDO O NULOS");
			    return;
			}
			    
			if(grafica.sonVecinos(num,num2)) {
			    System.err.println("ARISTAS REPETIDAS, INSERTE UNA COMBINACION CORRECTA");
			    return;
			}
			    
	    		grafica.conecta(num,num2);
			
			

		    }catch(NumberFormatException e) {
			System.err.println("INGRESE NUMEROS");
			return;
			   
		    }
			
		}
		int n = 2*(int)((grafica.getElementos()*50)/Math.PI)+50;
		System.out.println("<svg width='"+n+"' height='"+n+"'>");
		GraficaSVG prueba = new GraficaSVG(grafica);
		prueba.imprimeSVG();
       		
       		
		System.out.println(fin);
	    }
	    
	    
	    
	} 
	
    } 
    
    
    
} 
