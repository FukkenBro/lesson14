package com.ChenP;

//Написать класс "Группа", для отображения учебной группы в Hillel, который содержит поля:
//
//        - название курса
//        - дата старта занятий
//        - суммарное количество занятий
//        - количество занятий в неделю
//        - список учащихся типа Person
//
//        Написать методы:
//
//        - метод который выдает название группы на основе даты старта и названия курса
//        - метод который выдает дату старта последней недели курса
//        - печатает список студентов с порядковыми номерами
//        - добавить студента
//        - узнать если ли студент с такой фамилией в группе
//        - удалить студента по фамилии или по номеру

//        1.2* У студента есть список групп в которых он учится.
//        При добавлении студента в группу у него прописывается эта группа в списке.


import java.time.LocalDate;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //initializing groups

        Group JAVA_INTRO = new Group(nameGenerator(), randomStart(), 16, 2);
        groupMap.put("JAVA_INTRO", JAVA_INTRO);
        Group JAVA_BEGINNER = new Group(nameGenerator(), randomStart(), 31, 2);
        groupMap.put("JAVA_BEGINNER", JAVA_BEGINNER);
        Group JAVA_VETERAN = new Group(nameGenerator(), randomStart(), 112, 7);
        groupMap.put("JAVA_VETERAN", JAVA_VETERAN);
        Group JAVA_GRANDMASTER = new Group(nameGenerator(), randomStart(), 365, 1);
        groupMap.put("JAVA_GRANDMASTER", JAVA_GRANDMASTER);


        //random student generator
        String[] fNames = {"Alex", "Dan", "Andrew", "Lisa", "Kate", "Victoria", "Quentin", "Guillermo", "Hideo", "Emilia", "Isaac"};
        String[] lNames = {"Phillips", "Smith", "Parker", "Clarke", "Reeves", "Tarantino", "Del Toro", "Kojima"};
        for (int i = 0; i < 20; i++) {
            String randomName = fNames[RANDOM.nextInt(fNames.length)] + " " + lNames[RANDOM.nextInt(lNames.length)];
            String[] courses = {"JAVA_INTRO", "JAVA_BEGINNER", "JAVA_VETERAN", "JAVA_GRANDMASTER"};
            String randomKey = courses[RANDOM.nextInt(courses.length)];
            Person student = new Person(randomName);
            students.add(student);
            Group tmp = groupMap.get(randomKey);
            addStudent(tmp, student);
        }

        JAVA_INTRO.printGroupInfo();
        JAVA_BEGINNER.printGroupInfo();
        JAVA_VETERAN.printGroupInfo();
        JAVA_GRANDMASTER.printGroupInfo();

        //Group search
        for (int i = 0; i < 10; i++) {
            searchGroup(nameGenerator(), starts.get(RANDOM.nextInt(starts.size())));
        }

        //Search student
        for (int i = 0; i < 1; i++) {
            String randomName = fNames[RANDOM.nextInt(fNames.length)] + " " + lNames[RANDOM.nextInt(lNames.length)];
            String[] courses = {"JAVA_INTRO", "JAVA_BEGINNER", "JAVA_VETERAN", "JAVA_GRANDMASTER"};
            String randomKey = courses[RANDOM.nextInt(courses.length)];
            Person student = new Person(randomName);
            Group tmp = groupMap.get(randomKey);
            searchStudent(tmp, student);
        }

        System.out.println();
        //remove student by index
        for (int i = 0; i < 10; i++) {
            String[] courses = {"JAVA_INTRO", "JAVA_BEGINNER", "JAVA_VETERAN", "JAVA_GRANDMASTER"};
            String randomKey = courses[RANDOM.nextInt(courses.length)];
            Group tmp = groupMap.get(randomKey);
            tmp.removeStudent(RANDOM.nextInt(tmp.getStudents().size() + 1));
        }
        System.out.println();
        //remove student by name
        for (int i = 0; i < 100; i++) {
            String randomName = fNames[RANDOM.nextInt(fNames.length)] + " " + lNames[RANDOM.nextInt(lNames.length)];
            String[] courses = {"JAVA_INTRO", "JAVA_BEGINNER", "JAVA_VETERAN", "JAVA_GRANDMASTER"};
            String randomKey = courses[RANDOM.nextInt(courses.length)];
            Group tmp = groupMap.get(randomKey);
            tmp.removeStudent(randomName);
        }
        //Student info
        students.get(RANDOM.nextInt(students.size())).printStudentInfo();

    }

    public static void addStudent(Group group, Person student) {
        student.addGroup(group);
        group.addStudent(student);
    }

    public static final Random RANDOM = new Random();
    public static List<LocalDate> starts = new ArrayList<LocalDate>();
    public static List<Person> students = new ArrayList<>();
    public static Map<String, Group> groupMap = new HashMap<String, Group>();

    public static LocalDate randomStart() {
        LocalDate tmp = LocalDate.of(RANDOM.nextInt(2) + 2018, RANDOM.nextInt(12) + 1, RANDOM.nextInt(28) + 1);
        starts.add(tmp);
        return tmp;
    }

    public static String nameGenerator() {
        String[] prefix = {"Super", "Turbo", "Ultra", "PRO", "X - ", "GTX", "Force", "Deep", "Elite"};
        return prefix[RANDOM.nextInt(prefix.length)] + "Java";
    }

    public static List<Group> searchGroup(String name, LocalDate date) {
        List<Group> result = new ArrayList<>();
        for (Map.Entry entry : groupMap.entrySet()) {
            Group tmp = (Group) entry.getValue();
            if (tmp.getName().equals(name) && date == tmp.getStart()) {
                result.add(tmp);
            }
        }
        if (result.size() != 0) {
            for (int i = 0; i < result.size(); i++) {
                System.out.println();
                System.out.println("Searching for \"" + name + " " + date + "\"");
                System.out.println("Successfully found");
                System.out.printf(result.get(i).getName() + " ");
                System.out.println(result.get(i).getStart());
            }
        }
        return result;
    }

    public static void searchStudent(Group group, Person student) {
        Map<Integer, Person> tmp = group.getStudents();
        int flag = 0;
        for (Integer i : tmp.keySet()) {
            if (tmp.get(i).getName().equals(student)) {
                flag++;
                System.out.println();
                System.out.print("# " + i);
                System.out.println("Found" + tmp.get(i).getName());
            }
        }
        if (flag == 0) {
            System.out.println();
            System.out.println(student.getName() + " was not found in group \"" + group.getName() + "\"");
            ;
        }
    }
}



