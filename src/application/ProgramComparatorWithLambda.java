package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entities.Product;

public class ProgramComparatorWithLambda {

	public static void main(String args[]) {
		
		Locale.setDefault(Locale.US);
		List<Product> list = new ArrayList<>();
		
		list.add(new Product("TV",5600.0));
		list.add(new Product("Monitor",2000.0));
		list.add(new Product("Impressora",1500.0));
		list.add(new Product("Camera",800.0));
		list.add(new Product("HD",350.0));
		list.add(new Product("Cable",200.0));
		
		
		//--------------- COMPARATOR -----------------------
		
		// Utilizando fun��o anonima com express�es Lambda		
		Comparator<Product> comp = (p1, p2) ->{
			return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
		};
		
		list.sort(comp);
		
		// metodo estatico para imprimir lista
		printProducts(list);		
		
		
		
		// � possivel implementar a funcao anonima dentro da fun�ao sort
		list.sort((p1,p2) -> p1.getPrice().compareTo(p2.getPrice()));
		
		printProducts(list);
		
		
		
		//----------------------------PREDICATE-----------------------------------------
		
		
		// Fun��o anonima predicate ( implementa m�todo test )
		
		Predicate<Product> pred = p -> p.getPrice() >= 5000.00;
		list.removeIf(pred);
		printProducts(list);
		
		
		
		System.out.println();
		// utilizando predicato para retornar bollean que ser� removido pela fun��o removeIF
		list.removeIf(p -> p.getPrice() >= 2000.00);
		printProducts(list);
		
			
		
		
		//---------------------CONSUMER----------------------------------------------
		
		// func�o anonima para atualizar pre�os de produtos ( Implementa m�todo accept )
		System.out.println();
		Consumer<Product> con = p -> p.setPrice(p.getPrice() * 1.1);
		list.forEach(con);
		printProducts(list);		
		
		
				
		//UTILIZANDO express�o lambda Consumer para atualizar pre�os dos produtos
		System.out.println();
		list.forEach(p -> p.setPrice(p.getPrice() * 1.1));
		
		//utilizando reference metod para imprimir as variaveis
		System.out.println();
		System.out.println("List of Products ");
		list.forEach(System.out::println); //--> para funcionar � necess�rio implementar o metodo toString
		
	
		// ----------------------FUNCTION----------------------------
		
		//Utilizando Function para efetuar uma opera��o // Function<T,P> . recebe tipo de entrada e devolve um tipo diferente na saida
		// Implementa m�todo Apply
		
		System.out.println();
		Function<Product,String> func = p -> p.getName().toUpperCase();
		
		//express�o map executa uma function dentro de um stream de dados.. 
		//necess�rio converter lista para stream e depois converter para lista novamente
		
		List<String> names = list.stream().map(func).collect(Collectors.toList());
		names.forEach(System.out::println);
		
		// utilizando fun�ao inline
		System.out.println();
		List<String> names2 = list.stream().map(p -> p.getName().toUpperCase()).collect(Collectors.toList());
		names2.forEach(System.out::println);
		
		
		
		//-------------------------------CRIANDO FUNCOENS QUE RECEBEM FUNCOES COMO ARGUMENTO --------------------------------------------------
		
		
		System.out.println();
		double sum = filteredSum(list, p -> p.getName().toUpperCase().charAt(0) == 'C');
		System.out.println("Total dos produtos que come�am com c: " + String.format("%.2f", sum));
		
		
		
		
		
		
	}
	
	public static void printProducts(List<Product> list) {
		
		System.out.println();
		System.out.println("List of Products ");
		for (Product productForComparator : list) {
			System.out.println("Product [ " + productForComparator.getName() + " - $" + String.format("%.2f",productForComparator.getPrice()) + " ]");
		}
		
		
	}
	
	
	public static double filteredSum(List<Product> list, Predicate<Product> criteria) {
		
		double sum = 0.0;
		for (Product p : list) {
			
			if(criteria.test(p)) {
				sum += p.getPrice();
			}
		}
				
		return sum;
		
	}

}
