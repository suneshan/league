package za.co.suneshan.interview.span.league;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
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
	private ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();;

	@BeforeEach
	public void setUp() throws IOException {
		System.setOut(new PrintStream(outputStreamCaptor, true));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(standardOut);
	}

	@Test
	void testLeagueResults() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("output/league_table.txt").getFile());
		league.processResults("input/league_results.txt");
		assertEquals(contentOf(file), outputStreamCaptor.toString().trim());
	}

	@Test
	void testEqualRankOrderedAlphabetically() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("output/equal_rank_table.txt").getFile());
		league.processResults("input/equal_rank.txt");
		assertEquals(contentOf(file), outputStreamCaptor.toString().trim());
	}

}
