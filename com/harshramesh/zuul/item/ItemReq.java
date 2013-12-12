package com.harshramesh.zuul.item;

public class ItemReq {

	private Item requirement;

	private String desc;

	private String action;

	private boolean active;

	public ItemReq() {
		super();
		active = true;
	}

	public ItemReq(Item requirement, String desc, String action) {
		super();
		this.requirement = requirement;
		this.desc = desc;
		this.action = action;
		active = true;
	}

	public Item getKey() {
		return requirement;
	}

	public void setRequirement(Item requirement) {
		this.requirement = requirement;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void unlock(Item key) {
		if (key.equals(requirement)) {
			this.setActive(false);
			System.out.println(action);
		} else
			System.out.println(requirement.getDesc());
	}

	public void lock() {
		this.setActive(true);
	}

}