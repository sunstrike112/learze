package humanresources;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Class chứa các thông tin về Nhân viên Employee

public class Employee extends Staff implements ICalculator {
    private double salary;
    private double overtimeHour;

    public Employee(String id, String name, int age, double payRate, LocalDate startDate,
                    Department department, int numDayOff, double overtimeHour, double salary) {
        super(id, name, age, payRate, startDate, department, numDayOff);
        this.overtimeHour = overtimeHour;
        this.salary = salary;
    }

    public double getOvertimeHour() {
        return overtimeHour;
    }

    public void setOvertimeHour(double overtimeHour) {
        this.overtimeHour = overtimeHour;
    }

    public void setSalary() {
        this.salary = this.calculateSalary();
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        //Định dạng ngày tháng năm theo yyyy-mmm-dd
        LocalDate localDate = this.getStartDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LLL-dd");
        String strDate = localDate.format(formatter);

        //Lấy giá trị của bộ phận phòng ban Department của nhân viên
        String deptName = this.getDepartment().getName();

        //Định dạng số thập phân
        DecimalFormat df = new DecimalFormat("#,###.#");
        //                      id|name|age|pay |date|dept|off |ot |salary
        return String.format("%-5s%-20s%-5d%-10s%-15s%-15s%-10d%-35s%-10s",
                this.getId(), this.getName(), this.getAge(), df.format(this.getPayRate()),
                strDate, deptName, this.getNumDayOff(), df.format(this.getOvertimeHour()), df.format(this.salary));
    }

    @Override
    public double calculateSalary() {
        return (this.getPayRate() * 3_000_000 + this.overtimeHour * 200_000);
    }
}
