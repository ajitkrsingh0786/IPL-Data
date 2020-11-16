package implement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

public class ExtraRunsConceded {
    public void extraRunConcededByEachTeam() throws IOException {
        TreeMap<String, Integer> extra_run_conceded_in_2016 = new TreeMap<String ,Integer>();
        HashMap<String, Integer> match_id = new HashMap<String ,Integer>();
        String line = "";
        String file_path = "./resources/matches.csv";
        String file_path1 = "./resources/deliveries.csv";
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        BufferedReader br1 = new BufferedReader(new FileReader(file_path1));

        br.readLine();
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(!match_id.containsKey(values[0]) && values[1].equals("2016")){
                match_id.put(values[0], Integer.parseInt(values[0]));
            }
        }

        //extra run calculation
        br1.readLine();
        while((line = br1.readLine()) != null){
            String values1[] = line.split(",");
            if(match_id.containsKey(values1[0])){
                int extra_runs = Integer.parseInt(values1[16]);
                String bowling_team = values1[3];
                if(extra_run_conceded_in_2016.containsKey(bowling_team)){
                    int total_extra_runs = extra_run_conceded_in_2016.get(bowling_team)+extra_runs;
                    extra_run_conceded_in_2016.replace(bowling_team, total_extra_runs);
                }else {
                    extra_run_conceded_in_2016.put(bowling_team, extra_runs);
                }
            }
        }
        // printing the result
        for (String team: extra_run_conceded_in_2016.keySet()){
            int extra_run_conceded = extra_run_conceded_in_2016.get(team);
            System.out.println("Total extra runs conceded by "+ team + " in 2016 is : " + extra_run_conceded);
        }
    }
}
