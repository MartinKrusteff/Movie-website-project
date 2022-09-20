package net.codejava;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import net.codejava.entities.Movie;
import net.codejava.entities.User;
import net.codejava.repo.MovieRepository;
import net.codejava.repo.UserRepository;
@Controller
public class AppController2 {
	
	
		private final MovieRepository movieRepository;
		private final UserRepository userRepository;
		
		
		public AppController2(MovieRepository movieRepository, UserRepository userRepository) {
			this.movieRepository = movieRepository;
			this.userRepository=userRepository;
		}
	
		@PostMapping("/addMovie")
		public String showMovieForm(Movie movie) {
			movieRepository.saveAndFlush(movie);
			return "redirect:/MainPage";
		}
		
		@GetMapping("/addMovie")
	    public String moviePage() {
	        return "AddMovie";
	    }
		
		@GetMapping("/EditProfile")
	    public String editPage(HttpSession httpSession, Model model) {
			
	       Long id=(Long)httpSession.getAttribute("id");
	       
	       User user = userRepository.findById(id).get();
	       model.addAttribute("user", user);
	       
	       
			return "EditProfile";
	    }
		
		@PostMapping("/EditProfile")
	    public String editPageConfirm(HttpSession httpSession, @ModelAttribute User user) {
			
	      user.setId((Long) httpSession.getAttribute("id"));
	      
	      httpSession.removeAttribute("email");
	       userRepository.save(user);
	       
	       
			return "redirect:/login";
	    }
		
		@DeleteMapping("/remove/{movieId}")
		public String removeList(HttpSession httpSession, @PathVariable String movieId) {
			Long userId=(Long)httpSession.getAttribute("id");
			
			User userById = userRepository.findById(userId).get();
			Movie movieById = movieRepository.findById(Long.valueOf(movieId)).get();
	
			userById.getMovies().remove(movieById);
			movieById.getUsers().remove(userById);
			
			userRepository.saveAndFlush(userById);
			movieRepository.saveAndFlush(movieById);
			
			return "redirect:/myList";
		
		}
	}		
		
	



