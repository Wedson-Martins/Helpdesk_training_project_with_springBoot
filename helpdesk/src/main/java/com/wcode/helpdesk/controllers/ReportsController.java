package com.wcode.helpdesk.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wcode.helpdesk.models.Ticket;
import com.wcode.helpdesk.services.TicketService;
import com.wcode.helpdesk.util.TicketReportPdf;

@Controller
@RequestMapping("/reports")
public class ReportsController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/tickets")
	public String ticketReport(@RequestParam(value = "day", required = false) Integer day, Model model) {
		System.out.println("Day: " + day);
		model.addAttribute("list", this.ticketService.reportTicketByDays(day));
		return "reports/tickets";
	}

	@GetMapping("tickets/pdfgen")
	public String ticketReportGen(Model model) {
		return "reports/tickets_pdf";
	}

	@GetMapping(value = "/tickets/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> pdfTicketReport(
			@RequestParam(value = "day", required = false) Integer day, Model model) {
		System.out.println("Day: " + day);
		List<Ticket> tickets = this.ticketService.reportTicketByDays(day);
		ByteArrayInputStream pdf = TicketReportPdf.generate(tickets);
		return ResponseEntity.ok().header("Content-Disposition", "Inline; filname=report.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));

	}

}
