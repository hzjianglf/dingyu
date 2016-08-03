/**
 * RtxService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lmd.timer.service.rtx;

public interface RtxService extends java.rmi.Remote {
    public java.lang.String sendNotify(java.lang.String receivers, java.lang.String title, java.lang.String msg, java.lang.String delayTime, java.lang.String arg4) throws java.rmi.RemoteException;
    public java.lang.String deleteUser(java.lang.String userName) throws java.rmi.RemoteException;
    public java.lang.String deleteDept(java.lang.String deptId, java.lang.String type) throws java.rmi.RemoteException;
    public java.lang.String addUser(java.lang.String userName, java.lang.String deptID, java.lang.String chsName, java.lang.String pwd) throws java.rmi.RemoteException;
    public java.lang.String sendSms(java.lang.String sender, java.lang.String receiver, java.lang.String smsInfo, int autoCut, int noTitle) throws java.rmi.RemoteException;
    public java.lang.String addDept(java.lang.String deptId, java.lang.String detpInfo, java.lang.String deptName, java.lang.String parentDeptId) throws java.rmi.RemoteException;
}
