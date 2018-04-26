package com.atos.coderank.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.coderank.components.PdfGenerator;
import com.atos.coderank.components.XlsGenerator;
import com.atos.coderank.entities.ProjectMetricsEntity;
import com.atos.coderank.entities.ProjectReportsEntity;
import com.atos.coderank.services.ProjectMetricsService;
import com.atos.coderank.services.ProjectReportsService;
import com.atos.coderank.services.ProjectService;

@RestController
@RequestMapping("api/private/files")
public class FileController {

	private static final String BASE_PATH = "./files/";
	private static final String QUERY_CONSTANTS_METHOD = "method";
	private static final String QUERY_CONSTANTS_FORMAT = "format";
	private static final String QUERY_CONSTANTS_TYPE = "type";
	private static final String QUERY_CONSTANTS_HISTORIC = "historic";
	private static final String QUERY_CONSTANTS_PROJECTID = "projectId";
	private static final String QUERY_CONSTANTS_METRICKEYS = "metricKeys";
	private static final String QUERY_CONSTANTS_METRICID = "metricId";
	
	private static final Log LOG = LogFactory.getLog(FileController.class);
	
	@Autowired
	@Qualifier("projectMetricsService")
	private ProjectMetricsService pms;
	
	@Autowired
	@Qualifier("projectService")
	private ProjectService ps;
	
	@Autowired
	@Qualifier("projectReportsService")
	private ProjectReportsService prs;
	
	/**
	 * According to the recieved params this functions generates a file and returns an URL to download it
	 * Generated files will be composed by projectid_versiondate.format
	 * Possible queryParams:
	 * type = metric,report
	 * method = email,download
	 * format = pdf,xls,zip
	 * historic = 1,0
	 * projectId = str
	 * metricsId = str
	 * metricKeys = csv
	 * @param req
	 * @return
	 */
	public ResponseEntity<String> generateURL(HttpServletRequest req){	
		String format = req.getParameter(QUERY_CONSTANTS_FORMAT);
		String type = req.getParameter(QUERY_CONSTANTS_TYPE);
		String method = req.getParameter(QUERY_CONSTANTS_METRICID);
		String metricsId = req.getParameter(QUERY_CONSTANTS_METHOD);
		String metricKeys = req.getParameter(QUERY_CONSTANTS_METRICKEYS);
		String projectId = req.getParameter(QUERY_CONSTANTS_PROJECTID);
		boolean historic = req.getParameter(QUERY_CONSTANTS_HISTORIC).equalsIgnoreCase("0");
	
		List<ProjectMetricsEntity> pmes = new ArrayList<>();

		if (type.equalsIgnoreCase("metric")) {
			// metrics by id
			ProjectMetricsEntity pme = this.pms.findByMetricsId(Long.parseLong(metricsId));
			if (pme != null)
				pmes.add(pme);
		} else if (type.equalsIgnoreCase("report") && !historic) {
			// Most recent without historic
			pmes.addAll(this.pms.findTop1ByProjectProjectIdOrderByVersionDateDesc(projectId));
		} else if (type.equalsIgnoreCase("report") && historic) {
			// Most recent with historic
			pmes.addAll(this.pms.findTop10ByProjectProjectIdOrderByVersionDateDesc(projectId));
		}
			
		File generatedFile = generateFiles(format, metricKeys, pmes);
		String downloadLink = "";
		HttpStatus status = HttpStatus.OK;
		
		if (generatedFile != null) {
			downloadLink = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/download?filename=" + generatedFile.getName() + "&format=" + format;
			//TODO save the report
			ProjectReportsEntity pre = new ProjectReportsEntity();
			pre.setCreatedDate(new Date());
			pre.setFormat(format);
			pre.setMethod(method);
			pre.setName(generatedFile.getName());
			pre.setMetricsId(pmes.get(0).getMetricsId());
			pre.setProject(this.ps.findById(projectId));
			//TODO pre.setNotification(notification);
			try {
				pre.setDocument(Files.readAllBytes(generatedFile.toPath()));
				this.prs.saveOrUpdate(pre); //Save to generatedReports
			} catch (IOException e) {
				LOG.error("Error when parsing File to byte[] -> " + e.getMessage());				
			}
		}
		else
			status = HttpStatus.CONFLICT;
			
		return new ResponseEntity<>(downloadLink, status);
	}
	
	/**
	 * According to the recieved params this function will return a file
	 * Possible queryParams:
	 * method = email,download
	 * type = metric,report
	 * filename = str
	 * 
	 * @param req
	 * @return
	 */
	public FileSystemResource downloadFile(HttpServletRequest req) {
		
		return null;
	}
	
	/**
	 * Generate a file in the path files/ and returns its filename
	 * @param format
	 * @param metricKeys
	 * @param pme
	 * @param historic
	 * @return
	 */
	private File generateFiles(String format, String metricKeys, List<ProjectMetricsEntity> pmes) {	
		File dir = new File(BASE_PATH);
		
		if(!dir.exists())
			dir.mkdirs();
		
		//TODO metricKeys is a CSV, create the file with ONLY the metricKeys given
		
		String filename = "";
		
		switch (format) {
		case "PDF":
			filename += ".pdf";
			new PdfGenerator(BASE_PATH + filename); //TODO pass pmes
			break;
	    case "XLSX":
		case "XLS":
			filename += ".xlsx";
			new XlsGenerator(BASE_PATH + filename); //TODO pass pmes
			break;
		default:
			LOG.warn("Unsupported file format " + format);
		}
		
		File file = new File(BASE_PATH + filename);
		if (file.exists())
			return file;
		
		return null;
	}
	
	/**
	 * Generate a ZIP file with the list given and returns its filename
	 * @param format
	 * @param metricKeys
	 * @param pme
	 * @param historic
	 * @return
	 */
	private String generateZip(String format, String metricKeys, List<ProjectMetricsEntity> pme, boolean historic) {
		File dir = new File(BASE_PATH);
		if(!dir.exists())
			dir.mkdirs();
		
		return "";
	}
}
