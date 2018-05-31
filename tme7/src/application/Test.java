package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import services.Cell;

public class Test {

	
	public static void main(String[] args) {
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map"));
			Cell[][] cells = (Cell[][]) ois.readObject();
			
			StringBuilder string = new StringBuilder();
			for (int i = 0; i < cells.length; i++) {
				string.append("\n");
				for (int j = 0; j < cells[i].length; j++) {
					string.append("row " + j + " col " + i + " " + cells[j][i] + " | ");

				}
			}
			System.out.println(string.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
