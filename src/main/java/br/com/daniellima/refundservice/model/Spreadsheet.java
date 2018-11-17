package br.com.daniellima.refundservice.model;

import java.util.List;

public class Spreadsheet {
	private String project;
	private String client;
	private String consultant;
	private String email;
	private List<Expense> expenses;
	
	public void sortExpenseList(){
		expenses.sort((e1, e2) -> e1.getDate().compareTo(e2.getDate()));
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}