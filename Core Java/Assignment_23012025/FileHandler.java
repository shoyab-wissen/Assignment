package Assignment_23012025;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileHandler implements Closeable {
    private BufferedReader readFile = null;
    private PrintWriter writeFile = null;
    private File myFile = null;

    private static FileHandler fileHandler;

    private FileHandler() {
        try {
            myFile = new File(
                    "C:\\Users\\Wissen\\Desktop\\Project\\Assignment\\Assignment_23012025\\employees.csv");
            if (!myFile.exists())
                myFile.createNewFile();
            readFile = new BufferedReader(new FileReader(
                    myFile));
            writeFile = new PrintWriter(new FileWriter(
                    myFile, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        readFile.close();
        writeFile.close();
    }

    public BufferedReader getReader() {
        return readFile;
    }

    public PrintWriter getWriter() {
        return writeFile;
    }

    public static FileHandler getObject() {
        if (fileHandler == null) {
            fileHandler = new FileHandler();
            System.out.println("Object Created");
        }
        return fileHandler;
    }

    public int writeEmployee(Employee emp) {
        try {
            String empString = emp.toString();
            writeFile.write(empString);
            writeFile.flush();
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public HashMap<Integer, Employee> readFile() {
        HashMap<Integer, Employee> emplList = new HashMap<>();
        String line = null;
        List<String> designations = Arrays.asList("MANAGER", "CLERK", "PROGRAMMER", "CEO");
        try {
            if (!myFile.exists()) {
                myFile.createNewFile();
            }
            while ((line = readFile.readLine()) != null) {
                String[] data = line.split(",");
                Employee emp = Employee.getEmployee(designations.indexOf(data[3]) + 1, data[1], Integer.parseInt(
                        data[0]), Integer.parseInt(data[2]));
                emplList.put(Integer.parseInt(
                        data[0]), emp);
                emp.salary = Double.parseDouble(data[4]);

            }
        } catch (IOException e) {
            System.out.println("There was an error reading the file");
        }
        return emplList;
    }

    public int removeEmployee(Employee emp) {
        String line = null;
        File tempFile = new File("C:\\Users\\Wissen\\Desktop\\Project\\Assignment\\Assignment_23012025\\temp.csv");
        try {
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
            while ((line = readFile.readLine()) != null) {
                if (!emp.toString().equals(line)) {
                    tempWriter.write(line);
                }
            }
            tempWriter.flush();
            tempWriter.close();
            if (myFile.exists())
                myFile.delete();
            tempFile.renameTo(myFile);
        } catch (IOException e) {
            System.out.println("There was an error reading the file");
        }

        return 0;
    }

    public int raiseSalary(Employee emp) {
        String line = null;
        File tempFile = new File("C:\\Users\\Wissen\\Desktop\\Project\\Assignment\\Assignment_23012025\\temp.csv");
        try {
            if (!tempFile.exists()) {
                tempFile.createNewFile();
            }
            BufferedWriter tempWriter = new BufferedWriter(new FileWriter(tempFile));
            while ((line = readFile.readLine()) != null) {
                if (!emp.toString().equals(line)) {
                    tempWriter.write(line);
                } else {
                    emp.raiseSalary();
                    tempWriter.write(emp.toString());
                }
            }
            tempWriter.flush();
            tempWriter.close();
            if (myFile.exists())
                myFile.delete();
            tempFile.renameTo(myFile);
        } catch (IOException e) {
            System.out.println("There was an error reading the file");
        }

        return 0;
    }
}
