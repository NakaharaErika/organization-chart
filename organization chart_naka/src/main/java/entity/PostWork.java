package entity;

public class PostWork {
	/** 役職ID */
	private int postId;
	/** 役職名 */
	private String postName;
	
	
	/**
	 * コンストラクタ
	 */
	public PostWork() {
	}
	
	
	/**部署IDのゲッターとセッター*/
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	/**部署名のゲッターとセッター*/
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
}
