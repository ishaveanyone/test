package com.dist.Subselect;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-28 11:04
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
@Service
public class DateService {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Session getSession() {
        return entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }


    public List getDateType(){
        Session session = getSession();
        Query query= session.createNativeQuery("select f_datatime as f_datatime,count(1) as count from tbsys_loginfo t1  group by f_datatime having \n" +
                "\n" +
                "        t1.F_DATATIME between \n" +
                "          TO_DATE(TO_CHAR(1346432461000 / (1000 * 60 * 60 * 24) +  TO_DATE('1970-01-01 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS')\n" +
                "          \n" +
                "       AND TO_DATE(TO_CHAR(1564636332000 / (1000 * 60 * 60 * 24) + TO_DATE('1970-01-01 08:00:00', 'YYYY-MM-DD HH24:MI:SS'), 'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')");
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); // 设置返回的是map 封装
        return query.list();
    }

}
