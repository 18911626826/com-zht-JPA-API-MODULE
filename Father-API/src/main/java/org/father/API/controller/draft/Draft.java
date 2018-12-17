/**  

* <p>Title: Draft.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月17日 下午3:02:34 

* @version 1.0  

*/
package org.father.API.controller.draft;

import java.util.List;

import org.Father.COMMON.excel.FlowDiagramExport;
import org.father.API.dao.draft.TOrFlowDiagramDraftRepository;
import org.father.API.pojo.draft.TOrFlowDiagramDraft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**  

* <p>Title: Draft</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月17日 下午3:02:34 

*/
@Component
public class Draft extends FlowDiagramExport<TOrFlowDiagramDraft>{
	
	@Autowired
	private TOrFlowDiagramDraftRepository TOrFlowDiagramDraftRepository;

	@Override
	protected List<TOrFlowDiagramDraft> getList(TOrFlowDiagramDraft t) {
		return TOrFlowDiagramDraftRepository.getBypimsModelIdAndCaseIdAndPeriodIdAndDataSourceCode(t.getPimsModelId(),t.getCaseId(),t.getPeriodId(),t.getDataSourceCode());
	}

}
