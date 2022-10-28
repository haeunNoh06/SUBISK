package menu;

public class MenuVo {

	private String menuId;
	private String menuName;
	private int price;
	private String imageFileName;
	
	public MenuVo(String menuId, String menuName, int price, String imageFileName) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.price = price;
		this.imageFileName = imageFileName;
		
	}
	
	//classic 메뉴 정보 넣기
//	public int classic (MenuVo menuVo) {
//		
//	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
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
