package com.zjicm.common.lang.sql.dao;

import com.zjicm.common.lang.function.Consumer;
import com.zjicm.common.lang.function.Function;
import com.zjicm.common.lang.sql.BatchHandler;
import com.zjicm.common.lang.sql.ReadPolicy;
import com.zjicm.common.lang.page.PageResult;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;

import java.io.Serializable;
import java.sql.Connection;
import java.util.*;
import java.util.concurrent.ExecutorService;


public interface BaseDAO<V, K extends Serializable> {

    /**
     * @param func 业务处理函数
     * @return 函数返回值
     */
    <T> T useConnection(Function<Connection, T> func);

    /**
     * @param partitionSeed 数据库分区种子
     * @param func          业务处理函数
     * @return 函数返回值
     */
    <T> T useConnection(Number partitionSeed, Function<Connection, T> func);

    <T> T useConnection(Number partitionSeed, Function<Connection, T> func, boolean transaction);


    /**
     * @param processor 业务逻辑处理器
     */
    void useConnection(Consumer<Connection> processor);

    /**
     * @param partitionSeed 数据库分区种子
     * @param processor     业务逻辑处理器
     */
    void useConnection(Number partitionSeed, Consumer<Connection> processor);

    void useConnection(Number partitionSeed, Consumer<Connection> processor, boolean transaction);

    V getById(K id);

    V getById(K id, Number partitionSeed);

    V getById(K id, Number partitionSeed, String table, ReadPolicy readPolicy);

    Map<K, V> getByIdsMap(Collection<K> ids);

    Map<K, V> getByIdsMap(Collection<K> ids, Number partitionSeed);

    List<V> getByIds(Collection<K> ids);

    List<V> getByIds(Collection<K> ids, Number partitionSeed, String table, ReadPolicy readPolicy);

    V getByField(String field, Object value);

    V getByField(String field, Object value, Number partitionSeed);

    V getByField(String field, Object value, Number partitionSeed, String table, ReadPolicy readPolicy);

    int deltaUpdate(String field, Object value, K id);

    int deltaUpdate(String field, Object value, K id, Number partitionSeed, String table);

    int fieldUpdate(String field, Object value, K id);

    int fieldUpdate(String field, Object value, K id, Number partitionSeed);

    int fieldUpdate(String field, Object value, K id, Number partitionSeed, String table);

    int fieldUpdate(String field,
                    Object value,
                    Collection<Criterion> criterions,
                    Number partitionSeed,
                    String table);

    Number getNumber(String sql, Object[] vars);

    Number getNumber(String sql, Object[] vars, Number partitionSeed, String table, ReadPolicy readPolicy);

    Collection<Number> getNumbers(String sql,
                                  Object[] vars,
                                  int offset,
                                  int size,
                                  Number partitionSeed,
                                  String table,
                                  ReadPolicy readPolicy);

    <T> Collection<T> getObjects(Class<T> clazz,
                                 String sql,
                                 Object[] vars,
                                 int offset,
                                 int size,
                                 Number partitionSeed,
                                 String table,
                                 ReadPolicy readPolicy);

    V get(Collection<Criterion> criterions, List<Order> orders);

    V get(Collection<Criterion> criterions, List<Order> orders, Number partitionSeed);

    V get(Collection<Criterion> criterions,
          List<Order> orders,
          Number partitionSeed,
          String table,
          ReadPolicy readPolicy);

    void save(V entityObject);

    void save(V entityObject, Number partitionSeed);

    void save(V entityObject, Number partitionSeed, String table);

    void save(Collection<V> entities, Number partitionSeed, String table);

    void delete(V entityObject);

    void delete(V entityObject, Number partitionSeed);

    void delete(V entityObject, Number partitionSeed, String table);

    void deleteAll(Collection<V> entities);

    void deleteAll(Collection<V> entities, Number partitionSeed);

    void deleteAll(Collection<V> entities, Number partitionSeed, String table);

    void delete(Collection<Criterion> criterions);

    void delete(Collection<Criterion> criterions, Number partitionSeed);

    void delete(Collection<Criterion> criterions, Number partitionSeed, String table);

    void deleteById(K id);

    void deleteById(K id, Number partitionSeed);

    void deleteById(K id, Number partitionSeed, String table);

