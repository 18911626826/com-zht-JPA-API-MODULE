package org.father.API.dao.draft;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.father.API.pojo.draft.TOrFlowDiagramDraft;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-10-31 17:27
 * @Version 1.0
 */

public interface TOrFlowDiagramDraftRepositoryCustom {

    PaginationBean<TOrFlowDiagramDraft> getFlowPage(String pimsId, String caseId, String periodId, Pagination page, String dataSourceCode) throws Exception;

    void addOrFlowDiagramDraft(List<TOrFlowDiagramDraft> tOrFlowDiagramDraftList, Connection conn) throws SQLException;
    
    List<TOrFlowDiagramDraft> getBypimsModelIdAndCaseIdAndPeriodIdAndDataSourceCode(String pimsId,String caseId,String periodId,String dataSourceCode);

    Map<String, String> getQuantitySum(String dataSourceCode);
}
