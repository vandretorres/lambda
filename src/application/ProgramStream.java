package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Product;

public class ProgramStream {

	public static void main(String[] args) {


		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		//String path = sc.nextLine();
		
		String path = "c:\\temp\\in.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Product> list = new ArrayList<>();

			String line = br.readLine();
			while ( line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
							

			// UTILIZANDO STREAM PARA CALCULAR MEDIA DE PRODUTOS DE UM ARQUIVO
			// funçao map executa uma funçao como criteria
			// reduce efetua calculo
		
			
			double avg = list.stream()
					.map(p -> p.getPrice())
					.reduce(0.0, (x,y) -> x + y) / list.size();
			
			System.out.println("Average price: " + String.format("%.2f", avg));
		
				
			
			// Funçao para comparar Nomes de STrings será utilizado na função sorted			
			Comparator<String> comp = ( s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			// stream --> converte lista para Stream
			// filter --> filtra dados atraves de expressão lambda
			// map --> executa uma função dentro da função
			// sorted reverse --> ordena reverso
			// convert stream para lista novamente
		    List<String> names = list.stream()
		    		.filter(p -> p.getPrice() < avg)
		    		.map(p-> p.getName())
		    		.sorted(comp.reversed())
		    		.collect(Collectors.toList());
		    
		  names.forEach(System.out::println);
		
		
		}catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}finally {
			
			System.out.println("");
			
		}
		
		sc.close();
		
		
		



	}

}
