package br.ufsc.bridge.cnes.soap.profissional.service;

import java.net.MalformedURLException;

import br.ufsc.bridge.cnes.soap.profissional.CnesRequestConsultarProfissionalSaude;
import br.ufsc.bridge.cnes.soap.profissional.CnesResponseConsultarProfissionalSaude;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.SoapHttpClient;
import br.ufsc.bridge.soap.http.exception.SoapCreateMessageException;
import br.ufsc.bridge.soap.http.exception.SoapHttpConnectionException;
import br.ufsc.bridge.soap.http.exception.SoapHttpResponseException;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;

public class CnesConsultarProfissionalSaudeService {

	private SoapHttpClient<CnesResponseConsultarProfissionalSaude> soapHttpClient;

	public CnesConsultarProfissionalSaudeService() throws MalformedURLException {
		this.soapHttpClient = new SoapHttpClient<>(new CnesProfissionalSoapMessageInterpreter(new SoapCredential("CNES.PUBLICO", "cnes#2015public")));
		this.soapHttpClient.setUrl("https://servicos.saude.gov.br/cnes/ProfissionalSaudeService/v1r0");
	}

	public CnesResponseConsultarProfissionalSaude consultar(String cns)
			throws SoapHttpConnectionException, SoapHttpResponseException, SoapCreateMessageException, SoapReadMessageException {
		return this.soapHttpClient.send(new CnesRequestConsultarProfissionalSaude(cns));
	}
}
