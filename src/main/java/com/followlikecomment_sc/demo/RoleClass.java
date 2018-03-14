package com.followlikecomment_sc.demo;




import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class RoleClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

//    @Column(unique=true)
    private String roleName;

//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Collection<User> users;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public RoleClass() {
        this.users = new HashSet<>();
    }

    public RoleClass(String roleName) {
        this.roleName = roleName;
        this.users = new HashSet<>();
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public Collection<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Collection<User> users) {
//        this.users = users;
//    }

//    @Override
//    public String toString() {
//        return "RoleClass{" +
//                "role='" + role + '\'' +
//                '}';
//    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}