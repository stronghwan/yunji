package com.yunding.news.model.pojo;

public class PrivateChat {

	private String uid;
	private String fid;
	private String content;
	private String time;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "[uid=" + uid + ", fid=" + fid + ", content=" + content + ", time=" + time + "]";
	}
	
}
