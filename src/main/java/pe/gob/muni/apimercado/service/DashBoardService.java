package pe.gob.muni.apimercado.service;


import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pe.gob.muni.apimercado.model.dto.DashboardDto;
import pe.gob.muni.apimercado.repository.DashBoardRepository;
import pe.gob.muni.apimercado.utils.ApiException;
import pe.gob.muni.apimercado.utils.Util;
import pe.gob.muni.apimercado.utils.dto.GeneralPageTable;


@Service
public class DashBoardService implements IDashBoardService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private DashBoardRepository repository;

	@Override
	public DashboardDto datosDashBoard(Map<String, String> params) throws ApiException, Exception {
		logger.info("Cargando datos del dashboard");
		try {
			
			GeneralPageTable pagData = Util.mapToObject(params,GeneralPageTable.class);
			DashboardDto datos = new DashboardDto();
			
			datos.setComerciantes(repository.totalComerciantes(pagData));
			datos.setPuestos(repository.totalPuestos(pagData));
			datos.setCobrado(repository.totalCobrado(pagData));
			datos.setPendiente(repository.totalPendiente(pagData));
			datos.setTop_10_deudores(repository.top_10_deudores(pagData));
			datos.setMayor_recaudacion(repository.mayor_recaudacion(pagData));
			
			return datos;

		} catch (ApiException e) {
			logger.error("Error cargando datos del dashboard {} - {}.",e.getMessage(),e );
			throw e;
		}
		catch (Exception e) {
			logger.error("Error cargando datos del dashboard {} - {}.",e.getMessage(),e );
			throw e;
		}
	}

}
