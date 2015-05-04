package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.lang.Integer;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ionutz on 29.04.2015.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() {
        Department department = new Department();
        String tableName = EntityUtils.getTableName(Department.class);

        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetSize(){
        int n = EntityUtils.getColumns(Department.class).size();
        assertEquals((Object)3, (Object)n);
    }

    @Test
    public void testGetFieldsByAnnotation() {
        ArrayList<Field> myFields = EntityUtils.getFieldByAnnotations(Department.class, Column.class);
        ArrayList<Field> fields = new ArrayList<>();

        try{
            fields.add(Department.class.getDeclaredField("departmentName"));
            fields.add(Department.class.getDeclaredField("location"));
        }  catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("Incorrect number of retrieved fields!", fields.size(), myFields.size());
    }

    @Test
    public void testCastFromSqlType() {
        BigDecimal bigDecimal = new BigDecimal(100);

        Assert.assertEquals("Returned type should be Integer!", EntityUtils.castFromSqlType(bigDecimal, Integer.class).getClass(), Integer.class);
    }

    @Test
    public void testGetColumns(){
        ArrayList<ColumnInfo> columns = new ArrayList<ColumnInfo>();
        ArrayList<ColumnInfo> myColumns = EntityUtils.getColumns(Department.class);

        ColumnInfo c;
        c = new ColumnInfo();
        c.setColumnName("id");
        c.setColumnType(Long.class);
        c.setDbName("departament_id");
        c.setIsId(true);
        columns.add(c);

        c = new ColumnInfo();
        c.setColumnName("departmentName");
        c.setColumnType(Long.class);
        c.setDbName("department_name");
        c.setIsId(false);
        columns.add(c);

        c = new ColumnInfo();
        c.setColumnName("location");
        c.setColumnType(Long.class);
        c.setDbName("location_id");
        c.setIsId(false);
        columns.add(c);

        Assert.assertEquals("Incorrect number of retrived columns!", columns.size(), myColumns.size());
    }
}
