package entity;
//ユーザーの固有idはセッションとして保存
public class DBWork {
	/** 社員コード */
	private String empCode;
	/** 性 */
	private String familyName;
	/** 名 */
	private String lastName;
	/** 部署id */
	private int depId;
	/** 役職id */
	private int postId;
	/** 部署名 */
	private String depName;
	/** 役職名 */
	private String postName;

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
    
    /**部署IDのゲッターとセッター*/
    public int getDepId() {
    	return depId;
    }
    public void setDepId(int depId) {
    	this.depId = depId;
    }
    
    /**役職IDのゲッターとセッター*/
    public int getPostId() {
    	return postId;
    }
    public void setPostId(Integer postId) {
    	if(postId != null) {
    		this.postId = postId;
    	} else {
    		this.postId = 0;
    	}
    }
    
    /**部署のゲッターとセッター*/
    public String getDepName() {
    	return depName;
    }
    public void setDepName(String depName) {
    	this.depName = depName;
    }
    
    /**役職のゲッターとセッター*/
    public String getPostName() {
    	return postName;
    }
    public void setPostName(String postName) {
    	if(postName != null) {
    		this.postName = postName;
    	} else {
    		this.postName = "平社員・その他";
    	}
    }
    
    
}
