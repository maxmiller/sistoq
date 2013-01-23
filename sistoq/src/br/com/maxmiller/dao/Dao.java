/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.maxmiller.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import br.com.maxmiller.exception.RepositoryException;
import br.com.maxmiller.generic.OrderList;
import br.com.maxmiller.generic.Repository;

/**
 *
 * @author Max
 */
public class Dao implements Repository {

    private EntityManager manager = null;
    private boolean autoCommit = false;
    private boolean autoRollbackOnFail = true;

    public Dao() {
        this.manager = HibernateUtils.getEntityManager();
    }

    @Override
    public boolean isAutoCommit() {
        return autoCommit;
    }

    @Override
    public boolean isAutoRollbackOnFail() {
        return autoRollbackOnFail;
    }

    @Override
    public void setAutoRollbackOnFail(boolean enabled) {
        //  debug("Repository auto-rollback on fail switch to [%s]", enabled);
        this.autoRollbackOnFail = enabled;
    }

    @Override
    public void setAutoCommit(boolean enabled) {
        // debug("Repository auto-commit switch to [%s]", enabled);
        this.autoCommit = enabled;
    }

    private boolean isTransactionOpen() {
        return this.manager.getTransaction().isActive();
    }

    /**
     * Inicia uma transação se for necessário
     * @return se uma transação precisou ser inciada, e foi iniciada.
     */
    private boolean startTransactionIfNeed() {
        final boolean needTransaction = isAutoCommit() && !isTransactionOpen();
        if (needTransaction) {
            //debug("Transaction automatic started by HibernateRepository");
            this.manager.getTransaction().begin();
        }
        return needTransaction;
    }

    /**
     * Confirma a tranzação.
     * @throws IllegalStateException quando não há operação corrente.
     * @throws HibernateException quando há uma falha ao commitar a transação.
     */
    private void commitTransaction() {
        final EntityTransaction transaction = this.manager.getTransaction();
        if (transaction.isActive()) {
            try {
                transaction.commit();
            } finally {
                if (transaction.isActive()) {
                    if (isAutoRollbackOnFail()) {
                        transaction.rollback();
                    }
                }
            }
        } else {
            throw new IllegalStateException("trying to commit a inactive transaction");
        }
    }

    public void close() throws RepositoryException {
        this.manager.close();
    }

    public boolean isOpen() throws RepositoryException {
        return this.manager.isOpen();
    }

    public boolean isRecording() throws RepositoryException {
        return this.manager.getTransaction().isActive();
    }

    public void beginRecording() throws RepositoryException {
        this.manager.getTransaction().begin();
    }

    public void commitRecord() throws RepositoryException {
        this.manager.getTransaction().commit();
    }

    public void rollbackRecord() throws RepositoryException {
        this.manager.getTransaction().rollback();
    }

