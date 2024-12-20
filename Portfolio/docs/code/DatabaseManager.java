import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // Method to connect to the database
    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/main";
        String user = "root";
        String password = "newpassword";    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // 1. View All Employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employees";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("ssn"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    // 2. Add New Employee
    public void addEmployee(String firstName, String lastName, String ssn, String jobTitle, String division, double salary, String hireDate) {
        String query = "INSERT INTO Employees (first_name, last_name, ssn, job_title, division, salary, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, ssn);
            stmt.setString(4, jobTitle);
            stmt.setString(5, division);
            stmt.setDouble(6, salary);
            stmt.setDate(7, Date.valueOf(hireDate));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Update Employee Information
// Update all employee information
public boolean updateEmployee(int empId, String firstName, String lastName, String ssn, String jobTitle, String division, double salary, String hireDate) {
    String query = "UPDATE Employees SET first_name = ?, last_name = ?, ssn = ?, job_title = ?, division = ?, salary = ?, hire_date = ? WHERE emp_id = ?";

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, ssn);
        stmt.setString(4, jobTitle);
        stmt.setString(5, division);
        stmt.setDouble(6, salary);
        stmt.setDate(7, Date.valueOf(hireDate));
        stmt.setInt(8, empId);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected == 0) {
            System.out.println("Error: No employee found with ID " + empId);
            return false; // Indicate failure
        }

        return true; // Indicate success
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Indicate failure due to an exception
    }
}


    // 4. Search Employee by ID, Name, or SSN
    public Employee searchEmployeeById(int empId) {
        String query = "SELECT * FROM Employees WHERE emp_id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("ssn"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> searchEmployeeByName(String name) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Employees WHERE first_name LIKE ? OR last_name LIKE ?";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("ssn"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee searchEmployeeBySsn(String ssn) {
        String query = "SELECT * FROM Employees WHERE ssn = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, ssn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("ssn"),
                        rs.getString("job_title"),
                        rs.getString("division"),
                        rs.getDouble("salary"),
                        rs.getDate("hire_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 5. Update Salary by Percentage for a Range
    public int updateSalaryForRange(double minSalary, double maxSalary, double percentage) {
        String query = "UPDATE Employees SET salary = salary + (salary * ? / 100) WHERE salary >= ? AND salary <= ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setDouble(1, percentage);
            stmt.setDouble(2, minSalary);
            stmt.setDouble(3, maxSalary);
    
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected; // Return the number of rows updated
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Return 0 if an error occurs
        }
    }
    

    // 6. Generate Pay Statement
    public void addPayStatement(int empId, String payDate, double amountPaid) {
        String query = "INSERT INTO PayStatements (emp_id, pay_date, amount_paid) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, empId);
            stmt.setDate(2, Date.valueOf(payDate));
            stmt.setDouble(3, amountPaid);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Generate Report: Total Pay by Job Title
    public List<String> getTotalPayByJobTitle() {
        String query = "SELECT job_title, SUM(salary) AS total_salary FROM Employees GROUP BY job_title";
        List<String> results = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                results.add("Job Title: " + rs.getString("job_title") + ", Total Salary: " +
                        rs.getDouble("total_salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    // Generate Report: Total Pay by Division
    public List<String> getTotalPayByDivision() {
        String query = "SELECT division, SUM(salary) AS total_salary FROM Employees GROUP BY division";
        List<String> results = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                results.add("Division: " + rs.getString("division") + ", Total Salary: " +
                        rs.getDouble("total_salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}


