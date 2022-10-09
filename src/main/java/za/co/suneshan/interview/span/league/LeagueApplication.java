package za.co.suneshan.interview.span.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LeagueApplication implements ApplicationRunner {

	@Autowired
	League league;

	public static void main(String[] args) {
		SpringApplication.run(LeagueApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.print("Please enter input filename: ");
		Scanner scanner = new Scanner(System.in);
		league.processResults(scanner.nextLine());
	}
}
