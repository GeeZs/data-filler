package com.cheshire;
// Please, do not use internet or any other sources of information during the test
// tast shouldn’t take more than 30 minutes
// There are 3 classes: Student, Subject and Mark
// - Fill the data according to the following conditions (use lists)
// 1. It is known that there are such students in the group: Valery Popov, Semyon Korzhev, Peter Ivanov, Maria Semenova and Kolya Nesterenko
// 2. Mathematics, Physics, Astronomy, History and Ethics are learned by this group and all subjects are mandatory excluding Ethics. It is optional.
// 3. Fill the data about marks if it is known that students have mark 3 for mandatory subjects and Maria has mark 5 for History and Ethics.
// 4. Please print results in a such way:
//   Popova Valeria Mathematics-1 Physics-2 Astronomy-0 History-1 Ethics-3 (do not display the subject info if there is no data about it)

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