    public void add(Object entity) throws RepositoryException {
        try {
            this.manager.persist(entity);
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);
        }
    }

    public void addAll(Collection<?> entitys) throws RepositoryException {
        try {
            for (Object o : entitys) {
                this.add(o);
            }
          
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);

        }
    }

    public void addAll(Object... entitys) throws RepositoryException {
        addAll(Arrays.asList(entitys));
    }

    public void remove(Object entity) throws RepositoryException {
        try {
            Session hibernateSession =  (Session) this.manager.getDelegate();
            hibernateSession.refresh(entity);
            hibernateSession.delete(entity);
            hibernateSession = null;
            //entity  = this.marge(entity);
            //this.manager.remove(entity);
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);
        }
    }

    public void removeAll(Collection<?> entitys) throws RepositoryException {
        try {
            for (Object o : entitys) {
                this.remove(o);
            }
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);
        }
    }

    public void removeAll(Object... entitys) throws RepositoryException {
        removeAll(Arrays.asList(entitys));
    }

    public <T> void remove(Class<T> target, Object id) throws RepositoryException {
        try {
            T object = this.manager.find(target, id);
            this.remove(object);
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);
        }
    }

    public <T> T marge(T entity) throws RepositoryException {
        try {
            this.manager.merge(entity);
        } catch (RuntimeException ex) {
            throw new RepositoryException(ex);
        }
        return entity;
    }

    public <E> List<E> margeAll(Collection<E> entitys) {
        List<E> lista = new ArrayList<E>();
        try {
            for (E obj : entitys) {
                marge(obj);
                lista.add(obj);
            }
        } catch (RepositoryException ex) {
            throw new RuntimeException(ex);
        } catch (RuntimeException ex) {
            throw ex;//    Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public <E> List<E> margeAll(E... entitys) {
        return margeAll(Arrays.asList(entitys));
    }

    public void refresh(Object entity) throws RepositoryException {
        this.manager.refresh(entity);
    }

    public <T> T get(Class<T> classe, Object id) throws RepositoryException {
        return this.manager.find(classe, id);
    }

    public <T> List<T> list(Class<T> classe) throws RepositoryException {
        List<T> retorno = null;
        try {
            String sql = "select o from " + classe.getSimpleName() + "  o ";
            Query query = this.manager.createQuery(sql);
            retorno = query.getResultList();
        } catch (RuntimeException ex) {
            throw ex;
        }
        return retorno;
    }

    public <T> List<T> list(Class<T> classe, String... propertiesOrder) throws RepositoryException {
        List<T> retorno = null;
        try {
            String query = "select o from " + classe.getSimpleName() + "  o  order by ";

            for (String order : propertiesOrder) {
                query += order + " , ";
            }
            query = query.substring(0, query.length() - 2);
            Query q = this.manager.createQuery(query);
            retorno = q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public <T> List<T> list(Class<T> classe, OrderList... orders) throws RepositoryException {
        throw new UnsupportedOperationException("Não implementado.");
    }

    public <T> List<T> list(Class<T> classe, Integer maxResults, Integer firstResult) throws RepositoryException {
        List<T> retorno = null;
        try {
            String sql = "select o from " + classe.getSimpleName() + "  o ";
            Query query = this.manager.createQuery(sql);
            query.setMaxResults(maxResults);
            query.setFirstResult(firstResult);
            retorno = query.getResultList();
        } catch (RuntimeException ex) {
            throw ex;
        }
        return retorno;
    }

    public <T> List<T> list(Class<T> classe, Integer maxResults, Integer firstResult, String... properties) throws RepositoryException {
         List<T> retorno = null;
        try {
            String query = "select o from " + classe.getSimpleName() + "  o  order by ";

            for (String order : properties) {
                query += order + " , ";
            }
            query = query.substring(0, query.length() - 2);
            Query q = this.manager.createQuery(query);
            retorno = q.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return retorno;
    }

    public <T> List<T> list(Class<T> classe, Integer maxResults, Integer firstResult, OrderList... orders) throws RepositoryException {
        throw new UnsupportedOperationException("Não implementado.");
    }

    public <T> T getByExample(Class<T> classe, T example) {
        Session hibernateSession =  (Session) this.manager.getDelegate();
        Criteria criteria = hibernateSession.createCriteria(classe.getClass());
        criteria.add(Example.create(example));
        return (T) criteria.uniqueResult();
    }

    public <T> T getFirstByExample(Class<T> classe, T example) {
        Session hibernateSession =  (Session) this.manager.getDelegate();
        Criteria criteria = hibernateSession.createCriteria(classe.getClass());
        criteria.add(Example.create(example));
        List lista = criteria.list();
        return (T) ( lista == null ? null : lista.isEmpty() == true ? null :(T)lista.get(0));
    }

    public <T> List<T> listByExample(Class<T> classe, T example) {
        Session hibernateSession =  (Session) this.manager.getDelegate();
        Criteria criteria = hibernateSession.createCriteria(classe.getClass());
        criteria.add(Example.create(example));
        return criteria.list();
    }

    public <T> List<T> listByExample(Class<T> classe, T example, Integer maxResults, Integer firstResult) {
        Session hibernateSession =  (Session) this.manager.getDelegate();
        Criteria criteria = hibernateSession.createCriteria(classe.getClass());
        criteria.add(Example.create(example));
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    public <R> R find(DetachedCriteria detached) throws RepositoryException {
        Session hibernateSession = (Session) this.manager.getDelegate();
        return (R) detached.getExecutableCriteria(hibernateSession).uniqueResult();
    }

    public <R> R find(DetachedCriteria detached, Integer startPosition) throws RepositoryException {
        Session hibernateSession = (Session) this.manager.getDelegate();
        Criteria criteria = detached.getExecutableCriteria(hibernateSession);
        criteria.setFirstResult(startPosition);
        return (R) criteria.uniqueResult();
    }

    public <R extends Collection<?>>  R find(DetachedCriteria detached, Integer startPosition, Integer maxResults) throws RepositoryException {
        Session hibernateSession = (Session) this.manager.getDelegate();
        Criteria criteria = detached.getExecutableCriteria(hibernateSession);
        if(startPosition!=null){
            criteria.setFirstResult(startPosition);
        }
        if(maxResults!=null){
            criteria.setMaxResults(maxResults);
        }
        return (R) criteria.list();
    }

    @SuppressWarnings(value="true")
    public Object uniqueResult(DetachedCriteria detached) throws RepositoryException {
        Session hibernateSession = (Session) this.manager.getDelegate();
        return detached.getExecutableCriteria(hibernateSession).uniqueResult();
    }

    public <T> List<T> listByQuery(Class<T> clazz,String query) throws RepositoryException {
        javax.persistence.Query queryBy = this.manager.createNativeQuery(query,clazz);
        return queryBy.getResultList();
    }
    
}

