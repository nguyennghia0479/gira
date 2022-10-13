package cybersoft.javabackend.java18.gira.role.model;

import cybersoft.javabackend.java18.gira.common.model.BaseEntity;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.model.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = RoleEntity.UserGroup.TABLE_NAME)
public class UserGroup extends BaseEntity {
    @Column(name = RoleEntity.UserGroup.NAME, unique = true, length = 100)
    @Length(min = 5, max = 100, message = "User Group name must have length between {min} and {max}")
    private String name;

    @Column(name = RoleEntity.UserGroup.CODE, unique = true)
    @Length(min = 3, max = 10, message = "User group code must have length between {min} and {max}")
    private String code;

    @Column(name = RoleEntity.UserGroup.DESCRIPTION)
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = UserEntity.UserGroupMappedUser.JOIN_TABLE,
            joinColumns = @JoinColumn(name = UserEntity.UserGroupMappedUser.JOIN_TABLE_USER_GROUP_ID),
            inverseJoinColumns = @JoinColumn(name = UserEntity.UserGroupMappedUser.JOIN_TABLE_USER_ID)
    )
    private Set<User> users = new LinkedHashSet<>();

   @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
   @JoinTable(
           name = UserEntity.UserGroupMappedRole.JOIN_TABLE,
           joinColumns = @JoinColumn(name = UserEntity.UserGroupMappedRole.JOIN_TABLE_USER_GROUP_ID),
           inverseJoinColumns = @JoinColumn(name = UserEntity.UserGroupMappedRole.JOIN_TABLE_ROLE_ID)
   )
    private Set<Role> roles = new LinkedHashSet<>();

    public void addUser(User user) {
        this.users.add(user);
        user.getUserGroups().add(this);
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUserGroups().add(this);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj))
            return false;
        UserGroup userGroup = (UserGroup) obj;
        return this.id != null && Objects.equals(this.id, userGroup.id);
    }
}
