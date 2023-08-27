<%-- 
    Document   : alta
    Created on : 25 ago 2023, 10:28:54
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="components/header.jsp"%>

<%@include file="components/bodyprimeraparte.jsp"%>
            <form class="user">
                                <div class="form-group col">
                                    <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="dni"
                                            placeholder="Dni">
                                    </div>
                                    <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="nombre"
                                            placeholder="nombre">
                                    </div>
                                      <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="apelido"
                                            placeholder="apellido">
                                    </div>
                                      <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="telefono"
                                            placeholder="telefono">
                                    </div>
                                      <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="direccion"
                                            placeholder="direccion">
                                    </div>
                                      <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="especialidad"
                                            placeholder="especialidad">
                                    </div>
                                      <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="fechanac"
                                            placeholder="fecha nacimiento">
                                    </div>
                                      <div class="col-sm-6 mb-3">
                                        <input type="text" class="form-control form-control-user" id="direccion"
                                            placeholder="direccion">
                                    </div>
                                </div>
                                
                                <a href="#" class="btn btn-primary btn-user btn-block">
                                    Crear Odontólogo
                                </a>
                                <hr>
                              
                            </form>
<%@include file="components/bodyfinal.jsp"%>
    