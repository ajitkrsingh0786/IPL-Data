package implement;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class MatchesWonByEachTeam {
      public void matchesWonByEachTeam() throws IOException {
            TreeMap<String, Integer> match_won_by_each_teams = new TreeMap<String, Integer>();
            String line = "";
            String file_path = "./resources/matches.csv";// write your code here
            BufferedReader br = new BufferedReader(new FileReader(file_path));
            br.readLine();
            while((line = br.readLine()) != null){
                  String values[] = line.split(",");

                  if(match_won_by_each_teams.containsKey(values[10])){
                        int curr_value = match_won_by_each_teams.get(values[10])+1;
                        match_won_by_each_teams.replace(values[10], curr_value);
                  }else{
                        if(values[10] != null && !values[10].equals("")) {
                              match_won_by_each_teams.put(values[10], 1);
                        }
                  }
            }
            for (String team: match_won_by_each_teams.keySet()){
                  int match_won = match_won_by_each_teams.get(team);
                  System.out.println("Total number of  matches won by "+ team + " is : " + match_won);
            }
      }
}
