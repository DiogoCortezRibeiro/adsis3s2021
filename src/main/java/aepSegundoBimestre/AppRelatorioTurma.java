package aepSegundoBimestre;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class AppRelatorioTurma {
	public static void main(String[] args) {
		ProfessorPersistent professorRepository = new ProfessorPersistent();
		criarEInserirProfessor(professorRepository);

		String id_conteudo = UUID.randomUUID().toString();
		String id_turma = UUID.randomUUID().toString();
		String id_cronograma = UUID.randomUUID().toString();
		String id_curso = UUID.randomUUID().toString();

		inserirConteudo(id_conteudo);
		inserirCurso(id_curso, id_conteudo);
		inserirCronograma(id_turma, id_cronograma);
		inserirTurma(id_turma, id_curso, id_cronograma);
		inserirAlunos(id_turma);

		pegarDadosParaGerarRelatorio(id_turma, id_curso);
	}

	private static void pegarDadosParaGerarRelatorio(String id_turma, String id_curso) {
		TurmaPersistent turmaPersistent = new TurmaPersistent();
		Turma turma = turmaPersistent.getTurmaById(id_turma);

		CursoPersistent cursoPersistent = new CursoPersistent();
		Curso curso = cursoPersistent.getCursoById(id_curso);

		AlunoPersistent alunoRepository = new AlunoPersistent();
		List<Aluno> alunos = alunoRepository.getAlunosByTurma(id_turma);

		gerarRelatorioTurma(turma, curso, alunos);
	}

	private static void gerarRelatorioTurma(Turma turma, Curso curso, List<Aluno> alunos) {
		
		
	}

	private static void inserirTurma(String id_turma, String id_curso, String id_cronograma) {
		TurmaPersistent turmaPersistent = new TurmaPersistent();
		Turma turma = new Turma(new Nome("ADSIS3S"), "Noturno");
		turmaPersistent.salvar(turma, id_turma, id_curso, id_cronograma);
	}

	private static void inserirCronograma(String id_turma, String id_cronograma) {
		CronogramaPersistent cronogramaPersistent = new CronogramaPersistent();
		Cronograma cronograma = new Cronograma("20/05/21", "20/12/21");
		cronogramaPersistent.salvar(cronograma, id_turma, id_cronograma);
	}

	private static void inserirCurso(String id_curso, String id_conteudo) {
		CursoPersistent cursoPersistent = new CursoPersistent();
		Curso curso = new Curso(new Nome("Analise e Desenvolvimento de Sistema"), 150, 360.0, "Marcelo Boffin");
		cursoPersistent.salvar(curso, id_curso, id_conteudo);
	}

	private static void inserirConteudo(String id_conteudo) {
		ConteudoPersistent conteudoPersistent = new ConteudoPersistent();
		Conteudo conteudo = new Conteudo("Algoritmos e lógica de programção", 60.0);
		conteudoPersistent.salvar(conteudo, id_conteudo);
	}

	private static void criarEInserirProfessor(ProfessorPersistent professorRepository) {
		Professor professor = new Professor(new Nome("Alexandre Moreno"), new Email("moreno@gmail.com"),
				new Matricula("123456"));
		professor.adicionarEspecialidade("Redes de Computadores");
		professor.adicionarEspecialidade("Algoritmo e Lógica de Programação");
		professorRepository.salvar(professor);
	}

	private static List<Aluno> gerarAlunos() {
		List<Aluno> alunos = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			Random rand = new Random();
			int totalFatal = rand.nextInt(50);
			Aluno aluno = new Aluno(new Nome("Aluno " + i), new Matricula("1000" + i),
					new Email("aluno" + i + "@gmail.com"), totalFatal, "Esta é a anotação do aluno " + i);
			Double nota1 = rand.nextDouble() * 10;
			aluno.adicionarNota(nota1, 0);
			Double nota2 = rand.nextDouble() * 10;
			aluno.adicionarNota(nota2, 1);
			alunos.add(aluno);
		}
		return alunos;
	}

	private static void inserirAlunos(String id_turma) {
		List<Aluno> alunos = new ArrayList<>();
		alunos = gerarAlunos();

		AlunoPersistent alunoRepository = new AlunoPersistent();
		for (Aluno aluno : alunos) {
			alunoRepository.salvar(aluno, id_turma);
		}
	}
}
