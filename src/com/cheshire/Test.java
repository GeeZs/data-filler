package com.cheshire;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        // 1
        List<Student> studentsList = List.of(
                new Student("Valery", "Popov"),
                new Student("Semyon", "Korzhev"),
                new Student("Peter", "Ivanov"),
                new Student("Maria", "Semenova"),
                new Student("Kolya", "Nesterenko")
                );

        // 2
        List<Subject> subjectList = List.of(
                new Subject("Mathematics", true),
                new Subject("Physics", true),
                new Subject("Astronomy", true),
                new Subject("History", true),
                new Subject("Ethics", false)
        );
        // 3
        List<Mark> marksList = new ArrayList<>();

        for (Student student : studentsList) {
            if (!student.getFirstName().equals("Maria")){
                for (Subject subject : subjectList){
                    if (subject.isMandatory()){
                        marksList.add(new Mark(student, subject,3));
                    }
                }
            }else {
                for (Subject subject : subjectList){
                    if (!subject.getName().equals("History") ||
                            !subject.getName().equals("Ethics")){
                        marksList.add(new Mark(student, subject,3));
                    }else {
                        marksList.add(new Mark(student, subject,5));
                    }
                }
            }
        }
        // 4
        Map<Student, List<Mark>> studentRankData = marksList.stream().collect(Collectors.groupingBy(Mark::getStudent));

        for (Map.Entry<Student, List<Mark>> entry : studentRankData.entrySet()){
            System.out.print("\n" + entry.getKey().getFirstName() +
                                " "+ entry.getKey().getLastName());
            for(Mark mark : entry.getValue()){
                System.out.print(" " + mark.getSubject().getName()+ "-" + mark.getRank());
            }
        }
    }
}
