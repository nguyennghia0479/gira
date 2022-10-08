package cybersoft.javabackend.java18.gira.role.repository;

import cybersoft.javabackend.java18.gira.role.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
    @Query("select ug from UserGroup ug left join fetch ug.users")
    List<UserGroup> findAllWithUsers();

    @Query("select (count(u) > 0) from UserGroup u where u.name = ?1")
    boolean existsByName(String name);

    @Query("select (count(u) > 0) from UserGroup u where u.code = ?1")
    boolean existsByCode(String code);
}
