package net.codejava;

import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.codejava.entities.Movie;
import net.codejava.entities.User;
import net.codejava.repo.MovieRepository;
import net.codejava.repo.UserRepository;

@Controller
public class AppController3 {
	@Autowired
    private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/MainPage")
		    public String listMovies(Model model, String keyword, HttpSession session){
				List<Movie> movies = keyword == null ? movieRepository.findAll() : movieRepository.findByKeyword(keyword);
				boolean isLoggedIn = ((Long)session.getAttribute("id")) != null;
				
				System.out.println((Long)session.getAttribute("id"));
				
		        model.addAttribute("movies", movies);
		        model.addAttribute("isLoggedIn", isLoggedIn);
		        
		        System.out.println("HELLO: "+ isLoggedIn);
		        return "MainPage";
		    }
	
	
	@GetMapping("/myList")
	public String myList(Model model, HttpSession session) {
		Long id=(Long)session.getAttribute("id");
	    User user = userRepository.findById(id).get();
		user.getMovies();
        model.addAttribute("movies", user.getMovies());
		return "MyList";
	}
	
	
	
	@PostMapping("/addStar/{movieId}")
	public String addStar(@RequestParam Long rate, @PathVariable("movieId") Long movieId, HttpSession session) {
		System.out.println("Rate: " + rate);
		System.out.println("Movie id: " + movieId);
		Long userId=(Long)session.getAttribute("id");
		
		User user = userRepository.findById(userId).get();
		Movie movie = movieRepository.findById(movieId).get();
		
		if (movie.getUsersRated().stream().filter(u -> u.getId() == userId).findFirst().orElse(null) == null) {
			double rating = movie.getRating() == 0 ? rate : ((movie.getRating() + rate) / 2);
			movie.setRating(rating);
			movie.getUsersRated().add(user);
			
			movieRepository.save(movie);
		}
		
		return "redirect:/MainPage";
	}
	
	@PostMapping("/addToFav/{movieId}")
	public String addToFav(HttpSession httpSession, @PathVariable("movieId") Long movieId) {
		Long userId=(Long)httpSession.getAttribute("id");
		
		User userById = userRepository.findById(userId).get();
		Movie movieById = movieRepository.findById(movieId).get();
		
		if (userById != null) {
			if (userById.getMovies() == null) {
				userById.setMovies(new LinkedHashSet<>());
			}
			
			Movie existsing = userById.getMovies().stream().filter(m -> m.getId() == movieId).findAny().orElse(null);
			
			if (existsing == null) {
				userById.getMovies().add(movieById);
				userRepository.save(userById);
				
				if (movieById.getUsers() == null) {
					movieById.setUsers(new LinkedHashSet<>());
				}
				
				movieById.getUsers().add(userById);
				movieRepository.save(movieById);
			}
		} 
		
		return "redirect:/MainPage";
	}
	


    }