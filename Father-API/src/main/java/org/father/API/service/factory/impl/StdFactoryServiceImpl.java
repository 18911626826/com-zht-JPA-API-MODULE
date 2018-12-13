package org.father.API.service.factory.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.Father.COMMON.util.CommonProperty;
import org.Father.COMMON.util.CommonResult;
import org.Father.COMMON.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.father.API.dao.MasterData.TBcOrgRepository;
import org.father.API.dao.factory.StdFactoryRepository;
import org.father.API.pojo.MasterData.TBcOrg;
import org.father.API.pojo.factory.StdFactory;
import org.father.API.service.factory.StdFactoryService;
import org.father.API.service.factory.entity.StdFactoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * 分厂 业务层自定义 接口实现
 * 
 * @author haitao.zhang 2018-11-8
 * @version 1.0
 */
@Service
public class StdFactoryServiceImpl implements StdFactoryService {

	@Autowired
	private StdFactoryRepository stdFactoryRepository;

	@Autowired
	private TBcOrgRepository tBcOrgRepository;

	@PersistenceUnit(unitName = "entityManagerFactory")
	private EntityManagerFactory emf;

	@Override
	public CommonResult insert(StdFactoryEntity stdFactoryEntity) throws Exception {
		CommonResult cr = new CommonResult();
		if (stdFactoryEntity != null) {
			if (null != stdFactoryEntity.getFacCode()) {
				List<Object[]> objCode = stdFactoryRepository.getSingleStdFactoryByCode(stdFactoryEntity.getFacCode());

				if (objCode != null && objCode.size() > 0) {
					cr.setIsSuccess(false);
					cr.setMessage("编码不能重复，请重新输入！");
					cr.setResult(stdFactoryEntity);
					return cr;
				}
			}
			if (null != stdFactoryEntity.getFacName()) {
				List<Object[]> objsName = stdFactoryRepository.getSingleStdFactoryByName(stdFactoryEntity.getFacName());

				if (objsName != null && objsName.size() > 0) {
					cr.setIsSuccess(false);
					cr.setMessage("名称不能重复，请重新输入！");
					cr.setResult(stdFactoryEntity);
					return cr;
				}
			}
			CommonProperty commonProperty = new CommonProperty();
			// 业务实体转换为持久层实体
			StdFactory factory = ObjectConverter.entityConverter(stdFactoryEntity, StdFactory.class);
			// factory.setFacIsDelete(0);
			factory.setFacCreateUser(commonProperty.getUserName());
			// factory.setFacCreateTime(new
			// Timestamp(commonProperty.getSystemDateTime().getTime()));
			// factory.setFacUpdateTime(null);
			try {
				stdFactoryRepository.insert(factory.getFacCode(), factory.getFacName(), factory.getFacNO(),
						factory.getFacCreateUser(), factory.getOrgId());
				cr.setIsSuccess(true);
				cr.setMessage("插入成功！");
				cr.setResult(stdFactoryEntity);
				return cr;
			} catch (Exception e) {
				cr.setIsSuccess(false);
				cr.setMessage("插入失败！" + e.getMessage());
				e.printStackTrace();
				cr.setResult(stdFactoryEntity);
				return cr;
			}
		}
		cr.setIsSuccess(false);
		cr.setMessage("参数不能重复");
		return cr;
	}

	@Override
	public CommonResult updateStdFactory(StdFactoryEntity stdFactoryEntity) throws Exception {
		CommonResult cr = new CommonResult();
		if (stdFactoryEntity != null) {

			StdFactory stdFactory = stdFactoryRepository.getSingleStdFactory(stdFactoryEntity.getFacID());

			if (StringUtils.isNoneEmpty(stdFactoryEntity.getFacCode())) {
				if (!stdFactory.getFacCode().equals(stdFactoryEntity.getFacCode())) {
					List<Object[]> objByFacCode = stdFactoryRepository
							.getSingleStdFactoryByCode(stdFactoryEntity.getFacCode());
					if (objByFacCode != null && objByFacCode.size() > 0) {
						cr.setIsSuccess(false);
						cr.setMessage("编码不能重复，请重新输入!");
						return cr;
					}
				}
			}

			if (!stdFactory.getFacName().equals(stdFactoryEntity.getFacName())) {
				List<Object[]> objByFacName = stdFactoryRepository
						.getSingleStdFactoryByName(stdFactoryEntity.getFacName());
				if (objByFacName != null && objByFacName.size() > 0) {
					cr.setIsSuccess(false);
					cr.setMessage("名称不能重复，请重新输入!");
					return cr;
				}
			}

			String[] ignoreArr = { "facCreateUser", "facCreateTime", "facUpdateUser", "facUpdateTime", "facIsDelete",
					"facID" };
			List<String> ignoreLists = new ArrayList<>(Arrays.asList(ignoreArr));
			// 判断编码
			if (StringUtils.isEmpty(stdFactoryEntity.getFacCode().trim())) {
				ignoreLists.add("facCode");
			}
			// 判断名称
			if (StringUtils.isEmpty(stdFactoryEntity.getFacName().trim())) {
				ignoreLists.add("facName");
			}
			// 判断排序
			if (stdFactoryEntity.getFacNO() == null) {
				ignoreLists.add("facNO");
			}
			// 判断企业
			if (stdFactoryEntity.getOrgId() == null) {
				ignoreLists.add("orgId");
			}

			CommonProperty commonProperty = new CommonProperty();
			// 业务实体转换为持久层实体
			CommonUtil.objectExchange(stdFactoryEntity, stdFactory, ignoreLists.toArray(ignoreArr));
			// stdFactory.setFacIsDelete(0);
			stdFactory.setFacUpdateUser(commonProperty.getUserName());
			stdFactory.setFacUpdateTime(new Timestamp(commonProperty.getSystemDateTime().getTime()));

			cr = stdFactoryRepository.updateStdFactory(stdFactory);
			if (cr.getIsSuccess() == false) {
				cr.setIsSuccess(false);
				cr.setMessage(cr.getMessage());
//				throw new Exception(cr.getMessage());//由统一处理异常 改为返回通用消息体
				return cr;
			}
		}
		return cr;
	}

