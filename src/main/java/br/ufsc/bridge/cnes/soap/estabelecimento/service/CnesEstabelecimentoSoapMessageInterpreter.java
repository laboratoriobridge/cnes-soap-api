package br.ufsc.bridge.cnes.soap.estabelecimento.service;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.ufsc.bridge.cnes.soap.estabelecimento.CnesResponseConsultarEstabelecimentoSaude;
import br.ufsc.bridge.cnes.soap.estabelecimento.CnesResponseConsultarEstabelecimentoSaude.TipoUnidade;
import br.ufsc.bridge.soap.http.SOAPMessageInterpreter;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

public class CnesEstabelecimentoSoapMessageInterpreter extends SOAPMessageInterpreter<CnesResponseConsultarEstabelecimentoSaude> {

	public CnesEstabelecimentoSoapMessageInterpreter(SoapCredential c) {
		super(c);
	}

	@Override
	public CnesResponseConsultarEstabelecimentoSaude readMessage(Document response) throws SoapReadMessageException {
		CnesResponseConsultarEstabelecimentoSaude unidade = new CnesResponseConsultarEstabelecimentoSaude();
		try {
			XPathFactoryAssist xPathAssist = new XPathFactoryAssist(response).getXPathAssist("Envelope//DadosGeraisEstabelecimentoSaude");

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