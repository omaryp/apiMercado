package pe.gob.muni.apimercado.service;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;

import pe.gob.muni.apimercado.config.ParametrosApiRest;
import pe.gob.muni.apimercado.utils.ApiException;

@Service
public class ReportService implements IReportService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private final TemplateEngine templateEngine = null;
	@Autowired
	private ParametrosApiRest parametros;

	@Override
	public byte[] generarReporte(String template, Map<String, Object> params) throws ApiException, Exception {
		try {
			
			WebContext context = new WebContext(request, response, servletContext);
			params.forEach((k,v)->{
				context.setVariable(k, v);
			});
			
			String orderHtml = templateEngine.process(template, context);

			ByteArrayOutputStream target = new ByteArrayOutputStream();
			ConverterProperties converterProperties = new ConverterProperties();
			converterProperties.setBaseUri(parametros.getResource_url());

			HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

			byte[] pdfBytes = target.toByteArray();
			logger.info("Se generó reporte correctamente {}", template);
			return pdfBytes;
		} catch (Exception e) {
			logger.error("Error al generar reporte con plantilla {} {} {}", template, e.getMessage(),e);
			throw new ApiException("Error al generar el reporte con template"+template,null);
		}
	}

}
