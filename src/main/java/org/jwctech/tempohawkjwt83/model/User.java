package org.jwctech.tempohawkjwt83.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String email;

    String password;

    String fullName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_scopes", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "scope")
    private Set<String> scopes = new HashSet<>();

}
