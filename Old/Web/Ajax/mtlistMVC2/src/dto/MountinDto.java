package dto;

import java.io.Serializable;

public class MountinDto implements Serializable {

	private String location;
	private String name;
	private double height;
	private String manage;
	
	
	public MountinDto() {
	}

	public MountinDto(String location, String name, double height, String manage) {
		super();
		this.location = location;
		this.name = name;
		this.height = height;
		this.manage = manage;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public double getHeight() {
		return height;
	}

	public String getManage() {
		return manage;
	}

	@Override
	public String toString() {
		return "MountinDto [location=" + location + ", name=" + name + ", height=" + height + ", manage=" + manage
				+ "]";
	}
	
	
}
