package br.ufsc.bridge.cnes.soap.estabelecimento.service;

import java.net.MalformedURLException;

import javax.xml.xpath.XPathExpressionException;

import br.ufsc.bridge.cnes.soap.estabelecimento.CnesRequestConsultarEstabelecimentoSaude;
import br.ufsc.bridge.cnes.soap.estabelecimento.CnesResponseConsultarEstabelecimentoSaude;
import br.ufsc.bridge.cnes.soap.estabelecimento.CnesResponseConsultarEstabelecimentoSaude.TipoUnidade;
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

public class CnesConsultarEstabelecimentoSaudeService {

	private SoapMessageBuilder messageBuilder;

	public CnesConsultarEstabelecimentoSaudeService() throws MalformedURLException {
		this.messageBuilder = new JAXBMessageBuilder(new SoapCredential("CNES.PUBLICO", "cnes#2015public"));
	}

	public CnesResponseConsultarEstabelecimentoSaude consultar(String cnes)
			throws SoapHttpConnectionException, SoapHttpResponseException, SoapCreateMessageException, SoapReadMessageException {

		SoapHttpRequest request = new SoapHttpRequest("https://servicos.saude.gov.br/cnes/EstabelecimentoSaudeService/v1r0", "",
				this.messageBuilder.createMessage(new CnesRequestConsultarEstabelecimentoSaude(cnes)));

		SoapHttpResponse response = SoapClient.request(request);

		CnesResponseConsultarEstabelecimentoSaude unidade = new CnesResponseConsultarEstabelecimentoSaude();
		try {
			XPathFactoryAssist xPathAssist = new XPathFactoryAssist(response.getSoap()).getXPathAssist("Envelope//DadosGeraisEstabelecimentoSaude");

			unidade.setNomeFantasia(xPathAssist.getString("./nomeFantasia/Nome"));
			unidade.setTipoUnidade(new TipoUnidade(xPathAssist.getString("./tipoUnidade/codigo"), xPathAssist.getString("./tipoUnidade/descricao")));

			XPathFactoryAssist xPathEndereco = xPathAssist.getXPathAssist("./Endereco");
			unidade.setNomeLogradouro(xPathEndereco.getString("./nomeLogradouro"));
			unidade.setNumeroLogradouro(xPathEndereco.getString("./numero"));
			unidade.setSiglaUf(xPathEndereco.getString("./Municipio//siglaUF"));
			unidade.setDescricaoBairro(xPathEndereco.getString("./Bairro/descricaoBairro"));
			unidade.setNomeMunicipio(xPathEndereco.getString("./Municipio/nomeMunicipio"));
		} catch (XPathExpressionException e) {
			throw new SoapReadMessageException("Erro ao converter response soap do esbelecimento de saude", e);
		}
		return unidade.isEmpty() ? null : unidade;
	}
}
