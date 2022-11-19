package com.Project.Project.Modelo;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("trabajadores")
public class ListarTrabajadorPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("uncheked")
        List<Trabajador> listaTrabajadores = (List<Trabajador>) model.get("listaTrabajadores");

        /*Fuente, tamaño y color para cada seccion*/
        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA,20,Color.RED);
        Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA,12,Color.WHITE);
        Font fuenteDataCeldas = FontFactory.getFont(FontFactory.COURIER,10,Color.BLACK);

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20,-20,40,20);
        document.open();
        PdfPCell celda = null;

        /*Tabla para el titulo del pdf*/
        PdfPTable tablaTitulo = new PdfPTable(1);

        celda = new PdfPCell(new Phrase("Lista General de Trabajadores",fuenteTitulo));
        celda.setBorder(0);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);

        tablaTitulo.addCell(celda);
        tablaTitulo.setSpacingAfter(30);

        /*Tabla para mostrar la lista de trabajadores*/
        PdfPTable tablaTrabajadores = new PdfPTable(6);
        tablaTrabajadores.setWidths(new float[] {0.8f, 2f, 2f, 1.5f, 3.5f, 1.5f});

        celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaTrabajadores.addCell(celda);

        celda = new PdfPCell(new Phrase("DNI", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaTrabajadores.addCell(celda);

        celda = new PdfPCell(new Phrase("Nombre y Apellido", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaTrabajadores.addCell(celda);

        celda = new PdfPCell(new Phrase("Teléfono", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaTrabajadores.addCell(celda);

        celda = new PdfPCell(new Phrase("Correo", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaTrabajadores.addCell(celda);

        celda = new PdfPCell(new Phrase("Sede", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.DARK_GRAY);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaTrabajadores.addCell(celda);

        /*Bucle para mostrar los datos de los trabajadores*/
        for (Trabajador trabajador : listaTrabajadores) {
            celda = new PdfPCell(new Phrase(trabajador.getId().toString(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaTrabajadores.addCell(celda);

            celda = new PdfPCell(new Phrase(trabajador.getDni(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaTrabajadores.addCell(celda);

            celda = new PdfPCell(new Phrase(trabajador.getNombre(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaTrabajadores.addCell(celda);

            celda = new PdfPCell(new Phrase(trabajador.getTelefono(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaTrabajadores.addCell(celda);

            celda = new PdfPCell(new Phrase(trabajador.getCorreo(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaTrabajadores.addCell(celda);

            celda = new PdfPCell(new Phrase(trabajador.getSede().getNombre(), fuenteDataCeldas));
            celda.setPadding(5);
            tablaTrabajadores.addCell(celda);
        }

        /*
        listaTrabajadores.forEach(trabajador -> {
            tablaTrabajadores.addCell(trabajador.getId().toString());
            tablaTrabajadores.addCell(trabajador.getNombre());
            tablaTrabajadores.addCell(trabajador.getTelefono());
            tablaTrabajadores.addCell(trabajador.getCorreo());
            tablaTrabajadores.addCell(trabajador.getSede().getNombre());
        });
        */

        /*Mostramos las tablas*/
        document.add(tablaTitulo);
        document.add(tablaTrabajadores);
    }
}
