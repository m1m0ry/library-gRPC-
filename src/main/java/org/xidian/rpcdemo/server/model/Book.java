package org.xidian.rpcdemo.server.model;

public class Book {
	private int BookID;
	private String name;
	private String author;
	
	public Book(int id, String bookname, String author)
	{
		this.BookID = id;
		this.name = bookname;
		this.author = author;
	}

	public int getBookid() {
		return BookID;
	}
	
	public String getBookname() {
		return name;
	}

	public void setBookname(String bookname) {
		this.name = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}