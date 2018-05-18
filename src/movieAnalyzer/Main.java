package movieAnalyzer;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
			
		MAnalyzer ma = new MAnalyzer();
	    ma.csvParser("movies2.csv");
	    long total = System.nanoTime();
	    
	    //1. Imprime todas las pel�culas le�das por pantalla
	    ma.movieListTitle();
	    
	    
	    //2. Implementa un m�todo que, dado un valor m�nimo y un m�ximo, imprima el t�tulo de todas las pel�culas cuyo presupuesto es superior o igual al m�nimo e inferior o igual al m�ximo.
	    ma.minMaxBudget(50000, 500000);
	  
	    
	    //3. Implementa un m�todo que, dado un conjunto de g�neros, imprima el nombre y todos los g�neros de las pel�culas que contienen esos g�neros.
	    String genres[] = new String[] {"Horror","Crime"};
	    ma.searchGenres(genres);
	    
	    
	    //4. Implementa un m�todo que, dada una keyword, imprima la m�xima recaudaci�n que ha obtenido una pel�cula con ese keyword.
	    ma.maxKeyWord(" future");
	    
	    
	    // 5. Implementa un m�todo que, dado un idioma y un valor de popularidad, devuelva una lista con todas las pel�culas de ese idioma cuya popularidad sea, al menos, el valor pasado como par�metro.
	    List<Movie> langPopList = ma.langPop("es",30000000);
	    for(Movie m : langPopList){
	    	System.out.println(m.getTitle());
	    }
	    
	    
	    // 6. Implementa un m�todo que, dado un a�o, devuelva la recaudaci�n total que obtuvieron las pel�culas en ese a�o.
	    BigInteger ra = ma.recaudacionAno(2000);
	    System.out.println(ra);
	 
	    
	    // 7. Implementa un m�todo que, dado una votaci�n promedia m�nima y una m�xima, devuelva el n�mero total de votos obtenidos.
	    BigInteger tv = ma.totalVotos(7.5,8);
	    System.out.println(tv);
	    
	    
	    // 8. Implementa un m�todo que devuelva un mapa con los nombres de cada productora como clave y el t�tulo de sus pel�culas como valor.
	    Map<String, List<String>> lpp = ma.peliculasProducidas();
	    lpp.forEach((k, v) -> System.out.println(k + ": " + v));
	    
	    System.out.println("TIEMPO: "+String.format("%f [msec]",(System.nanoTime() - total) / 1000000.0));
	}

}
