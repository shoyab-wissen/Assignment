package impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import entity.LegalDocument;

public class Implementation {
	public static void main(String[] args) {
		try {
			File file = new File("C:\\Users\\Wissen\\Desktop\\Project\\Assignment\\Spring\\ws\\JsonDemo\\src\\main\\java\\impl\\document.json");
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String data = "";
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				data += line + "\n";
			}
			LegalDocument document = LegalDocument.fromJson(data);
			System.out.println(document);
			bufferedReader.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
