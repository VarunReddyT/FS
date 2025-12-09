// You are given data about students in a course. Each student is either 
// an undergraduate (UG) or a postgraduate (PG).

// Each student has:
//     - name (no spaces)
//     - age (positive integer)
//     - baseScore (0–100)
//     - extraCredits (0–20)
//     - level (UG or PG)

// The final score must be computed differently depending on the level:
//     For UGStudent:
//         finalScore = baseScore + 0.5 * extraCredits (capped at 100)
    
//     For PGStudent:
//         finalScore = baseScore + 1.0 * extraCredits (capped at 100)

// You are also given:
//     - passThreshold (0–100): minimum finalScore to pass.
//     - topK: number of top students (by finalScore) to display.


// Requirements:
// -------------
// * Encapsulation & POJO:
//     Implement an abstract POJO class Student with private fields 
//     (name, age, baseScore, extraCredits, level) and proper getters/setters.

// * Abstraction & Inheritance:
//     - abstract class Student with abstract method double calculateFinalScore().
//     - UGStudent and PGStudent extend Student and override calculateFinalScore().

// * Polymorphism:
//     - Store all students in a List<Student>.
//     - Use calculateFinalScore() polymorphically without checking level 
//       with if/switch in main.

// * Exception Handling:
//     - Create InvalidStudentDataException extends Exception.
//     - Throw it from the Student constructor / factory if:
//         - age <= 0
//         - baseScore < 0 or baseScore > 100
//         - extraCredits < 0 or extraCredits > 20
//         - level is not UG or PG
//     - Also treat topK < 1 or topK > N as invalid.
//     - In main, catch the exception and print exactly:
//         - INVALID_INPUT
//         and then terminate the program.

// * Lambda Functions & Streams:
//     Use streams to:
//         - Filter students with finalScore >= passThreshold.
//         - Sort them by finalScore in descending order using a lambda comparator.
//         - Limit to topK students for printing.

// * Output Rules (for valid input):
//     - First line: PASSED <count>
//         where <count> = number of students with finalScore >= passThreshold.
    
//     - Next lines (up to topK lines):
//         <name> <finalScore>
//         where <finalScore> is printed with one decimal place (e.g., 85.0).
        
//     - If fewer than topK students pass, print only the ones who passed (0 or more lines).

// Input Format:
// -------------
// Line 1: integer N (number of students).

// Next N lines:
// name age baseScore extraCredits level, name: String without spaces
// age: int, baseScore: double, extraCredits: double, level: UG or PG

// Line N + 2: integer passThreshold
// Line N + 3: integer topK

// Output Format (Valid Input):
// ----------------------------
//    - Line 1:
//         PASSED <count>
    
//    - Next lines (up to topK):
//         <name> <finalScore>

// Output (Invalid Input):
// -----------------------
// INVALID_INPUT


// Sample Testcase 1
// -----------------
// Input
// -----
// 4
// Alice 19 80 10 UG
// Bob 22 75 5 PG
// Cara 20 60 12 UG
// Dan 23 50 15 PG
// 70
// 2

// Output:
// -------
// PASSED 2
// Alice 85.0
// Bob 80.0

// Explanation:
// -------------
// Final scores:
//     Alice (UG): 80 + 0.5*10 = 85.0
//     Bob (PG): 75 + 5 = 80.0
//     Cara (UG): 60 + 0.5*12 = 66.0
//     Dan (PG): 50 + 15 = 65.0
// Passed (>=70): Alice, Bob ⇒ 2 students
// Top 2 by score: Alice (85.0), Bob (80.0)


// Sample Testcase 2
// -----------------
// Input
// -----
// 2
// X 18 105 5 UG
// Y 20 70 5 PG
// 60
// 1

// Output:
// -------
// INVALID_INPUT

// Explanation:
// -------------
// baseScore = 105 is invalid (>100) ⇒ exception

import java.util.*;
import java.util.stream.Collectors;

