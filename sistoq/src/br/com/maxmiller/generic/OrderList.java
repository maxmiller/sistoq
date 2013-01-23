/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.maxmiller.generic;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.internal.util.StringHelper;

/**
 *
 * @author Max
 */
public class OrderList {

        private final boolean ignoreCase;
	private final boolean asc;
	private final String property;
	private final String nativeSql;

	public static OrderList asc(String property) {
		return asc(property,true);
	}

	public static OrderList asc(String property, boolean ignoreCase) {
		return new OrderList(property, true, ignoreCase);
	}

	public static OrderList desc(String property) {
		return desc(property,true);
	}

	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public boolean isAsc() {
		return asc;
	}

	public String getProperty() {
		return property;
	}

	public static OrderList desc(String property, boolean ignoreCase) {
		return new OrderList(property, false, ignoreCase);
	}

	private OrderList(String property,boolean asc, boolean ignoreCase) {
		this(property,asc,ignoreCase,null);
	}

	private OrderList(String property,boolean asc, boolean ignoreCase, String nativeSql) {
		this.property = property;
		this.asc = asc;
		this.ignoreCase = ignoreCase;
		this.nativeSql = nativeSql;
	}

	private OrderList(String nativeSql,boolean asc) {
		this(null,asc,false,nativeSql);
	}

	public static OrderList nativeSql(String nativeSql) {
		return new OrderList(nativeSql,true);
	}

	public boolean isNativeSql() {
		return nativeSql != null;
	}

	public String getNativeSql() {
		return nativeSql;
	}

	public Order toHibernateOrder() {
		if (isNativeSql())
			return toNativeOrder();
		Order o = isAsc() ? Order.asc(getProperty()) : Order.desc(getProperty());
		if (isIgnoreCase())
			o = o.ignoreCase();
		return o;
	}

	private Order toNativeOrder() {
		return new Order("",asc) {
			private static final long serialVersionUID = -4003919170134306463L;

			@Override
			public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
				StringBuilder fragment = new StringBuilder();
				fragment.append("(");
				fragment.append(nativeSql);
				fragment.append(")");
				fragment.append(asc ? " asc" : " desc");
				return StringHelper.replace(fragment.toString(), "{alias}",	criteriaQuery.getSQLAlias(criteria));
			}
		};
	}

}
