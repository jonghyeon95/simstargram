package jpa.simstargram.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostTagMember {

    @Id
    @GeneratedValue
    @Column(name = "po_tag_mem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_img_id")
    private PostImg postImg;

    public void setPostImg(PostImg postImg) {
        this.postImg = postImg;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;



    //연관관계 메소드
    public void addTagMember(Member member) {
        this.member = member;
        member.getPostTagMemberList().add(this);
    }
}
