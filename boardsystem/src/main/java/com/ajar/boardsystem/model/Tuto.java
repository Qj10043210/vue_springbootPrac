package com.ajar.boardsystem.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//@SecondaryTable(name = "secondary_table", pkJoinColumns = @PrimaryKeyJoinColumn(name = "foreign_key_column"))
@Entity
@Table(name="tuto")
public class Tuto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="title")
    private String title;

    @Column(name = "description")
	private String description;

	@Column(name = "published")
	private boolean published;

    public Tuto() {
    }

    public Tuto(String title, String description, boolean published) {
        
        this.title = title;
        this.description = description;
        this.published = published;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
    
    
    
}
