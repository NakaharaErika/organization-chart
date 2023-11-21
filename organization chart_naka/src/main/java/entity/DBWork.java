package entity;
//ユーザーの固有idはセッションとして保存
public class DBWork {
	/** 社員コード */
	private String empCode;
	/** 性 */
	private String familyName;
	/** 名 */
	private String lastName;

	/**
	 * コンストラクタ
	 */
	public DBWork() {
	}
	
	public DBWork(String empCode) {
		this.empCode = empCode;
	}
	
	/**社員コードのゲッターとセッター*/
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	
	/**性のゲッターとセッター*/
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	/**名のゲッターとセッター*/
	public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    
}
