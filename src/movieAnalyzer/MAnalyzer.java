package movieAnalyzer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MAnalyzer {
	private List<Movie> movieList;
	
	// 1. Lee el fichero completo y almac�nalo en una lista. A continuaci�n, imprime todas las pel�culas le�das por pantalla.
	public void csvParser(String fileName){
		try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
			movieList = bf.lines()
					.skip(1)
					.map(line -> line.split(";"))
					.filter(tokens -> tokens.length == 12)
					.map(tokens ->  {
						try{
							String title = tokens[4];
							BigInteger budget = new BigInteger(tokens[0]);														
							
							Map<Integer, String> genres = new TreeMap<Integer, String>();	//INICIALIZAR 1							
							String[] aux = tokens[1].replaceAll("[^,:\\w\\.\\s\\-]","").split(",|:");
							for(int i=1; i<aux.length; i=i+4){
								genres.put(Integer.parseInt(aux[i].replaceAll(" ","")), aux[i+2].replaceAll(" ",""));
							}
							
							Map<Integer, String> keywords = new TreeMap<Integer, String>(); //INICIALIZAR 2
							aux = tokens[2].replaceAll("[^,:\\w\\.\\s\\-]","").split(",|:");
							
							for(int i=1; i<aux.length; i=i+4){
								String lin = aux[i+2];
								int num = -1;
								while(num == -1){
									try{
										num = Integer.parseInt(aux[i].replaceAll(" ",""));
									}
									catch(Exception e){
										lin = ", "+ aux[i+2];
										i++;
									}
								}
								keywords.put(num, lin);
							}
							
							
							String language = tokens[3];
							BigInteger popularity = new BigInteger(tokens[5].replace(".",""));
							
							Map<Integer, String> production_companies = new TreeMap<Integer, String>(); //INICIALIZAR 6
							aux = tokens[6].replaceAll("[^,:\\w\\.\\s\\-]","").split(",|:");
							for(int i=1; i<aux.length; i=i+4){
								String lin = aux[i];
								int num = -1;
								while(num == -1){
									try{
										num = Integer.parseInt(aux[i+2].replaceAll(" ",""));
									}
									catch(Exception e){
										lin = ", "+ aux[i+2];
										i++;
									}
								}
								production_companies.put(num, lin);
							}
							
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							LocalDate release_date = null;
							try{
								release_date = LocalDate.parse(tokens[7],formatter);
							}
							catch(Exception e){
							}
							
							BigInteger revenue = new BigInteger(tokens[8]);
							String status = tokens[9];
							double vote_average = Float.parseFloat(tokens[10]);
							BigInteger vote_count = new BigInteger(tokens[11]);

							
							Movie mov = new Movie(budget, genres, keywords, language, title, popularity, production_companies, release_date, revenue, status,
									vote_average, vote_count);
							return mov;
						}
						catch (Exception e) {
							e.printStackTrace();
                            return null;
						}
					})
					.filter(Objects::nonNull)
                    .collect(Collectors.toList());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//1. Imprime todas las pel�culas le�das por pantalla
	public void movieListTitle() {
        movieList.parallelStream()
        	.map(Movie::getTitle)	
        	.forEach(System.out::println);
    }
	
	//2. Implementa un m�todo que, dado un valor m�nimo y un m�ximo, imprima el t�tulo de todas las pel�culas cuyo presupuesto es superior o igual al m�nimo e inferior o igual al m�ximo.
	public void minMaxBudget(int min, int max){
		movieList.parallelStream()
        	.filter(m -> m.getBudget().compareTo(new BigInteger(String.valueOf(max)))<=0)
        	.filter(m -> m.getBudget().compareTo(new BigInteger(String.valueOf(min)))>=0)
        	.map(Movie::getTitle)
        	.forEachOrdered(System.out::println);
	}
	
	//3. Implementa un m�todo que, dado un conjunto de g�neros, imprima el nombre y todos los g�neros de las pel�culas que contienen esos g�neros.
	public void searchGenres(String[] genresList){
		movieList.parallelStream()
		.filter(m -> m.getGenres().values().containsAll(Arrays.asList(genresList)))
		.map(m -> m.getTitle() + "\t" + m.getGenres().values())  
		.forEach(System.out::println);
	}
	
	//4. Implementa un m�todo que, dada una keyword, imprima la m�xima recaudaci�n que ha obtenido una pel�cula con ese keyword.
	public void maxKeyWord(String kw){
		movieList.parallelStream()
			.filter(m-> m.getKeywords().containsValue(kw))
			.map(Movie::getRevenue)
			.max(Comparator.comparing(String::valueOf))
			.ifPresent(System.out::println);
	}
	
	// 5. Implementa un m�todo que, dado un idioma y un valor de popularidad, devuelva una lista con todas las pel�culas de ese idioma cuya popularidad sea, al menos, el valor pasado como par�metro.
	public List<Movie> langPop(String lang, int pop){
		return movieList.parallelStream()
			.filter(m-> m.getLanguage().equals(lang))
			.filter(m-> m.getPopularity().compareTo(new BigInteger(String.valueOf(pop)))>=0)
			.collect(Collectors.toList());
	}
	
	// 6. Implementa un m�todo que, dado un a�o, devuelva la recaudaci�n total que obtuvieron las pel�culas en ese a�o.
	public BigInteger recaudacionAno(int ano){
		return movieList.parallelStream()
			.filter(m-> m.getRelease_date() != null && m.getRelease_date().getYear() == ano)
			.map(m -> m.getRevenue())
			.reduce(BigInteger.ZERO, BigInteger::add);  
	}
	
	// 7. Implementa un m�todo que, dado una votaci�n promedia m�nima y una m�xima, devuelva el n�mero total de votos obtenidos.
	public BigInteger totalVotos(double min, double max){
		return movieList.parallelStream()
			.filter(m -> m.getVote_average() <= max)
			.filter(m -> m.getVote_average() >= min)
			.map(m -> m.getVote_count())
			.reduce(BigInteger.ZERO, BigInteger::add);
	}
	
	// 8. Implementa un m�todo que devuelva un mapa con los nombres de cada productora como clave y el t�tulo de sus pel�culas como valor.
	public Map<String, List<String>> peliculasProducidas(){
		return movieList.parallelStream()
				.map(m -> m.getProduction_companies().values())					
				.flatMap(m -> m.stream()) 									
				.distinct()
				.collect(Collectors.toMap(String::toString, m -> {
					return movieList.parallelStream()
						.filter(mov -> mov.getProduction_companies().values().contains(m))
						.map(Movie::getTitle)
						.collect(Collectors.toList());
				}));
	}
	
	public List<Movie> getMovieList(){
		return movieList;
	}
}
