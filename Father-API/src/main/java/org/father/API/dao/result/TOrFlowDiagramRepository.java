package org.father.API.dao.result;

import org.father.API.pojo.result.TOrFlowDiagram;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description TODO
 *
 * @author linjian
 * @create 2018-10-25 10:34
 * @Version 1.0
 */

public interface TOrFlowDiagramRepository extends JpaRepository<TOrFlowDiagram,String>,TOrFlowDiagramRepositoryCustom {
}