// Main class
public class StudentPerformanceAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //implement code
        int N = sc.nextInt();
        sc.nextLine(); // consume newline
        List<Student> students = new ArrayList<>();
        try {
            for (int i = 0; i < N; i++) {
                String line = sc.nextLine();
                String[] parts = line.split(" ");
                String name = parts[0];
                int age = Integer.parseInt(parts[1]);
                double baseScore = Double.parseDouble(parts[2]);
                double extraCredits = Double.parseDouble(parts[3]);
                String level = parts[4];

                Student student;
                if (level.equals("UG")) {
                    student = new UGStudent(name, age, baseScore, extraCredits, level);
                } else if (level.equals("PG")) {
                    student = new PGStudent(name, age, baseScore, extraCredits, level);
                } else {
                    throw new InvalidStudentDataException();
                }
                students.add(student);
            }

            int passThreshold = sc.nextInt();
            int topK = sc.nextInt();
            if (topK < 1 || topK > N) {
                throw new InvalidStudentDataException();
            }

            List<Student> passedStudents = students.stream()
                    .filter(s -> s.calculateFinalScore() >= passThreshold)
                    .sorted((s1, s2) -> Double.compare(s2.calculateFinalScore(), s1.calculateFinalScore()))
                    .limit(topK)
                    .collect(Collectors.toList());

            System.out.println("PASSED " + passedStudents.size());
            for (Student s : passedStudents) {
                System.out.printf("%s %.1f%n", s.getName(), s.calculateFinalScore());
            }
        } catch (InvalidStudentDataException e) {
            System.out.println("INVALID_INPUT");
        } finally {
            sc.close();
        }
    }
}

// Custom checked exception
class InvalidStudentDataException extends Exception {
    //implement code
    public InvalidStudentDataException() {
        super();
    }
}

// Abstract POJO class (encapsulation + abstraction)
abstract class Student {
    private String name;
    private int age;
    private double baseScore;
    private double extraCredits;
    private String level;

    public Student(String name, int age, double baseScore, double extraCredits, String level)
            throws InvalidStudentDataException {
        setName(name);
        setAge(age);
        setBaseScore(baseScore);
        setExtraCredits(extraCredits);
        setLevel(level);
    }

    // Encapsulated fields with getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) throws InvalidStudentDataException {
        if (age <= 0) throw new InvalidStudentDataException();
        this.age = age;
    }
    public double getBaseScore() {
        return baseScore;
    }
    public void setBaseScore(double baseScore) throws InvalidStudentDataException {
        if (baseScore < 0 || baseScore > 100) throw new InvalidStudentDataException();
        this.baseScore = baseScore;
    }
    public double getExtraCredits() {
        return extraCredits;
    }
    public void setExtraCredits(double extraCredits) throws InvalidStudentDataException {
        if (extraCredits < 0 || extraCredits > 20) throw new InvalidStudentDataException();
        this.extraCredits = extraCredits;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) throws InvalidStudentDataException {
        if (!level.equals("UG") && !level.equals("PG")) throw new InvalidStudentDataException();
        this.level = level;
    }

    public abstract double calculateFinalScore();
}

// UGStudent inherits Student and implements calculateFinalScore
class UGStudent extends Student {
    //implement code
    public UGStudent(String name, int age, double baseScore, double extraCredits, String level)
            throws InvalidStudentDataException {
        super(name, age, baseScore, extraCredits, level);
    }
    @Override
    public double calculateFinalScore() {
        double finalScore = getBaseScore() + 0.5 * getExtraCredits();
        return Math.min(finalScore, 100.0);
    }
}

// PGStudent inherits Student and implements calculateFinalScore
class PGStudent extends Student {
    //implement code
    public PGStudent(String name, int age, double baseScore, double extraCredits, String level)
            throws InvalidStudentDataException {
        super(name, age, baseScore, extraCredits, level);
    }
    @Override
    public double calculateFinalScore() {
        double finalScore = getBaseScore() + 1.0 * getExtraCredits();
        return Math.min(finalScore, 100.0);
    }
}
