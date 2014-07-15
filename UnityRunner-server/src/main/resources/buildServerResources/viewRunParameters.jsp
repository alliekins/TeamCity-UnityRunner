<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<div class="parameter">
        <label for="argument.unity_path">Working Directory: </label>
        <c:choose>
            <c:when test="${not empty propertiesBean.properties['argument.unity_path']}">
                <props:displayValue name="argument.unity_path" style="width:32em;"/>
            </c:when>
            <c:otherwise>
                <strong>"unity"</strong>
            </c:otherwise>
        </c:choose>
</div>

<div class="parameter">
        <label for="teamcity.build.workingDir">Working Directory: </label>
        <c:choose>
            <c:when test="${not empty propertiesBean.properties['teamcity.build.workingDir']}">
                <props:displayValue name="teamcity.build.workingDir" style="width:32em;"/>
            </c:when>
            <c:otherwise>
                <strong>Same as checkout directory</strong>
            </c:otherwise>
        </c:choose>
</div>

<div class="parameter">
        <label for="argument.logfile">Log file: </label>
        <c:choose>
            <c:when test="${not empty propertiesBean.properties['argument.logfile']}">
                <props:displayValue name="argument.logfile" style="width:32em;"/>
            </c:when>
            <c:otherwise>
                <strong>None</strong>
            </c:otherwise>
        </c:choose>
</div>

<div class="parameter">
        <label for="argument.project_path">Project path: </label>
        <c:choose>
            <c:when test="${not empty propertiesBean.properties['argument.project_path']}">
                <props:displayValue name="argument.project_path" style="width:32em;"/>
            </c:when>
            <c:otherwise>
                <strong>Same as working directory</strong>
            </c:otherwise>
        </c:choose>
</div>

<div class="parameter">
        <label for="argument.unity_action">Unity action to perform: </label>
        <props:displayValue name="argument.unity_action" style="width:32em;" />
</div>

<c:choose>
    <c:when test="${not empty propertiesBean.properties['argument.execute_method']}">
        <div class="parameter">
            <label for="argument.execute_method">Execute method: </label>
            <props:displayValue name="argument.execute_method" style="width:32em;"/>
         </div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${not empty propertiesBean.properties['argument.execute_method']}">
        <div class="parameter">
            <label for="argument.extra_params">Extra params: </label>
            <props:displayValue name="argument.extra_params" style="width:32em;"/>
         </div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${propertiesBean.properties['argument.execute_method'] == 'exportPackage'}">
        <tr id="export_packages_to_export">
            <th>
                <label for="argument.export_packages">Export Path: </label>
            </th>
            <td>
                <props:displayValue name="argument.export_packages" style="width:32em;"/>
            </td>
        </tr>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${propertiesBean.properties['argument.execute_method'] == 'exportPackage'}">
        <tr id="export_package_filename_td">
            <th>
                <label for="argument.export_package_filename">UnityPackage Filename: </label>
            </th>
            <td>
                <props:displayValue name="argument.export_package_filename" style="width:32em;"/>
            </td>
        </tr>
    </c:when>
</c:choose>

<!---------------- end switching text box block ------------>
<div class="parameter">
        <label for="argument.no_graphics">No graphics: </label>
        <c:choose>
            <c:when test="${propertiesBean.properties['argument.no_graphics']}">
                <strong>true</strong>
            </c:when>
            <c:otherwise>
                <strong>false</strong>
            </c:otherwise>
        </c:choose>
</div>

<c:choose>
    <c:when test="${not propertiesBean.properties['argument.no_graphics']}">
        <div class="parameter">
            <label for="argument.force_graphics">Force graphics engine (Windows only): </label>
            <props:displayValue name="argument.force_graphics" />
        </div>
    </c:when>
</c:choose>