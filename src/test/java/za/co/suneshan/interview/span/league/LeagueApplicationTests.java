package za.co.suneshan.interview.span.league;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


//@SpringBootTest(args="--score.sheet=sample1.txt")
@TestConfiguration
public class LeagueApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("print 11");
//		InputStream sysInBackup = System.in; // backup System.in to restore it later
//		ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
//		System.setIn(in);

		System.out.println("print 22");
	}

//	@Test
//	void whenAppStarts_thenScoreSheetFilenameIsNotEmpty(@Autowired Environment env) {
//		String filename = env.getProperty("score.sheet");
//		Assertions.assertTrue(!filename.isEmpty());
//
//		System.out.println("print 1");
//
//		InputStream sysInBackup = System.in; // backup System.in to restore it later
//		ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
//		System.setIn(in);
//
//		System.out.println("print 2");
//	}
}
