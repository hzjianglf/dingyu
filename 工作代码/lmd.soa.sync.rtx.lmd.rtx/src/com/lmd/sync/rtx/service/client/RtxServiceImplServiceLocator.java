/**
 * RtxServiceImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lmd.sync.rtx.service.client;

public class RtxServiceImplServiceLocator extends org.apache.axis.client.Service implements com.lmd.sync.rtx.service.client.RtxServiceImplService {

    public RtxServiceImplServiceLocator() {
    }


    public RtxServiceImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RtxServiceImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RtxServiceImplPort
    private java.lang.String RtxServiceImplPort_address = "http://10.10.10.133:8080/rtxservice/RtxService";

    public java.lang.String getRtxServiceImplPortAddress() {
        return RtxServiceImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String RtxServiceImplPortWSDDServiceName = "RtxServiceImplPort";

    public java.lang.String getRtxServiceImplPortWSDDServiceName() {
        return RtxServiceImplPortWSDDServiceName;
    }

    public void setRtxServiceImplPortWSDDServiceName(java.lang.String name) {
        RtxServiceImplPortWSDDServiceName = name;
    }

    public com.lmd.sync.rtx.service.client.RtxService getRtxServiceImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RtxServiceImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRtxServiceImplPort(endpoint);
    }

    public com.lmd.sync.rtx.service.client.RtxService getRtxServiceImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub _stub = new com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getRtxServiceImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRtxServiceImplPortEndpointAddress(java.lang.String address) {
        RtxServiceImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.lmd.sync.rtx.service.client.RtxService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub _stub = new com.lmd.sync.rtx.service.client.RtxServiceImplServiceSoapBindingStub(new java.net.URL(RtxServiceImplPort_address), this);
                _stub.setPortName(getRtxServiceImplPortWSDDServiceName());
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
        if ("RtxServiceImplPort".equals(inputPortName)) {
            return getRtxServiceImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.cxf.lmd.com/", "RtxServiceImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.cxf.lmd.com/", "RtxServiceImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RtxServiceImplPort".equals(portName)) {
            setRtxServiceImplPortEndpointAddress(address);
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
