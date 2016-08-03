/**
 * SmsManagerLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.test.client;

public class SmsManagerLocator extends org.apache.axis.client.Service implements com.test.client.SmsManager {

    public SmsManagerLocator() {
    }


    public SmsManagerLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SmsManagerLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SmsManagerPort
    private java.lang.String SmsManagerPort_address = "http://192.168.1.131:8000/services/smsManager/SmsManager";

    public java.lang.String getSmsManagerPortAddress() {
        return SmsManagerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SmsManagerPortWSDDServiceName = "SmsManagerPort";

    public java.lang.String getSmsManagerPortWSDDServiceName() {
        return SmsManagerPortWSDDServiceName;
    }

    public void setSmsManagerPortWSDDServiceName(java.lang.String name) {
        SmsManagerPortWSDDServiceName = name;
    }

    public com.test.client.SmsManagerPortType getSmsManagerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SmsManagerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSmsManagerPort(endpoint);
    }

    public com.test.client.SmsManagerPortType getSmsManagerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.test.client.SmsManagerSoapBindingStub _stub = new com.test.client.SmsManagerSoapBindingStub(portAddress, this);
            _stub.setPortName(getSmsManagerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSmsManagerPortEndpointAddress(java.lang.String address) {
        SmsManagerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.test.client.SmsManagerPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.test.client.SmsManagerSoapBindingStub _stub = new com.test.client.SmsManagerSoapBindingStub(new java.net.URL(SmsManagerPort_address), this);
                _stub.setPortName(getSmsManagerPortWSDDServiceName());
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
        if ("SmsManagerPort".equals(inputPortName)) {
            return getSmsManagerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://manager.sms.mas.lmd/", "SmsManager");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://manager.sms.mas.lmd/", "SmsManagerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SmsManagerPort".equals(portName)) {
            setSmsManagerPortEndpointAddress(address);
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
