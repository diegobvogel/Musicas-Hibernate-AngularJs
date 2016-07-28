package br.com.testes;

import br.com.grgit.dao.MusicaDAO;
import br.com.grgit.entities.Musica;

public class Testes {

	public static void main(String[] args) {
		Musica m = MusicaDAO.select(1);
		System.out.println(m.getNome());
	}
}
