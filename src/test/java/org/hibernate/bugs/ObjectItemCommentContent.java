package org.hibernate.bugs;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

class ObjectItemCommentContentId implements Serializable {
    public ObjectItemCommentId objectItemComment;
    public BigDecimal index;
}

@Entity
@Table(name = "obj_item_cmt_cntnt")
@IdClass(ObjectItemCommentContentId.class)
public class ObjectItemCommentContent {
    public ObjectItemCommentContent() {
    }

    public ObjectItemCommentContent(final ObjectItemComment objectItemComment, final String text) {
        this.objectItemComment = objectItemComment;
        this.text = text;
    }

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "obj_item_id", referencedColumnName = "obj_item_id", nullable = false)
    @JoinColumn(name = "obj_item_cmt_ix", referencedColumnName = "obj_item_cmt_ix", nullable = false)
    private ObjectItemComment objectItemComment;

	@Id
	@Column(name = "obj_item_cmt_cntnt_ix", nullable = false, precision = 20)
    @GeneratedValue
	private BigDecimal index = BigDecimal.ZERO;

    @Column(name = "txt", nullable = false, length = 2000)
    private String text;

    public String getText() {
        return text;
    }
}
