/**
 * 
 */
package org.bm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Black Moon
 *
 */
@Entity
@Table(name="books")
@NamedQuery(name = "Book.getAll", query = "SELECT b from Book b")
public class Book implements Key {
	private int id;	
	private int year;
	private int subjectid;
	
	private String name;
	private String author;
	private String publish;
	private String subjectname;
	
	private Subject subject; 
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	@Transient
	public String getSubjectname() {
		String name = null;		
		if (subject!= null)
			name = subject.getName();
			
		return name;
	}
	
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	
	@Transient
	public int getSubjectid() {
		return subjectid;
	}
	
	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}
	
	@ManyToOne
	@JoinColumn(name="subjectid")
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	@Override
	public String toString(){
		return name + ". " + author;
	}
}
