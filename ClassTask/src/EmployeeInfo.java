import java.util.*;
import java.time.*;

enum EmployeeType {
    OFFICER, STAFF
}

class EmployeeGenerics<T> {
    private int id;
    private String name;
    private LocalDate DOB;
    private String email;
    private LocalDate joinDate;
    private T employeeType;

    public EmployeeGenerics(int id, String name, String DOB, String email, String joinDate, T employeeType) {
        this.id = id;
        this.name = name;
        this.DOB = LocalDate.parse(DOB);
        this.email = email;
        this.joinDate = LocalDate.parse(joinDate);
        this.employeeType = employeeType;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public LocalDate getDOB() {
        return DOB;
    }
    public String getEmail() {
        return email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }
    public T getEmployeeType() {
        return employeeType;
    }
}

public class EmployeeInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<EmployeeGenerics<EmployeeType>> employees = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            System.out.println("Enter details for Employee " + i);
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Date of Birth (yyyy-MM-dd): ");
            String DOB = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Joining Date (yyyy-MM-dd): ");
            String joinDate = scanner.nextLine();
            System.out.print("Employee Type (OFFICER/STAFF): ");
            String employeeTypeStr = scanner.nextLine();

            EmployeeType employeeType = EmployeeType.valueOf(employeeTypeStr.toUpperCase());

            employees.add(new EmployeeGenerics<>(id, name, DOB, email, joinDate, employeeType));
            System.out.println();
        }

        for (EmployeeGenerics<EmployeeType> emp : employees) {
            int vacationDays = calculateLeavedays(emp, "Vacation");
            int sickDays = calculateLeavedays(emp, "Sick");

            System.out.println("Employee ID: " + emp.getId());
            System.out.println("Employee Name: " + emp.getName());
            System.out.println("Date of Birth: " + emp.getDOB());
            System.out.println("Email: " + emp.getEmail());
            System.out.println("Joining Date: " + emp.getJoinDate());
            System.out.println("Employee Type: " + emp.getEmployeeType());
            System.out.println("Total Vacation Leave: " + vacationDays);
            System.out.println("Total Sick Leave: " + sickDays);
            System.out.println();
        }
    }

    private static int calculateLeavedays(EmployeeGenerics<EmployeeType> employee, String leaveType) {
        int totalLeaveDays = 0;
        if (employee.getEmployeeType() == EmployeeType.OFFICER) {
            totalLeaveDays = leaveType.equals("Vacation") ? 15 : 10;
        } else if (employee.getEmployeeType() == EmployeeType.STAFF) {
            totalLeaveDays = leaveType.equals("Vacation") ? 10 : 7;
        }

        LocalDate currentYearStartDate = LocalDate.of(2023, 1, 1);
        LocalDate currentYearEndDate = LocalDate.of(2023, 12, 31);
        LocalDate joiningDate = employee.getJoinDate();
        long daysWorked = (currentYearEndDate.getDayOfYear() - joiningDate.getDayOfYear() + 1);

        double leaveFraction = (daysWorked * 1.0 / 365);

        int leaveDays = (int) (leaveFraction * totalLeaveDays + 0.5); // Rounded by adding 0.5
        return leaveDays;
    }
}