	@Override
	public CommonResult deleteStdFactory(Long[] facIDs) throws Exception {
		CommonResult cr = new CommonResult();
		if (facIDs != null && facIDs.length > 0) {
			cr = stdFactoryRepository.deleteStdFactory(facIDs);
			if (cr.getIsSuccess() == false) {
				throw new Exception(cr.getMessage());
			}
			return cr;
		}
		cr.setIsSuccess(false);
		cr.setMessage("分厂id数组不能为空");
		return cr;
	}

	@Override
	public StdFactoryEntity getSingleStdFactory(Long facID) throws Exception {
		StdFactory factory = stdFactoryRepository.getSingleStdFactory(facID);
		StdFactoryEntity entity = ObjectConverter.entityConverter(factory, StdFactoryEntity.class);
		if (entity.getOrgId() != null) {
			TBcOrg tBcOrg = tBcOrgRepository.getSingleTBcOrg(entity.getOrgId());
			if (tBcOrg != null) {
				entity.setOrgName(tBcOrg.getOrgName());
			}
		}
		return entity;
	}

	@Override
	public List<StdFactoryEntity> getStdFactoryList(Long[] facIDs) throws Exception {
		return ObjectConverter.listConverter(stdFactoryRepository.getStdFactoryList(facIDs), StdFactoryEntity.class);
	}

	@Override
	public PaginationBean<StdFactoryEntity> getStdFactory(Pagination page, String name) throws Exception {
		PaginationBean<StdFactory> factory = stdFactoryRepository.getStdFactory(page, name);
		List<StdFactoryEntity> entity = ObjectConverter.listConverter(factory.getPageList(), StdFactoryEntity.class);

		for (StdFactoryEntity en : entity) {
			if (en != null && en.getOrgId() != null) {
				TBcOrg tBcOrg = tBcOrgRepository.getSingleTBcOrg(en.getOrgId());
				if (tBcOrg != null) {
					en.setOrgName(tBcOrg.getOrgName());
				}
			}
		}

		PaginationBean<StdFactoryEntity> entityPage = new PaginationBean<>(page, factory.getTotal());
		entityPage.setPageList(entity);
		return entityPage;
	}

	@Override
	public List<StdFactoryEntity> getStdFactoryListForExport(String orgId) throws Exception {
		List<StdFactory> factorys = stdFactoryRepository.getStdFactoryListForExport(orgId);
		List<StdFactoryEntity> facEntitys = ObjectConverter.listConverter(factorys, StdFactoryEntity.class);
		return facEntitys;
	}

