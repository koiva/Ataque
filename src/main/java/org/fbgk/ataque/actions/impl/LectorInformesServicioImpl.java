package org.fbgk.ataque.actions.impl;

import java.util.List;

import org.fbgk.ataque.actions.base.LectorInformesServicioBase;
import org.fbgk.ataque.actions.constantes.GuerrasTribalesActions;
import org.fbgk.ataque.guerrastribales.dto.AtaqueDTO;
import org.fbgk.ataque.guerrastribales.dto.JugadoresDTO;
import org.fbgk.ataque.guerrastribales.dto.ListaAtaquesDTO;
import org.fbgk.ataque.guerrastribales.dto.LoginDTO;
import org.fbgk.ataque.guerrastribales.dto.PueblosDTO;
import org.fbgk.ataque.guerrastribales.dto.ServidorDTO;
import org.fbgk.ataque.url.dto.EjecutarHTTPDTO;
import org.fbgk.ataque.url.dto.RespuestaHTTPDTO;
import org.fbgk.ataque.vistas.constantes.Configuracion;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * The Class LectorInformesServicioImpl.
 */
public class LectorInformesServicioImpl extends LectorInformesServicioBase {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fbgk.ataque.actions.LectorInformesServicio#leerInformes(org.fbgk.
	 * ataque.guerrastribales.dto.LoginDTO,
	 * org.fbgk.ataque.guerrastribales.dto.ServidorDTO)
	 */
	@Override
	public void leerInformes(final LoginDTO loginDTO, final ServidorDTO servidorDTO) {
		if (this.configuration.getBoolean(Configuracion.LEERINFORMES.getKey(), Boolean.TRUE)) {
			if (this.urlActionsServicio.loginSiNoLoEsta(loginDTO.getLoginID(), servidorDTO.getServidorID())) {

				final JugadoresDTO jugadoresDTO = this.ataqueDao.consultar("FROM JugadoresDTO WHERE nombre=? AND servidorID=?", loginDTO.getUsuario(), servidorDTO.getServidorID());

				final List<PueblosDTO> pueblosDTO = this.ataqueDao.buscar("FROM PueblosDTO WHERE jugadoresID = ?", jugadoresDTO.getJugadoresID());

				final EjecutarHTTPDTO ejecutarHTTPDTO = new EjecutarHTTPDTO();
				ejecutarHTTPDTO.setCookie(Boolean.TRUE);
				ejecutarHTTPDTO.setIndRespuesta(Boolean.FALSE);
				ejecutarHTTPDTO.setUrl(String.format(GuerrasTribalesActions.URL_INFORMES_ATAQUE, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion(), pueblosDTO.get(0).getGameID()));
				final RespuestaHTTPDTO respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarGet(ejecutarHTTPDTO);

				final Document document = Jsoup.parse(this.urlActionsServicio.respuestaString(respuestaHTTPDTO));
				final Elements elementos = document.select("#report_list > tbody > tr > td:has(span)");

				this.procesarInformes(elementos, String.format(GuerrasTribalesActions.ATAQUE_PROCESO, servidorDTO.getServer(), servidorDTO.getJuego(), servidorDTO.getInternalizacion()), servidorDTO.getServidorID());

			}
		}
	}

	/**
	 * Procesar botin. Indicara si se vuelve a atacar.
	 * 
	 * @param document
	 *            the document
	 * @return the boolean
	 */
	private Boolean procesarBotin(final Document document) {
		Boolean atacar = Boolean.FALSE;

		if (this.configuration.getBoolean(Configuracion.ATACARCOMPLETO.getKey(), Boolean.TRUE)) {

			final Elements elementsRobados = document.select("#attack_results > tbody > tr > td:has(span)");

			if (elementsRobados.size() == 2) {

				final String[] robado = elementsRobados.get(1).text().split("/");

				atacar = robado[0].equals(robado[1]);
			}

		}

		final Elements elementsEspiados = document.select("#attack_spy > tbody > tr > td:has(span)");

		Integer totalEspiado = Integer.valueOf(0);

		if (elementsEspiados.size() == 1 && atacar) {

			final String[] espiado = document.select("#attack_spy > tbody > tr > td:has(span)").text().replace(".", "").trim().split(" ");

			totalEspiado = Integer.valueOf(espiado[0]) + Integer.valueOf(espiado[1]) + Integer.valueOf(espiado[2]);

			atacar = totalEspiado > this.configuration.getInteger(Configuracion.MINIMOATAQUECOMPLETO.getKey(), 1000);
		}

		return atacar;
	}

	/**
	 * Procesar informes.
	 * 
	 * @param elementos
	 *            the elementos
	 */
	private void procesarInformes(final Elements elementos, final String inicioURL, final Integer idServer) {
		final EjecutarHTTPDTO ejecutarHTTPDTO = new EjecutarHTTPDTO();
		ejecutarHTTPDTO.setCookie(Boolean.TRUE);
		ejecutarHTTPDTO.setIndRespuesta(Boolean.FALSE);
		for (final Element elements : elementos) {
			if (elements.text().contains("nuevo")) {
				final Elements informeElementos = elements.getElementsByTag("a");
				if (informeElementos.size() == 1) {
					final String url = String.format("%s%s", inicioURL, informeElementos.get(0).attr("href"));
					ejecutarHTTPDTO.setUrl(url);
					final RespuestaHTTPDTO respuestaHTTPDTO = this.clienteHTTPServicio.ejecutarGet(ejecutarHTTPDTO);
					final Document document = Jsoup.parse(this.urlActionsServicio.respuestaString(respuestaHTTPDTO));

					final String idAtaque = document.select("#attack_info_att > tbody > tr > td > span").get(0).attr("data-id");
					final String idDefensa = document.select("#attack_info_def > tbody > tr > td > span").get(0).attr("data-id");

					final Boolean procesar = this.procesarBotin(document);

					final ListaAtaquesDTO listaAtaquesDTO = this.ataqueDao.consultar("FROM ListaAtaquesDTO WHERE gameIDPropio = ? AND servidorID = ?", Integer.valueOf(idAtaque), idServer);

					if (listaAtaquesDTO != null) {

						final AtaqueDTO ataqueDTO = this.ataqueDao.consultar("FROM AtaqueDTO WHERE listaAtaquesID = ? AND gameIDAtaque = ?", listaAtaquesDTO.getListaAtaquesID(), Integer.valueOf(idDefensa));

						if (ataqueDTO != null) {
							if (procesar) {
								ataqueDTO.setTiempoAtaque(null);
								this.ataqueDao.actualizar(ataqueDTO);
							}
							if (this.configuration.getBoolean(Configuracion.ELIMINARINFORMELISTA.getKey(), Boolean.TRUE)) {
								this.clienteHTTPServicio.ejecutarGet(new EjecutarHTTPDTO(String.format("%s%s", inicioURL, document.select("a[href*=del_one]").get(0).attr("href")), null, Boolean.FALSE, null, null, Boolean.TRUE));
							}
						}
					}

				}
			}
		}
	}
}
