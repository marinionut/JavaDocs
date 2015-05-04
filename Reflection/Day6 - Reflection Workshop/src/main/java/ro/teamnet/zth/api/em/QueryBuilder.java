package ro.teamnet.zth.api.em;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionutz on 29.04.2015.
 */
public class QueryBuilder {

    private Object tableName;
    private List<ColumnInfo> queryColumns = new ArrayList<ColumnInfo>();
    private QueryType queryType;
    private List<Condition> conditions = new ArrayList<Condition>();

    public QueryBuilder setTableName(Object tableName) {
        this.tableName = tableName;
        return this;
    }

    public QueryBuilder addQueryColumns(List<ColumnInfo> queryColumns) {
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



    private String createSelectQuery(){
        StringBuilder str = new StringBuilder();
        str.append("SELECT ");
        boolean isFirst = true;
        for(ColumnInfo c : queryColumns) {
            if(isFirst == true) {
                str.append(c.getDbName());
                isFirst = false;
            }
            else {
                str.append(", " + c.getDbName());
            }
        }

        str.append(" FROM ");
        str.append(tableName);

        if(conditions != null && !conditions.isEmpty()) {
            str.append(" WHERE 1=1");
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
        boolean isFirst = true;
        for (ColumnInfo c : queryColumns) {
            if(isFirst == true) {
                if(c.getValue() instanceof Date) {
                    str.append(c.getDbName() + " =  STR_TO_DATE('" + c.getValue() + "', '%Y-%m-%d')");
                }
                else {
                    str.append(c.getDbName() + " = " + c.getValue());
                }
                isFirst = false;
            }
            else {
                if (c.getValue() instanceof Date) {
                    str.append(", " +c.getDbName() + " =STR_TO_DATE('" + c.getValue() + "', '%Y-%m-%d')");
                } else {
                    str.append(", " + c.getDbName() + " = " + c.getValue());
                }
            }
        }

        if(conditions != null & !conditions.isEmpty()) {
            str.append(" WHERE 1=1 ");
            for(Condition c : conditions) {
                str.append(" and ").append(c.getColumnName()).append("=").append(c.getValue());
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
        boolean isFirst = true;
        for(ColumnInfo c : queryColumns) {
            if(isFirst == true) {
                str.append(c.getDbName());
                isFirst = false;
            }
            else str.append(", " + c.getDbName());
        }

        str.append(") ");
        str.append("VALUES ( ");
        isFirst = true;
        for(ColumnInfo c : queryColumns) {
            if(isFirst == true) {
                    if(c.getValue() instanceof Date) {
                        str.append("str_to_date( '" + c.getValue() + "', '%Y-%m-%d')");
                    }
                    else {
                        str.append(c.getValue());
                    }
                isFirst = false;
            }
            else{
                    if(c.getValue() instanceof Date) {
                        str.append(", str_to_date('" + c.getValue() + "', '%Y-%m-%d')");
                    } else {
                        str.append(", " + c.getValue());
                    }
            }
        }
        str.append(");");
        System.out.println(str);
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
        return "No query - error!";
    }
}
