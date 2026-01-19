package utilities;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

	import org.apache.poi.ss.usermodel.CellStyle;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.FillPatternType;
	import org.apache.poi.ss.usermodel.IndexedColors;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelUtility {

	    public static FileInputStream fi;
	    public static FileOutputStream fo;
	    public static XSSFWorkbook wb;
	    public static XSSFSheet ws;
	    public static XSSFRow row;
	    public static XSSFCell cell;
	    public static CellStyle style;
	    String path;
	    public ExcelUtility(String path)
	    {
	     this.path=path;	
	    }

	    // ================= ROW COUNT =================
	    public  int getRowCount(String SheetName) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        ws = wb.getSheet(SheetName);
	        int rowCount = ws.getLastRowNum();
	        wb.close();
	        fi.close();
	        return rowCount;
	    }

	    // ================= CELL COUNT =================
	    public  int getCellCount(String SheetName, int rowNum) throws IOException {
	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        ws = wb.getSheet(SheetName);
	        row = ws.getRow(rowNum);
	        int cellCount = row.getLastCellNum();
	        wb.close();
	        fi.close();
	        return cellCount;
	    }

	    // ================= READ CELL DATA =================
	    public String getCellData(String SheetName,  int rowNum, int colNum)
	            throws IOException {

	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        ws = wb.getSheet(SheetName);
	        row = ws.getRow(rowNum);
	        cell = row.getCell(colNum);
	        
       //================== Data Formatter ==================
	        DataFormatter formatter = new DataFormatter();
	        String data;

	        try {
	            data = formatter.formatCellValue(cell);
	        } catch (Exception e) {
	            data = "";
	        }

	        wb.close();
	        fi.close();
	        return data;
	    }

	    // ================= WRITE CELL DATA =================
	    public void setCellData( String SheetName, int rowNum, int colNum, String data)
	            throws IOException {

	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        ws = wb.getSheet(SheetName);

	        row = ws.getRow(rowNum);
	        if (row == null)
	            row = ws.createRow(rowNum);

	        cell = row.createCell(colNum);
	        cell.setCellValue(data);

	        fo = new FileOutputStream(path);
	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // ================= FILL GREEN COLOR =================
	    public void fillGreenColor( String SheetName, int rowNum, int colNum)
	            throws IOException {

	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        ws = wb.getSheet(SheetName);

	        row = ws.getRow(rowNum);
	        cell = row.getCell(colNum);

	        style = wb.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	        cell.setCellStyle(style);

	        fo = new FileOutputStream(path);
	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }

	    // ================= FILL RED COLOR =================
	    public void fillRedColor( String SheetName, int rowNum, int colNum)
	            throws IOException {

	        fi = new FileInputStream(path);
	        wb = new XSSFWorkbook(fi);
	        ws = wb.getSheet(SheetName);

	        row = ws.getRow(rowNum);
	        cell = row.getCell(colNum);

	        style = wb.createCellStyle();
	        style.setFillForegroundColor(IndexedColors.RED.getIndex());
	        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	        cell.setCellStyle(style);

	        fo = new FileOutputStream(path);
	        wb.write(fo);

	        wb.close();
	        fi.close();
	        fo.close();
	    }
	}


