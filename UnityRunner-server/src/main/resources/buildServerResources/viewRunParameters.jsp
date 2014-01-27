<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<tr>
    <th>
        <label for="teamcity.build.workingDir">Working Directory: </label>
    </th>
    <td>
        <props:textProperty name="teamcity.build.workingDir" style="width:32em;"/>
        <span class="error" id="error_teamcity.build.workingDir"></span>
        <span class="smallNote">
             Optional, specify if differs from the checkout directory.
        </span>
    </td>
</tr>

<tr>
    <th>
        <label for="argument.project_path">Project path: </label>
    </th>
    <td>
        <props:textProperty name="argument.project_path" style="width:32em;"/>
        <span class="error" id="error_argument.project_path"></span>
        <span class="smallNote">
             Open the project at the given path.
        </span>
    </td>
</tr>
<tr>
    <td>
        <c:set var="actionSelected" value="unityActionSelected"/>
        <props:selectSectionProperty name="unityAction" title="Unity Action" onchange="UnityRunner.onActionChanged()">
            <props:selectSectionPropertyContent value="" caption="Do nothing"/>
            <props:selectSectionPropertyContent value="buildWindowsPlayer" caption="Build Windows Player"/>
            <props:selectSectionPropertyContent value="buildOSXPlayer" caption="Build OSX Player"/>
            <props:selectSectionPropertyContent value="buildWebPlayer" caption="Build Web Player" />
            <props:selectSectionPropertyContent value="executeMethod" caption="Execute Custom Method" />
        </props:selectSectionProperty>
    </td>
</tr>

<tr id="execute_method_to_execute" style="display:none">
    <th>
        <label for="argument.execute_method">Execute method: </label>
    </th>
    <td>
        <props:textProperty name="argument.execute_method" style="width:32em;"/>
        <span class="error" id="error_argument.execute_method"></span>
        <span class="smallNote">
             Execute the static method as soon as Unity is started and the project folder has been opened.
        </span>
    </td>
</tr>

<tr>
    <th>
        <label for="argument.no_graphics">No graphics: </label>
    </th>
    <td>
        <props:checkboxProperty name="argument.no_graphics"/>
        <span class="error" id="error_argument.no_graphics"></span>
        <span class="smallNote">
             When running in batch mode, do not initialize graphics device at all. This makes it possible to run your
             automated workflows on machines that don't even have a GPU (automated workflows only work, when you have a
             window in focus, otherwise you can't send simulated input commands). A standalone player generated with
             this option will not feature any graphics.
        </span>
    </td>
</tr>

<script type="text/javascript">
  UnityRunner = {
    onActionChanged:function () {
      var sel = $('unityAction');
      var selectedValue = sel[sel.selectedIndex].value;
      var executeTD = $('execute_method_to_execute');
      if ('executeMethod' == selectedValue) {
        //show the method prompt
        executeTD.style.display = 'block';

      } else {
        //hide the method prompt
        executeTD.style.display = 'none';
      }
    }
  };
</script>