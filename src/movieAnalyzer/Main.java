package movieAnalyzer;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
			
		MAnalyzer ma = new MAnalyzer();
	    ma.csvParser("movies2.csv");
	    long total = System.nanoTime();
	    
	    //1. Imprime todas las películas leídas por pantalla
	    ma.movieListTitle();
	    
	    
	    //2. Implementa un método que, dado un valor mínimo y un máximo, imprima el título de todas las películas cuyo presupuesto es superior o igual al mínimo e inferior o igual al máximo.
	    ma.minMaxBudget(50000, 500000);
	  
	    
	    //3. Implementa un método que, dado un conjunto de géneros, imprima el nombre y todos los géneros de las películas que contienen esos géneros.
	    String genres[] = new String[] {"Horror","Crime"};
	    ma.searchGenres(genres);
	    
	    
	    //4. Implementa un método que, dada una keyword, imprima la máxima recaudación que ha obtenido una película con ese keyword.
	    ma.maxKeyWord(" future");
	    
	    
	    // 5. Implementa un método que, dado un idioma y un valor de popularidad, devuelva una lista con todas las películas de ese idioma cuya popularidad sea, al menos, el valor pasado como parámetro.
	    List<Movie> langPopList = ma.langPop("es",30000000);
	    for(Movie m : langPopList){
	    	System.out.println(m.getTitle());
	    }
	    
	    
	    // 6. Implementa un método que, dado un año, devuelva la recaudación total que obtuvieron las películas en ese año.
	    BigInteger ra = ma.recaudacionAno(2000);
	    System.out.println(ra);
	 
	    
	    // 7. Implementa un método que, dado una votación promedia mínima y una máxima, devuelva el número total de votos obtenidos.
	    BigInteger tv = ma.totalVotos(7.5,8);
	    System.out.println(tv);
	    
	    
	    // 8. Implementa un método que devuelva un mapa con los nombres de cada productora como clave y el título de sus películas como valor.
	    Map<String, List<String>> lpp = ma.peliculasProducidas();
	    lpp.forEach((k, v) -> System.out.println(k + ": " + v));
	    
	    System.out.println("TIEMPO: "+String.format("%f [msec]",(System.nanoTime() - total) / 1000000.0));
	}

}
