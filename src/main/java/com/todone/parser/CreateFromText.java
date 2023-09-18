package com.todone.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateFromText {
    private List<Task> tasks = new ArrayList<>();

    public void setTasks(StringBuilder tasksFileContent) {
        String stringTasksFileContent = tasksFileContent.toString();

        String[] lines = stringTasksFileContent.split("\n");
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
        }

        for (String textTask : lines) {
            if (isValidTask(textTask)) {
                Task task = new Task();
                task.setStatus(parseStatus(textTask));
                task.setDoneDate(parseDoneDate(textTask));
                task.setPriority(parsePriority(textTask));
                task.setCreationDate(parseCreationDate(textTask));
                task.setProjects(parseProjects(textTask));
                task.setTags(parseTags(textTask));
                task.setDueDate(parseDueDate(textTask));
                task.setAttachments(parseAttachments(textTask));
                task.setDescription(parseDescription(textTask));
                task.setTitle(parseTitle(textTask, task));
                this.tasks.add(task);
            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    private String parseStatus(String task) {
        String regEx = " done:\\d{4}-\\d{2}-\\d{2}| done:";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()) {
            return "done";
        }
        return "undone";
    }

    private String parseDoneDate(String task) {
        String regEx = " done:\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()) {
            String date = matcher.group().trim().substring(5);
            if (isValidDate(date)) {
                return date;
            }
        }
        return "";
    }

    private String parsePriority(String task) {
        String regEx = " \\([A-Z]\\) ";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()) {
            return matcher.group().trim().substring(1, 2);
        }
        return "";
    }

    private String parseCreationDate(String task) {
        String regEx = " created:\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()) {
            String date = matcher.group().trim().substring(8);
            if (isValidDate(date)) {
                return date;
            }
        }
        return "";
    }

    private List<String> parseProjects(String task) {
        Pattern pattern = Pattern.compile(" \\+(\\w+)");
        Matcher matcher = pattern.matcher(task);
        List<String> projects = new ArrayList<>();
        while (matcher.find()) {
            projects.add(matcher.group(1));
        }
        return projects;
    }

    private List<String> parseTags(String task) {
        Pattern pattern = Pattern.compile(" @(\\w+)");
        Matcher matcher = pattern.matcher(task);
        List<String> tags = new ArrayList<>();
        while (matcher.find()) {
            tags.add(matcher.group(1));
        }
        return tags;
    }

    private String parseDueDate(String task) {
        String regEx = " due:\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()) {
            String date = matcher.group().trim().substring(4);
            if (isValidDate(date)) {
                return date;
            }
        }
        return "";
    }

    private List<String> parseAttachments(String task) {
        String regEx = " \\[(.*?)]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        List<String> attachments = new ArrayList<>();
        while (matcher.find()) {
            String match = matcher.group().trim();
            match = match.substring(1, match.length() - 1).trim();
            attachments.add(match);
        }
        return attachments;
    }

    private String parseDescription(String task) {
        String regEx = " \\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(task);
        if (matcher.find()) {
            String match = matcher.group().trim();
            match = match.substring(1, match.length() - 1).trim();
            return match;
        }
        return "";
    }

    private String parseTitle(String taskText, Task task) {
        taskText = taskText.replaceAll(taskText.substring(0, 2), "");
        taskText = taskText.replaceAll("done:" + task.getDoneDate(), "");
        taskText = taskText.replaceAll("\\([A-Z]\\)", "");
        taskText = taskText.replaceAll("created:" + task.getCreationDate(), "");
        taskText = taskText.replaceAll("due:" + task.getDueDate(), "");
        taskText = taskText.replaceAll("\\{" + task.getDescription() + "}", "");
        for (String attachment : task.getAttachments()) {
            taskText = taskText.replaceAll("\\[" + attachment + "]", "");
        }
        for (String project : task.getProjects()) {
            String regEx = "\\+" + project + ".*";
            taskText = taskText.replaceAll(regEx, "");
        }
        for (String tag : task.getTags()) {
            String regEx = "@" + tag + ".*";
            taskText = taskText.replaceAll(regEx, "");
        }
        taskText = taskText.replaceAll("\\s+", " ");
        return taskText.trim();
    }

    private boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private boolean isValidTask(String task) {
        return task.charAt(0) == '-' && task.charAt(1) == ' ';
    }
}
