package com.sng.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;

/**
 * Contiene la informacion sobre los endpoints disponibles en el controlador.
 * @author juanmiguel
 *
 */
public class ApiInfo {

	private final List<Link> links;

	public ApiInfo() {
		this.links = new ArrayList<Link>();
	}

	public void addLink(Link ln) {
		this.links.add(ln);
	}
	
	public List<Link> getLinks() {
		return links;
	}
	
}
