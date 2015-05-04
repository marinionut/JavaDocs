package ro.teamnet.zth.api.em;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionutz on 29.04.2015.
 */
public class QueryBuilder {
    /*
    private Object tableName;
    private List<ColumnInfo> queryColumns = new ArrayList<ColumnInfo>();
    private QueryType queryType;
    private List<Condition> conditions = new ArrayList<Condition>();

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
//        for(ColumnInfo c : queryColumns) this.queryColumns.add(c);
        this.queryColumns.addAll(queryColumns);
        return this;
    }

    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    public QueryBuilder addCondition(Condition condition) {
        this.conditions.add(condition);
        return this;
    }

    public boolean isFirst(ColumnInfo c) {
        return c.equals(queryColumns.get(0));
    }

    private String createSelectQuery(){
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        for(ColumnInfo c : queryColumns) {
            if(isFirst(c)) {
                str.append(c.getColumnName());
            }
            else
                str.append(", " + c.getColumnName());
        }

        str.append("FROM ");
        str.append(tableName);

        if(conditions != null && !conditions.isEmpty()) {
            str.append(" WHERE 1=1 ");
            for(Condition c : conditions) {
                if(c.getValue() instanceof String) {
                    str.append(" and ").append(c.getColumnName()).append("=").append("'").append(c.getValue()).append("'");
                }
                else str.append(" and ").append(c.getColumnName()).append("=").append(c.getValue());
            }
        }
        str.append(";");

        return str.toString();
    }
    //De verificat
    private String createDeleteQuery(){
        StringBuilder str = new StringBuilder();
        str.append("DELETE ");

        str.append("FROM ");
        str.append(tableName);

        if(conditions != null && !conditions.isEmpty()) {
            str.append(" WHERE 1=1 ");
            for(Condition c : conditions) {
                if(c.getValue() instanceof String) {
                    str.append(" and ").append(c.getColumnName()).append("=").append("'").append(c.getValue()).append("'");
                }
                else str.append(" and ").append(c.getColumnName()).append("=").append(c.getValue());
            }
        }
        str.append(";");
        return str.toString();
    }

    private String createUpdateQuery(){
        StringBuilder str = new StringBuilder();
        str.append("UPDATE ");
        str.append(tableName);
        str.append(" SET ");
        for (ColumnInfo c : queryColumns) {
            if(isFirst(c)) {
                str.append(c.getColumnName() + " = " + c.getValue());
            }
            else str.append("," + c.getColumnName() + " = " + c.getValue());
        }

        if(conditions != null & !conditions.isEmpty()) {
            str.append(" WHERE 1=1 ");
            for(Condition c : conditions) {
                if(c.getValue() instanceof String) {
                    str.append(" and ").append(c.getColumnName()).append("=").append("'").append(c.getValue()).append("'");
                }
                else str.append(" and ").append(c.getColumnName()).append("=").append(c.getValue());
            }
        }

        str.append(";");
        return str.toString();
    }

    private String createInsertQuery(){
        StringBuilder str = new StringBuilder();
        str.append("INSERT INTO ");
        str.append(tableName);
        str.append(" (");
        for(ColumnInfo c : queryColumns) {
            if(isFirst(c)) {
                str.append(c.getColumnName());
            }
            else str.append(", " + c.getColumnName());
        }

        str.append(") ");
        str.append("VALUES(");
        for(ColumnInfo c : queryColumns) {
            if(isFirst(c)) {
                str.append(c.getValue());
            }
            else str.append(", " + c.getValue());
        }
        str.append(")");
        str.append(";");
        return str.toString();
    }

    public String createQuery(){
        if(queryType.equals(QueryType.SELECT))
            return createSelectQuery();
        if(queryType.equals(QueryType.INSERT))
            return createInsertQuery();
        if(queryType.equals(QueryType.DELETE))
            return createDeleteQuery();
        if(queryType.equals(QueryType.UPDATE))
            return createUpdateQuery();
        return "No query error!";
    }
*/
    private Object tableName;
    private List<ColumnInfo> queryColumns;
    private QueryType queryType;
    private List<Condition> conditions;

    /**
     * Create QueryBuilder object
     */
    public QueryBuilder() {

    }

    public QueryBuilder addCondition(Condition condition) {
        if (this.conditions == null){
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    /**
     * Set the table name for query
     * @param tableName - table name
     */
    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     *
     * @param queryColumns
     */
    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
        if (this.queryColumns == null){
            this.queryColumns = new ArrayList<>();
        }
        this.queryColumns.addAll(queryColumns);
        return this;
    }


    /**
     *
     * @param queryType
     */
    public QueryBuilder setQueryType(QueryType queryType) {
        this.queryType = queryType;
        return this;
    }

    /**
     * Create a SQL query
     * @return - a valid SQL query
     */
    public String createQuery() {
        if (QueryType.SELECT.equals(this.queryType)){
            return createSelectQuery();
        } else if (QueryType.INSERT.equals(this.queryType)) {
            return createInsertQuery();
        } else if (QueryType.UPDATE.equals(this.queryType)) {
            return createUpdateQuery();
        } else if (QueryType.DELETE.equals(this.queryType)) {
            return createDeleteQuery();
        }
        return null;
    }

    private String createSelectQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        boolean isFirst = true;
        for(ColumnInfo columnInfo : queryColumns) {
            if(!isFirst) {
                sql.append(",");
            }
            sql.append(tableName + "." + columnInfo.getDbName());
            isFirst = false;
        }
        sql.append(" from " + tableName);

        boolean whereAdded = false;
        if(conditions != null && !conditions.isEmpty()) {
            for(Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=")
                        .append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createDeleteQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(tableName);
        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createUpdateQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(tableName).append(" set ");
        boolean first = true;
        for (ColumnInfo column : queryColumns) {
            if (!column.isId()) {
                if (!first) {
                    sql.append(",");
                } else {
                    first = false;
                }
                sql.append(column.getDbName()).append("=").append(getValueForQuery(column.getValue()));
            }
        }

        boolean whereAdded = false;
        if (conditions != null  && !conditions.isEmpty()){
            for (Condition condition : conditions) {
                sql.append(whereAdded ? " and" : " where ").append(condition.getColumnName()).append("=").append(getValueForQuery(condition.getValue()));
                whereAdded = true;
            }
        }
        return sql.toString();
    }

    private String createInsertQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into ").append(tableName).append(" (");
        StringBuilder sqlValues = new StringBuilder(" values (");
        boolean first = true;
        for (ColumnInfo columnInfo : queryColumns) {
            if (columnInfo.isId()) {
                continue;
            }
            if (!first) {
                sql.append(",");
                sqlValues.append(",");
            } else {
                first = false;
            }
            sql.append(columnInfo.getDbName());
            sqlValues.append(getValueForQuery(columnInfo.getValue()));
        }

        sql.append(") ");
        sqlValues.append(")");
        sql.append(sqlValues);

        return sql.toString();
    }

    private String getValueForQuery(Object value) {
        if (value == null){
            return null;
        }
        if (value instanceof String){
            return "'" + value + "'";
        } else {
            return value.toString();
        }
    }

}
