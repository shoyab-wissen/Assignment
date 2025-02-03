package com.Employee;


import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler implements AutoCloseable {
    private static DatabaseHandler handler = null;
    private static MongoClient client = null;
    private static MongoDatabase empDb = null;
    private static MongoCollection<Document> empCollection = null;
    private static MongoCollection<Document> deptCollection = null;
    private static MongoCollection<Document> desigCollection = null;

    private DatabaseHandler() {
        try {
        	client = MongoClients.create("mongodb://localhost:27017");
        	empDb = client.getDatabase("demodb");
        	empCollection = empDb.getCollection("Employee");
        	deptCollection = empDb.getCollection("department");
        	desigCollection = empDb.getCollection("designation");
//            rset = RowSetProvider.newFactory().createJdbcRowSet();
//            rset.setUrl("jdbc:postgresql://localhost:5432/empdb");
//            rset.setUsername("postgres");
//            rset.setPassword("tiger");
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
        Bson filter = Filters.empty();
        if (by != 0 && search != null) {
            if (by == 1)
            	filter = Filters.eq("id", search);
//                condition += " and id = " + search;
            if (by == 2)
            	filter = Filters.eq("designation", search);
//                condition += " and designation = '" + search + "'";
            if (by == 3)
            	filter = Filters.eq("name", search);
//                condition += " and name = '" + search + "'";
            if (by == 4)
            	filter = Filters.eq("department", search);
//                condition += " and department = '" + search + "'";
        }
        HashMap<Integer, Employee> empList = new HashMap<>();
        try {
        	FindIterable<Document> employees = empCollection.find(filter);
//            rset.setCommand("select * from employee where" + condition);
//            rset.execute();
            for (Document d : employees) {
                Employee emp = new Employee(d.getInteger("eid"),d.getString("name"), d.getInteger("age"), d.getString("designation"),
                        d.getString("department"), d.getDouble("salary"));
                emp.id = d.getInteger("eid");
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
//            String query = "select * from employee order by " + (by == 1 ? "eid"
//                    : by == 2 ? "name"
//                            : by == 3 ? "age"
//                                    : by == 4 ? "Designation" : by == 5 ? "Department" : by == 6 ? "salary" : "eid");
//            System.out.println(query);
            Bson sort = Sorts.ascending((by == 1 ? "eid"
                    : by == 2 ? "name"
                            : by == 3 ? "age"
                                    : by == 4 ? "Designation" : by == 5 ? "Department" : by == 6 ? "salary" : "eid"));
            FindIterable<Document> sortedEmployee = empCollection.find().sort(sort);
//            rset.setCommand(query);
//            rset.execute();
            for (Document d : sortedEmployee) {
                Employee emp = new Employee(d.getInteger("eid"),d.getString("name"), d.getInteger("age"), d.getString("designation"),
                        d.getString("department"), d.getDouble("salary"));
                emp.id = d.getInteger("eid");
                empList.put(emp.id, emp);
            }
            System.out.println(empList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empList;
    }

    public int insertEmployee(Employee emp) {
        try {
//            rset.setCommand("select eid, name, age, designation, department, salary from employee");
//            rset.execute();
//            rset.moveToInsertRow();
//            rset.setInt(1, emp.id);
//            rset.setString(2, emp.name);
//            rset.setInt(3, emp.age);
//            rset.setString(4, emp.designation);
//            rset.setString(5, emp.department);
//            rset.setDouble(6, emp.salary);
//            rset.insertRow();
//            
//            // System.out.println(getInsertQuery(emp));
//            // rset.setCommand(getInsertQuery(emp));
//            // rset.execute();
//            // boolean affected = rset.rowInserted();
//            // if (affected) {
//            String query = "select eid from employee where name='" + emp.name + "' and age=" + emp.age
//                    + " and designation='" + emp.designation + "' and department='" + emp.department
//                    + "' and salary=" + emp.salary;
//            rset.setCommand(query);
//            rset.execute();
//            System.out.println(rset.first());
//            // rset.next();
//            return rset.getInt(1);
//            // }
        	Document employee = new Document();
        	employee.append("eid", emp.id);
        	employee.append("name", emp.name);
        	employee.append("age", emp.age);
        	employee.append("designation", emp.designation);
        	employee.append("department", emp.department);
        	employee.append("salary", emp.salary);
        	InsertOneResult result = empCollection.insertOne(employee);
        	return 1;
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
//            rset.setCommand("select * from department");
//            rset.execute();
        	FindIterable<Document> department = deptCollection.find();
            for (Document d : department) {
                result.add(d.getString("department"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getDesignation() {
        List<String> result = new ArrayList<>();
        try {
//            rset.setCommand("select * from designation");
//            rset.execute();
        	FindIterable<Document> designation = desigCollection.find();
            for (Document d : designation) {
                result.add(d.getString("designation"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addDesignation(String other) {
        try {
//            rset.setCommand("select * from designation");
//            rset.execute();
//            rset.moveToInsertRow();
//            rset.updateString(1, other);
//            // rset.setCommand("insert into department values('" + other + "')");
//            rset.insertRow();
//            return rset.rowInserted();
        	if(desigCollection.find(Filters.eq("designation", other)).first() == null) {
        		InsertOneResult designation = desigCollection.insertOne(new Document().append("designation", other));
        		return designation.wasAcknowledged();
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addDepartment(String other) {
        try {
//            rset.setCommand("select * from department");
//            rset.execute();
//            rset.moveToInsertRow();
//            rset.updateString(1, other);
//            // rset.setCommand("insert into department values('" + other + "')");
//            rset.insertRow();
//            return rset.rowInserted();
        	InsertOneResult department = deptCollection.insertOne(new Document().append("department", other));
        	return department.wasAcknowledged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean raiseSalary(int id, double increment) {
        try {

//            rset.setCommand("update employee set salary = salary + " + increment + " where eid = " + id);
//            rset.execute();
//            return rset.rowUpdated();
        	Bson filter = Filters.eq("eid", id);
        	Bson update = Updates.set("salary", empCollection.find(filter).first().getDouble("salary") + increment);
        	UpdateResult result = empCollection.updateOne(filter, update);
        	return result.wasAcknowledged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        try {
//            rset.setCommand("select * from employee where eid=" + id);
//            rset.execute();
//            rset.first();
//            rset.deleteRow();
//            return rset.rowDeleted();
        	Bson filter = Filters.eq("eid", id);
        	DeleteResult result = empCollection.deleteOne(filter);
        	return result.wasAcknowledged();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        client.close();
    }
}
