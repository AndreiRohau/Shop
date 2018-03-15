package by.asrohau.shop.bean;

public class Product {
	private int id;
	private String name;
	private String type;
	private String price;
	private String description;

	public Product() {}

	public Product(String name, String type, String price, String description) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
	}

	public Product(int id, String name, String type, String price, String description) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Product product = (Product) o;

		if (id != product.id) return false;
		if (name != null ? !name.equals(product.name) : product.name != null) return false;
		if (type != null ? !type.equals(product.type) : product.type != null) return false;
		if (price != null ? !price.equals(product.price) : product.price != null) return false;
		return description != null ? description.equals(product.description) : product.description == null;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (type != null ? type.hashCode() : 0);
		result = 31 * result + (price != null ? price.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", price='" + price + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}