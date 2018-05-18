package movieAnalyzer;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;

public class Movie {
	private BigInteger budget;
	private Map<Integer, String> genres;
	private Map<Integer, String> keywords;
	private String language;
	private String title;
	private BigInteger popularity;
	private Map<Integer, String> production_companies;
	private LocalDate release_date;
	private BigInteger revenue;
	private String status;
	private double vote_average;
	private BigInteger vote_count;
	
	public Movie(BigInteger budget, Map<Integer, String> genres,
			Map<Integer, String> keywords, String language, String title,
			BigInteger popularity, Map<Integer, String> production_companies,
			LocalDate release_date, BigInteger revenue, String status,
			double vote_average, BigInteger vote_count) {
		this.budget = budget;
		this.genres = genres;
		this.keywords = keywords;
		this.language = language;
		this.title = title;
		this.popularity = popularity;
		this.production_companies = production_companies;
		this.release_date = release_date;
		this.revenue = revenue;
		this.status = status;
		this.vote_average = vote_average;
		this.vote_count = vote_count;
	}

	public BigInteger getBudget() {
		return budget;
	}

	public void setBudget(BigInteger budget) {
		this.budget = budget;
	}

	public Map<Integer, String> getGenres() {
		return genres;
	}

	public void setGenres(Map<Integer, String> genres) {
		this.genres = genres;
	}

	public Map<Integer, String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Map<Integer, String> keywords) {
		this.keywords = keywords;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BigInteger getPopularity() {
		return popularity;
	}

	public void setPopularity(BigInteger popularity) {
		this.popularity = popularity;
	}

	public Map<Integer, String> getProduction_companies() {
		return production_companies;
	}

	public void setProduction_companies(Map<Integer, String> production_companies) {
		this.production_companies = production_companies;
	}

	public LocalDate getRelease_date() {
		return release_date;
	}

	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}

	public BigInteger getRevenue() {
		return revenue;
	}

	public void setRevenue(BigInteger revenue) {
		this.revenue = revenue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getVote_average() {
		return vote_average;
	}

	public void setVote_average(double vote_average) {
		this.vote_average = vote_average;
	}

	public BigInteger getVote_count() {
		return vote_count;
	}

	public void setVote_count(BigInteger vote_count) {
		this.vote_count = vote_count;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static class BudgetComparator implements Comparator<Movie>{
		@Override
		public int compare(Movie m1, Movie m2) {
			return m1.getBudget().compareTo(m2.getBudget());
		}
	 }
	
	
}
