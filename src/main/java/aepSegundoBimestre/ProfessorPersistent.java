package aepSegundoBimestre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfessorPersistent extends Conexao implements ProfessorRepository {
	private Connection conexao;
	
    public ProfessorPersistent() {
		conexao = this.abrirConexao();
	}

	@Override
	public void salvar(Professor novoProfessor) {
		try 
		{
			String id_professor = UUID.randomUUID().toString();
			PreparedStatement psSalvar = conexao.prepareStatement("INSERT INTO professor(id_professor, nome, email, matricula)VALUES(?, ?, ?, ?)");
			psSalvar.setString(1, id_professor);
			psSalvar.setString(2, novoProfessor.getNome());
			psSalvar.setString(3, novoProfessor.getEmail());
			psSalvar.setString(4 , novoProfessor.getMatricula());
			psSalvar.execute();
			psSalvar.close();
			
			if(novoProfessor.getEspecializacoes().size() > 0)
			{
				this.salvarEspecializacoes(novoProfessor, id_professor);
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	private void salvarEspecializacoes(Professor novoProfessor, String id_professor) {
		try
		{
			for(String especialidade : novoProfessor.getEspecializacoes())
			{
				PreparedStatement psSalvarEspecialidade = conexao.prepareStatement("INSERT INTO especialidades_professor(id_professor, especialidade)VALUES(?, ?)");
				psSalvarEspecialidade.setString(1, id_professor);
				psSalvarEspecialidade.setString(2, especialidade);
				psSalvarEspecialidade.execute();
				psSalvarEspecialidade.close();
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Professor> getAll() {
		return null;
	}

}
