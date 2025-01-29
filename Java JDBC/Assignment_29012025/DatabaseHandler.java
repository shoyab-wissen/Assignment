package Assignment_29012025;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseHandler {
    private static DatabaseHandler handler = null;

    private static Connection conn;

    private DatabaseHandler() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/empdb", "postgres", "tiger");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseHandler getHandler() {
        if (handler == null)
            handler = new DatabaseHandler();
        return handler;
    }

    public HashMap<Integer, Employee> getEmployees(int by, String search) {
        String condition = " 1=1";
        if (by != 0 && search != null) {
            if (by == 1)
                condition += " and id = " + search;
            if (by == 2)
                condition += " and designation = '" + search + "'";
            if (by == 3)
                condition += " and name = '" + search + "'";
            if (by == 4)
                condition += " and department = '" + search + "'";
        }
        HashMap<Integer, Employee> empList = new HashMap<>();
        try (
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("select * from employee where" + condition);
            while (rs.next()) {
                Employee emp = new Employee(rs.getString("name"), rs.getInt("age"), rs.getString("designation"),
                        rs.getString("department"), Double.parseDouble(rs.getString("salary")));
                emp.id = rs.getInt("eid");
                empList.put(emp.id, emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    public HashMap<Integer, Employee> getEmployeesSorted(int by) {
        HashMap<Integer, Employee> empList = new HashMap<>();
        try (
                Statement stmt = conn.createStatement();) {
            String query = "select * from employee order by " + (by == 1 ? "eid"
                    : by == 2 ? "name"
                            : by == 3 ? "age"
                                    : by == 4 ? "Designation" : by == 5 ? "Department" : by == 6 ? "salary" : "eid");
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Employee emp = new Employee(rs.getString("name"), rs.getInt("age"), rs.getString("designation"),
                        rs.getString("department"), Double.parseDouble(rs.getString("salary")));
                emp.id = rs.getInt("eid");
                emp.display();
                empList.put(emp.id, emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    public int insertEmployee(Employee emp) {
        try (
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(
                        "select eid from employee where name=? and age=? and designation=? and department=? and salary=?");) {
            System.out.println(getInsertQuery(emp));
            int affected = stmt.executeUpdate(getInsertQuery(emp));
            if (affected > 0) {
                pstmt.setString(1, emp.name);
                pstmt.setInt(2, emp.age);
                pstmt.setString(3, emp.designation);
                pstmt.setString(4, emp.department);
                pstmt.setDouble(5, emp.salary);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static String getInsertQuery(Employee emp) {
        StringBuilder insertQuery = new StringBuilder();
        insertQuery.append("insert into employee(name, age, designation, department, salary) values('");
        insertQuery.append(emp.name);
        insertQuery.append("', ");
        insertQuery.append(emp.age);
        insertQuery.append(", '");
        insertQuery.append(emp.designation);
        insertQuery.append("', '");
        insertQuery.append(emp.department);
        insertQuery.append("', ");
        insertQuery.append(emp.salary);
        insertQuery.append(")");
        return insertQuery.toString();
    }

    public List<String> getDepatments() {
        List<String> result = new ArrayList<>();
        try (
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("select * from department");
            while (rs.next()) {
                result.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getDesignation() {
        List<String> result = new ArrayList<>();
        try (
                Statement stmt = conn.createStatement();) {
            ResultSet rs = stmt.executeQuery("select * from designation");
            while (rs.next()) {
                result.add(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addDesignation(String other) {
        try (
                PreparedStatement pstmt = conn.prepareStatement("insert into designation values(?)");) {
            pstmt.setString(1, other);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDepartment(String other) {
        try (
                PreparedStatement pstmt = conn.prepareStatement("insert into department values(?)");) {
            pstmt.setString(1, other);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean raiseSalary(int id, double increment) {
        try (
                PreparedStatement pstmt = conn
                        .prepareStatement("update employee set salary = salary + ? where eid = ?");) {
            pstmt.setDouble(1, increment);
            pstmt.setInt(2, id);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        try (
                PreparedStatement pstmt = conn
                        .prepareStatement("delete from employee where eid=?");) {
            pstmt.setInt(1, id);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
