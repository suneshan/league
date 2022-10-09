package za.co.suneshan.interview.span.league;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = League.class,
		webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LeagueApplicationTests {

	@Autowired
	private League league;

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	@BeforeEach
	public void setUp() {
		System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	void contextLoads() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("output/out_1.txt").getFile());
		league.processResults("input/in_1.txt");
		ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
		assertEquals(contentOf(file), outputStreamCaptor.toString().trim());
	}

}
