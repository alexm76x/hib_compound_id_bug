package org.hibernate.bugs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

class ObjectItemCommentId implements Serializable {
    public BigDecimal objectItem;
    public BigDecimal index;
}

@Entity
@Table(name = "obj_item_cmt")
@IdClass(ObjectItemCommentId.class)
public class ObjectItemComment {
    public ObjectItemComment() {
    }

    public ObjectItemComment(final ObjectItem objectItem) {
        this.objectItem = objectItem;
    }

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "obj_item_id", nullable = false)
    private ObjectItem objectItem;

	@Id
	@Column(name = "obj_item_cmt_ix", nullable = false, precision = 20)
    @GeneratedValue
	private BigDecimal index = BigDecimal.ZERO;

    @OneToMany(mappedBy = "objectItemComment", cascade = CascadeType.ALL)
    private List<ObjectItemCommentContent> contents = new ArrayList<>();

    public List<ObjectItemCommentContent> getContents() {
        return contents;
    }

    public ObjectItemCommentContent addContent(final String text) {
        final ObjectItemCommentContent content = new ObjectItemCommentContent(this, text);
        contents.add(content);
        return content;
    }
}
