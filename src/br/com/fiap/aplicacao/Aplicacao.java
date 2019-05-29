package br.com.fiap.aplicacao;

import javax.persistence.EntityManagerFactory;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import br.com.fiap.entity.Eventos;
import br.com.fiap.entity.Participantes;
import br.com.fiap.helper.EventosHelper;

public class Aplicacao {

	public static void main(String[] args) {
		System.out.println("Foi");
		 //incluirEvento();
		 //listarEventos();
		 //listarParticipantes(1);
		 } 

	private static void incluirEvento() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();

		EventosHelper helper = new EventosHelper(em);

		Eventos evento = new Eventos();

		evento.setDescricao("Novo curso disponivel");
		evento.setResponsavel("Juvenal Santos");
		evento.setDate(new Date());

		Participantes p1 = new Participantes();
		p1.setNome("Jose Antonio");
		p1.setEmail("jantonio@fap.com.br");
		p1.setEventos(evento);

		Participantes p2 = new Participantes();
		p2.setNome("Camila");
		p2.setEmail("camila@fap.com.br");
		p2.setEventos(evento);

		Participantes p3 = new Participantes();
		p3.setNome("Bonifacio");
		p3.setEmail("bonifacio@fap.com.br");
		p3.setEventos(evento);

		evento.getParticipantes().add(p1);
		evento.getParticipantes().add(p2);
		evento.getParticipantes().add(p3);

		System.out.println(helper.salvar(evento));
	}

	private static void listarEventos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		EventosHelper helper = new EventosHelper(em);
		
		for (Eventos evento : helper.ListarEventos()) {
			 System.out.println("Id: " + evento.getId());
			 System.out.println("Descrição: " + evento.getDescricao());
			 System.out.println("Responsável: " + evento.getResponsavel());
			 System.out.println("-------------------------------------");
			 } 
	}

	private static void listarParticipantes(int idEvento) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();
		EventosHelper helper = new EventosHelper(em);

		for (Participantes participante : helper.ListarParticipantes(idEvento)) {
			System.out.println("Id: " + participante.getId());
			System.out.println("Nome: " + participante.getNome());
			System.out.println("Email: " + participante.getEmail());
			System.out.println("-------------------------------------");
		}

	}
}
