import java.util.List;
import java.util.Scanner;

public class Main {
    private static DatabaseManager dbManager = new DatabaseManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. View All Employees");
            System.out.println("2. Add New Employee");
            System.out.println("3. Update Employee Information");
            System.out.println("4. Search Employee by ID, Name, or SSN");
            System.out.println("5. Update Salary by Percentage for a Range");
            System.out.println("6. Generate Pay Statement");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAllEmployees();
                    break;
                case 2:
                    addNewEmployee(scanner);
                    break;
                case 3:
                    updateEmployeeInformation(scanner);
                    break;
                case 4:
                    searchEmployee(scanner);
                    break;
                case 5:
                    updateSalaryByRange(scanner);
                    break;
                case 6:
                    generatePayStatement(scanner);
                    break;
                case 7:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }

    private static void viewAllEmployees() {
        List<Employee> employees = dbManager.getAllEmployees();
        System.out.println("\n--- All Employees ---");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    private static void addNewEmployee(Scanner scanner) {
        System.out.println("\n--- Add New Employee ---");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("SSN (No dashes): ");
        String ssn = scanner.nextLine();
        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine();
        System.out.print("Division: ");
        String division = scanner.nextLine();
        System.out.print("Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Hire Date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();

        dbManager.addEmployee(firstName, lastName, ssn, jobTitle, division, salary, hireDate);
        System.out.println("Employee added successfully.");
    }

    private static void updateEmployeeInformation(Scanner scanner) {
        System.out.println("\n--- Update Employee Information ---");
        System.out.print("Enter Employee ID to update: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        System.out.print("New First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("New Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("New SSN (No dashes): ");
        String ssn = scanner.nextLine();
        System.out.print("New Job Title: ");
        String jobTitle = scanner.nextLine();
        System.out.print("New Division: ");
        String division = scanner.nextLine();
        System.out.print("New Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("New Hire Date (YYYY-MM-DD): ");
        String hireDate = scanner.nextLine();
    
        boolean success = dbManager.updateEmployee(empId, firstName, lastName, ssn, jobTitle, division, salary, hireDate);
        if (success) {
            System.out.println("Employee information updated successfully.");
        } else {
            System.out.println("");
        }
    }
    
    

    private static void searchEmployee(Scanner scanner) {
        System.out.println("\n--- Search Employee ---");
        System.out.println("Search by: ");
        System.out.println("1. Employee ID");
        System.out.println("2. Name");
        System.out.println("3. SSN");
        System.out.print("Choice: ");
        int searchChoice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (searchChoice) {
            case 1:
                System.out.print("Enter Employee ID: ");
                int empId = scanner.nextInt();
                scanner.nextLine(); // consume newline
                Employee empById = dbManager.searchEmployeeById(empId);
                System.out.println(empById != null ? empById : "Employee not found.");
                break;
            case 2:
                System.out.print("Enter Employee Name: ");
                String name = scanner.nextLine();
                List<Employee> employeesByName = dbManager.searchEmployeeByName(name);
                employeesByName.forEach(System.out::println);
                break;
            case 3:
                System.out.print("Enter SSN: ");
                String ssn = scanner.nextLine();
                Employee empBySsn = dbManager.searchEmployeeBySsn(ssn);
                System.out.println(empBySsn != null ? empBySsn : "Employee not found.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void updateSalaryByRange(Scanner scanner) {
        System.out.println("\n--- Update Salary by Range ---");
        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();
        System.out.print("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();
        System.out.print("Enter percentage increase (e.g., 5 for 5%): ");
        double percentage = scanner.nextDouble();
    
        int rowsUpdated = dbManager.updateSalaryForRange(minSalary, maxSalary, percentage);
        if (rowsUpdated > 0) {
            System.out.println("Salary updated successfully.");
        } else {
            System.out.println("No employees found with a salary in the specified range.");
        }
    }
    

    private static void generatePayStatement(Scanner scanner) {
        System.out.println("\n--- Generate Pay Statement ---");
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Pay Date (YYYY-MM-DD): ");
        String payDate = scanner.nextLine();
        System.out.print("Enter Amount Paid: ");
        double amountPaid = scanner.nextDouble();

        dbManager.addPayStatement(empId, payDate, amountPaid);
        System.out.println("Pay statement added successfully.");
    }
}
