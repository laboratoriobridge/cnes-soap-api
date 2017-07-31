package br.ufsc.bridge.cnes.soap.estabelecimento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "requestConsultarEstabelecimentoSaude", namespace = "http://servicos.saude.gov.br/cnes/v1r0/estabelecimentosaudeservice")
public class CnesRequestConsultarEstabelecimentoSaude {

	@XmlElement(name = "FiltroPesquisaEstabelecimentoSaude", namespace = "http://servicos.saude.gov.br/wsdl/mensageria/v1r0/filtropesquisaestabelecimentosaude")
	private CnesFiltroPesquisaEstabelecimentoSaude filtro;

	public CnesRequestConsultarEstabelecimentoSaude(String cnes) {
		this.setCnes(cnes);
	}

	public void setCnes(String cnes) {
		this.filtro = new CnesFiltroPesquisaEstabelecimentoSaude(new CnesCodigoCnes(cnes));
	}

	private static final String CODIGO_CNES_NAMESPACE = "http://servicos.saude.gov.br/schema/cnes/v1r0/codigocnes";

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CnesFiltroPesquisaEstabelecimentoSaude {

		@XmlElement(name = "CodigoCNES", namespace = CODIGO_CNES_NAMESPACE)
		private CnesCodigoCnes codigoCnes;

	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CnesCodigoCnes {

		@XmlElement(name = "codigo", namespace = CODIGO_CNES_NAMESPACE)
		private String codigo;
	}

}
