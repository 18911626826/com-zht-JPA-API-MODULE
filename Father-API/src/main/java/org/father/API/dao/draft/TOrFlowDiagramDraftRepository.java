package org.father.API.dao.draft;

import org.father.API.pojo.draft.TOrFlowDiagramDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-10-31 17:27
 * @Version 1.0
 */

public interface TOrFlowDiagramDraftRepository extends JpaRepository<TOrFlowDiagramDraft,String>,TOrFlowDiagramDraftRepositoryCustom{

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM t_or_flow_diagram_draft WHERE data_source_code = ?1",nativeQuery = true)
    void deleteFlowDiagramDraft(String dataSourceCode);
}
