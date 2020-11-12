package implement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HighestIndividualScoresEachYear {

    public void highestIndividualScoresEachYear(int year) throws IOException {

        HashMap<String, Integer> batsman_total_runs = new HashMap<String , Integer>();
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
            if(!match_id.containsKey(values[0]) && values[1].equals(""+year)){
                match_id.put(values[0], Integer.parseInt(values[0]));
            }
        }

        //Calculating the batsman scores
        br1.readLine();
        while((line = br1.readLine()) != null){
            String values1[] = line.split(",");
            String is_super_over = values1[9];
            String batsman_name = values1[6];
            int batsman_current_ball_runs = Integer.parseInt(values1[15]);

            if(match_id.containsKey(values1[0]) && is_super_over.equals("0")){
                       if(batsman_total_runs.containsKey(batsman_name)){
                           int current_total_runs = batsman_total_runs.get(batsman_name) + batsman_current_ball_runs;
                           batsman_total_runs.replace(batsman_name, current_total_runs);
                       }else{
                           batsman_total_runs.put(batsman_name, batsman_current_ball_runs);
                       }
                }
        }
        // sorting based on high scores
        ArrayList<String> batsman_list = new ArrayList<String>();
        for (String batsman: batsman_total_runs.keySet()){
            String  value2 = batsman+","+batsman_total_runs.get(batsman);
            batsman_list.add(value2);
        }
        System.out.println("List of top 10 high scorers batsman in "+year+"\n");
        for (int i=0;i<10;i++){
            for(int j=0;j<batsman_list.size()-1;j++){
                float v1 = Float.parseFloat(batsman_list.get(j).split(",")[1]);
                float v2 = Float.parseFloat(batsman_list.get(j+1).split(",")[1]);
                String current_string1 = batsman_list.get(j);
                String current_string2 = batsman_list.get(j+1);
                if(v1>v2){
                    batsman_list.set(j,current_string2 );
                    batsman_list.set(j+1,current_string1 );
                }
            }
            System.out.println(batsman_list.get(batsman_list.size()-1-i).split(",")[0]+" : "+ batsman_list.get(batsman_list.size()-1-i).split(",")[1]);
        }
    }
}
