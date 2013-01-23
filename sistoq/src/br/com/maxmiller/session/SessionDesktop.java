/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.maxmiller.session;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Max
 */
public class SessionDesktop {

      //private static Map<String,Object> session = null;
      private static Dictionary<String,Object>  session = null;

      public static void addItemSession(String nome,Object obj)
      {
            if(session==null)
            {
                session = new Hashtable<String, Object>();
            }
            session.put(nome, obj);
      }

      public static Object getItemSession(String nome)throws NullPointerException
      {     if(session!=null)
            {
                return session.get(nome);
            }
            else{
                return null;
            }
      }
      public static Object removeItemSession(String nome)throws NullPointerException
      {
          if(session!=null){
              return session.remove(nome);
          }else{
              return null;
          }
      }
      
}
