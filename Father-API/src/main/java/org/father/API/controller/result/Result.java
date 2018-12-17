/**  

* <p>Title: Result.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月17日 上午11:44:01 

* @version 1.0  

*/
package org.father.API.controller.result;

import java.util.List;

import org.Father.COMMON.excel.FlowDiagramExport;
import org.father.API.dao.result.TOrFlowDiagramRepository;
import org.father.API.pojo.result.TOrFlowDiagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


/**  

* <p>Title: Result</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月17日 上午11:44:01 

*/
@Component
public class Result extends FlowDiagramExport<TOrFlowDiagram>{
	
	@Autowired
    private TOrFlowDiagramRepository tOrFlowDiagramRepository;
	
	@Override
	protected List<TOrFlowDiagram> getList(TOrFlowDiagram t) {
		return tOrFlowDiagramRepository.getByResultId(t.getResultId());
	}
	
}
