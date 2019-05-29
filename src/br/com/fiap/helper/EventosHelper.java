package br.com.fiap.helper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.List;

import br.com.fiap.entity.Eventos;
import br.com.fiap.entity.Participantes;

public class EventosHelper {

	private EntityManager em;

	public EventosHelper(EntityManager em) {
		this.em = em;
	}

	public String salvar(Eventos evento) {
		try {
			em.getTransaction().begin();
			em.persist(evento);
			em.getTransaction().commit();
			return "Evento Cadastrado com Sucesso !!";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}

	public String adicionarParticipante(int idEvento, Participantes participante) {
		try {
			em.getTransaction().begin();
			Eventos evento = em.find(Eventos.class, idEvento);
			participante.setEventos(evento);
			em.persist(participante);
			em.getTransaction().commit();
			return "Participante Cadastrado com Sucesso";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getMessage();
		}
	}

	public List<Eventos> ListarEventos() {
		TypedQuery<Eventos> query = em.createQuery("select * from Eventos e", Eventos.class);
		return query.getResultList();
	}

	public List<Participantes> ListarParticipantes(int idEvento) {
		TypedQuery<Participantes> query = em.createQuery("select * from Participantes p where p.evento.id :idEvento",
				Participantes.class);
		query.setParameter("idEvento", idEvento);
		return query.getResultList();
	}

}
