package jpa.simstargram.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostImg {

    @Id
    @GeneratedValue
    @Column(name = "po_img_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "po_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    @Builder.Default
    @OneToMany(mappedBy = "postImg", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTagMember> postTagMemberList = new ArrayList<>();


    //연과관계 메소드
    public void addPostTagMember(PostTagMember postTagMember) {
        postTagMemberList.add(postTagMember);
        postTagMember.setPostImg(this);
    }
}
