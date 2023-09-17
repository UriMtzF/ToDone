package com.todone.parser;

import com.todone.tasks.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private List<Task> tasks;
    public List<String> getTasks(StringBuilder tasksFileContent){
        String stringTasksFileContent = tasksFileContent.toString();

        String[] lines = stringTasksFileContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        for (String textTask : lines) {
            Task task = new Task();
            if (isValidTask(textTask)){
                task.setDoneDate(parseDoneDate(textTask));
                task.setPriority(parsePriority(textTask));
                task.setCreationDate(parseCreationDate(textTask));
                task.setProjects(parseProjects(textTask));
                task.setTags(parseTags(textTask));
                task.setDueDate(parseDueDate(textTask));
                //task.setAttachments(parseAttachments(textTask));
            }
        }
        return null;
    }

    public String parseStatus(String task){
        return String.valueOf(task.charAt(0));
    }
    public String parseDoneDate(String task){
        String regEx = "done:\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        String date = matcher.group().substring(5);
        if (isValidDate(date)){
            return date;
        }
        return "";
    }
    public String parsePriority(String task){
        String regEx = "\\([A-Z]\\)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()){
            return matcher.group().substring(1,2);
        }
        return "";
    }
    public String parseCreationDate(String task){
        String regEx = "created:\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        String date = matcher.group().substring(8);
        if (isValidDate(date)){
            return date;
        }
        return "";
    }
    public List<String> parseProjects(String task){
        Pattern pattern = Pattern.compile("\\+(\\w+)");
        Matcher matcher = pattern.matcher(task);
        List<String> projects = new ArrayList<>();
        while (matcher.find()){
            projects.add(matcher.group(1));
        }
        return projects;
    }
    public List<String> parseTags(String task){
        Pattern pattern = Pattern.compile("@(\\w+)");
        Matcher matcher = pattern.matcher(task);
        List<String> tags = new ArrayList<>();
        while (matcher.find()){
            tags.add(matcher.group(1));
        }
        return tags;
    }
    public String parseDueDate(String task){
        String regEx = "due:\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        String date = matcher.group().substring(4);
        if (isValidDate(date)){
            return date;
        }
        return "";
    }
//    public List<String> parseAttachments(String task){
//
//    }

    public boolean isValidDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public boolean isValidTask(String task){
        return task.charAt(0) == '-' && task.charAt(1) == ' ';
    }
}