    List<V> getAll();

    List<V> getAll(Collection<Criterion> criterions, List<Order> orders);

    List<V> getAllByField(String field, Object value, Number partitionSeed, String table, ReadPolicy readPolicy);

    List<V> getAll(Collection<Criterion> criterions, List<Order> orders, Number partitionSeed);

    List<V> getAll(Collection<Criterion> criterions,
                   List<Order> orders,
                   Number partitionSeed,
                   String table,
                   ReadPolicy readPolicy);

    <T> List<T> getAll(Collection<Criterion> criterions,
                       ProjectionList projectionList,
                       Class<T> clazz,
                       List<Order> orders,
                       Number partitionSeed,
                       String table,
                       ReadPolicy readPolicy);

    List<V> selectIn(String field, Collection<?> values, List<Order> orders);

    List<V> selectIn(String field,
                     Collection<?> values,
                     List<Order> orders,
                     Number partitionSeed,
                     String table,
                     ReadPolicy readPolicy);

    List<V> getPage(Collection<Criterion> criterions, List<Order> orders, int offset, int size);

    List<V> getPage(Collection<Criterion> criterions,
                    List<Order> orders,
                    int offset,
                    int size,
                    Number partitionSeed);

    List<V> getPage(Collection<Criterion> criterions,
                    List<Order> orders,
                    int offset,
                    int size,
                    Number partitionSeed,
                    String table,
                    ReadPolicy readPolicy);

    <T> List<T> getPage(Class<T> clazz,
                        Collection<Criterion> criterions,
                        ProjectionList projectionList,
                        List<Order> orders,
                        int offset,
                        int size,
                        Number partitionSeed,
                        String table,
                        ReadPolicy readPolicy);

    /**
     * 支持 PageResult 的分页查询
     *
     * @param criterions
     * @param orders
     * @param page
     * @param size
     * @return
     */
    PageResult<V> getPageResult(Collection<Criterion> criterions, List<Order> orders, int page, int size);

    <T> PageResult<T> getPageResult(Class<T> clazz,
                                    Collection<Criterion> criterions,
                                    ProjectionList projectionList,
                                    List<Order> orders,
                                    int page,
                                    int size);

    int count(Collection<Criterion> criterions);

    int count(Collection<Criterion> criterions, Number partitionSeed);

    int count(Collection<Criterion> criterions, Number partitionSeed, String table, ReadPolicy readPolicy);

    Number max(Collection<Criterion> criterions, final String property);

    Number max(Collection<Criterion> criterions,
               final String property,
               Number partitionSeed,
               String table,
               ReadPolicy readPolicy);

    Number min(Collection<Criterion> criterions, final String property);

    Number min(Collection<Criterion> criterions,
               final String property,
               Number partitionSeed,
               String table,
               ReadPolicy readPolicy);

    Number sum(Collection<Criterion> criterions, final String property);

    Number sum(Collection<Criterion> criterions,
               final String property,
               Number partitionSeed,
               String table,
               ReadPolicy readPolicy);

    <T> List<T> distinct(Collection<Criterion> criterions,
                         List<String> properties,
                         Class<T> clazz,
                         Number partitionSeed,
                         String table,
                         ReadPolicy readPolicy);

    void iterate(int batchSize, boolean byIdRange, BatchHandler<V> handler);

    void iterate(Collection<Criterion> criterions,
                 List<Order> orders,
                 int batchSize,
                 boolean byIdRange,
                 BatchHandler<V> handler);

    void iterate(Collection<Criterion> criterions,
                 List<Order> orders,
                 int batchSize,
                 boolean byIdRange,
                 BatchHandler<V> handler,
                 ExecutorService executorService,
                 int workers,
                 boolean wait,
                 Number partitionSeed,
                 String table,
                 ReadPolicy readPolicy);

    void setPartition(Number partitionSeed, String table, ReadPolicy readPolicy, boolean mod);

    void clearPartition();

    boolean isAlive(Number partitionSeed, ReadPolicy readPolicy);

    List<V> top(Collection<Criterion> criterions,
                List<Order> orders,
                int size,
                Comparator<V> comparator,
                Collection<Number> partitions,
                String table,
                ReadPolicy readPolicy);
}
