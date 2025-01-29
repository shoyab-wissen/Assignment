package Assignment_24012025;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FileHandler implements Closeable {
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private BufferedReader readFile = null;
    private PrintWriter writeFile = null;
    private File myFile = null;

    private static FileHandler fileHandler;

    private FileHandler() {
        try {
            myFile = new File(
                    "C:\\Users\\Wissen\\Desktop\\Project\\Assignment\\Assignment_24012025\\employees.ser");
            if (!myFile.exists())
                myFile.createNewFile();
            objectInputStream = new ObjectInputStream(new FileInputStream(myFile));
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(myFile));
            // readFile = new BufferedReader(new FileReader(
            // myFile));
            // writeFile = new PrintWriter(new FileWriter(
            // myFile, true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
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

    public HashMap<Integer, Employee> readFromObjectStream() {
        try {
            HashMap<Integer, Employee> employees = (HashMap<Integer, Employee>) objectInputStream.readObject();
            return employees;
        } catch (Exception e) {
            System.out.println("There's no file created yet");
        }
        HashMap<Integer, Employee> emptyMap = new HashMap<Integer, Employee>();
        return emptyMap;
    }

    public int writeObjectToFile(HashMap<Integer, Employee> employees) {
        try {
            objectOutputStream.writeObject(employees);
        } catch (IOException e) {
            System.out.println("There was an error writing to file");
            return -1;
        }
        return 0;
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
