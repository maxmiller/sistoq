/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.maxmiller.generic;

import br.com.maxmiller.exception.RepositoryException;


/**
 *
 * @author Max
 */
public interface Query<R> {

	/**
	 * Executa a query no repositorio enviado.
	 * @param repository o repositorio onde a busca será realizada.
	 * @return o resultado da consulta.
	 */
	R queryOn(Repository repository) throws RepositoryException;
	/**
	 * Executa a query no repositorio enviado.
	 * @param repository o repositorio onde a busca será realizada.
	 * @param startPosition a posição inicial da busca.
	 * @return o resultado da consulta.
	 */
	R queryOn(Repository repository, Integer startPosition);

}
