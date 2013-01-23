/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.maxmiller.generic;

/**
 *
 * @author Max
 */
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import br.com.maxmiller.exception.RepositoryException;


/**
 * Repositorio para armazenamento e pesquisa.
 * @author Tomaz Lavieri
 * @since 1.0
 */
public interface Repository {

/* – ESTADO DO REPOSITORIO – */

	/**
	 * Verifica se este repositorio esta configurado para commitar automaticamente as transações
	 * quando um escopo transacional não estiver aberto.
	 */
	boolean isAutoCommit();
	/**
	 * Ativa ou desativa o commitador automatico de transações, este comitador só
	 * comitará quando uma operação for submetida fora de um escopo transacional.
	 * @param enabled se o auto commit deve ser ligado.
	 */
	void setAutoCommit(boolean enabled);
        /**
         * 
         * @return
         */
        boolean isAutoRollbackOnFail() ;

        void setAutoRollbackOnFail(boolean enabled);
       // void setAutoCommit(boolean enabled);
       // boolean isTransactionOpen() ;
	/**
	 * Fecha este {@link Repository}.
	 * <BR>
	 * <BR>Caso haja tranzação aberta um {@link #rollbackRecord()} será chamado antes de
	 * fechar o {@link Repository}
	 * @throws RepositoryException caso haja alguma falha ao fechar o {@link Repository}
	 */
	void close() throws RepositoryException;
	/**
	 * Verifica se esta {@link Repository} se encontra aberto
	 */
	boolean isOpen() throws RepositoryException;

	/**
	 * Verifica se há alguma transação aberta.
	 */
	boolean isRecording() throws RepositoryException;
	/**
	 * Inicia uma tranzação para este {@link Repository}
	 * @throws RepositoryException caso haja alguma falha ao iniciar a tranzação.
	 * @throws IllegalRepositoryStateException caso uma tranzação esteja em aberto.
	 * @see #isRecording()
	 */
	void beginRecording() throws RepositoryException;

/* – AÇÕES NO RESPOSITORIO – */

	/**
	 * Commita a transação corrente associada a este {@link Repository}, confirmando
	 * todas as operações realisadas desde o último  {@link #beginRecording()}.
	 * @throws RepositoryException Caso ocorra alguma falha ao commitar a transação.
	 * @throws IllegalRepositoryStateException quando não há transação ativa.
	 */
	void commitRecord() throws RepositoryException;
	/**
	 * Retrocede, caso exista uma transação ativa, todas as operações realizadas até o
	 * momento da última chamada a {@link #beginRecording()}.
	 * @throws RepositoryException
	 */
	void rollbackRecord() throws RepositoryException;

	/**
	 * Adciona uma entidade a persistencia.
	 * @param entity a entidade.
	 */
	void add(Object entity) throws RepositoryException ;
	/**
	 * Adiciona uma coleção de entidades a persistencia.
	 * @param entitys a coleção de entidades.
	 */
	void addAll(Collection<?> entitys) throws RepositoryException;
	/**
	 * Adiciona uma coleção de entidades a persistencia.
	 * @param entitys a coleção de entidades.
	 */
	void addAll(Object ... entitys) throws RepositoryException;
	/**
	 * Remove uma entidade da persistencia.
	 * @param entity a entidade
	 */
	void remove(Object entity) throws RepositoryException ;
	/**
	 * Remove uma coleção de entidade da persistencia.
	 * @param entitys as entidades
	 */
	void removeAll(Collection<?> entitys) throws RepositoryException ;
	/**
	 * Remove uma coleção de entidade da persistencia.
	 * @param entitys as entidade
	 */
	void removeAll(Object ... entitys) throws RepositoryException ;
	/**
	 * Remove a entidade do tipo target, que tiver o id enviado.
	 * @param <T> o tipo da entidade
	 * @param target o tipo da entidade que sera removida
	 * @param id o ID que identifica uma entidade unica
	 * @throws RepositoryException caso algum erro ocorra no processo.
	 */
	<T> void remove(Class<T> target,Object id) throws RepositoryException;
	/**
	 * Armazena a entidade na persistencia, adcionando caso ainda não existe, ou
	 * atualizando caso já exista.
	 * @param entity a entidae
	 * @return A entidade a persistente
	 */
	<T> T marge(T entity) throws RepositoryException ;
	/**
	 * Armazena a coleção de entidades na persistencia, adcionando caso ainda não existe, ou
	 * atualizando caso já exista.
	 * @param <E> o tipo da entidade
	 * @param entity a coleção de entidae
	 * @return a coleção das entidades parsistentes.
	 */
	<E> List<E> margeAll(Collection<E> entitys);
	/**
	 * Armazena a coleção de entidades na persistencia, adcionando caso ainda não existe, ou
	 * atualizando caso já exista.
	 * @param <E> o tipo da entidade
	 * @param entity a coleção de entidae
	 * @return a coleção das entidades parsistentes.
	 */
	<E> List<E> margeAll(E ... entitys);

/* – BUSCAS NO REPOSITORIO – */

