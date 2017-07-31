package br.ufsc.bridge.cnes.soap.profissional;

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
public class CnesResponseConsultarProfissionalSaude implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;

	private String descricaoCBO;

	public boolean isEmpty() {
		return StringUtils.isBlank(this.nome) &&
				StringUtils.isBlank(this.descricaoCBO);
	}
}
