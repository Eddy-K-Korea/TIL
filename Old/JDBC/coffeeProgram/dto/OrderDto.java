package dto;

public class OrderDto {

	private String id;
	private String coffee;
	private String c_size;
	private String syrup;
	private String other;
	private int count;
	
	public OrderDto() {
	}

	public OrderDto(String id, String coffee, String c_size, String syrup, String other, int count) {
		super();
		this.id = id;
		this.coffee = coffee;
		this.c_size = c_size;
		this.syrup = syrup;
		this.other = other;
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoffee() {
		return coffee;
	}

	public void setCoffee(String coffee) {
		this.coffee = coffee;
	}

	public String getC_size() {
		return c_size;
	}

	public void setC_size(String c_size) {
		this.c_size = c_size;
	}

	public String getSyrup() {
		return syrup;
	}

	public void setSyrup(String syrup) {
		this.syrup = syrup;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
