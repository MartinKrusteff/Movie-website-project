package net.codejava.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "Movie")
@Table(name= "movies")
public class Movie {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column( unique=true, length = 150)
		public String title;
		
		@Column(length = 150)
		public String genre;
		
		@Column(nullable=false, length = 150)
		public String director;
		
		@Column(nullable=false, unique=true, length = 100000)
		public String poster;
		
		@Column(nullable=false,unique=true, length = 600)
		public String plot;
		
		@Column(nullable=false,unique=true, length = 100000)
		private String trailerurl;
		
		@Column(columnDefinition = "float default 0")
		private double rating;
		
		//@Column(columnDefinition = "float default 0")
		//private double averagerating;
		
	//	public Double getAveragerating() {
	//		return averagerating;
	//	}

	//	public void setAveragerating(Double averagerating) {
	//		this.averagerating = averagerating;
	//	}

		@ManyToMany
			  @JoinTable(name = "movies_users",
			        joinColumns = { @JoinColumn(name = "movie_id") },
			        inverseJoinColumns = { @JoinColumn(name = "user_id") })
			  private Set<User> users = new HashSet<>();
		
		@ManyToMany
		  @JoinTable(name = "movies_users_ratings",
		        joinColumns = { @JoinColumn(name = "movie_id") },
		        inverseJoinColumns = { @JoinColumn(name = "user_id") })
		  private Set<User> usersRated = new HashSet<>();
		
		public String getTrailerurl() {
			return trailerurl;
		}

		public void setTrailerurl(String trailerurl) {
			this.trailerurl = trailerurl;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		public void setGenre(String genre) {
			this.genre = genre;
		}
		
		public String getGenre() {
			return genre;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public String getPoster() {
			return poster;
		}

		public void setPoster(String poster) {
			this.poster = poster;
		}

		public String getPlot() {
			return plot;
		}

		public void setPlot(String plot) {
			this.plot = plot;
		}
		
		public double getRating() {
			return rating;
		}

		public void setRating(double rating) {
			this.rating = rating;
		}

		public static void addAttribute(String string, List<Movie> findAll) {
			// TODO Auto-generated method stub
			
		}

		public Set<User> getUsers() {
			return users;
		}

		public void setUsers(Set<User> users) {
			this.users = users;
		}

		public Set<User> getUsersRated() {
			return usersRated;
		}

		public void setUsersRated(Set<User> usersRated) {
			this.usersRated = usersRated;
		}

		
		
		
		
		

}
