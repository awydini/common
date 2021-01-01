package net.aydini.common.io.excel;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import net.aydini.common.doamin.annotation.ExcelColumn;
import net.aydini.common.exception.ExcelException;
import net.aydini.common.mapper.Mapper;
import net.aydini.common.reflection.ReflectionUtil;

/**
 * 
 * @author <a href="mailto:hi@aydini.net">Aydin Nasrollahpour </a>
 *
 *         Jan 1, 2021
 */
public class ExcelUtils
{
    
    public static ExcelUtils getInstance()
    {
        return new ExcelUtils();
    }

    private Workbook load(InputStream excelInputStream)
    {
        try
        {
            return new XSSFWorkbook(excelInputStream);
        }
        catch (Exception e)
        {
            throw new ExcelException(e);
        }
    }

    public <T> List<T> readFirstSheet(InputStream excelinInputStream, Class<T> objectClass)
    {
        return readSheet(excelinInputStream, objectClass, 0);
    }

    public <T> List<T> readSheet(InputStream excelinInputStream, Class<T> objectClass , Integer sheetNumber)
    {
        Workbook workbook = load(excelinInputStream);
        Sheet datatypeSheet = workbook.getSheetAt(sheetNumber);
        Iterator<Row> rowItearator = datatypeSheet.iterator();
        List<T> mappedRows = new ArrayList<>();
        while (rowItearator.hasNext())
        {

            Row currentRow = rowItearator.next();
            readRow(currentRow, objectClass).ifPresent(mappedRows::add);
        }
        
        return mappedRows;
    }

    private <T> Optional<T> readRow(Row row, Class<T> objectClass)
    {

        try
        {
            T instance = ReflectionUtil.instantiate(objectClass);
            Set<Field> excelColumns = ReflectionUtil.getAnnotatedClassFields(objectClass, ExcelColumn.class);

            for (Field excelColumn : excelColumns)
            {
                ExcelColumn annotation = excelColumn.getAnnotation(ExcelColumn.class);
                Object object = getCellValue(row.getCell(annotation.index()), annotation.mapperClass());
                ReflectionUtil.setFieldValueToObject(excelColumn, instance, object);

            }
            return Optional.of(instance);
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
        return Optional.empty();
    }

    @SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
    private Object getCellValue(Cell cell, Class<? extends Mapper> mapperClass)
    {
        Object object = null;

        if (cell.getCellTypeEnum() == CellType._NONE || cell.getCellTypeEnum() == CellType.BLANK
                || cell.getCellTypeEnum() == CellType.STRING)
            object = cell.getStringCellValue();
        else if (cell.getCellTypeEnum() == CellType.NUMERIC) object = new BigDecimal(cell.getNumericCellValue());
        else if (cell.getCellTypeEnum() == CellType.BOOLEAN) object = cell.getBooleanCellValue();

        if (!ReflectionUtil.isInterface(mapperClass))
        {
            Mapper mapper = ReflectionUtil.instantiate(mapperClass);
            object = mapper.map(object);
        }

        return object;
    }

}
