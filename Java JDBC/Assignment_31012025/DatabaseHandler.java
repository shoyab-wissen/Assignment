package Assignment_31012025;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler implements AutoCloseable {
    private static DatabaseHandler handler = null;
    private static JdbcRowSet rset = null;

    private DatabaseHandler() {
        try {
            rset = RowSetProvider.newFactory().createJdbcRowSet();
            rset.setUrl("jdbc:postgresql://localhost:5432/empdb");
            rset.setUsername("postgres");
            rset.setPassword("tiger");
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
        try {
            rset.setCommand("select * from employee where" + condition);
            rset.execute();
            while (rset.next()) {
                Employee emp = new Employee(rset.getString("name"), rset.getInt("age"), rset.getString("designation"),
                        rset.getString("department"), Double.parseDouble(rset.getString("salary")));
                emp.id = rset.getInt("eid");
                empList.put(emp.id, emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    public HashMap<Integer, Employee> getEmployeesSorted(int by) {
        HashMap<Integer, Employee> empList = new HashMap<>();
        try {
            String query = "select * from employee order by " + (by == 1 ? "eid"
                    : by == 2 ? "name"
                            : by == 3 ? "age"
                                    : by == 4 ? "Designation" : by == 5 ? "Department" : by == 6 ? "salary" : "eid");
            System.out.println(query);
            rset.setCommand(query);
            rset.execute();
            while (rset.next()) {
                Employee emp = new Employee(rset.getString("name"), rset.getInt("age"), rset.getString("designation"),
                        rset.getString("department"), Double.parseDouble(rset.getString("salary")));
                emp.id = rset.getInt("eid");
                emp.display();
                empList.put(emp.id, emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    public int insertEmployee(Employee emp) {
        try {
            rset.setCommand("select eid, name, age, designation, department, salary from employee");
            rset.execute();
            rset.moveToInsertRow();
            rset.setInt(1, emp.id);
            rset.setString(2, emp.name);
            rset.setInt(3, emp.age);
            rset.setString(4, emp.designation);
            rset.setString(5, emp.department);
            rset.setDouble(6, emp.salary);
            rset.insertRow();
            // System.out.println(getInsertQuery(emp));
            // rset.setCommand(getInsertQuery(emp));
            // rset.execute();
            // boolean affected = rset.rowInserted();
            // if (affected) {
            String query = "select eid from employee where name='" + emp.name + "' and age=" + emp.age
                    + " and designation='" + emp.designation + "' and department='" + emp.department
                    + "' and salary=" + emp.salary;
            rset.setCommand(query);
            rset.execute();
            System.out.println(rset.first());
            // rset.next();
            return rset.getInt(1);
            // }

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
        try {
            rset.setCommand("select * from department");
            rset.execute();
            while (rset.next()) {
                result.add(rset.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getDesignation() {
        List<String> result = new ArrayList<>();
        try {
            rset.setCommand("select * from designation");
            rset.execute();
            while (rset.next()) {
                result.add(rset.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addDesignation(String other) {
        try {
            rset.setCommand("select * from designation");
            rset.execute();
            rset.moveToInsertRow();
            rset.updateString(1, other);
            // rset.setCommand("insert into department values('" + other + "')");
            rset.insertRow();
            return rset.rowInserted();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDepartment(String other) {
        try {
            rset.setCommand("select * from department");
            rset.execute();
            rset.moveToInsertRow();
            rset.updateString(1, other);
            // rset.setCommand("insert into department values('" + other + "')");
            rset.insertRow();
            return rset.rowInserted();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean raiseSalary(int id, double increment) {
        try {

            rset.setCommand("update employee set salary = salary + " + increment + " where eid = " + id);
            rset.execute();
            return rset.rowUpdated();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        try {
            rset.setCommand("select * from employee where eid=" + id);
            rset.execute();
            rset.first();
            rset.deleteRow();
            return rset.rowDeleted();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        rset.close();
    }
}
