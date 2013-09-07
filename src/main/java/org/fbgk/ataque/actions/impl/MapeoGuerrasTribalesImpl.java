package org.fbgk.ataque.actions.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fbgk.ataque.actions.base.MapeoGuerrasTribalesBase;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class MapeoGuerrasTribalesImpl.
 */
public class MapeoGuerrasTribalesImpl extends MapeoGuerrasTribalesBase {

	static Logger	logger	= LoggerFactory.getLogger(MapeoGuerrasTribalesImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.MapeoGuerrasTribales#mapeoTropasDisponibles(java
	 * .lang.String)
	 */
	public Map<String, String> mapeoTropasDisponibles(final String cadena) {
		logger.debug("Se ha invocado al Mapeo de Tropas Disponibles");
		final Map<String, String> mapeo = new HashMap<String, String>();
		final Document doc = Jsoup.parse(cadena);
		final Elements elements = doc.select("table#units_home");
		if (elements != null) {
			final Document tabla = Jsoup.parse(elements.outerHtml());
			final Elements elementsUnits = tabla.select("img");
			final List<String> lista = new ArrayList<String>();
			for (final Element element : elementsUnits) {
				final String url = element.attr("src");
				final Matcher matcher = Pattern.compile("/unit/(.+).png").matcher(url);
				if (matcher.find()) {
					lista.add(matcher.group().replace("/unit/", "").replace(".png", ""));
				}
			}
			final Elements trElementos = tabla.select("tr");
			final Elements elementsTD = Jsoup.parse("<table>" + trElementos.get(1).outerHtml() + "</table>").select("td");
			for (int x = 1; x < elementsTD.size(); x++) {
				logger.debug("Se ha encontrado: {} - {}", lista.get(x - 1), elementsTD.get(x).text());
				mapeo.put(lista.get(x - 1), elementsTD.get(x).text());
			}
		}
		return mapeo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.MapeoGuerrasTribalesServicio#mapeoTropasListAtaque
	 * (org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO)
	 */
	public Map<String, String> mapeoTropasListAtaque(final ListaAtaquesDTO ataquesDTO) {
		logger.debug("Se ha invocado al mapeo de tropas de ataque");
		final Map<String, String> mapeoTropas = new HashMap<String, String>();
		mapeoTropas.put("spear", ataquesDTO.getLancero().toString());
		mapeoTropas.put("sword", ataquesDTO.getEspadas().toString());
		mapeoTropas.put("axe", ataquesDTO.getHachas().toString());
		mapeoTropas.put("spy", ataquesDTO.getEspias().toString());
		mapeoTropas.put("light", ataquesDTO.getLigeros().toString());
		mapeoTropas.put("heavy", ataquesDTO.getPesados().toString());
		mapeoTropas.put("ram", ataquesDTO.getAriete().toString());
		mapeoTropas.put("catapult", ataquesDTO.getCatapulta().toString());
		// mapeoTropas.put("knight", value);
		mapeoTropas.put("snob", ataquesDTO.getNoble().toString());
		logger.debug("Se ataca con los siguientes datos: {}", mapeoTropas);
		return mapeoTropas;
	}
}
