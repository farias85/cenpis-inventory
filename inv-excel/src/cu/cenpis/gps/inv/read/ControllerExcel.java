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
import cu.cenpis.gps.inv.data.entity.TipoActivo;
import cu.cenpis.gps.inv.data.service.ActivoFijoService;
import cu.cenpis.gps.inv.data.service.EstadoService;
import cu.cenpis.gps.inv.data.service.LocalService;
import cu.cenpis.gps.inv.data.service.MetadataService;
import cu.cenpis.gps.inv.data.service.ResponsableService;
import cu.cenpis.gps.inv.data.service.RevisionService;
import cu.cenpis.gps.inv.data.service.TipoActivoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    private Float valorTotal;
    private Float valorTotalMC;
    private Float depTotalAcu;
    private Float depTotalAcuMC;
    private Date fecha;

    private String elaborado;
    private String revisado;
    private String responsableText;

    private RevisionService revisionService;
    private MetadataService metadataService;
    private LocalService localService;
    private EstadoService estadoService;
    private ResponsableService responsableService;
    private TipoActivoService tipoActivoService;
    private ActivoFijoService activoFijoService;

    //private String excelFilePath;
    public ControllerExcel() {
        this.listaInfo = new ArrayList<>();
        this.listaInfoRe = new ArrayList<>();
        cantidadC = 0;
        totalActivos = 0;
        valorTotal = 0f;
        valorTotalMC = 0f;
        depTotalAcu = 0f;
        depTotalAcuMC = 0f;
        elaborado = "";
        revisado = "";
        responsableText = "";
        // excelFilePath = "";
        revisionService = (RevisionService) Context.getBean("revisionServiceImpl");
        metadataService = (MetadataService) Context.getBean("metadataServiceImpl");
        localService = (LocalService) Context.getBean("localServiceImpl");
        estadoService = (EstadoService) Context.getBean("estadoServiceImpl");
        responsableService = (ResponsableService) Context.getBean("responsableServiceImpl");
        tipoActivoService = (TipoActivoService) Context.getBean("tipoActivoServiceImpl");
        activoFijoService = (ActivoFijoService) Context.getBean("activoFijoServiceImpl");
    }

    public int getTotalActivos() {
        return totalActivos;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public Float getValorTotalMC() {
        return valorTotalMC;
    }

    public Float getDepTotalAcu() {
        return depTotalAcu;
    }

    public Float getDepTotalAcuMC() {
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

    public String getResponsableText() {
        return responsableText;
    }

    /* public String getExcelFilePath() {
     return excelFilePath;
     }*/
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

        //excelFilePath = dir;
        
        this.listaInfo = new ArrayList<>();
        this.listaInfoRe = new ArrayList<>();
        
        FileInputStream inputStream = null;
        inputStream = new FileInputStream(new File(dir));
        Workbook workbook = null;

        try {
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
                    fila.add(getValueCell(nextCell).toString().trim());
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato del Excel 5.0/7.0 (BIFF5) no soportado. Cambiar por BIFF8", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void recortarEcxel(int iF, int fF, int iC, int fC) {

        if (iF > 0 && fF > 0 && iC > 0 && fC > 0) {
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

        if (listaInfo.size() > 0) {
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

            for (int j = 20; j < listaInfoRe.size(); j++) {
                String[] listaInfoRe1 = listaInfoRe.get(j);
                for (int i = 0; i < listaInfoRe1.length; i++) {
                    //if (j > 10) {
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
                                            elaborado = listaInfoRe1[i + 1].trim();
                                        } else {
                                            if (listaInfoRe1[i].contains("Responsable") && !listaInfoRe1[i + 1].contains("Revisado")) {
                                                responsableText = listaInfoRe1[i + 1].trim();
                                            } else {
                                                if (listaInfoRe1[i].contains("Revisado") && listaInfoRe1.length > i + 1) {
                                                    revisado = listaInfoRe1[i + 1].trim();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //}

                }
            }
        }

    }

    public void ModificarData(int tA, Float vT, Float vTMC, Float dT, Float dTMC) {
        totalActivos = tA;
        valorTotal = vT;
        valorTotalMC = vTMC;
        depTotalAcu = dT;
        depTotalAcuMC = dTMC;
    }

    public void crearRevision() {
        
        //Eliminar revision 43 
        //revisionService.removeById(50L);
        if (!listaInfoRe.isEmpty()) {

            if (listaInfoRe.size() % 2 == 0) {
                //RevisionService revisionService = (RevisionService) Context.getBean("revisionServiceImpl");

                Revision revision = new Revision();
                revision.setActivo(true);
                List<Revision> revisiones = revisionService.findByExample(revision);
                Long idURev = null;//Última revisión activa

                if (revisiones.size() > 0) {
                    revision = revisionService.findByExample(revision).get(0);
                    idURev = revisiones.get(0).getIdRevision();
                    revision.setActivo(false);
                    revisionService.edit(revision);
                }

                revision = new Revision(true, new Date(), fecha, "Todavia");
                revisionService.create(revision);

                Metadata metadata = new Metadata(totalActivos, valorTotal, valorTotalMC, depTotalAcu, depTotalAcuMC, revision);
                metadata.setElaborado(elaborado);
                metadata.setResponsable(responsableText);
                metadata.setRevisado(revisado);
                metadataService.create(metadata);

                Local SinLocal = localService.find(0L);

                Estado SinEstado = estadoService.find(0L);

                Responsable SinResponsable = responsableService.find(0L);

                TipoActivo SinTipoActivo = tipoActivoService.find(0);

                Date fechaA = null;
                Date fechaEA = null;

                int cantAG = 0;

                try {

                    for (int i = 0; i < listaInfoRe.size(); i += 2) {
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                       // if (listaInfoRe.get(i).length == 11 && listaInfoRe.get(i + 1).length == 5) {
                            fechaA = formatter.parse(listaInfoRe.get(i)[9]);
                            fechaEA = formatter.parse(listaInfoRe.get(i)[10]);

                            Local local;
                            Estado estado;
                            Responsable responsable;
                            TipoActivo tipoActivo;

                            HashMap<String, Object> params = new HashMap<>();
                            params.put("mRotulo", listaInfoRe.get(i)[1]);
                            params.put("idRevision", idURev);
                            List<ActivoFijo> activosFijos = activoFijoService.findNamedQuery("ActivoFijo.findRevision", params);

                            if (activosFijos.size() > 0) {

                                local = activosFijos.get(0).getLocal();
                                estado = activosFijos.get(0).getEstado();
                                responsable = activosFijos.get(0).getResponsable();
                                tipoActivo = activosFijos.get(0).getTipoActivo();

                            } else {
                                local = SinLocal;
                                estado = SinEstado;
                                responsable = SinResponsable;
                                tipoActivo = SinTipoActivo;
                            }

                            ActivoFijo activoFijo = new ActivoFijo(listaInfoRe.get(i)[1], listaInfoRe.get(i)[2], Float.parseFloat(listaInfoRe.get(i)[3]),
                                    Float.parseFloat(listaInfoRe.get(i)[4]), Float.parseFloat(listaInfoRe.get(i + 1)[3]), Float.parseFloat(listaInfoRe.get(i + 1)[3]),
                                    Float.parseFloat(listaInfoRe.get(i)[5]), Float.parseFloat(listaInfoRe.get(i + 1)[4]), Float.parseFloat(listaInfoRe.get(i)[6]),
                                    listaInfoRe.get(i)[7], listaInfoRe.get(i)[8], fechaA, fechaEA, estado, local, responsable, revision, tipoActivo);

                            //activoFijo.setRotulo(i/*Long.parseLong(listaInfoRe.get(i)[1]));
                            // activoFijo.setDescripcion(listaInfoRe.get(i)[2]);
                            //activoFijo.setValorMn(Float.parseFloat(listaInfoRe.get(i)[3]));
                            //activoFijo.setTasa(Float.parseFloat(listaInfoRe.get(i)[4]));
                            //activoFijo.setDepAcuMn(Float.parseFloat(listaInfoRe.get(i)[5]));
                            //activoFijo.setValorActualMn(Float.parseFloat(listaInfoRe.get(i)[6]));
                            //activoFijo.setResponsableText(listaInfoRe.get(i)[7]);
                            //activoFijo.setEstadoText(listaInfoRe.get(i)[8]);
                            //activoFijo.setValorCuc(Float.parseFloat(listaInfoRe.get(i + 1)[2]));
                            //activoFijo.setDepAcuCuc(Float.parseFloat(listaInfoRe.get(i + 1)[3]));
                            //activoFijo.setValorActualCuc(Float.parseFloat(listaInfoRe.get(i + 1)[4]));
                            activoFijoService.create(activoFijo);

                            cantAG++;
                            
                            /*if (cantAG == 394) {
                                int r = 4;
                            }*/

                        //}
                        /*else{
                         JOptionPane.showMessageDialog(null, "Error en el formato del documento." + "\n" +   cantAG + " Activos fijos insertados de" + totalActivos +  "\n"
                         + "Debe eliminar la última revisión y el último metadata ", "Error", JOptionPane.ERROR_MESSAGE);
                         break;
                         }*/
                    }
                } catch (ArrayIndexOutOfBoundsException excepcion) {
                    JOptionPane.showMessageDialog(null, "Error en el formato del documento." + "\n" + "Activo número: " + (cantAG + 1) + "\n"
                            + "Fila: " + listaInfoRe.get(cantAG*2)[0], "Error", JOptionPane.ERROR_MESSAGE);
                  
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el formato de fecha." + "\n" + "Activo número: " + (cantAG + 1) + " --- " + "Rotulo: " + listaInfoRe.get(cantAG*2)[1] + "\n"
                           + "Fila: " + listaInfoRe.get(cantAG*2)[0] + "\n" + 0 + "  Activos fijos insertados de  " + listaInfoRe.size() / 2, "Error", JOptionPane.ERROR_MESSAGE);                   
                }

                if (cantAG != listaInfoRe.size() / 2) {
                    revisionService.removeById(revision.getIdRevision());
                    if (idURev != null) {
                        revision = revisionService.find(idURev);
                        revision.setActivo(true);
                        revisionService.edit(revision);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, cantAG + "  Activos fijos insertados de  " + listaInfoRe.size() / 2);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Los datos no están completos. Verifique el filtro o el documento", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Debe cargar el excel primero!", "Información ", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
