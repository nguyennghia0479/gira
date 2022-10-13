package cybersoft.javabackend.java18.gira.role.repository;

import cybersoft.javabackend.java18.gira.role.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OperationRepository extends JpaRepository<Operation, UUID> {
    @Query("select (count(u) > 0) from Operation u where u.name = ?1")
    boolean existsByName(String name);

    @Query("select o from Operation o left join o.roles r left join r.userGroups g left join g.users u where u.username = ?2 and o.name = ?1")
    List<Operation> findAllByNameAndUsername(String operationName, String username);
}
