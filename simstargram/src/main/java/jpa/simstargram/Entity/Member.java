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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "mem_id")
    private Long id;

    @Column(name = "mem_username", unique = true)
    private String username;

    @Column(name = "mem_nickname", unique = true)
    private String nickname;

    @Column(name = "mem_name")
    private String name;

    @Column(name = "mem_password")
    private String password;

    @Column(name = "mem_state")
    private int state;  //1:활성화 2:비활성화 3:탈퇴

    @Column(name = "mem_mem_social")
    private int social; //1:일반회원 2:구글 3:카카오

    @Column(name = "mem_provider")
    private String provider;

    @Column(name = "mem_img")
    private String image;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<PostTagMember> postTagMemberList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL)
    private List<Follow> followerList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "toMember", cascade = CascadeType.ALL)
    private List<Follow> followList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PostSave> postSaveList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<PostLike> postLikeList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Reply> replyList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<ReplyLike> replyLikeList = new ArrayList<>();

    //연관관계 메소드
    void addPost(Post post) {
        postList.add(post);
        post.setMember(this);
    }

    //생성 메소드
    void addFollow(Member followMember) {
        Follow follow = Follow.builder().fromMember(this).toMember(followMember).build();
        this.followList.add(follow);
    }

    void addPostSave(Post post) {
        PostSave postSave = PostSave.builder().member(this).post(post).build();
        postSaveList.add(postSave);
    }

}
