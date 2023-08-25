package com.matheus.helpdesk;

import com.matheus.helpdesk.domain.enums.Perfil;
import com.matheus.helpdesk.domain.enums.Prioridade;
import com.matheus.helpdesk.domain.enums.Status;
import com.matheus.helpdesk.repositories.ChamadoRepository;
import com.matheus.helpdesk.repositories.ClienteRepository;
import com.matheus.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {

		SpringApplication.run(HelpdeskApplication.class, args);
		System.out.print("Hello World!");
	}

	@Override
	public void run(String... args) throws Exception {

		Tecnico t1 = new Tecnico(null, "Valdir Cezar", "59845861008", "valdir@email.com", "12345678");
		t1.addPerfis(Perfil.ADMIN);

		Cliente c1 = new Cliente(null, "Linus Torvalds", "51977459080", "torvalds@email.com", "12345");

		Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "primeiro chamado", t1, c1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(chamado1));
	}
}
