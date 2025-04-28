package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TaskData {

    private static final Set<Task> allTasks = new TreeSet<>();
    private static final Set<Task> annTasks = new TreeSet<>();
    private static final Set<Task> bobTasks = new TreeSet<>();
    private static final Set<Task> carolTasks = new TreeSet<>();

    static {
        loadTasks();
    }

    private static void loadTasks() {
        try {
            InputStream input = TaskData.class.getResourceAsStream("/data.csv");
            if (input == null) {
                throw new RuntimeException("data.csv not found in package.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            String currentSection = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.equalsIgnoreCase("All Tasks")) {
                    currentSection = "all";
                    continue;
                } else if (line.equalsIgnoreCase("Ann's Tasks")) {
                    currentSection = "ann";
                    continue;
                } else if (line.equalsIgnoreCase("Bob's Tasks")) {
                    currentSection = "bob";
                    continue;
                } else if (line.equalsIgnoreCase("Carol's Tasks:")) {
                    currentSection = "carol";
                    continue;
                }

                String[] parts = Arrays.stream(line.split(","))
                        .map(String::trim)
                        .toArray(String[]::new);

                if (parts.length < 3) {
                    continue;
                }

                String project = parts[0];
                String description = parts[1];
                Priority priority = Priority.valueOf(parts[2].toUpperCase());
                Status status = Status.NOT_ASSIGNED;
                if (parts.length >= 4) {
                    String statusStr = parts[3].toUpperCase().replace(' ', '_');
                    status = Status.valueOf(statusStr);
                }

                String memberName = null;
                switch (currentSection) {
                    case "ann" -> {
                        memberName = "Ann";
                        annTasks.add(new Task(project, description, priority, status, memberName));
                    }
                    case "bob" -> {
                        memberName = "Bob";
                        bobTasks.add(new Task(project, description, priority, status, memberName));
                    }
                    case "carol" -> {
                        memberName = "Carol";
                        carolTasks.add(new Task(project, description, priority, status, memberName));
                    }
                    case "all" -> allTasks.add(new Task(project, description, priority, Status.IN_QUEUE, null));
                    default -> {
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error loading data.csv", e);
        }
    }

    public static Set<Task> getTasks(String name) {
        return switch (name.toLowerCase()) {
            case "ann" -> new TreeSet<>(annTasks);
            case "bob" -> new TreeSet<>(bobTasks);
            case "carol" -> new TreeSet<>(carolTasks);
            default -> new TreeSet<>(allTasks);
        };
    }
}