	/**
	 * Reinicia a entidade a partir do ID com os valores originais do banco de dados.
	 * @param entity a entidade
	 * @throws RepositoryException caso não seja possivel reiniciar a entidade.
	 */
	void refresh(Object entity) throws RepositoryException;

	/**
	 * Busca a entidade <tt>T</tt> com o <tt>id</tt> passado na persistencia.
	 * @param <T> o tipo de entidade a ser localizado.
	 * @param classe a classe da entidade que deve ser localizada.
	 * @param id o ID da entidade.
	 * @return <tt>T</tt> a entidade correspondente ao ID passado.<br>
	 * 			<tt>null</tt> caso não exista entidade correspondente ao ID.
	 * @throws RepositoryException caso ocorra alguma falha ao tentar localizar a entidade.
	 */
	<T> T get(Class<T> classe,Object id) throws RepositoryException;

	/**
	 * Executa a busca passada neste repositorio.<br/>
	 * <br/>
	 * Tem o mesmo efeito que: <br/>
	 * <tt>query.queryOn(thisRepository)</tt>
	 * @param <R> O tipo do resultado esperado da busca
	 * @param query a consulta que será executada.
	 * @return o resutlado da busca
	 * @throws RepositoryException caso ocorra alguma falha que impessa a realização da
	 * 			busca.
	 */
	<R> R find(DetachedCriteria detached) throws RepositoryException;
	/**
	 * Executa a busca enviada neste repositorio, iniciada a partir do elemento na posição
	 * <tt>startPosition</tt>.<br/>
	 * <br/>
	 * Enviar a <tt>startPosition == null</tt> não gera exceção, o parametro será apenas
	 * desconciderado.<br/>
	 * <br/>
	 * Tem o mesmo efeito que: <br/>
	 * <tt>query.queryOn(thisRepository,startPosition)</tt>
	 * @param <R> O tipo do resultado esperado da busca
	 * @param query a consulta que será executada.
	 * @param startPosition a posição inicial da busca.
	 * @return o resutlado da busca
	 * @throws RepositoryException caso ocorra alguma falha que impessa a realização da
	 * 			busca.
	 */
	<R> R find(DetachedCriteria detached,Integer startPosition) throws RepositoryException;
	/**
	 * Executa a busca enviada neste repositorio, iniciada a partir do elemento na
	 * posição <tt>startPosition</tt>, com o número máximo de resultados definido em
	 * <tt>maxResults</tt>.<br/>
	 * <br/>
	 * Enviar a <tt>startPosition == null || maxResults == null</tt> não gera exceção,
	 * o(s) parametro(s) será(ão) apenas desconciderado(s).<br/>
	 * <br/>
	 * Tem o mesmo efeito que: <br/>
	 * <tt>query.queryOn(thisRepository,startPosition,maxResults)</tt>
	 * @param <R> O tipo do resultado esperado da busca
	 * @param query a consulta que será executada.
	 * @param startPosition a posição inicial da busca.
	 * @param maxResults a quantidade máxima de resultados que esta busca deve retornar.
	 * @return o resutlado da busca
	 * @throws RepositoryException caso ocorra alguma falha que impessa a realização da
	 * 			busca.
	 */
	<R extends Collection<?>> R find(DetachedCriteria detached,Integer startPosition,
			Integer maxResults) throws RepositoryException;

