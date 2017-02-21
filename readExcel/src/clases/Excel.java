/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author vladimir
 */
public class Excel {

    private List<String[]> listaInfo;
    private List<String[]> listaInfoRe;

    public List<String[]> getListaInfoRe() {
        return listaInfoRe;
    }
    //private List<String> listaNombreColumna;
    //private boolean columnasCompletas;
    private int cantidadC;

    public int getCantidadC() {
        return cantidadC;
    }

    public List<String[]> getListaInfo() {
        return listaInfo;
    }

    /*public List<String> getListaNombreColumna() {
     return listaNombreColumna;
     }*/
    public Excel() {
        this.listaInfo = new ArrayList<>();
        this.listaInfoRe = new ArrayList<>();
        cantidadC = 0;
        //listaNombreColumna = new ArrayList<>();
        //columnasCompletas = false;
    }

    public void readExcel(String dir) throws IOException {

        FileInputStream inputStream = null;
        inputStream = new FileInputStream(new File(dir));
        Workbook workbook = null;
        if (dir.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            workbook = new HSSFWorkbook(inputStream);
        }

        List<String> fila = new ArrayList<>();

        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        int i = 1;
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                fila.add((String) getValueCell(nextCell));
            }

            fila.add(0, Integer.toString(i));
            cantidadC = Math.max(cantidadC, fila.size());
            String[] miarray = new String[fila.size()];
            miarray = fila.toArray(miarray);
            listaInfo.add(miarray);
            fila = new ArrayList<>();
            i++;
        }

        inputStream.close();
        
        //Collections.copy(listaInfo, listaInfoRe);

    }

    public void recortarEcxel(int iF, int fF, int iC, int fC) {
        
        listaInfoRe = listaInfo.subList(0, fF);
        listaInfoRe = listaInfoRe.subList(iF - 1, listaInfoRe.size());        
   
        for (int i = 0; i < listaInfoRe.size(); i++) {
            String[] get = listaInfoRe.get(i);
            if (((fC - 1) >= get.length) && (iC - 1) <= get.length) {
                String[] nuevo = new String[fC - iC];
                System.arraycopy(get, iC, nuevo, 0, fC - iC);
                listaInfoRe.set(i, get);
            }
        }
    }

    private Object getValueCell(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
                    return dt1.format(cell.getDateCellValue());
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case Cell.CELL_TYPE_BLANK:
                return " ";
        }
        return null;
    }
}
