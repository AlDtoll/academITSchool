package ru.academits.tolmachev.main;

import ru.academits.tolmachev.person.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Балуемся с лямбдами)");

        Person Ivan = new Person("Ivan", 30);
        Person Petr = new Person("Petr", 10);
        Person Pavel = new Person("Pavel", 24);
        Person Daniel = new Person("Daniel", 33);
        Person Sergei = new Person("Sergei", 38);
        Person Ivan2 = new Person("Ivan", 48);
        Person Petr2 = new Person("Petr", 17);
        Person Pavel2 = new Person("Pavel", 48);
        Person Ivan3 = new Person("Ivan", 11);

        ArrayList<Person> personArrayList = new ArrayList<>(Arrays.asList(Ivan, Petr, Pavel, Daniel, Sergei, Ivan2, Petr2, Pavel2, Ivan3));

        // Получаем список уникальных имен
        List<String> uniqueNameList = personArrayList.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());
        // Печатаем его
        System.out.println("Уникальные имена: " + uniqueNameList.stream()
                .collect(Collectors.joining(", ", "", ".")));

        //Получаем список несовершеннолетних
        Stream<Person> youngPersonCollection = personArrayList.stream()
                .filter(person -> person.getAge() < 18);
        //Считаем их средний возраст
        double averageAge = youngPersonCollection.mapToInt(Person::getAge).average().getAsDouble();
        System.out.println("Их средний возраст :" + averageAge);

        // Получаем Map при помощи группировки
        Map<String, List<Person>> personsByName = personArrayList.stream()
                .collect(Collectors.groupingBy(p -> p.getName()));
        personsByName.forEach((name, p) -> System.out.printf("name %s: %s\n", name, p.stream()
                .mapToInt(Person::getAge)
                .average().getAsDouble()));

        // Получаем список людей в возрасте от 20 до 45
        List<Person> nonYoungPersonCollection = personArrayList.stream()
                .filter(person -> person.getAge() >= 20 && person.getAge() <= 45)
                .collect(Collectors.toList());
        // Печатаем его отсортированным в порядке убывания
        System.out.print("Люди в возрасте от 25 до 45 в порядке убывания: " + nonYoungPersonCollection.stream()
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", ", "", ".")));


    }
}
