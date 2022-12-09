package com.Project.Project;

import com.Project.Project.Dao.IReservaDao;
import com.Project.Project.Dao.Service.ReportService;
import com.Project.Project.Modelo.Reserva;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
@RestController
public class ProjectApplication {

	@Autowired
	private IReservaDao reservaDao;

	@Autowired
	private ReportService reportService;

	@GetMapping("/getReserva")
	public List<Reserva> getReserva(){

		return reservaDao.findAll();
	}

	@GetMapping("report/{format}")
	public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {

		return reportService.exportReport(format);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
