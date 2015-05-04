package ro.teamnet.zth.api.em;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.database.DBProperties;

import javax.xml.transform.Result;

/**
 * Created by Ionutz on 30.04.2015.
 */
public  class EntityManagerImpl implements EntityManager {
    @Override
    public <T> T findById(Class<T> entityClass, Object id) {
        try {
            Connection conn = DBManager.getConnection();
            Statement stmt = conn.createStatement();

            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
            List<Field> fieldsByAnnotations = EntityUtils.getFieldByAnnotations(entityClass, Id.class);
            Condition cond = new Condition(fieldsByAnnotations.get(0).getAnnotation(Id.class).name(), id);
            QueryBuilder query = new QueryBuilder();
            query.setTableName(tableName);
            query.addQueryColumns(columns);
            query.setQueryType(QueryType.SELECT);
            query.addCondition(cond);

            String sqlQuery = query.createQuery();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            T instance = null;

            if (rs.next()) {
                instance = entityClass.newInstance();
                for (ColumnInfo c : columns) {
                    c.setValue(rs.getObject(c.getDbName()));
                    Field field = instance.getClass().getDeclaredField(c.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(c.getValue(), c.getColumnType()));
                }
            }
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        /*
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement()) {

            QueryBuilder query = new QueryBuilder();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
            List<Field> fieldsByAnnotations = EntityUtils.getFieldByAnnotations(entityClass, Id.class);

            Condition condition = new Condition(fieldsByAnnotations.get(0).getAnnotation(Id.class).name(), id);
            query.setTableName(tableName).addQueryColumns(columns).setQueryType(QueryType.SELECT).addCondition(
                    condition);
            String sql = query.createQuery();
            ResultSet rs = stmt.executeQuery(sql);

            T instance = null;
            if(rs.next()) {
                instance = entityClass.newInstance();
                for(ColumnInfo column : columns) {
                    column.setValue(rs.getObject(column.getDbName()));
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(column.getValue(), column.getColumnType()));
                }
            }
            return instance;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
        */
    }
        @Override
    public <T> List<T> findAll(Class<T> entityClass) {
            try {
                Connection conn = DBManager.getConnection();
                Statement stmt = conn.createStatement();

                List<T> rows = new ArrayList<>();
                String tableName = EntityUtils.getTableName(entityClass);
                List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
                QueryBuilder query = new QueryBuilder();
                query.setTableName(tableName);
                query.addQueryColumns(columns);
                query.setQueryType(QueryType.SELECT);

                String sqlQuery = query.createQuery();
                ResultSet rs = stmt.executeQuery(sqlQuery);

                T instance = entityClass.newInstance();

                while (rs.next()) {
                    for (ColumnInfo c : columns) {
                        c.setValue(rs.getObject(c.getDbName()));
                        Field field = instance.getClass().getDeclaredField(c.getColumnName());
                        field.setAccessible(true);
                        field.set(instance, EntityUtils.castFromSqlType(c.getValue(), c.getColumnType()));
                    }
                    rows.add(instance);
                }
                return rows;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                return null;
            } catch (InstantiationException e) {
                e.printStackTrace();
                return null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
            /*
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement()){

            List<T> rows = new ArrayList<>();
            QueryBuilder query = new QueryBuilder();
            String tableName = EntityUtils.getTableName(entityClass);
            List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
            query.setTableName(tableName).addQueryColumns(columns).setQueryType(QueryType.SELECT);
            String sql = query.createQuery();
            ResultSet rs = stmt.executeQuery(sql);

            T instance = entityClass.newInstance();
            while (rs.next()) {
                for (ColumnInfo column : columns) {
                    column.setValue(rs.getObject(column.getDbName()));
                    Field field = instance.getClass().getDeclaredField(column.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(column.getValue(), column.getColumnType()));
                }
                rows.add(instance);
            }
            return rows;
        } catch (SQLException | NoSuchFieldException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            return null;
        }
        */
    }

    @Override
    public <T> T insert(T entity) {
        try {
            Connection conn = DBManager.getConnection();
            Statement stmt = conn.createStatement();

                QueryBuilder query = new QueryBuilder();
                String tableName = EntityUtils.getTableName(entity.getClass());
                List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

                Integer lastId;
                for (ColumnInfo c : columns) {
                    {
                        Field field = entity.getClass().getDeclaredField(c.getColumnName());
                        field.setAccessible(true);
                        Object value = field.get(entity);
                        c.setValue(EntityUtils.getSqlValue(value));
                    }
                }

                query.setTableName(tableName).setQueryType(QueryType.INSERT).addQueryColumns(columns);

                String sqlQuery = query.createQuery();
                stmt.executeUpdate(sqlQuery);

                T instance = null;
                sqlQuery = "select LAST_INSERT_ID()";
                ResultSet rs = stmt.executeQuery(sqlQuery);
                rs.next();
                lastId = rs.getInt(1);
                rs.close();
                return (T) findById(entity.getClass(), lastId.intValue());
        } catch(SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <T> T update(T entity) {
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement()) {
            QueryBuilder query = new QueryBuilder();
            //get table name
            String tableName = EntityUtils.getTableName(entity.getClass());
            //get columns
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());
            //set values for columns
            for(ColumnInfo column : columns) {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                Object value = field.get(entity);
                column.setValue(EntityUtils.getSqlValue(value));
            }
            Condition condition = new Condition(columns.get(0).getDbName(), columns.get(0).getValue());
            query = query.setTableName(tableName).setQueryType(QueryType.UPDATE).addQueryColumns(columns)
                    .addCondition(condition);
            String sql = query.createQuery();
            System.out.println(sql);
            stmt.executeUpdate(sql);
            return entity;
        } catch(SQLException  | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Object entity) {
        try( Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement()){
            QueryBuilder query = new QueryBuilder();
            String tableName = EntityUtils.getTableName(entity.getClass());
            List<ColumnInfo> columns = EntityUtils.getColumns(entity.getClass());

            for(ColumnInfo column : columns) {
                Field field = entity.getClass().getDeclaredField(column.getColumnName());
                field.setAccessible(true);
                Object value = field.get(entity);
                column.setValue(EntityUtils.getSqlValue(value));
            }
            Condition condition = new Condition(columns.get(0).getDbName(), columns.get(0).getValue());
            query.setTableName(tableName).setQueryType(QueryType.DELETE).addCondition(condition);
            String sql = query.createQuery();
            stmt.executeUpdate(sql);
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private Long getSeqNextValue() throws SQLException, ClassNotFoundException {
        ResultSet rs;
        try(Connection conn = DBManager.getConnection();
            Statement stmt = conn.createStatement()){
            String sql = "select ZTH_SEQ.nextval from dual";
            rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getLong(1);
        }
    }
}
