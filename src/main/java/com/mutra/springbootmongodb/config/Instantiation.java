package com.mutra.springbootmongodb.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.mutra.springbootmongodb.domain.Post;
import com.mutra.springbootmongodb.domain.User;
import com.mutra.springbootmongodb.dto.AuthorDTO;
import com.mutra.springbootmongodb.dto.CommentDTO;
import com.mutra.springbootmongodb.repository.PostRepository;
import com.mutra.springbootmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.UK);
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		User maria= new User(null, "Maria Brown", "maria@gmail.com");
		User alex= new User(null, "Alex Green", "alex@gmail.com");
		User bob= new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
	
		Post post1 = new Post(null,LocalDate.parse("21/03/2018", dtf), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",new AuthorDTO(maria));
		Post post2 = new Post(null,LocalDate.parse("23/03/2018", dtf), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!",LocalDate.parse("21/03/2018", dtf) , new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite",LocalDate.parse("22/03/2018", dtf) , new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!",LocalDate.parse("23/03/2018", dtf) , new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1,c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1,post2));
		maria.getPosts().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
		
	}
}
