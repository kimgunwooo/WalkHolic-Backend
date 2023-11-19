package com.example.OpenSource.domain.member.domain;

import com.example.OpenSource.domain.auth.domain.oauth.OAuthProvider;
import com.example.OpenSource.domain.path.domain.Path;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.sql.Blob;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "member")
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String nickname;

    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    private int walk;

    @Enumerated(EnumType.STRING)
    private Rank rank;

    @Lob
    private Blob profileImage;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Path> paths;

    @Builder
    public Member(String email, String password, String nickname, String name, int walk, Authority authority,
                  OAuthProvider oAuthProvider, Rank rank) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.walk = walk;
        this.authority = authority;
        this.oAuthProvider = oAuthProvider;
        this.rank = rank;
    }

    public void setProfileImage(Blob imageFile) {
        this.profileImage = imageFile;
    }
}
