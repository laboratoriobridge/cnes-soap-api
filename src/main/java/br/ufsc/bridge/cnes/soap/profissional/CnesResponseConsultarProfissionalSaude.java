package br.ufsc.bridge.cnes.soap.profissional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
	private Map<String, String> cboMap = new HashMap<>();

	public boolean isEmpty() {
		return StringUtils.isBlank(this.nome) && this.cboMap.isEmpty();
	}

	public String getDescricaoCboByCodigo(String coCbo2002) {
		return this.cboMap.get(coCbo2002);
	}
}
