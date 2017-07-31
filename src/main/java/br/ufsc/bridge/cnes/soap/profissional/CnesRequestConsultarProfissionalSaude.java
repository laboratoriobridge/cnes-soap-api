package br.ufsc.bridge.cnes.soap.profissional;

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
@XmlRootElement(name = "requestConsultarProfissionalSaude", namespace = "http://servicos.saude.gov.br/cnes/v1r0/profissionalsaudeservice")
public class CnesRequestConsultarProfissionalSaude {
	private static final String CNS_NAMESPACE = "http://servicos.saude.gov.br/schema/cadsus/v5r0/cns";

	@XmlElement(name = "FiltroPesquisaProfissionalSaude", namespace = "http://servicos.saude.gov.br/wsdl/mensageria/v1r0/filtropesquisaprofissionalsaude")
	private CnesFiltroPesquisaProfissionalSaude filtro;

	public CnesRequestConsultarProfissionalSaude(String cns) {
		this.setCns(cns);
	}

	public void setCns(String cns) {
		this.filtro = new CnesFiltroPesquisaProfissionalSaude(new CnesNumeroCns(cns));
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CnesFiltroPesquisaProfissionalSaude {
		@XmlElement(name = "CNS", namespace = CNS_NAMESPACE)
		private CnesNumeroCns cns;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CnesNumeroCns {
		@XmlElement(name = "numeroCNS", namespace = CNS_NAMESPACE)
		private String cns;
	}
}
