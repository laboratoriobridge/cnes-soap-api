package br.ufsc.bridge.cnes.soap.profissional.service;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import br.ufsc.bridge.cnes.soap.profissional.CnesResponseConsultarProfissionalSaude;
import br.ufsc.bridge.soap.http.SOAPMessageInterpreter;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class CnesProfissionalSoapMessageInterpreter extends SOAPMessageInterpreter<CnesResponseConsultarProfissionalSaude> {

	public CnesProfissionalSoapMessageInterpreter(SoapCredential c) {
		super(c);
	}

	@Override
	public CnesResponseConsultarProfissionalSaude readMessage(Document response) throws SoapReadMessageException {
		CnesResponseConsultarProfissionalSaude profissional = new CnesResponseConsultarProfissionalSaude();
		try {
			XPathFactoryAssist xPathAssist = new XPathFactoryAssist(response).getXPathAssist("Envelope//ProfissionalSaude");

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