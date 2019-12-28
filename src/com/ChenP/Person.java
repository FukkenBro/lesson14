package com.ChenP;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private List<Group> groups;


    public Person(String name) {
        this.groups = new ArrayList<Group>();
        this.name = name;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void printStudentInfo() {
        System.out.println();
        System.out.print("*** ");
        System.out.println(name + " ***");
        for (int i = 0; i < groups.size(); i++) {
            System.out.println("Attending " + groups.get(i).getName() + " since " + groups.get(i).getStart());
            System.out.println("Last week starts " + groups.get(i).getLastWeek());
        }
    }

    public String getName() {
        return name;
    }
}
