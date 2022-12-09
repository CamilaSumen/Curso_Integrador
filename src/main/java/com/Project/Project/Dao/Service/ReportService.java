package com.Project.Project.Dao.Service;

import com.Project.Project.Dao.IReservaDao;
import com.Project.Project.Modelo.Reserva;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private IReservaDao reservaDao;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        List<Reserva> reservas=reservaDao.findAll();
        String path="C:\\\\Users\\\\camil\\\\Desktop\\\\Report\"+\"\\\\reservas.html";

        //Cargar el archivo y compilar
        File file = ResourceUtils.getFile("classpath:Reserva.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reservas);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "javaTechie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\reservas.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\reservas.pdf");
        }

        return "reporte generado en la ruta: " + path;
    }
}
