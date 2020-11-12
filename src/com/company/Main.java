package com.company;

import implement.EconomicalBowlersOf2015;
import implement.ExtraRunsConceded;
import implement.MatchesPlayedPerYear;
import implement.MatchesWonByEachTeam;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 to see the total matches played per year\n" +
                "Enter 2 to see the total number of matches won by each\n" +
                "Enter 3 to see the total extra runs conceded by each team in 2016\n" +
                "Enter 4 to see the top 10 economical bowlers in 2015");

        int i = sc.nextInt();
         switch (i) {
             case 1:
                 MatchesPlayedPerYear match_per_year = new MatchesPlayedPerYear();
                 match_per_year.matchPlayedPerYear();
                 break;
             case 2:
                 MatchesWonByEachTeam match_played = new MatchesWonByEachTeam();
                 match_played.matchesWonByEachTeam();
                 break;
             case 3:
                 ExtraRunsConceded extra_runs = new ExtraRunsConceded();
                 extra_runs.extraRunConcededByEachTeam();
                 break;
             case 4:
                 EconomicalBowlersOf2015 economical_bowlers = new EconomicalBowlersOf2015();
                 economical_bowlers.allEconomicalBowlersOf2015();
                 break;
             default:
                 System.out.println("Question number is wrong\n Enter correct the number");
         }
    }
}
