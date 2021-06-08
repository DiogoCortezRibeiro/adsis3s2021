package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ConteudoPersistent extends Conexao implements ConteudoRepository {
	private Connection conexao;
	
	public ConteudoPersistent()
	{
		this.conexao = this.abrirConexao();
	}
	
	@Override
	public void salvar(Conteudo novoConteudo, String id_conteudo) {
		try
		{
			PreparedStatement psSalvarAluno = conexao.prepareStatement("INSERT INTO conteudo(id_conteudo, descricao, cargaHoraria)VALUES(?, ?, ?)");
			psSalvarAluno.setString(1, id_conteudo);
			psSalvarAluno.setString(2, novoConteudo.getDescricao());
			psSalvarAluno.setDouble(3, novoConteudo.getCargaHoraria());
			psSalvarAluno.execute();
			psSalvarAluno.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
