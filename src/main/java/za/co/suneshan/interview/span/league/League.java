package za.co.suneshan.interview.span.league;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

@Component
public class League {

    private static final char SPACE = ' ';
    private static final String COMMA = ",";
    private static Map<String, Integer> league = new HashMap<>();

    public void processResults(String filename) {
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(filename))).lines()) {
            stream.forEach(match -> {
                String[] team = match.split(COMMA);
                if (getTeamScore(team[0]) > getTeamScore(team[1])) {
                    updateLeagueTableWinner(getTeamName(team[0]), getTeamName(team[1]));
                } else if (getTeamScore(team[0]) < getTeamScore(team[1])) {
                    updateLeagueTableWinner(getTeamName(team[1]), getTeamName(team[0]));
                } else {
                    updateLeagueTableDraw(getTeamName(team[0]), getTeamName(team[1]));
                }
            });
        }
        sortLeagueTable();
        displayLeagueTable();
    }

    private void updateLeagueTableWinner(String winner, String loser) {
        league.put(winner, league.get(winner) == null ? 3 : league.get(winner) + 3);
        league.put(loser, league.get(loser) == null ? 0 : league.get(loser) + 0);
    }

    private void updateLeagueTableDraw(String teamOne, String teamTwo) {
        league.put(teamOne, league.get(teamOne) == null ? 1 : league.get(teamOne) + 1);
        league.put(teamTwo, league.get(teamTwo) == null ? 1 : league.get(teamTwo) + 1);
    }

    private int getTeamScore(String team) {
        return Integer.parseInt(team.substring(team.lastIndexOf(SPACE)+1));
    }

    private String getTeamName(String team) {
        return team.substring(0, team.lastIndexOf(SPACE));
    }

    private void sortLeagueTable() {
        league = league
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    private void displayLeagueTable() {
        int position = 0;
        int prevPointsPosition = -1;
        int tiedPositionCount = 1;
        for (Map.Entry<String, Integer> team:league.entrySet()) {
            if (team.getValue() != prevPointsPosition) {
                if (tiedPositionCount != 1) {
                    position += tiedPositionCount;
                    tiedPositionCount = 1;
                } else {
                    position += 1;
                }
            } else {
                tiedPositionCount += 1;
            }
            System.out.format("%d. %s, %d %s%n", position, team.getKey().trim(), team.getValue(), team.getValue() == 1 ? "pt" : "pts");
            prevPointsPosition = team.getValue();
        }
    }
}
