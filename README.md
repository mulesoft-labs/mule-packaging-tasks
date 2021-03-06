Ant Tasks for Mule Applications
===============================

Ant is a tool for building Java applications, which can be extended by adding custom tasks.  These tasks will let you build and deploy Mule applications.

To use these tasks:

<ul>
    <li>Add <code>-lib mule-anttasks.jar</code> to your Ant command line, to put the classes that implement the tasks in Ant's classpath.</li>
    <li>Add <code>&lt;taskdef resource=&quot;org/mule/mulePackagingTasks.properties&quot;/&gt;</code> to your Ant build file,
        to import the task definitions.</li>
</ul>


<h2><a name="mulePackage">mulePackage</a></h2>
<h3>Description</h3>
<p>Create a Mule application archive.</p>

<h3>Parameters</h3>
<table border="1" cellpadding="6" cellspacing="2">
    <tr>
        <td valign="top"><b>Attribute</b></td>
        <td valign="top"><b>Description</b></td>
        <td align="center" valign="top"><b>Required</b></td>
    </tr>

    <tr>
        <td valign="top">applicationFile</td>
        <td valign="top">the application file to be created</td>
        <td align="center" valign="top">Yes</td>
    </tr>
</table>

<h3>Parameters specified as nested elements</h3>
<table border="1" cellpadding="6" cellspacing="2">
    <tr>
        <td valign="top"><b>Element</b></td>
        <td valign="top"><b>Description</b></td>
    </tr>
    <tr>
        <td>config</td>
        <td valign="top">A <a href="http://ant.apache.org/manual/Types/fileset.html">FileSet</a> specifying configuration files
            to be placed at the top level of the application.</td>
    </tr>
    <tr>
        <td>lib</td>
        <td valign="top">A <a href="http://ant.apache.org/manual/Types/fileset.html">FileSet</a> specifying jar files
            to be placed in the application's <i>lib</i> folder.</td>
    </tr>
    <tr>
        <td>classes</td>
        <td valign="top">A <a href="http://ant.apache.org/manual/Types/fileset.html">FileSet</a> specifying class and resource files
            to be placed in the application's <i>classes</i> folder.</td>
    </tr>
</table>

<h3>Examples</h3>

<pre>&lt;mulePackage applicationFile=&quot;${app.file}&quot;&gt;
  &lt;config dir=&quot;src/app&quot;/&gt;
  &lt;lib dir=&quot;target/lib&quot;/&gt;
&lt;/mulePackage&gt;</pre>

<p>packages the application <code>${app.file}</code> using the
configuration files in the <code>config</code> directory and the jars in the
<code>lib</code> directory. In this example, there were no classes or resources outside the jars.</p>

<pre>&lt;mulePackage applicationFile=&quot;${app.file}&quot;&gt;
  &lt;config dir=&quot;src/app&quot;/&gt;
  &lt;classes dir=&quot;target/classes&quot;/&gt;
  &lt;lib dir=&quot;target/lib&quot;/&gt;
&lt;/mulePackage&gt;</pre>

<p>packages the application <code>${app.file}</code> using the
configuration files in the <code>config</code> directory, the
classes and resources in the <code>classes</code> directory, and the jars in the
<code>lib</code> directory.</p>

<p></p>

<h2><a name="muleDeploy">muleDeploy</a></h2>
<h3>Description</h3>

<p>Deploys a Mule application archive.</p>

<h3>Parameters</h3>
<table border="1" cellpadding="6" cellspacing="2">
    <tr>
        <td valign="top"><b>Attribute</b></td>
        <td valign="top"><b>Description</b></td>
        <td align="center" valign="top"><b>Required</b></td>
    </tr>

    <tr>
        <td valign="top">applicationFile</td>
        <td valign="top">the application file to deploy</td>
        <td align="center" valign="top">Yes</td>
    </tr>

    <tr>
        <td valign="top">muleHome</td>
        <td valign="top">the base directory for the Mule installation.</td>
        <td align="center" valign="top">No</td>
    </tr>
</table>

<p></p>

If <i>muleHome</i> is not specified, the value of the property <i>dir.mule.home</i> is used.

<h3>Examples</h3>

<pre>&lt;muleDeploy applicationFile=&quot;${app.file}&quot;/&gt;
</pre>

<p>deploys the application <code>${app.file}</code> to the mule installation at

<code>${dir.mule.home}</code>, which can be set either in the Ant build file or on the Ant command line. </p>

<pre>&lt;muleDeploy applicationFile=&quot;${app.file}&quot; muleHome=&quot;/usr/opt/mule&quot;/&gt;
</pre>

<p>deploys the application <code>${app.file}</code> to the mule installation at

<code>/usr/opt/mule</code>. </p>


<h2><a name="cloudHubDeploy">cloudHubDeploy</a></h2>
<h3>Description</h3>

<p>Deploys a Mule application archive to CloudHub.</p>

<h3>Parameters</h3>
<table border="1" cellpadding="6" cellspacing="2">
    <tr>
        <td valign="top"><b>Attribute</b></td>
        <td valign="top"><b>Description</b></td>
        <td align="center" valign="top"><b>Required</b></td>
    </tr>

    <tr>
        <td valign="top">applicationFile</td>
        <td valign="top">the application file to deploy</td>
        <td align="center" valign="top">Yes</td>
    </tr>

    <tr>
        <td valign="top">domain</td>
        <td valign="top">the CloudHub domain of the application.</td>
        <td align="center" valign="top">Yes</td>
    </tr>
    <tr>
        <td valign="top">username</td>
        <td valign="top">the CloudHub account username.</td>
        <td align="center" valign="top">Yes</td>
    </tr>
    <tr>
        <td valign="top">password</td>
        <td valign="top">the CloudHub account password.</td>
        <td align="center" valign="top">Yes</td>
    </tr>
</table>

<h3>Examples</h3>

<pre>&lt;cloudHubDeploy applicationFile=&quot;${app.file}&quot; domain=&quot;${app.name}&quot;/&gt; username="myUsername" password="myPassword" /&gt;
</pre>

<p>deploys the application <code>${app.file}</code> to CloudHub 
