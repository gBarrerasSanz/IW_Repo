package common;

import java.net.URI;

public class ToDoElement {
	
	private int id;
	private String task;
	private String context;
	private String project;
	private int priority;
	private URI href;
	
	public ToDoElement() {}
	
	public ToDoElement(int id, String task, String context, String project, int priority, URI href) {
		super();
		this.id = id;
		this.task = task;
		this.context = context;
		this.project = project;
		this.priority = priority;
		this.href = href;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public void setHref(URI href) {
		this.href = href;
	}
	
	public URI getHref() {
		return href;
	}
	
}
