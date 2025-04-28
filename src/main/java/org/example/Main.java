package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<Task> allTasks = TaskData.getTasks("all");
        Set<Task> annTasks = TaskData.getTasks("Ann");
        Set<Task> bobTasks = TaskData.getTasks("Bob");
        Set<Task> carolTasks = TaskData.getTasks("Carol");

        Set<Task> fullTaskList = getUnion(Arrays.asList(allTasks, annTasks, bobTasks, carolTasks));
        System.out.println("Full Task List:");
        fullTaskList.forEach(System.out::println);

        System.out.println("\nAssigned Tasks:");
        Set<Task> assignedTasks = getUnion(Arrays.asList(annTasks, bobTasks, carolTasks));
        assignedTasks.forEach(System.out::println);

        System.out.println("\nTasks to Assign:");
        Set<Task> tasksToAssign = getDifference(allTasks, assignedTasks);
        tasksToAssign.forEach(System.out::println);

        System.out.println("\nTasks assigned to multiple employees:");
        Set<Task> multipleAssignedTasks = getIntersect(
                getIntersect(annTasks, bobTasks),
                carolTasks
        );
        multipleAssignedTasks.forEach(System.out::println);
    }

    public static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> result = new TreeSet<>();
        for (Set<Task> s : sets) {
            result.addAll(s);
        }
        return result;
    }

    public static Set<Task> getIntersect(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new TreeSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    public static Set<Task> getDifference(Set<Task> set1, Set<Task> set2) {
        Set<Task> result = new TreeSet<>(set1);
        result.removeAll(set2);
        return result;
    }
}
