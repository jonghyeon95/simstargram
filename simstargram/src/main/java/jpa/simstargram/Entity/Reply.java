package jpa.simstargram.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reply {

    @Id
    @GeneratedValue
    @Column(name = "rep_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    @Column(name = "rep_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rep_parent")
    private Reply parentReply;

    public void setParentReply(Reply parentReply) {
        this.parentReply = parentReply;
    }

    @Builder.Default
    @OneToMany(mappedBy = "parentReply")
    private List<Reply> childReplyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "reply")
    private List<ReplyLike> replyLikeList = new ArrayList<>();


    //연관관계메소드
    public void addChildReply(Reply childReply) {
        this.childReplyList.add(childReply);
        childReply.setParentReply(this);
    }


}
