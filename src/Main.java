import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countYoungPeople = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("1. Количество несовершеннолетних: " + countYoungPeople + ".");

        List<String> invocatoryPeople = persons.stream()
                .filter(person -> person.getAge() > 17 && person.getAge() < 28)
                .filter(person -> Sex.MAN.equals(person.getSex()))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println("2. Призывники: " + invocatoryPeople.size() + ".");

        List<Person> workPeople = persons.stream()
                .filter(person -> person.getAge() > 17 &&
                        (Sex.MAN.equals(person.getSex()) && person.getAge() < 65) ||
                        (Sex.WOMAN.equals(person.getSex()) && person.getAge() < 60)
                )
                .filter(person -> Education.HIGHER.equals(person.getEducation()))
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .collect(Collectors.toList());
        System.out.println("3. Работники: " + workPeople.size() + ".");
    }
}
