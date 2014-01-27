<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>

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
        <label for="argument.logfile">Working Directory: </label>
    </th>
    <td>
        <props:textProperty name="argument.logfile" style="width:32em;"/>
        <span class="error" id="error_argument.logfile"></span>
        <span class="smallNote">
             Optional, specify a file path and name for the logfile.
        </span>
    </td>
</tr>

<tr>
    <th>
        <label for="argument.project_path">Project path: </label>
    </th>
    <td>
        <props:textProperty name="argument.project_path" style="width:32em;"/>
        <bs:vcsTree fieldId="argument.project_path"/>
        <span class="error" id="error_argument.project_path"></span>
        <span class="smallNote">
             Open the project at the given path in relation to the working directory. Optional, specify if differs from the working directory.
        </span>
    </td>
</tr>
<tr>
    <th>
        <label for="argument.unity_action">Unity action to perform: </label>
    </th>
    <td>
        <c:set var="actionSelected" value="argument.unity_action.selected"/>
        <props:selectProperty name="argument.unity_action" onchange="UnityRunner.onActionChanged()">
            <props:option value="Nothing">Do nothing </props:option>
            <props:option value="buildWindowsPlayer">Build Windows Player </props:option>
            <props:option value="buildOSXPlayer" >Build OSX Player </props:option>
            <props:option value="buildWebPlayer" >Build Web Player </props:option>
            <props:option value="exportPackage" >Export UnityPackage </props:option>
            <props:option value="executeMethod" >Execute Custom Method </props:option>
        </props:selectProperty>
    </td>
</tr>


    <tr id="execute_method_to_execute">
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


    <tr id="export_packages_to_export">
        <th>
            <label for="argument.export_packages">Export Path: </label>
        </th>
        <td>
            <props:textProperty name="argument.export_packages" style="width:32em;"/>
            <bs:vcsTree fieldId="argument.export_packages"/>
            <span class="error" id="error_argument.export_packages"></span>
            <span class="smallNote">
                 Exports a package given a path (or set of given paths).
            </span>
        </td>
    </tr>

    <tr id="export_package_filename_td">
        <th>
            <label for="argument.export_package_filename">UnityPackage Filename: </label>
        </th>
        <td>
            <props:textProperty name="argument.export_package_filename" style="width:32em;"/>
            <span class="error" id="error_argument.export_package_filename"></span>
            <span class="smallNote">
                 What to name the exported .unityPackage
            </span>
        </td>
    </tr>

<!---------------- end switching text box block ------------>
<tr>
    <th>
        <label for="argument.no_graphics">No graphics: </label>
    </th>
    <td>
        <props:checkboxProperty name="argument.no_graphics" onclick="UnityChanger.onGraphicsChanged()"/>
        <span class="smallNote">
             When running in batch mode, do not initialize graphics device at all. This makes it possible to run your
             automated workflows on machines that don't even have a GPU (automated workflows only work, when you have a
             window in focus, otherwise you can't send simulated input commands). A standalone player generated with
             this option will not feature any graphics.
        </span>
    </td>
</tr>

<tr id="tr_forceGraphics">
    <th>
        <label for="argument.force_graphics">Force graphics engine (Windows only): </label>
    </th>
    <td>
        <props:selectProperty name="argument.force_graphics">
            <props:option value="none">Use default </props:option>
            <props:option value="opengl">OpenGL </props:option>
            <props:option value="d3d9" >Direct3D 9 </props:option>
            <props:option value="d3d11" >Direct3D 11 </props:option>
        </props:selectProperty>
    </td>
</tr>


<script type="text/javascript">

    UnityRunner =
    {
        onActionChanged:function ()
        {
            var sel = $('argument.unity_action');
            var selectedValue = sel[sel.selectedIndex].value;

            var executeTD = $('execute_method_to_execute');
            var exportTD = $('export_packages_to_export');
            var exportFileTD = $('export_package_filename_td');

            if ('executeMethod' == selectedValue)
            {
                executeTD.style.display = 'table-row';
                //hide the other prompts
                exportTD.style.display = 'none';
                exportFileTD.style.display = 'none';
                //hideRow(exportTD);

            }
            else if ('exportPackage' == selectedValue)
            {
                exportTD.style.display = 'table-row';
                exportFileTD.style.display = 'table-row';
                //hide the other prompts
                executeTD.style.display = 'none';

            }
            else
            {
                //hide all prompts
                executeTD.style.display = 'none';
                exportTD.style.display = 'none';
                exportFileTD.style.display = 'none';
            }
            BS.VisibilityHandlers.updateVisibility('mainContent');
        }
    };

    UnityChanger = {
        onGraphicsChanged:function () {
                var box = $('argument.no_graphics');
                var graphicsRow = $('tr_forceGraphics');
                if (box.checked) {
                    graphicsRow.style.display = 'none';
                } else {
                    graphicsRow.style.display = 'table-row'
                }
                BS.VisibilityHandlers.updateVisibility('mainContent');
            }
     };




  //run this in case we are loading an existing configuration
  UnityRunner.onActionChanged();
  UnityChanger.onGraphicsChanged();
</script>