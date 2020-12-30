package com.matheusf.mvcmf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.matheusf.mvcmf.model.Convidado;
import com.matheusf.mvcmf.model.Evento;
import com.matheusf.mvcmf.repository.ConvidadoRepository;
import com.matheusf.mvcmf.repository.EventoRepository;

@Controller
@RequestMapping(value = "/eventos")
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	@GetMapping(value = "/cadastrarEvento")
	public String form() {
		return "evento/formEvento";
	}
	
	@PostMapping(value = "/novo")
	public String form(Evento evento) {		
		eventoRepository.save(evento);		
		
		return "redirect:/eventos";
	}
	
	@GetMapping
	public String listaEventos(Model model) {
		List<Evento> eventos = eventoRepository.findAll();
		model.addAttribute("eventos", eventos);
		return "index";
	}
	
	@GetMapping(value = "/detalhes/{id}")
	public ModelAndView detalhes(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView("evento/detalheEvento");
		Evento evento = eventoRepository.findById(id).get();
		mv.addObject("evento", evento);
		
		List<Convidado> convidados = convidadoRepository.findByEvento(evento);
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@PostMapping(value = "/detalhes/{id}")
	public String addConvidados(@PathVariable Long id, Convidado convidado) {
		Evento evento = eventoRepository.findById(id).get();
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		return "redirect:/eventos/detalhes/{id}";		
	}
}
