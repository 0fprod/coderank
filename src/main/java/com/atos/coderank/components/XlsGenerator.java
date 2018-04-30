package com.atos.coderank.components;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atos.coderank.entities.ProjectMetricsEntity;

public class XlsGenerator {

	private static final Log LOG = LogFactory.getLog(XlsGenerator.class);
	
	public XlsGenerator(String filename, List<ProjectMetricsEntity> list) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		try {
			workbook.write(new FileOutputStream(filename));
		} catch (FileNotFoundException e) {
			LOG.error("File not found -> " + e.getMessage());
		} catch (IOException e) {
			LOG.error("IO Exception -> "+ e.getMessage());		
		}
	}
}
