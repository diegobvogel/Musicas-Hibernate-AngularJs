package br.com.grgit.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

import br.com.grgit.dao.MusicaDAO;
import br.com.grgit.entities.Musica;

@Path("/musicas")
public class ServicoMusicas {
	
	@GET
	@Path("/getMusica/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Musica select(@PathParam("codigo") Integer codigo) {
		return MusicaDAO.select(codigo);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Musica> list() {
		return MusicaDAO.listAll();
	}
	
	@POST
	@Path("/gravar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String gravar(JSONObject params){
		ObjectMapper mapper = new ObjectMapper();
		String retorno = "true";
		try {
			Musica attr = new Musica();
			attr = mapper.readValue(params.getString("musica"), Musica.class);
			return MusicaDAO.gravar(attr);
		} catch (Exception e) {
			retorno = "false";
			e.printStackTrace();
		}
		return retorno;
	}
	
	@POST
	@Path("/editar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editar(JSONObject params){
		ObjectMapper mapper = new ObjectMapper();
		String retorno = "true";
		try {
			Musica attr = new Musica();
			attr = mapper.readValue(params.getString("musica"), Musica.class);
			return MusicaDAO.editar(attr);
		} catch (Exception e) {
			retorno = "false";
			e.printStackTrace();
		}
		return retorno;
	}
	
	@POST
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public String select(JSONObject params) {
		ObjectMapper mapper = new ObjectMapper();
		Integer codigo;
		try {
			codigo = mapper.readValue(params.getString("codigo"), Integer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return String.valueOf(MusicaDAO.delete(codigo));
	}
}