	@Override
	public CommonResult saveStdFactoryforImport(List<StdFactoryEntity> facs) throws Exception {
		/*
		 * CommonResult cr=new CommonResult(); Map<String,Object> mapFail=new
		 * HashMap<>(); mapFail.put("totalNum", facs.size()); int failNum=0;
		 * 
		 * List<StdFactoryEntity> facsDistinct=new ArrayList<>(); List<StdFactoryEntity>
		 * facsFailes=new ArrayList<>(); //查询数据库中 编码和名称重复的记录 不参与插入 for(StdFactoryEntity
		 * fac:facs) { List<Object[]>
		 * objsCode=stdFactoryRepository.getSingleStdFactoryByCode(fac.getFacCode());
		 * List<Object[]>
		 * objsName=stdFactoryRepository.getSingleStdFactoryByName(fac.getFacName());
		 * 
		 * if((objsCode==null || objsCode.size()==0) && (objsName==null ||
		 * objsName.size()==0)) { facsDistinct.add(fac); }else { if(objsCode!=null &&
		 * objsCode.size()>0) fac.setBatchInsertFailMessage("编码不能重复，请重新输入!");
		 * if(objsName!=null && objsName.size()>0)
		 * fac.setBatchInsertFailMessage("名称不能重复，请重新输入!"); if((objsCode!=null &&
		 * objsCode.size()>0) && (objsName!=null && objsName.size()>0))
		 * fac.setBatchInsertFailMessage("编码和名称都不能重复，请重新输入!"); facsFailes.add(fac);
		 * failNum++; } }
		 */
		// 批量功能 方法一
		/*
		 * int entityCount = facsDistinct.size(); int batchSize = 25;
		 * 
		 * EntityManager entityManager = null; EntityTransaction transaction = null; int
		 * i=0; try { entityManager = emf.createEntityManager();
		 * 
		 * transaction = entityManager.getTransaction(); transaction.begin();
		 * 
		 * for (i = 0; i < entityCount; ++i ) { if ( i > 0 && i % batchSize == 0 ) {
		 * entityManager.flush(); entityManager.clear();
		 * 
		 * transaction.commit(); transaction.begin(); }
		 * 
		 * StdFactory factory=ObjectConverter.entityConverter(facsDistinct.get(i),
		 * StdFactory.class); entityManager.persist(factory); }
		 * 
		 * transaction.commit(); } catch (RuntimeException e) { if ( transaction != null
		 * && transaction.isActive()) { transaction.rollback();
		 * 
		 * facsFailes.add(facsDistinct.get(i)); failNum++; } throw e; } finally { if
		 * (entityManager != null) { entityManager.close(); } }
		 */
		// 批量功能 方法二
		/*
		 * EntityManager em = emf.createEntityManager(); String insertSql =
		 * "insert dbo.STD_FACTORY (FAC_CODE,FAC_NAME,FAC_NO,FAC_CREATE_USER,ORG_ID) values (?,?,?,?,?)"
		 * ; Session session1 = (Session) em.getDelegate(); Connection con =
		 * em.unwrap(SessionImpl.class).connection(); PreparedStatement stmt =
		 * con.prepareStatement(insertSql);
		 * 
		 * CommonProperty commonProperty = new CommonProperty(); int i = 0; try { //
		 * 方式2：批量提交 con.setAutoCommit(false); for (i=0;i<facsDistinct.size();i++) {
		 * stmt.setString(1,facsDistinct.get(i).getFacCode());
		 * stmt.setString(2,facsDistinct.get(i).getFacName());
		 * stmt.setInt(3,facsDistinct.get(i).getFacNO());
		 * 
		 * StdFactory factory=ObjectConverter.entityConverter(facsDistinct.get(i),
		 * StdFactory.class);
		 * 
		 * factory.setFacCreateUser(commonProperty.getUserId());
		 * stmt.setString(4,factory.getFacCreateUser());
		 * if(facsDistinct.get(i).getOrgId()!=null) {
		 * stmt.setInt(5,facsDistinct.get(i).getOrgId()); }else { stmt.setInt(5,0); }
		 * stmt.addBatch(); i++; if (i % 100 == 0) { stmt.executeBatch(); con.commit();
		 * } } stmt.executeBatch(); con.commit(); }catch(Exception e){
		 * e.printStackTrace();
		 * facsDistinct.get(i).setBatchInsertFailMessage("导入失败"+e.getMessage());
		 * facsFailes.add(facsDistinct.get(i)); failNum++; }
		 */

		CommonResult cr = new CommonResult();
		Map<String, Object> mapFail = new HashMap<>();
		mapFail.put("totalNum", facs.size());
		int failNum = 0;
		List<StdFactoryEntity> failEntitys = new ArrayList<>();
		StdFactoryEntity failEntity = null;
		for (StdFactoryEntity chain : facs) {
			cr = insert(chain);
			if (!cr.getIsSuccess()) {
				failEntity = (StdFactoryEntity) cr.getResult();
				if (failEntity != null)
					failEntity.setBatchInsertFailMessage(cr.getMessage());
				failNum++;
				failEntitys.add(failEntity);
			}
		}
		if (failNum == 0) {
			cr.setIsSuccess(true);
			cr.setMessage("所有数据导入成功!");
			cr.setResult(null);
			return cr;
		} else {
			mapFail.put("failNum", failNum);
			mapFail.put("failList", failEntitys);
			cr.setIsSuccess(false);
			cr.setMessage("部分数据导入失败!");
			cr.setResult(mapFail);
			return cr;
		}
	}

}
