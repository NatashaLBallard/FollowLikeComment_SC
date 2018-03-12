package com.followlikecomment_sc.demo;




import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RoleClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String role;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Collection<User> users;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public RoleClass() {
        users = new HashSet<>();
    }



//    public RoleClass(String role) {
//        this.role = role;
//    }

//    public RoleClass() {
//    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public Collection<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Collection<User> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "RoleClass{" +
                "role='" + role + '\'' +
                '}';
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}