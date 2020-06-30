package br.ufsc.bridge.cnes.soap.profissional.service;

import java.net.MalformedURLException;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;

import br.ufsc.bridge.cnes.soap.profissional.CnesRequestConsultarProfissionalSaude;
import br.ufsc.bridge.cnes.soap.profissional.CnesResponseConsultarProfissionalSaude;
import br.ufsc.bridge.soap.SoapClient;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.SoapHttpRequest;
import br.ufsc.bridge.soap.http.SoapHttpResponse;
import br.ufsc.bridge.soap.http.SoapMessageBuilder;
import br.ufsc.bridge.soap.http.exception.SoapCreateMessageException;
import br.ufsc.bridge.soap.http.exception.SoapHttpConnectionException;
import br.ufsc.bridge.soap.http.exception.SoapHttpResponseException;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;
import br.ufsc.bridge.soap.jaxb.JAXBMessageBuilder;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class CnesConsultarProfissionalSaudeService {

	private SoapMessageBuilder messageBuilder;

	public CnesConsultarProfissionalSaudeService() throws MalformedURLException {
		this.messageBuilder = new JAXBMessageBuilder(new SoapCredential("CNES.PUBLICO", "cnes#2015public"));
	}

	public CnesResponseConsultarProfissionalSaude consultar(String cns)
			throws SoapHttpConnectionException, SoapHttpResponseException, SoapCreateMessageException, SoapReadMessageException {

		SoapHttpRequest request = new SoapHttpRequest("https://servicos.saude.gov.br/cnes/ProfissionalSaudeService/v1r0", "",
				this.messageBuilder.createMessage(new CnesRequestConsultarProfissionalSaude(cns)));

		SoapHttpResponse response = SoapClient.request(request);

		CnesResponseConsultarProfissionalSaude profissional = new CnesResponseConsultarProfissionalSaude();
		try {
			XPathFactoryAssist xPathAssist = new XPathFactoryAssist(response.getSoap()).getXPathAssist("Envelope//ProfissionalSaude");

			profissional.setNome(xPathAssist.getString("./Nome/Nome"));

			Node cboNode = xPathAssist.getNode("CBO");
			while (cboNode.getNodeName().contains("CBO")) {
				String cbo2002 = cboNode.getFirstChild().getFirstChild().getNodeValue();
				String nomeCbo = cboNode.getFirstChild().getNextSibling().getFirstChild().getNodeValue();
				profissional.getCboMap().put(cbo2002, nomeCbo);
				cboNode = cboNode.getNextSibling();
			}
		} catch (XPathExpressionException e) {
			throw new SoapReadMessageException("Erro ao converter response soap do profissioanl", e);
		}

		return profissional.isEmpty() ? null : profissional;
	}
}
