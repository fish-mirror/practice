package com.zjicm.common.lang.sql.dao;

import com.zjicm.common.lang.function.Consumer;
import com.zjicm.common.lang.function.Function;
import com.zjicm.common.lang.consts.StringConsts;
import com.zjicm.common.lang.sql.*;
import com.zjicm.common.lang.sql.domain.CanonicalDomain;
import com.zjicm.common.lang.sql.exception.DAOReadException;
import com.zjicm.common.lang.sql.exception.DAOWriteException;
import com.zjicm.common.lang.sql.util.JdbcUtil;
import com.zjicm.common.lang.util.NumberUtil;
import com.zjicm.common.lang.util.ObjectUtil;
import com.zjicm.common.lang.page.PageResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.cfg.NamingStrategy;
import org.hibernate.criterion.*;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ExecutorService;

public class BaseDAOImpl<V extends CanonicalDomain<K>, K extends Serializable> extends HibernateDaoSupport implements BaseDAO<V, K> {
    @Autowired
    private NamingStrategy namingStrategy;
    private Class<V> entityClass;

    private String table = "";

    private String primaryKey = "id";//Database primary key name
    private String entityKey = "id";//entity identity

    private PartitionedTable partitionedTable = null;

    public BaseDAOImpl() {
        super();
        this.entityClass = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass.isAnnotationPresent(PartitionedTable.class)) {
            partitionedTable = entityClass.getAnnotation(PartitionedTable.class);
        }

