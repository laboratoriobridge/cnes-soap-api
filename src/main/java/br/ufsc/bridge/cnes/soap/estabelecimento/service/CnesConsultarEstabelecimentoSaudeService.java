package br.ufsc.bridge.cnes.soap.estabelecimento.service;

import java.net.MalformedURLException;

import br.ufsc.bridge.cnes.soap.estabelecimento.CnesRequestConsultarEstabelecimentoSaude;
import br.ufsc.bridge.cnes.soap.estabelecimento.CnesResponseConsultarEstabelecimentoSaude;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.SoapHttpClient;
import br.ufsc.bridge.soap.http.exception.SoapCreateMessageException;
import br.ufsc.bridge.soap.http.exception.SoapHttpConnectionException;
import br.ufsc.bridge.soap.http.exception.SoapHttpResponseException;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;

public class CnesConsultarEstabelecimentoSaudeService {

	private SoapHttpClient<CnesResponseConsultarEstabelecimentoSaude> soapHttpClient;

	public CnesConsultarEstabelecimentoSaudeService() throws MalformedURLException {
		this.soapHttpClient = new SoapHttpClient<>(new CnesEstabelecimentoSoapMessageInterpreter(new SoapCredential("CNES.PUBLICO", "cnes#2015public")));
		this.soapHttpClient.setUrl("https://servicos.saude.gov.br/cnes/EstabelecimentoSaudeService/v1r0");
	}

	public CnesResponseConsultarEstabelecimentoSaude consultar(String cnes)
			throws SoapHttpConnectionException, SoapHttpResponseException, SoapCreateMessageException, SoapReadMessageException {
		return this.soapHttpClient.send(new CnesRequestConsultarEstabelecimentoSaude(cnes));
	}
}