	/**
	 * Busca a lista de todas as entidades do tipo T existentes na persistencia.
	 * @param <T> O tipo da entidade a partir da qual a lista deve ser montada
	 * @param classe a classe da entidade a partir da qual a lsita deve ser montada.
	 * @return a lista de entidades da persistencia.
	 * @throws RepositoryException caso ocorra alguma falha na montagem da lista.
	 */
	<T> List<T> list(Class<T> classe) throws RepositoryException;
	/**
	 * Cria uma lista ordenada ascendente ignore case, a partir das propriedades enviadas
	 * e mantendo a mesma sequencia de prioridade usada nos argumentos deste método.
	 * @param <T> O tipo da entidade a partir da qual a lista deve ser montada
	 * @param classe a classe da entidade a partir da qual a lsita deve ser montada.
	 * @param propertiesOrder as propriedades a serem ordenadas.
	 * @return a lsita ordenada.
	 * @throws RepositoryException caso ocorra alguma falha na hora de criar a lista,
	 * 			mais detalhes em {@link RepositoryException#getCause()}.
	 * @see #list(Class, OrderList[])
	 */
	<T> List<T> list(Class<T> classe,String ... propertiesOrder) throws RepositoryException;
	/**
	 * Cria uma lista ordenada mantendo a ordem na sequencia dos argumentos passados.
	 * cada propriedade pode ser setada como ascendente ou descendente e como ignore case
	 * ou não.
	 * @param <T> O tipo da entidade a partir da qual a lista deve ser montada
	 * @param classe a classe da entidade a partir da qual a lsita deve ser montada.
	 * @param orders a ordem da lista
	 * @return a lista ordeanda.
	 * @throws RepositoryException caso ocorra alguma falha na hora de criar a lista,
	 * 			mais detalhes em {@link RepositoryException#getCause()}.
	 * @see #list(Class, String[])
	 */
	<T> List<T> list(Class<T> classe,OrderList ... orders) throws RepositoryException;

	/**
	 * O mesmo que {@link #list(Class)} com a opção de escolher o <tt>maxResults</tt>
	 * e o <tt>firstResult</tt>
	 * @see #list(Class)
	 */
	<T> List<T> list(Class<T> classe,Integer maxResults, Integer firstResult) throws RepositoryException;

	/**
	 * O mesmo que {@link #list(Class, String...)} com a opção de escolher o
	 * <tt>maxResults</tt> e o <tt>firstResult</tt>
	 * @see #list(Class, String...)
	 */
	<T> List<T> list(Class<T> classe,Integer maxResults, Integer firstResult,String ... properties) throws RepositoryException;
	/**
	 * O mesmo que {@link #list(Class, OrderList...)} com a opção de escolher o
	 * <tt>maxResults</tt> e o <tt>firstResult</tt>
	 * @see #list(Class, OrderList...)
	 */
	<T> List<T> list(Class<T> classe,Integer maxResults, Integer firstResult,OrderList ... orders) throws RepositoryException;

	/**
	 * Encontra o iníco resultado do tipo <tt>T</tt> baseado no exemplo enviado.
	 * @param <T> o tipo de resultado a ser buscado.
	 * @param target a classe da busca a ser realizada, necessário em casos onde vai
	 * 			ser usado uma subclasse como no argumento <tt>example</tt>. Objetos
	 * 			recuperados por consultas no {@link Repository} são subclasses pois
	 * 			são implementados com proxy, neste caso deve-se passar o argumento
	 * 			target.
	 * @param example um exemplo preenchido com as propriedades da busca.
	 * @see #getByExample(Object)
	 */
	<T> T getByExample(Class<T> target,T example);
	/**
	 * Encontra o primeiro resultado do tipo <tt>T</tt> baseado no exemplo enviado.
	 * @param <T> o tipo de resultado a ser buscado.
	 * @param target a classe da busca a ser realizada, necessário em casos onde vai
	 * 			ser usado uma subclasse como no argumento <tt>example</tt>. Objetos
	 * 			recuperados por consultas no {@link Repository} são subclasses pois
	 * 			são implementados com proxy, neste caso deve-se passar o argumento
	 * 			target.
	 * @param example um exemplo preenchido com as propriedades da busca.
	 * @see #getFirstByExample(Object)
	 */
	<T> T getFirstByExample(Class<T> target,T example);
	/**
	 * Encontra a lista de resultados do tipo <tt>T</tt> baseado no exemplo enviado.
	 * @param <T> o tipo de resultado a ser buscado.
	 * @param target a classe da busca a ser realizada, necessário em casos onde vai
	 * 			ser usado uma subclasse como no argumento <tt>example</tt>. Objetos
	 * 			recuperados por consultas no {@link Repository} são subclasses pois
	 * 			são implementados com proxy, neste caso deve-se passar o argumento
	 * 			target.
	 * @param example um exemplo preenchido com as propriedades da busca.
	 * @see #listByExample(Object)
	 */
	<T> List<T> listByExample(Class<T> target,T example);
	/**
	 * O mesmo que {@link #listByExample(Class, Object)} com a opção de escolher o
	 * <tt>maxResults</tt> e o <tt>firstResult</tt>
	 * @see {@link #listByExample(Class, Object)}
	 */
	<T> List<T> listByExample(Class<T> target,T example,Integer maxResults, Integer firstResult);

        <R> Object uniqueResult(DetachedCriteria detached) throws RepositoryException;
        
        <T> List<T> listByQuery(Class<T> clazz,String query)throws RepositoryException;

}