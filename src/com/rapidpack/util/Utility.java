package com.rapidpack.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rapidpack.entity.Publicacion;

public class Utility {
	
	
	public ArrayList<String[]> leerPublicaciones(File excelFile){    
        ArrayList<String[]> arrayDatos = new ArrayList<>();
        InputStream excelStream = null;
        try {
            excelStream = new FileInputStream(excelFile);
            // Representaci�n del m�s alto nivel de la hoja excel.
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
            // Elegimos la hoja que se pasa por par�metro.
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);    
            // Objeto que nos permite leer un fila de la hoja excel, y de aqu� extraer el contenido de las celdas.
            XSSFRow xssfRow = xssfSheet.getRow(xssfSheet.getTopRow());
            String [] datos = new String[xssfRow.getLastCellNum()];            
                      
            for (Row row: xssfSheet) {                    
                for (Cell cell : row) {
                    /* 
                        We have those cell types (tenemos estos tipos de celda): 
                            CELL_TYPE_BLANK, CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN, CELL_TYPE_ERROR
                    */
                    datos[cell.getColumnIndex()] =  
                            (cell.getCellType() == Cell.CELL_TYPE_STRING)?cell.getStringCellValue():
                            (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)?"" + cell.getNumericCellValue():
                            (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN)?"" + cell.getBooleanCellValue():
                            (cell.getCellType() == Cell.CELL_TYPE_BLANK)?"BLANK":
                            (cell.getCellType() == Cell.CELL_TYPE_FORMULA)?"FORMULA":
                            (cell.getCellType() == Cell.CELL_TYPE_ERROR)?"ERROR":"";                                                                   
                }
                arrayDatos.add(datos); 
                datos = new String[xssfRow.getLastCellNum()];  
            }            
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No se encontr� el fichero: " + fileNotFoundException);
        } catch (IOException ex) {
            System.out.println("Error al procesar el fichero: " + ex);
        } finally {
            try {
                excelStream.close();
            } catch (IOException ex) {
                System.out.println("Error in file processing after close it (Error al procesar el fichero despu�s de cerrarlo): " + ex);
            }
        }
        return arrayDatos;
    }
	
	

}
