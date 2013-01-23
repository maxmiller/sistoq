/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.maxmiller.generic;

import java.util.Collection;

/**
 *
 * @author Max
 */
public interface QueryList<R extends Collection<?>> extends Query<R> {
	/**
	 * Executa a query no repositorio enviado.
	 * @param repository o repositorio onde a busca será realizada.
	 * @param startPosition a posição inicial da busca.
	 * @param maxResults o número maximo de registros retornados pela busca.
	 * @return o resultado da consulta.
	 */
	R queryOn(Repository repository, Integer startPosition,Integer maxResults);


}
