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
		
		// Utilizando função anonima com expressões Lambda		
		Comparator<Product> comp = (p1, p2) ->{
			return p1.getName().toUpperCase().compareTo(p2.getName().toUpperCase());
		};
		
		list.sort(comp);
		
		// metodo estatico para imprimir lista
		printProducts(list);		
		
		
		
		// é possivel implementar a funcao anonima dentro da funçao sort
		list.sort((p1,p2) -> p1.getPrice().compareTo(p2.getPrice()));
		
		printProducts(list);
		
		
		
		//----------------------------PREDICATE-----------------------------------------
		
		
		// Função anonima predicate ( implementa método test )
		
		Predicate<Product> pred = p -> p.getPrice() >= 5000.00;
		list.removeIf(pred);
		printProducts(list);
		
		
		
		System.out.println();
		// utilizando predicato para retornar bollean que será removido pela função removeIF
		list.removeIf(p -> p.getPrice() >= 2000.00);
		printProducts(list);
		
			
		
		
		//---------------------CONSUMER----------------------------------------------
		
		// funcão anonima para atualizar preços de produtos ( Implementa método accept )
		System.out.println();
		Consumer<Product> con = p -> p.setPrice(p.getPrice() * 1.1);
		list.forEach(con);
		printProducts(list);		
		
		
				
		//UTILIZANDO expressão lambda Consumer para atualizar preços dos produtos
		System.out.println();
		list.forEach(p -> p.setPrice(p.getPrice() * 1.1));
		
		//utilizando reference metod para imprimir as variaveis
		System.out.println();
		System.out.println("List of Products ");
		list.forEach(System.out::println); //--> para funcionar é necessário implementar o metodo toString
		
	
		// ----------------------FUNCTION----------------------------
		
		//Utilizando Function para efetuar uma operação // Function<T,P> . recebe tipo de entrada e devolve um tipo diferente na saida
		// Implementa método Apply
		
		System.out.println();
		Function<Product,String> func = p -> p.getName().toUpperCase();
		
		//expressão map executa uma function dentro de um stream de dados.. 
		//necessário converter lista para stream e depois converter para lista novamente
		
		List<String> names = list.stream().map(func).collect(Collectors.toList());
		names.forEach(System.out::println);
		
		// utilizando funçao inline
		System.out.println();
		List<String> names2 = list.stream().map(p -> p.getName().toUpperCase()).collect(Collectors.toList());
		names2.forEach(System.out::println);
		
		
		
		//-------------------------------CRIANDO FUNCOENS QUE RECEBEM FUNCOES COMO ARGUMENTO --------------------------------------------------
		
		
		System.out.println();
		double sum = filteredSum(list, p -> p.getName().toUpperCase().charAt(0) == 'C');
		System.out.println("Total dos produtos que começam com c: " + String.format("%.2f", sum));
		
		
		
		
		
		
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
