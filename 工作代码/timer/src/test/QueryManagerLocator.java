/**
 * QueryManagerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package test;

public class QueryManagerLocator extends org.apache.axis.client.Service implements test.QueryManager {

    public QueryManagerLocator() {
    }


    public QueryManagerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public QueryManagerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for QueryManagerPort
    private java.lang.String QueryManagerPort_address = "http://10.10.10.207:8000/services/resCountManager/queryManager";

    public java.lang.String getQueryManagerPortAddress() {
        return QueryManagerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String QueryManagerPortWSDDServiceName = "QueryManagerPort";

    public java.lang.String getQueryManagerPortWSDDServiceName() {
        return QueryManagerPortWSDDServiceName;
    }

    public void setQueryManagerPortWSDDServiceName(java.lang.String name) {
        QueryManagerPortWSDDServiceName = name;
    }

    public test.QueryManagerPortType getQueryManagerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(QueryManagerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getQueryManagerPort(endpoint);
    }

    public test.QueryManagerPortType getQueryManagerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            test.QueryManagerSoapBindingStub _stub = new test.QueryManagerSoapBindingStub(portAddress, this);
            _stub.setPortName(getQueryManagerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setQueryManagerPortEndpointAddress(java.lang.String address) {
        QueryManagerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (test.QueryManagerPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                test.QueryManagerSoapBindingStub _stub = new test.QueryManagerSoapBindingStub(new java.net.URL(QueryManagerPort_address), this);
                _stub.setPortName(getQueryManagerPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("QueryManagerPort".equals(inputPortName)) {
            return getQueryManagerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.count.res.lmd/", "QueryManager");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.count.res.lmd/", "QueryManagerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("QueryManagerPort".equals(portName)) {
            setQueryManagerPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
