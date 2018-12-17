package org.father.API.dao.result;

import java.util.List;
import java.util.Map;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.father.API.pojo.result.TOrFlowDiagram;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-11-02 14:27
 * @Version 1.0
 */

public interface TOrFlowDiagramRepositoryCustom {
    PaginationBean<TOrFlowDiagram> getPage(String resultId, Pagination page, String dataSourceCode
            , String pimsModelId, String caseId, String periodId) throws Exception;
    
    List<TOrFlowDiagram> getByResultId(String result_id);

    void add(List<TOrFlowDiagram> listFlow) throws Exception;

    Map<String, String> getQuantitySum(String dataSourceCode);
}
