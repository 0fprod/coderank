package com.atos.coderank.components;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.atos.coderank.entities.ProjectMetricsEntity;
import com.qoppa.pdfWriter.PDFDocument;

public class PdfGenerator {

	private static final Log LOG = LogFactory.getLog(PdfGenerator.class);

	
	public PdfGenerator(String filename, List<ProjectMetricsEntity> list) {
		PDFDocument pdfDoc = new PDFDocument();
		try {
			pdfDoc.saveDocument(filename);
		} catch (IOException e) {
			LOG.error("Error writing file " + filename + " -> " + e.getMessage());
		}
	}
}
