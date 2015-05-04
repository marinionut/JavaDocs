package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ionutz on 29.04.2015.
 */
public class EntityUtils {

    public static final String EMPTY_STRING = "";

    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        Table tableAnnotation = (Table) entity.getAnnotation(Table.class);
        return EMPTY_STRING.equals(tableAnnotation.name()) ? entity.getClass().getSimpleName() : tableAnnotation.name();
    }

    public static ArrayList<ColumnInfo> getColumns(Class entity) {
       /* ArrayList<ColumnInfo> columns = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();

        Annotation a;
        for (Field f : fields) {
            a = f.getAnnotation(Column.class);
            if (a != null) {
                ColumnInfo coloana;
                coloana = new ColumnInfo();
                coloana.setColumnName(f.getName());
                coloana.setColumnType((f.getType()));
                coloana.setDbName(f.getClass().getName());
                columns.add(coloana);
            }
            a = f.getAnnotation(Id.class);
            if(a != null) {
                ColumnInfo coloana;
                coloana = new ColumnInfo();
                coloana.setColumnName(f.getName());
                coloana.setColumnType((f.getType()));
                coloana.setDbName(f.getClass().getName());
                columns.add(coloana);
            }
        }
        return columns;
        */
        ArrayList<ColumnInfo> columns = new ArrayList<>();
        Field[] fields = entity.getDeclaredFields();
        for(Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setColumnName(field.getName());
            columnInfo.setColumnType(field.getType());
            if(column != null) {
                columnInfo.setDbName(column.name());
            } else {
                Id id = field.getAnnotation(Id.class);
                columnInfo.setDbName(id.name());
                columnInfo.setId(true);
            }

            columns.add(columnInfo);
        }
        return columns;
    }

    public static ArrayList<Field> getFieldByAnnotations(Class clazz, Class annotations) {
        ArrayList<Field> columns = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields) {
            if (f.getAnnotation(annotations) != null) {
                columns.add(f);
            }
        }
        return columns;

    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if(value instanceof BigDecimal && wantedType == Integer.class) {
            BigDecimal newValue = (BigDecimal) value;
            return newValue;
        }

        else return value;

    }
    public static Object getSqlValue(Object object) {
        if(object instanceof String) {
            return "'" + object + "'";
        } else {
            return object;
        }

    }

}
