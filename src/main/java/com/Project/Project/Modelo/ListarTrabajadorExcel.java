package com.Project.Project.Modelo;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("trabajadoresexcel")
public class ListarTrabajadorExcel extends AbstractXlsView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"listado-trabajadores.xlsx\"");
        Sheet hoja = workbook.createSheet("Trabajadores");

        Row filaTitulo = hoja.createRow(0);
        Cell celda = filaTitulo.createCell(0);

        celda.setCellValue("Listado General de Trabajadores");

        Row filaData = hoja.createRow(2);
        String[] columnas = {"ID", "DNI", "Nombre", "Telefono", "Correo", "Sede"};

        for (int i = 0; i < columnas.length; i++){
            celda = filaData.createCell(i);
            celda.setCellValue(columnas[i]);
        }

        List<Trabajador> listaC = (List<Trabajador>) model.get("trabajadores");

        int numFila=3;
        for (Trabajador trabajador : listaC){
            filaData = hoja.createRow(numFila);

            filaData.createCell(0).setCellValue(trabajador.getId());
            filaData.createCell(1).setCellValue(trabajador.getDni());
            filaData.createCell(2).setCellValue(trabajador.getNombre());
            filaData.createCell(3).setCellValue(trabajador.getTelefono());
            filaData.createCell(4).setCellValue(trabajador.getCorreo());
            filaData.createCell(5).setCellValue(trabajador.getSede().getNombre());

            numFila ++;
        }

    }

}
