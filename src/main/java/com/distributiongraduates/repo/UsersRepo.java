package com.distributiongraduates.repo;

import com.distributiongraduates.model.Users;
import com.distributiongraduates.model.enums.Citizenship;
import com.distributiongraduates.model.enums.Marital;
import com.distributiongraduates.model.enums.Role;
import com.distributiongraduates.model.enums.YesNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    List<Users> findAllByOrderByRole();

    List<Users> findAllByRoleAndStudent_JobContainingAndStudent_MaritalAndStudent_CitizenshipAndStudent_Conscripted(Role role, String job, Marital marital, Citizenship citizenship, YesNo conscripted);

    List<Users> findAllByRole(Role role);

    List<Users> findAllByRoleAndSpecializationIsNotNull(Role role);

    List<Users> findAllByRoleAndStudent_Marital(Role role, Marital marital);

    List<Users> findAllByRoleAndStudent_Citizenship(Role role, Citizenship citizenship);

    List<Users> findAllByRoleAndStudent_Conscripted(Role role, YesNo yesNo);
}
