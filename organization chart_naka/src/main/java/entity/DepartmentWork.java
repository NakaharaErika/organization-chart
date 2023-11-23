package entity;

public class DepartmentWork {
	/** 部署ID */
	private int depId;
	/** 部署名 */
	private String depName;
	
	
	/**
	 * コンストラクタ
	 */
	public DepartmentWork() {
	}

	
	public DepartmentWork(int depId) {
		this.depId = depId;
		
	}
	
	
	/**部署IDのゲッターとセッター*/
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	
	/**部署名のゲッターとセッター*/
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
}
