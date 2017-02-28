/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cenpis.gps.inv.read;

import cu.cenpis.gps.inv.data.entity.ActivoFijo;
import cu.cenpis.gps.inv.data.entity.Estado;
import cu.cenpis.gps.inv.data.entity.Local;
import cu.cenpis.gps.inv.data.entity.Metadata;
import cu.cenpis.gps.inv.data.entity.Responsable;
import cu.cenpis.gps.inv.data.entity.Revision;
import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.service.EstadoService;
import cu.cenpis.gps.inv.data.service.LocalService;
import cu.cenpis.gps.inv.data.service.MetadataService;
import cu.cenpis.gps.inv.data.service.ResponsableService;
import cu.cenpis.gps.inv.data.service.RevisionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ControllerExcel {

    private List<String[]> listaInfo;
    private List<String[]> listaInfoRe;

    private int totalActivos;
    private float valorTotal;
    private float valorTotalMC;
    private float depTotalAcu;
    private float depTotalAcuMC;
    private Date fecha;

    private String elaborado;
    private String revisado;
    private String responsable;

    public ControllerExcel() {
        this.listaInfo = new ArrayList<>();
        this.listaInfoRe = new ArrayList<>();
        cantidadC = 0;
        totalActivos = 0;
        valorTotal = 0;
        valorTotalMC = 0;
        depTotalAcu = 0;
        depTotalAcuMC = 0;
        elaborado = "";
        revisado = "";
        responsable = "";
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public float getValorTotalMC() {
        return valorTotalMC;
    }

    public float getDepTotalAcu() {
        return depTotalAcu;
    }

    public float getDepTotalAcuMC() {
        return depTotalAcuMC;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getElaborado() {
        return elaborado;
    }

    public String getRevisado() {
        return revisado;
    }

    public String getResponsable() {
        return responsable;
    }

    public List<String[]> getListaInfoRe() {
        return listaInfoRe;
    }

    private int cantidadC;

    public int getCantidadC() {
        return cantidadC;
    }

    public List<String[]> getListaInfo() {
        return listaInfo;
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
    }

    public void recortarEcxel(int iF, int fF, int iC, int fC) {

        listaInfoRe = new ArrayList<>(listaInfo);
        listaInfoRe = listaInfoRe.subList(0, fF);
        listaInfoRe = listaInfoRe.subList(iF - 1, listaInfoRe.size());

        for (int i = 0; i < listaInfoRe.size(); i++) {
            String[] get = listaInfoRe.get(i);
            String[] nuevo = null;
            if ((fC + 1) > get.length && (iC) <= get.length) {
                nuevo = new String[get.length - iC + 1];
                System.arraycopy(get, iC, nuevo, 1, get.length - iC);
                nuevo[0] = get[0];
                listaInfoRe.set(i, nuevo);
            } else {
                if (((fC + 1) <= get.length) && (iC) <= get.length) {
                    nuevo = new String[fC - iC + 2];
                    System.arraycopy(get, iC, nuevo, 1, fC - iC + 1);
                    nuevo[0] = get[0];
                    listaInfoRe.set(i, nuevo);
                } else {
                    nuevo = new String[1];
                    nuevo[0] = get[0];
                    listaInfoRe.set(i, nuevo);
                }
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

    public void readData() {

        if (listaInfoRe.isEmpty()) {
            listaInfoRe = new ArrayList<>(listaInfo);
        }

        String str = listaInfoRe.get(listaInfoRe.size() - 1)[1];
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha = formatter.parse(str);
        } catch (ParseException ex) {
            Logger.getLogger(ControllerExcel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (String[] listaInfoRe1 : listaInfoRe) {
            for (int i = 0; i < listaInfoRe1.length; i++) {
                if (i > 10) {
                    if (listaInfoRe1[i].contains("Total de Activos")) {
                        totalActivos = (int) Float.parseFloat(listaInfoRe1[i + 1]);
                    } else {
                        if (listaInfoRe1[i].contains("Valor Total") && !listaInfoRe1[i].contains("M.C")) {
                            valorTotal = Float.parseFloat(listaInfoRe1[i + 1]);
                        } else {
                            if (listaInfoRe1[i].contains("Valor Total M.C")) {
                                valorTotalMC = Float.parseFloat(listaInfoRe1[i + 1]);
                            } else {
                                if (listaInfoRe1[i].contains("Depreciación Acumulada Total") && !listaInfoRe1[i].contains("M.C")) {
                                    depTotalAcu = Float.parseFloat(listaInfoRe1[i + 1]);
                                } else {
                                    if (listaInfoRe1[i].contains("Depreciación Acumulada Total M.C")) {
                                        depTotalAcuMC = Float.parseFloat(listaInfoRe1[i + 1]);
                                    } else {
                                        if (listaInfoRe1[i].contains("Elaborado") && !listaInfoRe1[i + 1].contains("Responsable")) {
                                            elaborado = listaInfoRe1[i + 1];
                                        } else {
                                            if (listaInfoRe1[i].contains("Responsable") && !listaInfoRe1[i + 1].contains("Revisado")) {
                                                responsable = listaInfoRe1[i + 1];
                                            } else {
                                                if (listaInfoRe1[i].contains("Revisado") && listaInfoRe1.length > i + 1) {
                                                    revisado = listaInfoRe1[i + 1];
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public void ModificarData(int tA, float vT, float vTMC, float dT, float dTMC) {
        totalActivos = tA;
        valorTotal = vT;
        valorTotalMC = vTMC;
        depTotalAcu = dT;
        depTotalAcuMC = dTMC;
    }

    public void crearRevision() {
        if (!listaInfoRe.isEmpty()) {
            RevisionService revisionService = (RevisionService) Context.getBean("revisionServiceImpl");
            
            Revision revision = new Revision();
            revision.setLatest((short) 1);
            revision.setExcel("Todavia");
            revision.setFecha(fecha);
            revisionService.create(revision);

            MetadataService metadataService = (MetadataService) Context.getBean("metadataServiceImpl");
            Metadata metadata = new Metadata();
            metadata.setDepAcuTotal(depTotalAcu);
            metadata.setDepAcuTotalMc(depTotalAcuMC);
            metadata.setElaborado(elaborado);
            metadata.setIdRevision(revision);
            metadata.setResponsable(responsable);
            metadata.setRevisado(revisado);
            metadata.setTotalActivos(totalActivos);
            metadata.setValorTotal(valorTotal);
            metadata.setValorTotalMc(valorTotalMC);
            metadataService.create(metadata);

            LocalService localService = (LocalService) Context.getBean("localServiceImpl");
            Local local = localService.find(0L);

            EstadoService estadoService = (EstadoService) Context.getBean("estadoServiceImpl");
            Estado estado = estadoService.find(0L);

            ResponsableService responsableService = (ResponsableService) Context.getBean("responsableServiceImpl");
            Responsable responsable = responsableService.find(0L);

            ActivoFijoService activoFijoService = (ActivoFijoService) Context.getBean("activoFijoServiceImpl");

            for (int i = 0; i < listaInfoRe.size(); i += 2) {
                ActivoFijo activoFijo = new ActivoFijo();
                activoFijo.setIdRevision(revision);
                activoFijo.setIdEstado(estado);
                activoFijo.setIdLocal(local);
                activoFijo.setIdResponsable(responsable);

                activoFijo.setRotulo(i/*Long.parseLong(listaInfoRe.get(i)[1])*/);
                activoFijo.setDescripcion(listaInfoRe.get(i)[2]);
                activoFijo.setValorMn(Float.parseFloat(listaInfoRe.get(i)[3]));
                activoFijo.setTasa(Float.parseFloat(listaInfoRe.get(i)[4]));
                activoFijo.setDepAcuMn(Float.parseFloat(listaInfoRe.get(i)[5]));
                activoFijo.setValorActualMn(Float.parseFloat(listaInfoRe.get(i)[6]));
                activoFijo.setResponsableText(listaInfoRe.get(i)[7]);
                activoFijo.setEstadoText(listaInfoRe.get(i)[8]);

                activoFijo.setValorCuc(Float.parseFloat(listaInfoRe.get(i + 1)[2]));
                activoFijo.setDepAcuCuc(Float.parseFloat(listaInfoRe.get(i + 1)[3]));
                activoFijo.setValorActualCuc(Float.parseFloat(listaInfoRe.get(i + 1)[4]));
                
                activoFijoService.create(activoFijo);
            }

        }
    }

}
