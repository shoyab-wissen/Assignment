package Assignment06022025;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Assignment06022025.Student;

public class UniversityStatisitcs {

    public static List<Student> createStudents() {
        List<Student> students = new ArrayList<>();

        // School names
        String[] schools = {
                "Delhi Public School",
                "St. Mary's Academy",
                "Modern High School",
                "Greenwood International",
                "City Montessori School"
        };

        // Common Indian names
        String[] firstNames = {
                "Aarav", "Aditi", "Arjun", "Ananya", "Dev",
                "Diya", "Ishaan", "Kavya", "Krishna", "Mira",
                "Neil", "Priya", "Rahul", "Riya", "Samar",
                "Sanya", "Vihaan", "Zara"
        };

        for (int i = 0; i < 50; i++) {
            String rollNo = String.format("R%03d", i + 1);
            String name = firstNames[i % firstNames.length] + " " + (char) ('A' + i % 26);
            int age = 13 + (i % 6);
            int standard = 6 + (i % 7);
            String school = schools[i % schools.length];
            Gender gender = (i % 2 == 0) ? Gender.MALE : Gender.FEMALE;
            double percentage = 20.0 + (Math.random() * 60);
            percentage = Math.round(percentage * 10.0) / 10.0;

            double totalFees = 50000 + (Math.random() * 50000);
            double feesPaid = totalFees * (0.3 + (Math.random() * 0.7)); // 30-100% paid
            students.add(new Student(
                    rollNo,
                    name,
                    age,
                    standard,
                    school,
                    gender,
                    percentage,
                    totalFees,
                    feesPaid));
        }

        return students;
    }

    public static void main(String[] args) {
        List<Student> university = new ArrayList<>();
        university = createStudents();
        // How many students in each standard
        System.out.println("How many students in each standard");
        Map<Object, Long> eachStandard = university.stream()
                .collect(Collectors.groupingBy(st -> st.getStandard(), Collectors.counting()));
        eachStandard.forEach((key, value) -> {
            System.out.println(key + " standard has " + value + " students");
        });

        System.out.println("-------------------------------------------------");

        // How many students male & female
        System.out.println("How many students male & female");
        Map<Object, Long> studentGender = university.stream()
                .collect(Collectors.groupingBy(st -> st.getGender(), Collectors.counting()));
        studentGender.forEach((key, value) -> {
            System.out.println(value + " students are " + key);
        });

        System.out.println("-------------------------------------------------");
        // How many students have failed and pass (40%)

        System.out.println("How many students have failed and pass (40%) in university");
        Map<Boolean, Long> studentPassFailUni = university.stream()
                .collect(Collectors.partitioningBy(st -> st.getPercentage() < 40.0, Collectors.counting()));
        studentPassFailUni.forEach((key, value) -> {
            System.out.println(value + " students have " + (key ? "Failed" : "Passed"));
        });

        System.out.println("-------------------------------------------------");
        System.out.println("How many students have failed and pass (40%) in School");
        Map<String, List<Student>> schoolWiseStudent = university.stream()
                .collect(Collectors.groupingBy(st -> st.getSchool()));
        schoolWiseStudent.forEach((key, value) -> {
            System.out.println("In School " + key);
            Map<Boolean, Long> studentPassFail = value.stream()
                    .collect(Collectors.partitioningBy(st -> st.getPercentage() < 40.0, Collectors.counting()));
            studentPassFail.forEach((key1, value1) -> {
                System.out.println(value1 + " students have " + (key1 ? "Failed" : "Passed"));
            });
        });

        System.out.println("-------------------------------------------------");
        // Top 3 students (Whole university)
        System.out.println("Top 3 students (Whole university)");
        List<Student> topThree = university.stream().sorted((a, b) -> (int) (a.getPercentage() - b.getPercentage()))
                .limit(3).collect(Collectors.toList());

        topThree.forEach(System.out::println);

        System.out.println("-------------------------------------------------");
        // Top scorer school wise

        System.out.println("Top scorer school wise");
        schoolWiseStudent.forEach((key, value) -> {
            System.out.println("In School " + key);
            List<Student> topStudentSchool = value.stream().sorted((a, b) -> (int) (a.getPercentage()
                    - b.getPercentage())).limit(3)
                    .collect(Collectors.toList());
            topStudentSchool.forEach(System.out::println);
        });

        System.out.println("-------------------------------------------------");
        // Average age of male & female students

        System.out.println("Average age of male & female students");
        int totalCount = university.size();

        Map<Boolean, Long> genderWise = university.stream()
                .collect(Collectors.partitioningBy((st -> st.getGender() == Gender.FEMALE),
                        Collectors.counting()));

        genderWise.forEach((k, v) -> {
            System.out.println("Average " + (k ? "Female" : "Male") + " are " + Double.valueOf(v) / totalCount);
        });

        System.out.println("-------------------------------------------------");
        // Total fees collected school wise
        System.out.println("Total fees collected school wise");
        schoolWiseStudent.forEach((key, value) -> {
            System.out.println(key + " has Collected "
                    + value.stream().mapToDouble(st -> st.getFees().getFeesPaid()).sum());
        });

        System.out.println("-------------------------------------------------");
        // Total fees pending school wise
        System.out.println("Total fees pending school wise");
        schoolWiseStudent.forEach((key, value) -> {
            System.out.println(key + " has pending "
                    + value.stream().mapToDouble(st -> st.getFees().getFeesPending()).sum());
        });

        System.out.println("-------------------------------------------------");
        // Total number of students (University)

        System.out.println(totalCount);
    }
}