        Table tableAnn = this.entityClass.getAnnotation(Table.class);
        if (tableAnn != null) {//some entities are not stored in database;
            table = tableAnn.name();//get table name
            Field[] fields = this.entityClass.getDeclaredFields();
            if (fields == null || fields.length == 0) {
                return;
            }

            for (Field field : fields) {//get database primary key
                if (field != null && field.isAnnotationPresent(Id.class)) {
                    entityKey = field.getName();
                    Column columnAnn = field.getAnnotation(Column.class);
                    if (columnAnn != null) {
                        primaryKey = columnAnn.name();
                    }
                    if (StringUtils.isEmpty(primaryKey)) {
                        primaryKey = entityKey;
                    }
                    break;
                }
            }
        }
    }

    @PostConstruct
    public void setEntityInterceptor() {
        if (this.partitionedTable != null) {
            this.getHibernateTemplate().setEntityInterceptor(new TableRoutingInterceptor(this.table));
        }
    }

    @Override
    public V getById(K id) {
        return getById(id, null, null, null);
    }

    @Override
    public V getById(K id, Number partitionSeed) {
        return getById(id, partitionSeed, null, null);
    }

    @Override
    public V getById(K id, Number partitionSeed, String table, ReadPolicy readPolicy) {
        if (id != null) {
            try {
                setPartition(partitionSeed, table, readPolicy);
                return (V) this.getHibernateTemplate().get(this.entityClass, id);
            } catch (Throwable e) {
                throw new DAOReadException(id.toString(), e);
            } finally {
                clearPartition();
            }
        }
        return null;
    }

    @Override
    public Number getNumber(final String sql,
                            final Object[] vars,
                            Number partitionSeed,
                            String table,
                            ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);
            return this.getHibernateTemplate().execute(session -> {
                SQLQuery query = session.createSQLQuery(sql);
                if (ArrayUtils.isNotEmpty(vars)) {
                    for (int i = 0; i < vars.length; i++) {
                        query.setParameter(i, vars[i]);
                    }
                }
                return (Number) query.uniqueResult();
            });
        } catch (Throwable e) {
            throw new DAOReadException(sql, e);
        } finally {
            clearPartition();
        }
    }

    @Override
    public Collection<Number> getNumbers(final String sql,
                                         final Object[] vars,
                                         final int offset,
                                         final int size,
                                         Number partitionSeed,
                                         String table,
                                         ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);
            return this.getHibernateTemplate()
                       .execute(session -> {
                           SQLQuery query = session.createSQLQuery(sql);
                           if (ArrayUtils.isNotEmpty(vars)) {
                               for (int i = 0; i < vars.length; i++) {
                                   query.setParameter(i, vars[i]);
                               }
                           }
                           query.setFirstResult(offset);
                           query.setMaxResults(size);
                           return (Collection<Number>) query.list();
                       });
        } catch (Throwable e) {
            throw new DAOReadException(sql, e);
        } finally {
            clearPartition();
        }
    }

    @Override
    public <T> Collection<T> getObjects(final Class<T> clazz,
                                        final String sql,
                                        final Object[] vars,
                                        final int offset,
                                        final int size,
                                        Number partitionSeed,
                                        String table,
                                        ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);
            return this.getHibernateTemplate().execute(session -> {
                try {
                    SQLQuery query = session.createSQLQuery(sql);
                    if (ArrayUtils.isNotEmpty(vars)) {
                        for (int i = 0; i < vars.length; i++) {
                            query.setParameter(i, vars[i]);
                        }
                    }
                    query.setFirstResult(offset);
                    query.setMaxResults(size);
                    query.setResultTransformer(new AliasToBeanResultTransformer(clazz));
                    return (Collection<T>) query.list();
                } catch (Throwable e) {
                    throw new DAOReadException(sql, e);
                }
            });
        } catch (Throwable e) {
            throw new DAOReadException(sql, e);
        } finally {
            clearPartition();
        }
    }

    @Override
    public int count(Collection<Criterion> criterions) {
        return count(criterions, null, null, null);
    }

    @Override
    public int count(Collection<Criterion> criterions, Number partitionSeed) {
        return count(criterions, partitionSeed, null, ReadPolicy.MASTER);
    }

    @Override
    public int count(Collection<Criterion> criterions, Number partitionSeed, String table, ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);
            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null && !criterions.isEmpty()) {
                for (Criterion criterion : criterions) {
                    dc.add(criterion);
                }
            }

            return ObjectUtil.nullToDefault(this.getHibernateTemplate()
                                                .execute(session -> {
                                                    Criteria criteria = dc.getExecutableCriteria(session);
                                                    Integer count = NumberUtil.parseIntQuietly(criteria.setProjection(Projections
                                                                                                                     .rowCount())
                                                                                              .uniqueResult());
                                                    criteria.setProjection(null);
                                                    return count;
                                                }), 0);
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }
    }

    @Override
    public Number max(Collection<Criterion> criterions,
                      final String property,
                      Number partitionSeed,
                      String table,
                      ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);

            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null && !criterions.isEmpty()) {
                for (Criterion criterion : criterions) {
                    dc.add(criterion);
                }
            }

            return ObjectUtil.nullToDefault(this.getHibernateTemplate()
                                                .execute(session -> (Number) dc.getExecutableCriteria(session)
                                                                               .setProjection(Projections.max(
                                                                                       property))
                                                                               .uniqueResult()), 0);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }

        return 0;
    }


    @Override
    public <T> List<T> distinct(Collection<Criterion> criterions,
                                List<String> properties,
                                Class<T> clazz,
                                Number partitionSeed,
                                String table,
                                ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);

            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null && !criterions.isEmpty()) {
                for (Criterion criterion : criterions) dc.add(criterion);
            }
            if (CollectionUtils.isNotEmpty(properties) && clazz != null) {
                ProjectionList projections = Projections.projectionList()
                                                        .add(Projections.distinct(Projections.property(properties.get(0))
                                                                                             .as(properties.get(0))));
                if (properties.size() > 1) {
                    properties.stream().skip(1).forEach(property -> projections.add(Projections.property(property)
                                                                                               .as(property)));
                }
                dc.setProjection(projections)
                  .setResultTransformer(new AliasToBeanResultTransformer(clazz));
            }

            return ObjectUtil.nullToDefault(this.getHibernateTemplate()
                                                .execute(session -> dc.getExecutableCriteria(session)
                                                                      .list()),
                                            Collections.emptyList());
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }
    }

    @Override
    public Number min(Collection<Criterion> criterions,
                      final String property,
                      Number partitionSeed,
                      String table,
                      ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);

            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null && !criterions.isEmpty()) {
                for (Criterion criterion : criterions) {
                    dc.add(criterion);
                }
            }

            return ObjectUtil.nullToDefault(this.getHibernateTemplate()
                                                .execute(session -> (Number) dc.getExecutableCriteria(session)
                                                                               .setProjection(Projections.min(
                                                                                       property))
                                                                               .uniqueResult()), 0);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }

        return 0;
    }

    @Override
    public Number sum(Collection<Criterion> criterions,
                      final String property,
                      Number partitionSeed,
                      String table,
                      ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);

            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null && !criterions.isEmpty()) {
                for (Criterion criterion : criterions) {
                    dc.add(criterion);
                }
            }

            return ObjectUtil.nullToDefault((Number) this.getHibernateTemplate()
                                                         .execute(new HibernateCallback<Number>() {
                                                             public Number doInHibernate(Session session) throws
                                                                                                          HibernateException {
                                                                 return (Number) dc.getExecutableCriteria(session)
                                                                                   .setProjection(Projections.sum(
                                                                                           property))
                                                                                   .uniqueResult();
                                                             }
                                                         }), 0);
        } catch (NullPointerException e) {
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }

        return 0;
    }

    @Override
    public V getByField(String field, Object value, Number partitionSeed) {
        return getByField(field, value, partitionSeed, null, ReadPolicy.MASTER);
    }

    @Override
    public V getByField(String field, Object value, Number partitionSeed, String table, ReadPolicy readPolicy) {
        if (StringUtils.isNotEmpty(field)) {
            Collection<Criterion> criterions = new ArrayList<Criterion>(1);
            criterions.add(Restrictions.eq(field, value));

            return get(criterions, null, partitionSeed, table, readPolicy);
        }

        return null;
    }

    @Override
    public V get(Collection<Criterion> criterions, List<Order> orders, Number partitionSeed) {
        return get(criterions, orders, partitionSeed, null, ReadPolicy.MASTER);
    }

    @Override
    public V get(Collection<Criterion> criterions,
                 List<Order> orders,
                 Number partitionSeed,
                 String table,
                 ReadPolicy readPolicy) {
        try {
            List<V> entities = getPage(criterions, orders, 0, 1, partitionSeed, table, readPolicy);
            if (CollectionUtils.isNotEmpty(entities)) {
                return entities.get(0);
            }
        } catch (Throwable e) {
            throw new DAOReadException(e);
        }

        return null;
    }

    @Override
    public void delete(V entityObject) {
        delete(entityObject, null, null);
    }

    @Override
    public void delete(V entityObject, Number partitionSeed) {
        delete(entityObject, partitionSeed, null);
    }

    @Override
    public void delete(V entityObject, Number partitionSeed, String table) {
        if (entityObject != null) {
            try {
                setPartition(partitionSeed, table, null);
                this.getHibernateTemplate().delete(entityObject);
            } catch (Throwable e) {
                throw new DAOWriteException(entityObject.getId().toString(), e);
            } finally {
                clearPartition();
            }
        }
    }

    @Override
    public void deleteAll(Collection<V> entities) {
        deleteAll(entities, null, null);
    }

    @Override
    public void deleteAll(Collection<V> entities, Number partitionSeed) {
        deleteAll(entities, partitionSeed, null);
    }

    @Override
    public void deleteAll(Collection<V> entities, Number partitionSeed, String table) {
        if (CollectionUtils.isNotEmpty(entities)) {
            try {
                setPartition(partitionSeed, table, null);
                this.getHibernateTemplate().deleteAll(entities);
            } catch (Throwable e) {
                throw new DAOWriteException(e);
            } finally {
                clearPartition();
            }
        }
    }

    @Override
    public void deleteById(K id) {
        this.deleteById(id, null, null);
    }

    @Override
    public void deleteById(K id, Number partitionSeed) {
        this.deleteById(id, partitionSeed, null);
    }

    @Override
    public void deleteById(K id, Number partitionSeed, String table) {
        this.delete(getById(id, partitionSeed, table, null), partitionSeed, table);
    }

    @Override
    public List<V> getAll() {
        return getAll(null, null, null, null, null);
    }

    @Override
    public List<V> getAllByField(String field,
                                 Object value,
                                 Number partitionSeed,
                                 String table,
                                 ReadPolicy readPolicy) {
        return getAll(buildEQ(field, value), null, partitionSeed, table, readPolicy);
    }

    @Override
    public List<V> getAll(Collection<Criterion> criterions, List<Order> orders) {
        return getAll(criterions, orders, null, null, null);
    }

    @Override
    public List<V> getAll(Collection<Criterion> criterions, List<Order> orders, Number partitionSeed) {
        return getAll(criterions, orders, partitionSeed, null, null);
    }

    @Override
    public List<V> getAll(Collection<Criterion> criterions,
                          List<Order> orders,
                          Number partitionSeed,
                          String table,
                          ReadPolicy readPolicy) {
        return getAll(criterions, null, null, orders, partitionSeed, table, readPolicy);
    }


    @Override
    public <T> List<T> getAll(Collection<Criterion> criterions,
                              ProjectionList projectionList,
                              Class<T> clazz,
                              List<Order> orders,
                              Number partitionSeed,
                              String table,
                              ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);
            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null) {
                for (Criterion criterion : criterions) {
                    dc.add(criterion);
                }
            }
            if (projectionList != null && clazz != null) {
                dc.setProjection(projectionList)
                  .setResultTransformer(new AliasToBeanResultTransformer(clazz));
            }
            if (orders != null) {
                for (Order order : orders) {
                    dc.addOrder(order);
                }
            }

            return this.getHibernateTemplate()
                       .execute((HibernateCallback<List<T>>) session -> dc.getExecutableCriteria(session).list());
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }
    }

    @Override
    public void save(V entityObject) {
        save(entityObject, null, null);
    }

    @Override
    public void save(V entityObject, Number partitionSeed) {
        save(entityObject, partitionSeed, null);
    }

    @Override
    public void save(V entityObject, Number partitionSeed, String table) {
        if (entityObject != null) {
            Date now = new Date();
            if (entityObject.getCreateTime() == null) {
                entityObject.setCreateTime(now);
            }
            entityObject.setModifyTime(now);
            entityObject.prepare();
            try {
                setPartition(partitionSeed, table, null);
                this.getHibernateTemplate().saveOrUpdate(entityObject);
            } catch (Throwable e) {
                throw new DAOWriteException(e);
            } finally {
                clearPartition();
            }
        }
    }

    @Override
    public void save(Collection<V> entities, Number partitionSeed, String table) {
        if (entities != null && entities.size() > 0) {
            for (V entityObject : entities) {
                if (entityObject != null) {
                    Date now = new Date();
                    if (entityObject.getCreateTime() == null) {
                        entityObject.setCreateTime(now);
                    }
                    entityObject.setModifyTime(now);
                    entityObject.prepare();
                }
            }

            try {
                setPartition(partitionSeed, table, null);
                for (V entity : entities) {
                    this.getHibernateTemplate().saveOrUpdate(entity);
                }
            } catch (Throwable e) {
                throw new DAOWriteException(e);
            } finally {
                clearPartition();
            }
        }
    }

    @Override
    public List<V> getPage(Collection<Criterion> criterions, List<Order> orders, final int offset, final int size) {
        return getPage(criterions, orders, offset, size, null, null, null);
    }

    @Override
    public List<V> getPage(Collection<Criterion> criterions,
                           List<Order> orders,
                           int offset,
                           int size,
                           Number partitionSeed) {
        return getPage(criterions, orders, offset, size, partitionSeed, null, null);
    }

    @Override
    public List<V> getPage(Collection<Criterion> criterions,
                           List<Order> orders,
                           final int offset,
                           final int size,
                           Number partitionSeed,
                           String table,
                           ReadPolicy readPolicy) {
        return getPage(null, criterions, null, orders, offset, size, partitionSeed, table, readPolicy);
    }

    @Override
    public <T> List<T> getPage(Class<T> clazz,
                               Collection<Criterion> criterions,
                               ProjectionList projectionList,
                               List<Order> orders,
                               int offset,
                               int size,
                               Number partitionSeed,
                               String table,
                               ReadPolicy readPolicy) {
        try {
            setPartition(partitionSeed, table, readPolicy);

            final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
            if (criterions != null && !criterions.isEmpty()) {
                for (Criterion criterion : criterions) {
                    dc.add(criterion);
                }
            }
            if (projectionList != null && clazz != null) {
                dc.setProjection(projectionList)
                  .setResultTransformer(new AliasToBeanResultTransformer(clazz));
            }
            if (orders != null) {
                for (Order order : orders) {
                    dc.addOrder(order);
                }
            } else {
                dc.addOrder(Order.asc(entityKey));
            }

            return this.getHibernateTemplate().execute((HibernateCallback<List<T>>) session -> {
                Criteria criteria = dc.getExecutableCriteria(session);
                criteria.setMaxResults(size);
                criteria.setFirstResult(offset);
                return criteria.list();
            });
        } catch (Throwable e) {
            throw new DAOReadException(e);
        } finally {
            clearPartition();
        }
    }


    @Override
    public PageResult<V> getPageResult(Collection<Criterion> criterions, List<Order> orders, int page, int size) {
        return getPageResult(null, criterions, null, orders, page, size);
    }

    @Override
    public <T> PageResult<T> getPageResult(Class<T> clazz,
                                           Collection<Criterion> criterions,
                                           ProjectionList projectionList,
                                           List<Order> orders,
                                           int page,
                                           int size) {
        PageResult<T> result = new PageResult<>();
        int total = count(criterions);
        if (total < page) page = total;

        result.setTotal(total);
        result.setParameters(total, page, size);
        List<T> items = getPage(clazz,
                                criterions,
                                projectionList,
                                orders,
                                result.getStart(),
                                result.getLimit(), null, null, null);
        result.setResult(items);

        return result;
    }

    @Override
    public void delete(Collection<Criterion> criterions) {
        delete(criterions, null, null);
    }

    @Override
    public void delete(Collection<Criterion> criterions, Number partitionSeed) {
        delete(criterions, partitionSeed, null);
    }

    @Override
    public void delete(Collection<Criterion> criterions, Number partitionSeed, String table) {
        if (CollectionUtils.isNotEmpty(criterions)) {
            try {
                Collection<V> entities = null;
                while (CollectionUtils.isNotEmpty(entities = this.getPage(criterions,
                                                                          null,
                                                                          0,
                                                                          100,
                                                                          partitionSeed,
                                                                          table,
                                                                          null))) {
                    deleteAll(entities, partitionSeed, table);
                }
            } catch (Throwable e) {
                throw new DAOWriteException(e);
            }
        }
    }

    @Override
    public void iterate(int batchSize, boolean byIdRange, BatchHandler<V> handler) {
        iterate(null, null, batchSize, byIdRange, handler, null, 1, true, null, null, ReadPolicy.MASTER);
    }

    @Override
    public void iterate(Collection<Criterion> criterions,
                        List<Order> orders,
                        int batchSize,
                        boolean byIdRange,
                        BatchHandler<V> handler) {
        iterate(criterions, orders, batchSize, byIdRange, handler, null, 1, true, null, null, ReadPolicy.MASTER);
    }


    @Override
    public void iterate(final Collection<Criterion> criterions,
                        final List<Order> orders,
                        final int batchSize,
                        boolean byIdRange,
                        final BatchHandler<V> handler,
                        ExecutorService executorService,
                        int workers,
                        boolean wait,
                        final Number partitionSeed,
                        final String table,
                        final ReadPolicy readPolicy) {
        if (handler != null) {
            final int minId = byIdRange
                    ? this.min(null, this.entityKey, partitionSeed, table, readPolicy).intValue()
                    : 0;
            final int maxId = byIdRange
                    ? this.max(null, this.entityKey, partitionSeed, table, readPolicy).intValue()
                    : 0;
            if (maxId > 0 && executorService != null && !executorService.isShutdown()) {
                final int step = (maxId - minId + 1) / workers + 1;
                final Set<Integer> tasks = Collections.synchronizedSet(new HashSet<Integer>());
                for (int i = 0; i < workers; i++) {
                    final int min = minId + i * step;
                    tasks.add(min);
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            iterate(min,
                                    Math.min(min + step - 1, maxId),
                                    criterions,
                                    orders,
                                    batchSize,
                                    handler,
                                    partitionSeed,
                                    table,
                                    readPolicy);
                            tasks.remove(min);
                        }
                    });
                }

                while (wait && !tasks.isEmpty()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                iterate(minId, maxId, criterions, orders, batchSize, handler, partitionSeed, table, readPolicy);
            }
        }
    }

    private void iterate(int minId,
                         int maxId,
                         Collection<Criterion> criterions,
                         List<Order> orders,
                         int batchSize,
                         BatchHandler<V> consumer,
                         Number partitionSeed,
                         String table,
                         ReadPolicy readPolicy) {
        int offset = minId;
        Collection<V> items = null;
        while (CollectionUtils.isNotEmpty(items = this.getPage(getIterateCriterions(criterions,
                                                                                    offset,
                                                                                    batchSize,
                                                                                    maxId),
                                                               orders,
                                                               maxId > 0 ? 0 : offset,
                                                               batchSize,
                                                               partitionSeed,
                                                               table,
                                                               readPolicy)) || (maxId > 0 && offset <= maxId)) {
            if (CollectionUtils.isNotEmpty(items)) {
                try {
                    for (V item : items) {
                        if (item != null) {
                            try {
                                consumer.consume(item);
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

            offset += batchSize;

            consumer.afterBatch(offset);
        }
    }

    private Collection<Criterion> getIterateCriterions(Collection<Criterion> criterions,
                                                       int offset,
                                                       int size,
                                                       int maxId) {
        if (maxId > 0) {
            Collection<Criterion> result = new ArrayList<Criterion>(criterions == null ? 1 : 1 + criterions.size());
            result.add(Restrictions.between(this.entityKey,
                                            Integer.valueOf(offset),
                                            Math.min(offset + size - 1, maxId)));
            if (criterions != null) {
                result.addAll(criterions);
            }
            return result;
        }
        return criterions;
    }

    @Override
    public void clearPartition() {
        if (this.partitionedTable != null) {
            PartitionContext.clear();
        }
    }

    @Override
    public Map<K, V> getByIdsMap(Collection<K> ids) {
        return getByIdsMap(ids, null);
    }

    @Override
    public Map<K, V> getByIdsMap(Collection<K> ids, Number partitionSeed) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyMap();
        }

        List<V> entities = getByIds(ids, partitionSeed, null, null);
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyMap();
        }

        Map<K, V> entityMap = new HashMap<K, V>();
        for (V entity : entities) {
            if (entity != null) {
                entityMap.put(entity.getId(), entity);
            }
        }

        return entityMap;
    }

    @Override
    public List<V> getByIds(Collection<K> ids) {
        return getByIds(ids, null, null, null);
    }

    @Override
    public List<V> getByIds(Collection<K> ids, Number partitionSeed, String table, ReadPolicy readPolicy) {
        return this.selectIn(entityKey, ids, null, partitionSeed, null, readPolicy);
    }

    @Override
    public List<V> selectIn(String field,
                            Collection<?> values,
                            List<Order> orders,
                            Number partitionSeed,
                            String table,
                            ReadPolicy readPolicy) {
        if (StringUtils.isNotEmpty(field) && CollectionUtils.isNotEmpty(values)) {
            try {
                setPartition(partitionSeed, table, readPolicy);

                final DetachedCriteria dc = DetachedCriteria.forClass(this.entityClass);
                dc.add(Restrictions.in(field, values));
                if (orders != null) {
                    for (Order order : orders) {
                        dc.addOrder(order);
                    }
                }

                return this.getHibernateTemplate()
                           .execute((HibernateCallback<List<V>>) session -> dc.getExecutableCriteria(session).list());
            } catch (Throwable e) {
                throw new DAOReadException(e);
            } finally {
                clearPartition();
            }
        }

        return Collections.emptyList();
    }

    private void setPartition(Number partitionSeed, String table, ReadPolicy readPolicy) {
        setPartition(partitionSeed, table, readPolicy, true);
    }

    @Override
    public void setPartition(Number partitionSeed, String table, ReadPolicy readPolicy, boolean mod) {
        if (this.partitionedTable != null) {
            if (readPolicy == null) {
                readPolicy = ReadPolicy.MASTER;
            }
            int partiton = 0;
            if (this.partitionedTable.value() != 0) {
                partiton = Math.abs(this.partitionedTable.value());
            } else if (partitionSeed != null) {
                partiton = mod
                        ? ((int) (partitionSeed.longValue() % PartitionContext.SIZE) + 1)
                        : partitionSeed.intValue();
            }

            switch (readPolicy) {
                case MASTER:
                    partiton = Math.abs(partiton);
                    break;
                case SLAVER:
                    partiton = -Math.abs(partiton);
                    break;
                case AUTO:
                    partiton = NumberUtil.nextInt(2) == 1 ? Math.abs(partiton) : (-Math.abs(partiton));
                    break;
            }

            PartitionContext.set(partiton, table);
        }
    }

    @Override
    public boolean isAlive(Number partitionSeed, ReadPolicy readPolicy) {
        return getNumber("select 1", null, partitionSeed, null, readPolicy).intValue() > 0;
    }

    @Override
    public V getByField(String field, Object value) {
        return getByField(field, value, null, null, null);
    }

    @Override
    public Number getNumber(String sql, Object[] vars) {
        return getNumber(sql, vars, null, null, null);
    }

    @Override
    public V get(Collection<Criterion> criterions, List<Order> orders) {
        return get(criterions, orders, null, null, null);
    }

    @Override
    public List<V> selectIn(String field, Collection<?> values, List<Order> orders) {
        return selectIn(field, values, orders, null, null, null);
    }

    @Override
    public Number max(Collection<Criterion> criterions, String property) {
        return max(criterions, property, null, null, null);
    }

    @Override
    public Number min(Collection<Criterion> criterions, String property) {
        return min(criterions, property, null, null, null);
    }

    @Override
    public Number sum(Collection<Criterion> criterions, String property) {
        return sum(criterions, property, null, null, null);
    }

    public int fieldUpdate(String field, Object value, K id) {
        return fieldUpdate(field, value, id, -1, null, null);
    }
    @Override
    public int fieldUpdate(String field, Object value, K id, int modifier) {
        return fieldUpdate(field, value, id, modifier, null, null);
    }

    @Override
    public int fieldUpdate(String field, Object value, K id, int modifier, Number partitionSeed) {
        return fieldUpdate(field, value, id, modifier, partitionSeed, null);
    }

    @Override
    public int fieldUpdate(String field,
                           Object value,
                           Collection<Criterion> criterions,
                           int modifier,
                           Number partitionSeed,
                           String table) {
        int count = 0;
        List<V> items = getAll(criterions, null, partitionSeed, table, ReadPolicy.MASTER);
        if (CollectionUtils.isNotEmpty(items)) {
            for (V item : items) {
                if (item != null) {
                    count += fieldUpdate(field, value, item.getId(), modifier, partitionSeed, table);
                }
            }
        }

        return count;
    }

    @Override
    public int fieldUpdate(final String field, final Object value, final K id, final int modifier, Number partitionSeed, String table) {
        if (StringUtils.isNotEmpty(field) && value != null && id != null) {
            final String column = _getField(field);
            final String modifyColumn = _getField("modifyTime");
            final String modifierColumn = _getField("modifier");
            if (StringUtils.isNotEmpty(column)) {
                try {
                    setPartition(partitionSeed, table, null);
                    int updated = (Integer) this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
                        public Integer doInHibernate(Session session) throws HibernateException {
                            SQLQuery query = session.createSQLQuery(StringUtils.join(new String[]{
                                    "update",
                                    getTable(),
                                    "set",
                                    column,
                                    "=?",
                                    StringUtils.isEmpty(modifyColumn)
                                            ? StringConsts.EMPTY
                                            : ("," + modifyColumn + "=now()"),
                                    StringUtils.isEmpty(modifierColumn) || modifier < 0
                                            ? StringConsts.EMPTY
                                            : ("," + modifierColumn + "=" + modifier),
                                    "where",
                                    primaryKey,
                                    "=?"
                            }, " "));
                            query.setParameter(0, value);
                            query.setParameter(1, id);
                            return query.executeUpdate();
                        }
                    });

                    return updated;
                } catch (Throwable e) {
                    throw new DAOWriteException(field, e);
                } finally {
                    clearPartition();
                }
            }
        }
        return 0;
    }

    @Override
    public int deltaUpdate(final String field, final Object value, final K id) {
        return this.deltaUpdate(field, value, id, null, null);
    }

    @Override
    public int deltaUpdate(final String field,
                           final Object value,
                           final K id,
                           Number partitionSeed,
                           String table) {
        if (StringUtils.isNotEmpty(field) && value != null && id != null) {
            final String column = _getField(field);
            final String modifyColumn = _getField("modifyTime");
            if (StringUtils.isNotEmpty(column)) {
                try {
                    setPartition(partitionSeed, table, null);
                    int updated = this.getHibernateTemplate().execute(session -> {
                        SQLQuery query = session.createSQLQuery(StringUtils.join(new String[]{
                                "update",
                                getTable(),
                                "set",
                                column,
                                "=",
                                column,
                                "+?",
                                StringUtils.isEmpty(modifyColumn)
                                        ? StringConsts.EMPTY
                                        : ("," + modifyColumn + "=now()"),
                                "where",
                                primaryKey,
                                "=?"
                        }, " "));
                        query.setParameter(0, value);
                        query.setParameter(1, id);
                        return query.executeUpdate();
                    });

                    return updated;
                } catch (Throwable e) {
                    throw new DAOWriteException(field, e);
                } finally {
                    clearPartition();
                }
            }
        }
        return 0;
    }

    public String getTable() {
        return StringUtils.defaultIfEmpty(PartitionContext.getTable(), this.table);
    }

    private String _getField(String property) {
        if (StringUtils.isNotEmpty(property)) {
            try {
                Field[] fields = this.entityClass.getDeclaredFields();
                if (ArrayUtils.isNotEmpty(fields)) {
                    for (Field field : fields) {
                        if (field != null && field.getName().equals(property)) {
                            if (field.isAnnotationPresent(Column.class)) {
                                return StringUtils.defaultIfEmpty(field.getAnnotation(Column.class).name(), property);
                            }

                            if (!field.isAnnotationPresent(Transient.class)) {
                                return namingStrategy.propertyToColumnName(property);
                            }
                        }
                    }
                }
            } catch (Throwable t) {
            }
        }
        return null;

    }

    private List<Criterion> buildEQ(String field, Object value) {
        List<Criterion> criterions = new ArrayList<Criterion>(1);
        criterions.add(Restrictions.eq(field, value));
        return criterions;
    }

    public List<Order> buildOrder(String field, boolean desc) {
        List<Order> orders = new ArrayList<Order>(1);
        orders.add(desc ? Order.desc(field) : Order.asc(field));
        return orders;
    }

    @Override
    public List<V> top(Collection<Criterion> criterions,
                       List<Order> orders,
                       int size,
                       Comparator<V> comparator,
                       Collection<Number> partitions,
                       String table,
                       ReadPolicy readPolicy) {
        if (CollectionUtils.isEmpty(partitions)) {
            List<V> items = getPage(criterions, orders, 0, size, null, table, readPolicy);
            if (CollectionUtils.isNotEmpty(items) && comparator != null) {
                Collections.sort(items, comparator);
            }
            return items;
        }

        List<V> items = new ArrayList<V>(size * partitions.size());
        for (Number partition : partitions) {
            items.addAll(getPage(criterions, orders, 0, size, partition, table, readPolicy));
        }

        if (CollectionUtils.isNotEmpty(items) && comparator != null) {
            Collections.sort(items, comparator);
        }

        if (items.size() > size) {
            for (int i = items.size() - 1; i >= size; i--) {
                items.remove(i);
            }
        }

        return items;
    }

    private Connection getConnection(Number partitionSeed) {
        try {
            setPartition(partitionSeed, null, null);
            DataSource dataSource = SessionFactoryUtils.getDataSource(getSessionFactory());
            if (dataSource != null) {
                return dataSource.getConnection();
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            clearPartition();
        }
        return null;
    }

    @Override
    public void useConnection(Consumer<Connection> processor) {
        useConnection(null, processor);
    }

    @Override
    public void useConnection(Number partitionSeed, Consumer<Connection> processor) {
        useConnection(partitionSeed, processor, false);
    }

    @Override
    public void useConnection(Number partitionSeed, Consumer<Connection> processor, boolean transaction) {
        if (processor != null) {
            Connection conn = null;
            try {
                conn = getConnection(partitionSeed);
                if (transaction) {
                    conn.setAutoCommit(false);
                }
                processor.accept(conn);
                if (transaction) {
                    conn.commit();
                }
            } catch (Throwable e) {
                if (transaction) {
                    JdbcUtil.rollback(conn);
                }
                throw new RuntimeException(e);
            } finally {
                JdbcUtil.closeQuiet(conn);
            }
        }
    }


    @Override
    public <T> T useConnection(Function<Connection, T> func) {
        return useConnection(null, func);
    }

    @Override
    public <T> T useConnection(Number partitionSeed, Function<Connection, T> func) {
        return useConnection(partitionSeed, func, false);
    }

    @Override
    public <T> T useConnection(Number partitionSeed, Function<Connection, T> func, boolean transaction) {
        if (func != null) {
            Connection conn = null;
            try {
                conn = getConnection(partitionSeed);
                if (transaction) {
                    conn.setAutoCommit(false);
                }
                T value = func.apply(conn);
                if (transaction) {
                    conn.commit();
                }
                return value;
            } catch (Throwable e) {
                if (transaction) {
                    JdbcUtil.rollback(conn);
                }
                throw new RuntimeException(e);
            } finally {
                JdbcUtil.closeQuiet(conn);
            }
        }
        return null;
    }
}
