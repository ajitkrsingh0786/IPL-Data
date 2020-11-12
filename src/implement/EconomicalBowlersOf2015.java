package implement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EconomicalBowlersOf2015 {

    public void allEconomicalBowlersOf2015() throws IOException {
        HashMap<String, HashMap<String, Integer>> bowlers_total_balls_runs_in_2015 = new HashMap<String ,HashMap<String, Integer>>();
        HashMap<String, Float> bowlers_economy_in_2015 = new HashMap<String ,Float>();
        HashMap<String, Integer> match_id = new HashMap<String ,Integer>();
        String line = "";
        String file_path = "./resources/matches.csv";
        String file_path1 = "./resources/deliveries.csv";
        BufferedReader br = new BufferedReader(new FileReader(file_path));
        BufferedReader br1 = new BufferedReader(new FileReader(file_path1));

        //checking the matches id of 2015
        br.readLine();
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(!match_id.containsKey(values[0]) && values[1].equals("2015")){
                match_id.put(values[0], Integer.parseInt(values[0]));
            }
        }

        //calculating total balls and runs by bowlers
        br1.readLine();
        while((line = br1.readLine()) != null){
            String values1[] = line.split(",");
            String is_super_over = values1[9];
            if(match_id.containsKey(values1[0]) && is_super_over.equals("0")){
                int wide_runs = Integer.parseInt(values1[10]);
                int noball_runs = Integer.parseInt(values1[13]);
                int batsman_runs = Integer.parseInt(values1[15]);
                String bowler_name = values1[8];
                HashMap<String, Integer> ball_and_runs = new HashMap<String ,Integer>(); //each ball's run
                if(bowlers_total_balls_runs_in_2015.containsKey(bowler_name)){
                     int pre_ball_count = bowlers_total_balls_runs_in_2015.get(bowler_name).get("balls");
                     int pre_run_count = bowlers_total_balls_runs_in_2015.get(bowler_name).get("runs");
                    if(wide_runs==0 && noball_runs==0){
                        ball_and_runs.put("balls", pre_ball_count + 1);
                        ball_and_runs.put("runs", pre_run_count+batsman_runs);
                    }else if(noball_runs!=0){
                        ball_and_runs.put("balls", pre_ball_count + 0);
                        ball_and_runs.put("runs", pre_run_count+batsman_runs+1);
                    }else {
                        ball_and_runs.put("balls", pre_ball_count + 0);
                        ball_and_runs.put("runs", pre_run_count + 1);
                    }

                    bowlers_total_balls_runs_in_2015.replace(bowler_name, ball_and_runs);

                }else {

                    if(wide_runs == 0 && noball_runs == 0){
                        ball_and_runs.put("balls", 1);
                        ball_and_runs.put("runs",batsman_runs);
                    }else if(noball_runs!=0){
                        ball_and_runs.put("balls", 0);
                        ball_and_runs.put("runs", batsman_runs+1);
                    }else {
                        ball_and_runs.put("balls", 0);
                        ball_and_runs.put("runs", 1);
                    }

                    bowlers_total_balls_runs_in_2015.put(bowler_name, ball_and_runs);
                }
            }
        }
       // calculating economy
        for (String bowler: bowlers_total_balls_runs_in_2015.keySet()){
              float bowler_total_ball = bowlers_total_balls_runs_in_2015.get(bowler).get("balls");
              float bowler_total_runs = bowlers_total_balls_runs_in_2015.get(bowler).get("runs");
              float bowler_total_over = bowler_total_ball/6;
              float bowler_economy = bowler_total_runs/bowler_total_over;
              bowlers_economy_in_2015.put(bowler, bowler_economy);
        }

        //To sort the bowlers by economy
        ArrayList<String> economy_list = new ArrayList<String>();
        for (String bowler: bowlers_economy_in_2015.keySet()){
            String  value2 = bowler+","+bowlers_economy_in_2015.get(bowler);
            economy_list.add(value2);
        }
        System.out.println("List of top 10 economical bowlers in 2015\n");
        for (int i=0;i<10;i++){
             for(int j=0;j<economy_list.size()-1;j++){
                  float v1 = Float.parseFloat(economy_list.get(j).split(",")[1]);
                  float v2 = Float.parseFloat(economy_list.get(j+1).split(",")[1]);
                  String current_string1 = economy_list.get(j);
                  String current_string2 = economy_list.get(j+1);
                  if(v1<v2){
                      economy_list.set(j,current_string2 );
                      economy_list.set(j+1,current_string1 );
                  }
             }
             System.out.println(economy_list.get(economy_list.size()-1-i).split(",")[0]+" : "+ economy_list.get(economy_list.size()-1-i).split(",")[1]);
        }
    }
}
