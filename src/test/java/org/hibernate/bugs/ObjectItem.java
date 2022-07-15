package org.hibernate.bugs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "obj_item")
@Inheritance(strategy = InheritanceType.JOINED)
public class ObjectItem {
	public ObjectItem() {
	}

	public ObjectItem(final String name) {
		this.name = name;
	}

	@Id
	@Column(name = "obj_item_id", nullable = false, updatable = false, precision = 20)
	@GeneratedValue
	private BigDecimal id;

	@Column(name = "name_txt", nullable = false, length = 100)
	private String name;

	@OneToMany(mappedBy = "objectItem", cascade = CascadeType.ALL)
	private List<ObjectItemComment> comments = new ArrayList<>();

	// comments

	public List<ObjectItemComment> getComments() {
		return comments;
	}

	public ObjectItemComment addComment() {
		final ObjectItemComment comment = new ObjectItemComment(this);
		comments.add(comment);
		return comment;
	}

}
