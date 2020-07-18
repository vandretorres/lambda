package entities;

public class ProductForComparator implements Comparable<ProductForComparator> {
	
	private String name;
	private Double price;
	
	
	public ProductForComparator() {
		super();
	}


	public ProductForComparator(String name, Double price) {
	
		this.name = name;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	@Override
	public int compareTo(ProductForComparator other) {
		 return name.toUpperCase().compareTo(other.getName().toUpperCase());
	}
	
	
	
	
	

}
