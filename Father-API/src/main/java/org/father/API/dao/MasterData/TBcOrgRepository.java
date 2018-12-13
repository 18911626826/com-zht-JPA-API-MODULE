package org.father.API.dao.MasterData;

import org.father.API.pojo.MasterData.TBcOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TBcOrgRepository extends JpaRepository<TBcOrg, Long>, TBcOrgRepositoryCustom {

	@Transactional
	@Modifying
	@Query(value = "insert dbo.T_BC_Org (Org_Code,Org_Name,Org_SName,User_Code,User_Name,User_Phone,In_Use,Crt_User_Id,Is_Delete,Sort_Num)"
			+ " values (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
	void addTBcOrg(String orgCode, String orgName, String orgSname, String userCode, String userName, String userPhone,
			String inUse, String crtUserId, int isDelete, int sortNum) throws Exception;

}
