package com.circumborrealis.serverside;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.circumborrealis.serverside.Model.User;
import com.circumborrealis.serverside.Model.CodeSnip;
import com.circumborrealis.serverside.Repository.IUserRepository;
import com.circumborrealis.serverside.Repository.ICodeSnipRepository;

@SpringBootApplication
public class AngularClientSpringServerApplication {
	private static final Logger log = LoggerFactory.getLogger(AngularClientSpringServerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(AngularClientSpringServerApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner bookCommandLineRunner(IUserRepository userRepository, ICodeSnipRepository codeSnipRepository) {
		return (args) -> {
			// username, pw-hash, user-role
			log.info("create a couple of users");
			userRepository.save(new User("u", "$2a$04$jpaop0rtM9vB4JlXXR0Rf.laXBiGHyhvYU.97bOqyXH0uXrnn/ogC", "USER"));
			userRepository.save(new User("a", "$2a$04$L8b76mFZldgRcjiGGqWc0OEeUfXYlnWVZ7EtT1d/d598J2BXFs0X2", "ADMIN"));
			
			// id, owner, details, code, language, time, User user,
			log.info("create codesnips for both users");
			codeSnipRepository.save(new CodeSnip( 1L, "u", "An interesting way of implementing addition.", "var a = 5; a + a;", "JavaSctipt", 10L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 2L, "u", "An Unordered HTML List", "¤)#TGJ#(¤", "Html", 100000000000L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 3L, "u", "Some C# code that I found", "ry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown print", "C#", 105426000000L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 4L, "u", "Super weird stuff", "ry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containin", "C#", 100064373000L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 5L, "u", "Never seen this before", "ry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containin", "C#", 100743670000L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 6L, "u", "I have to remember this!", "ry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containin", "Java", 100004573600L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 7L, "u", "Super weird stuff again!", "ry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containin", "General", 10000075600L, userRepository.findByUsername("u")));
			codeSnipRepository.save(new CodeSnip( 3L, "a", "A nice CSS trick", ".body {color: red;}", "CSS", 105426000L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 4L, "a", "Something I came across", "Use Entity Framework when doing a .NET project!", "C#", 1012345000L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 5L, "a", "Microsoft rules", ".NET is a powerful Framework!", "C#", 1000000000L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 6L, "a", "Something I came across", "Java code ummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release", "Java", 1000024300L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 7L, "a", "Something I came across", "Lisp code ummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release", "Lisp", 1000245000L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 8L, "a", "Something I came across", "Pyhton code ummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release", "Python", 1000064200L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 9L, "a", "Something I came across", "Perl code ummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release", "Perl", 1000000654L, userRepository.findByUsername("a")));
			codeSnipRepository.save(new CodeSnip( 10L, "a", "Something I came across", "Pascal code ummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release", "Pascal", 1001245600L, userRepository.findByUsername("a")));
			
			log.info("fetch all users");
			for (User user : userRepository.findAll()) {
				log.info(user.toString());
			}
		};
	}
	
	
	
	
}
