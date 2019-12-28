package com.ChenP;

import java.time.LocalDate;
import java.util.*;

public class Group {


    private String name;
    private LocalDate start;
    private LocalDate lastWeek;
    private int lessonsTotal;
    private int lessonsPerWeek;
    private Map<Integer, Person> students;

    public final Random RANDOM = new Random();

    public Group(String name, LocalDate start, int lessonsTotal, int lessonsPerWeek) {
        this.name = name;
        this.start = start;
        this.lessonsTotal = lessonsTotal;
        this.lessonsPerWeek = lessonsPerWeek;
        this.students = new HashMap<>();
        this.lastWeek = lastWeek();
    }

    public void addStudent(Person student) {
        students.put(students.size() + 1, student);
    }

    public Map<Integer, Person> getStudents() {
        return students;
    }

    public void printGroupInfo() {
        System.out.println();
        System.out.print("*** ");
        System.out.println(getName() + " " + start + " ***");
        System.out.println("Last week starts " + getLastWeek());
        for (Integer i : students.keySet()) {
            System.out.print("#" + i + " ");
            Person tmp = students.get(i);
            System.out.println(tmp.getName());
        }
    }

    public LocalDate getStart() {
        return start;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastWeek() {
        return lastWeek;
    }

    public LocalDate lastWeek() {
        return start.plusDays(lessonsTotal - lessonsPerWeek);
    }

    public void removeStudent(int key) {
        try {
            System.out.println("Student #" + key + " " + students.get(key).getName() + " was removed from the group");
            students.remove(key);
        } catch (Exception e) {
//        System.out.println("Invalid index");
        }
    }

    public void removeStudent(String name) {
        try {
            for (Integer i : students.keySet()) {
                if (students.get(i).getName().equals(name)) {
                    removeStudent(i);
                }
            }
        } catch (Exception e) {
//        System.out.println("Invalid index");
        }
    }

}
