package com.uttra.taskmanager.bean;

import java.util.Date;
import java.util.Objects;

// description, due date, priority, tags 
public class TaskBean {

	private String name;
	private String description;
	private Date cr_date;
	private Date end_date;
	private String status;
	private int priority;
	private String tags;
	private long time;
	public TaskBean(String name, String description, Date cr_date, Date end_date, String status, int priority,
			String tags, long time) {
		super();
		this.name = name;
		this.description = description;
		this.cr_date = cr_date;
		this.end_date = end_date;
		this.status = status;
		this.priority = priority;
		this.tags = tags;
		this.time = time;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCr_date() {
		return cr_date;
	}
	public void setCr_date(Date cr_date) {
		this.cr_date = cr_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cr_date, description, end_date, name, priority, status, tags, time);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(cr_date, other.cr_date) && Objects.equals(description, other.description)
				&& Objects.equals(end_date, other.end_date) && Objects.equals(name, other.name)
				&& priority == other.priority && Objects.equals(status, other.status)
				&& Objects.equals(tags, other.tags) && Objects.equals(time, other.time);
	}
	@Override
	public String toString() {
		return "TaskBean [name=" + name + ", description=" + description + ", cr_date=" + cr_date + ", end_date="
				+ end_date + ", status=" + status + ", priority=" + priority + ", tags=" + tags + ", time=" + time
				+ "]";
	}
	public TaskBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}