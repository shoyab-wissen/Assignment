package com.jsonpostgres.implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler implements AutoCloseable {
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
	@Override
	public void close() throws Exception {
		conn.close();
	}
	
	public int insertEmployee(Employee emp) {
        try (
                Statement stmt = conn.createStatement();
                PreparedStatement pstmt = conn.prepareStatement(
                        "insert into emp_json(data) values(?::jsonb)");) {
        	pstmt.setString(1, emp.toJson());
            int affected = pstmt.executeUpdate();
            System.out.println(affected);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
	
	public List<String> getAllEmployees() {
		List<String> data = new ArrayList<>();
		try(Statement stmt = conn.createStatement()){
			ResultSet rs =  stmt.executeQuery("select * from emp_json");
			while(rs.next()) {
				data.add(rs.getString("data"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public <T> void updateEmployee(Employee emp, T data, String columnName){
		try(PreparedStatement stmt = conn.prepareStatement("update emp_json set data = ?::jsonb where data ->> ? = ?")){
			stmt.setString(1, emp.toJson());
			stmt.setString(2, columnName);
			if(columnName == "id" || columnName == "age") {
				stmt.setInt(3, Integer.parseInt(data.toString()));
			}
			else {
				stmt.setString(3, data.toString());
			}
			stmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public <T> void deleteEmployee(T data, String columnName){
		try(PreparedStatement stmt = conn.prepareStatement("delete from emp_json where data ->> ? = ?")){
//			stmt.setString(1, emp.toJson());
			stmt.setString(1, columnName);
			if(columnName == "id" || columnName == "age") {
				stmt.setInt(2, Integer.parseInt(data.toString()));
			}
			else {
				stmt.setString(2, data.toString());
			}
			stmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
