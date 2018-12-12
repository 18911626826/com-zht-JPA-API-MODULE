package org.father.API.dao.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.Father.COMMON.Pagination;
import org.Father.COMMON.PaginationBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.repository.NoRepositoryBean;


/*
 * Repository基类
 * 模块编号：pcitc_wm_dal_common_class_BaseRepository
 * 作       者：pcitc
 * 创建时间：2017/09/17
 * 修改编号：1
 * 描       述：Repository基类
 */
@NoRepositoryBean
public class BaseRepository<T, ID extends Serializable> {

    private Class<T> entityClass;
    private EntityManager entityManager;

    /**
     * 注入EntityManager，同时实例化SimpleJpaRepository
     *
     * @param entityManager
     * @author pcitc 2017-09-18
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;

        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    /**
     * 获取EntityManager，操作jpa api的入口
     *
     * @return EntityManager
     * @author pcitc 2017-09-18
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 获取分页数据
     *
     * @param page      分页参数
     * @param jpql      hql语句
     * @param paramList 参数列表
     * @return 分页数据
     * @author pcitc 2017-09-18
     */
    public PaginationBean<T> findAll(Pagination page, String jpql, Map<String, Object> paramList) {
        Long count = getCount(jpql, paramList);
        PaginationBean<T> resultList = new PaginationBean<T>(page, count);
        TypedQuery<T> query = this.entityManager.createQuery(jpql, entityClass);

        this.setParameterList(query, paramList);
        query.setFirstResult(resultList.getBeginIndex()).setMaxResults(resultList.getPageSize());
        resultList.setPageList(query.getResultList());
        return resultList;
    }
    /**
     * 获取分页数据
     *
     * @param page      分页参数
     * @param sql       原生sql语句
     * @param paramList 参数列表
     * @return 分页数据
     * @author xuelei.wang 2018-07-05
     */
    public PaginationBean<T> findAllByNativeQuery(Pagination page, String sql, Map<String, Object> paramList) {
        Long count = getCountByNativeQuery(sql, paramList);
        PaginationBean<T> resultList = new PaginationBean<T>(page, count);
        Query query = this.entityManager.createNativeQuery(sql, entityClass);
        this.setParameterList(query, paramList);
        query.setFirstResult(resultList.getBeginIndex()).setMaxResults(resultList.getPageSize());
        resultList.setPageList(query.getResultList());
        return resultList;
    }


    /**
     * 设置Query的参数
     *
     * @param query     Query查询对象
     * @param paramList 参数列表
     * @author pcitc 2017-09-18
     */
    protected void setParameterList(Query query, Map<String, Object> paramList) {
        for (Entry<String, Object> pair : paramList.entrySet()) {
            query.setParameter(pair.getKey(), pair.getValue());
        }
    }

    /**
     * 获取分页数据的总量
     *
     * @param jpql      查询语句
     * @param paramList 参数列表
     * @return 数据条数
     * @author pcitc 2017-09-18
     */
    protected long getCount(String jpql, Map<String, Object> paramList) {
        String countHql = CountHqlBuilder.toCountHql(jpql);
        TypedQuery<Long> query = this.entityManager.createQuery(countHql, Long.class);
        this.setParameterList(query, paramList);
        return query.getSingleResult();
    }
    /**
     * 获取分页数据的总量
     *
     * @param sql       原生SQL查询语句
     * @param paramList 参数列表
     * @return 数据条数
     * @author pcitc 2017-09-18
     */
    protected long getCountByNativeQuery(String sql, Map<String, Object> paramList) {
        String countHql = CountHqlBuilder.toCountHql(sql);
        Query query=this.entityManager.createNativeQuery(countHql);
        this.setParameterList(query, paramList);
        Integer result= (Integer) query.getSingleResult();
        return result.longValue();
    }
    /**
     * 处理Like语句
     *
     * @param name sql字符串
     * @return 处理完的字符串
     * @author pcitc 2017-09-18
     */
    protected String sqlLikeReplace(String name) {
        if (StringUtils.isEmpty(name))
            return "";
        return name.replace("/", "//").replace("%", "/%").replace("_", "/_");
    }


    /**
     * 统一编码处理
     * @param code 编码首字母
     * @param seqName 序列名称
     * @return
     */
    public  String getSeq(String code,String seqName) {
        String countHql = "SELECT NEXT VALUE FOR "+seqName;
        Query query=this.entityManager.createNativeQuery(countHql);
        BigInteger seq = (BigInteger) query.getSingleResult();
        String result = String.format("%04d", seq);
        if(code!=null&&!"".equals(code)){
            return code + result;
        }else{
            return result;
        }
    }

}
