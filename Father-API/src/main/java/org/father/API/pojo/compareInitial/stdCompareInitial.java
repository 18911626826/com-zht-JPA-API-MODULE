/**  

* <p>Title: StdCompareInitial.java</p>  

* <p>Description: </p>  

* @author haitao.zhang  

* @date 2018年12月5日 上午11:01:55 

* @version 1.0  

*/
package org.father.API.pojo.compareInitial;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**  

* <p>Title: StdCompareInitial</p>  

* <p>Description: </p>  

* @author haitao.zhang

* @date 2018年12月5日 上午11:01:55 

*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STD_COMPARE_INITIAL",schema="dbo")
public class stdCompareInitial{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="COMP_INIT_ID")
	private Integer compInitId;
	
	@Column(name="COMP_INIT_PIMS_PIPO_TYPE")
	private String compInitPimsPipoType;//1:线上PIMS 2:线下PIMS 3:PIPO    （三选一）
	
	@Column(name="COMP_INIT_MES_TYPE")
	private String compInitMesType;//1:线上MES对照数据   2:线下MES对照数据   （二选一）先定线下    默认  2
	
	@Column(name="COMP_INIT_MONTH_TYPE")
	private String compInitMonthType;//1:线上月计划对照数据   2:线下月计划对照数据（二选一）

}
