/**
 * GsmManagerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lmd.timer.service.jcg;

public class GsmManagerLocator extends org.apache.axis.client.Service implements com.lmd.timer.service.jcg.GsmManager {

    public GsmManagerLocator() {
    }


    public GsmManagerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public GsmManagerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for GsmManagerPort
    private java.lang.String GsmManagerPort_address = "http://10.10.10.207:8000/services/gsmManager/GsmManager";

    public java.lang.String getGsmManagerPortAddress() {
        return GsmManagerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String GsmManagerPortWSDDServiceName = "GsmManagerPort";

    public java.lang.String getGsmManagerPortWSDDServiceName() {
        return GsmManagerPortWSDDServiceName;
    }

    public void setGsmManagerPortWSDDServiceName(java.lang.String name) {
        GsmManagerPortWSDDServiceName = name;
    }

    public com.lmd.timer.service.jcg.GsmManagerPortType getGsmManagerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(GsmManagerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getGsmManagerPort(endpoint);
    }

    public com.lmd.timer.service.jcg.GsmManagerPortType getGsmManagerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lmd.timer.service.jcg.GsmManagerSoapBindingStub _stub = new com.lmd.timer.service.jcg.GsmManagerSoapBindingStub(portAddress, this);
            _stub.setPortName(getGsmManagerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setGsmManagerPortEndpointAddress(java.lang.String address) {
        GsmManagerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lmd.timer.service.jcg.GsmManagerPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lmd.timer.service.jcg.GsmManagerSoapBindingStub _stub = new com.lmd.timer.service.jcg.GsmManagerSoapBindingStub(new java.net.URL(GsmManagerPort_address), this);
                _stub.setPortName(getGsmManagerPortWSDDServiceName());
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
        if ("GsmManagerPort".equals(inputPortName)) {
            return getGsmManagerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://manager.gsm.jcg.lmd/", "GsmManager");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://manager.gsm.jcg.lmd/", "GsmManagerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("GsmManagerPort".equals(portName)) {
            setGsmManagerPortEndpointAddress(address);
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
