package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import entities.ProductForComparator;

public class ProgramComparator01 {
	
	public static void main(String args[]) {
		
		Locale.setDefault(Locale.US);
		List<ProductForComparator> list = new ArrayList<>();
		
		list.add(new ProductForComparator("TV",5600.0));
		list.add(new ProductForComparator("Monitor",20000.0));
		list.add(new ProductForComparator("Impressora",1500.0));
		
		Collections.sort(list);
		
		System.out.println("List of Products ");
		for (ProductForComparator productForComparator : list) {
			System.out.println("Product [ " + productForComparator.getName() + " - $" + String.format("%.2f",productForComparator.getPrice()) + " ]");
		}
		
	}

}
