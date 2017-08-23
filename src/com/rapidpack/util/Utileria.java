package com.rapidpack.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rapidpack.entity.Publicacion;

/**
 * Utility class, where we will create methods for training read and write excel
 * files, with <a href="https://poi.apache.org/">Apache POI</a>, we use
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java
 * API To Access Microsoft</a> HSSF is the POI Project's pure Java
 * implementation of the Excel '97(-2007) file.
 * 
 * Clase de utilidades, donde crearemos métodos para el aprendizaje de la
 * lectura y escritura de ficheros excel con
 * <a href="https://poi.apache.org/">Apache POI</a>, usaremos
 * <a href="https://poi.apache.org/spreadsheet/">POI-HSSF and POI-XSSF - Java
 * API To Access Microsoft</a> HSSF es el proyecto POI de implementación total
 * en Java para ficheros Excel '97(-2007).
 *
 * @author Xules You can follow me on my website http://www.codigoxules.org/en
 *         Puedes seguirme en mi <span id="IL_AD11" class="IL_AD">web</span>
 *         http://www.codigoxules.org).
 */
public class Utileria {
	/**
	 * Explanation of the method by which we read the excel file we pass as
	 * parameter if exists, in this example we print the content in the console.
	 * Explicación del método con el que leemos el fichero excel que pasamos como
	 * parámetro si existe, en este ejemplo mostramos el contenido por la consola.
	 * <h3>Example (Ejemplo)</h3>
	 * 
	 * <pre>
	 * JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
	 * javaPoiUtils.readExcelFile(new File("/home/xules/codigoxules/apachepoi/PaisesIdiomasMonedas.xls"));
	 * </pre>
	 *
	 * @param excelFile
	 *            <code>String</code> excel File we are going to read. Fichero excel
	 *            que vamos a leer.
	 */
	public void readExcelFile(File excelFile) {
		InputStream excelStream = null;
		try {
			excelStream = new FileInputStream(excelFile);
			// High level representation of a workbook.
			// Representación del más alto nivel de la hoja excel.
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
			// We chose the sheet is passed as parameter.
			// Elegimos la hoja que se pasa por parámetro.
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
			// An object that allows us to read a row of the excel sheet, and <span
			// id="IL_AD3" class="IL_AD">extract</span> from it the cell contents.
			// Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el
			// contenido de las celdas.
			XSSFRow xssfRow;
			// Initialize the object to read the value of the cell
			// Inicializo el objeto que leerá el <span id="IL_AD8"
			// class="IL_AD">valor</span> de la celda
			XSSFCell cell;
			// I get the number of rows occupied on the sheet
			// Obtengo el número de filas ocupadas en la hoja
			int rows = xssfSheet.getLastRowNum();
			// I get the number of columns occupied on the sheet
			// Obtengo el número de columnas ocupadas en la hoja
			int cols = 0;
			// A string used to store the reading cell
			// Cadena que usamos para almacenar la lectura de la celda
			String cellValue;
			// For this example we'll loop through the rows getting the data we want
			// Para este ejemplo vamos a recorrer las filas obteniendo los <span
			// id="IL_AD12" class="IL_AD">datos</span> que queremos
			for (int r = 0; r < rows; r++) {
				xssfRow = xssfSheet.getRow(r);
				if (xssfRow == null) {
					break;
				} else {
					System.out.print("Row: " + r + " -> ");
					for (int c = 0; c < (cols = xssfRow.getLastCellNum()); c++) {
						/*
						 * We have those cell types (tenemos estos tipos de1 celda): CELL_TYPE_BLANK,
						 * CELL_TYPE_NUMERIC, CELL_TYPE_BLANK, CELL_TYPE_FORMULA, CELL_TYPE_BOOLEAN,
						 * CELL_TYPE_ERROR
						 */
						cellValue = xssfRow.getCell(c) == null ? ""
								: (xssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_STRING)
										? xssfRow.getCell(c).getStringCellValue()
										: (xssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_NUMERIC)
												? "" + xssfRow.getCell(c).getNumericCellValue()
												: (xssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BOOLEAN)
														? "" + xssfRow.getCell(c).getBooleanCellValue()
														: (xssfRow.getCell(c).getCellType() == Cell.CELL_TYPE_BLANK)
																? "BLANK"
																: (xssfRow.getCell(c)
																		.getCellType() == Cell.CELL_TYPE_FORMULA)
																				? "FORMULA"
																				: (xssfRow.getCell(c)
																						.getCellType() == Cell.CELL_TYPE_ERROR)
																								? "ERROR"
																								: "";
						System.out.print("[Column " + c + ": " + cellValue + "] ");
					}
					System.out.println();
				}
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("The file not exists (No se encontró el fichero): " + fileNotFoundException);
		} catch (IOException ex) {
			System.out.println("Error in file procesing (Error al procesar el fichero): " + ex);
		} finally {
			try {
				excelStream.close();
			} catch (IOException ex) {
				System.out.println("Error al procesar el fichero después de cerrarlo: " + ex);
			}
		}
	}

	public ArrayList<String[]> readExcelFileToArray(File excelFile) {
		ArrayList<String[]> arrayDatos = new ArrayList<>();
		InputStream excelStream = null;
		try {
			excelStream = new FileInputStream(excelFile);
			// Representación del más alto nivel de la hoja excel.
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
			// Elegimos la hoja que se pasa por parámetro.
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);

			// Objeto que nos permite leer un fila de la hoja excel, y de aquí extraer el
			// contenido de las celdas.
			XSSFRow xssfRow = xssfSheet.getRow(xssfSheet.getTopRow());
			String[] datos = new String[xssfRow.getLastCellNum()];

			for (Row row : xssfSheet) {
				
				for (Cell cell : row) {
					datos[cell.getColumnIndex()] = (cell.getCellType() == Cell.CELL_TYPE_STRING)
							? cell.getStringCellValue()
							: (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) ? "" + cell.getNumericCellValue()
									: (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) ? "" + cell.getBooleanCellValue()
											: (cell.getCellType() == Cell.CELL_TYPE_BLANK) ? "BLANK"
													: (cell.getCellType() == Cell.CELL_TYPE_FORMULA) ? "FORMULA"
															: (cell.getCellType() == Cell.CELL_TYPE_ERROR) ? "ERROR"
																	: "";
				}
				arrayDatos.add(datos);
				datos = new String[xssfRow.getLastCellNum()];
			}
		} catch (FileNotFoundException fileNotFoundException) {
			System.out.println("No se encontró el fichero: " + fileNotFoundException);
		} catch (IOException ex) {
			System.out.println("Error al procesar el fichero: " + ex);
		} finally {
			try {
				excelStream.close();
			} catch (IOException ex) {
				System.out.println(
						"Error in file processing after close it (Error al procesar el fichero después de cerrarlo): "
								+ ex);
			}
		}
		return arrayDatos;
	}

