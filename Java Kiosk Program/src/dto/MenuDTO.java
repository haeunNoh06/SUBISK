package dto;

public class MenuDTO {

	private int menuId;
	private String menuName;
	private int price;
	private String imageFileName;
	
	public MenuDTO(int menuId, String menuName, int price, String imageFileName) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.price = price;
		this.imageFileName = imageFileName;
		
	}
	
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
