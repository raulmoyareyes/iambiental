<%@page import="es.ujaen.iambiental.clienteuso.modelos.Dependencia"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    JSONObject obj = new JSONObject();
    Map m = new HashMap();
    List l = (ArrayList) request.getAttribute("dependencias");
    for(int i=0; i<l.size();i++){
        Dependencia d = (Dependencia) l.get(i);
        m.put(d.getId(), d);
    }
    obj.putAll(m);
//    obj.put("name", "foo");
//    obj.put("num", new Integer(100));
//    obj.put("balance", new Double(1000.21));
//    obj.put("is_vip", new Boolean(true));
//    obj.put("nickname", null);
    out.print(obj);
    out.flush();
%>