package org.thoughts.on.java.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.log4j.Logger;

@Entity
public class Book implements Comparable<Book> {

	@Transient
	private Logger log = Logger.getLogger(Book.class.getSimpleName());
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	private int version;

	private String title;

	@ManyToMany
	@JoinTable(name = "book_author", 
				joinColumns = { @JoinColumn(name = "fk_book") }, 
				inverseJoinColumns = { @JoinColumn(name = "fk_author") })
	private List<Author> authors = new ArrayList<Author>();

	public Long getId() {
		return this.id;
	}

	public int getVersion() {
		return this.version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public void addAuthor(Author a) {
		this.authors.add(a);
		a.getBooks().add(this);
	}

	public void removeAuthor(Author a) {
		this.authors.remove(a);
		a.getBooks().remove(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Book)) {
			return false;
		}
		Book other = (Book) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + "]";
	}

	@Override
	public int compareTo(Book o) {
		log.info("compare");
		return title.compareTo(o.getTitle());
	}
	
	
}