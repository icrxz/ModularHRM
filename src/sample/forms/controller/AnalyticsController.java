package sample.forms.controller;

import br.com.ec6.modular.contoller.TaskDAO;
import br.com.ec6.modular.contoller.TeamMemberDAO;
import br.com.ec6.modular.model.Task;
import br.com.ec6.modular.model.TeamMember;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import sample.Screens;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import java.net.URL;

public class AnalyticsController implements Initializable {


    @FXML
    private BarChart<?, ?> BarChart;

    @FXML
    private AreaChart<?, ?> AreaChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Analytics");

        BarChart.getData().add(getMemberData());
        AreaChart.getData().add(getTaskData());
    }

    public static  XYChart.Series getTaskData(){

        XYChart.Series areaData = new XYChart.Series();
        TaskDAO tDAO = new TaskDAO();
        List<Task> allTasks =  tDAO.getAllTasks();

        Map<String, Integer> weekToAtivitymap = new HashMap<String, Integer>();
        for(Task tak : allTasks){
            LocalDateTime today = LocalDateTime.now();
            if(tak.getDueDate().getMonth().equals(today.getMonth())){
                Calendar cal = Calendar.getInstance();
                cal.setTime(Date.from(tak.getDueDate().toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

                String keyMap = "Semana "+cal.get(Calendar.WEEK_OF_MONTH);
                if(!weekToAtivitymap.containsKey(keyMap))   {
                    weekToAtivitymap.put(keyMap, 0);
                }
                weekToAtivitymap.put(keyMap, weekToAtivitymap.get(keyMap)+1);
            }
        }
        for(String key : weekToAtivitymap.keySet()){
            areaData.getData().add(new XYChart.Data(key, weekToAtivitymap.get(key)));
        }
        return areaData;
    }
    public static XYChart.Series getMemberData(){

        XYChart.Series areaData = new XYChart.Series();
        TeamMemberDAO tDAO = new TeamMemberDAO();
        List<TeamMember> allMembers =  tDAO.getAllTeamMember();

        Map<String, Integer> teamToQtdMembermap = new HashMap<String, Integer>();
        for(TeamMember teamMember : allMembers){
            String keyMap = teamMember.getTeam().getName();
            if(!teamToQtdMembermap.containsKey(keyMap))   {
                teamToQtdMembermap.put(keyMap, 0);
            }
            teamToQtdMembermap.put(keyMap, teamToQtdMembermap.get(keyMap)+1);
        }
        for(String key : teamToQtdMembermap.keySet()){
            areaData.getData().add(new XYChart.Data(key, teamToQtdMembermap.get(key)));
        }
        return areaData;
    }
}
