package entity;

public class DepartmentWork {
	/** 部署ID */
	private String depId;
	/** 部署名 */
	private String depName;
	
	
	/**
	 * コンストラクタ
	 */
	public DepartmentWork() {
	}

	
	public DepartmentWork(String depId) {
		this.depId = depId;
		
	}
	
	
	/**部署IDのゲッターとセッター*/
	public String getdepId() {
		return depId;
	}
	public void setDepId(String depId) {
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