	/**
	 * Main method for the tests for the methods of the class <strong>Java read
	 * excel</strong> and <strong>Java create excel</strong> with
	 * <a href="https://poi.apache.org/">Apache POI</a>. <br />
	 * Método main para las pruebas para los método de la clase, pruebas de
	 * <strong>Java leer excel</strong> y <strong>Java crear excel</strong> con
	 * <a href="https://poi.apache.org/">Apache POI</a>.
	 * 
	 * @param args
	 */
	public ArrayList<Publicacion> cargarPublicaciones() {
		Utileria javaPoiUtils = new Utileria();
		// javaPoiUtils.readExcelFile(new
		// File("C:\\Users\\Jairis\\Desktop\\anuncios.xlsx"));
		String path = System.getProperty("user.dir");
		
		// the executable as above mentioned then doing this directly through code
		

		ArrayList<String[]> arrayDatosExcel = javaPoiUtils
				.readExcelFileToArray(new File(path +"utility\\anuncios.xlsx"));
		ArrayList<Publicacion> publicaciones = new ArrayList<>();
		int r = 1;
		for (String[] next : arrayDatosExcel) {
			Publicacion publicacion = new Publicacion();
			for (int c = 0; c < next.length; c++) {
				if (c == 0) {
					// Usuario
					publicacion.setUsuario(next[c]);

				}
				// Password
				else if (c == 1) {
					publicacion.setPassword(next[c]);

				}
				// Titulo
				else if (c == 2) {
					publicacion.setTitulo(next[c]);

				}
				// Descripcion
				else if (c == 3) {
					publicacion.setDescripcion(next[c]);

				}
				// Precio
				else if (c == 4) {
					publicacion.setPrecio(next[c].toString());
				}
				// imagen 1
				else if (c == 5) {
					publicacion.setImagenes(next[c]);
				}
			}
			publicaciones.add(publicacion);
		}
		return publicaciones;

	}
	
	public static void main(String[] args) {
		Utileria javaPoi = new Utileria();
		
		ArrayList<Publicacion> publicaciones = javaPoi.cargarPublicaciones();
		
		int i = 0;
		
		for (Publicacion publicacion : publicaciones) {
			
			System.out.println(publicacion.getPrecio());
			System.out.println(publicacion.getPassword());
			
			
		}
		
		
	}
}