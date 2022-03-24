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
public class Post extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "po_id")
    private Long id;

    @Column(name = "po_content")
    private String content;

    @Column(name = "po_reply_state")
    private int replyState; //1:가능 2:불가능

    @Column(name = "po_secret")
    private int secret; //1:공개 2:비공개

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostImg> imgList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> postLikeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replyList = new ArrayList<>();

    //연관관계 메소드
    public void addPostImg(PostImg postImg) {
        imgList.add(postImg);
        postImg.setPost(this);
    }

    //생성 메소드
    public void addLike(Member fromMember) {
        PostLike postLike = PostLike.builder().post(this).member(fromMember).build();
        postLikeList.add(postLike);
    }


}
