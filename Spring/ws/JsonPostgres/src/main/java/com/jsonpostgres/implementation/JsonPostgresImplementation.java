package com.jsonpostgres.implementation;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

public class JsonPostgresImplementation {
	public static void main(String[] args) {
//		int width = 100;
//        int height = 20;
//
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = image.getGraphics();
//        g.setFont(new Font("TIMESNEWROMAN", Font.ITALIC, 16));
//
//        Graphics2D graphics = (Graphics2D) g;
//        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
//                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//        graphics.drawString("SARVESH", 10, 20);
//
//        //save this image
//
//        for (int y = 0; y < height; y++) {
//            StringBuilder sb = new StringBuilder();
//            for (int x = 0; x < width; x++) {
//
//                sb.append(image.getRGB(x, y) == -16777216 ? " " : "-");
//
//            }
//
//            if (sb.toString().trim().isEmpty()) {
//                continue;
//            }
//
//            System.out.println(sb);
//        }
		DatabaseHandler handler = DatabaseHandler.getHandler();
		Employee emp = new Employee();
		emp.setAge(25);
		emp.setDesignation("PROGRAMMER");
		emp.setId(2);
		emp.setName("KAIFALI SAYYAD");
//		handler.insertEmployee(emp);
//		handler.updateEmployee(emp, "KAIFALI SAYYAD", "name");
		handler.deleteEmployee("KAIFALI SAYYAD", "name");
		List<String> employees = handler.getAllEmployees();
		for(String employee : employees) {
			System.out.println(Employee.fromJson(employee).toJson());
		}
	}
}
