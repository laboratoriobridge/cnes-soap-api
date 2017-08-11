package br.ufsc.bridge.cnes.soap.estabelecimento;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CnesResponseConsultarEstabelecimentoSaude implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nomeFantasia;

	private String nomeLogradouro;

	private String numeroLogradouro;

	private String descricaoBairro;

	private String siglaUf;

	private String nomeMunicipio;

	private TipoUnidade tipoUnidade;

	public boolean isEmpty() {
		return StringUtils.isBlank(this.nomeFantasia) &&
				StringUtils.isBlank(this.nomeLogradouro) &&
				StringUtils.isBlank(this.numeroLogradouro) &&
				StringUtils.isBlank(this.descricaoBairro) &&
				StringUtils.isBlank(this.siglaUf) &&
				(this.tipoUnidade == null || this.tipoUnidade.isEmpty());
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class TipoUnidade implements Serializable {
		private static final long serialVersionUID = 1L;
		private String tipo;
		private String descricao;

		public boolean isEmpty() {
			return StringUtils.isBlank(this.tipo) &&
					StringUtils.isBlank(this.descricao);
		}
	}
}
