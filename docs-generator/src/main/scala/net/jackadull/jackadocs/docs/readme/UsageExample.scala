package net.jackadull.jackadocs.docs.readme

import net.jackadull.jackadocs.structure.{Chapter, RootChapter}

import scala.xml.NodeSeq

object UsageExample extends Chapter {
  def title = "Usage Example"

  def contents(root:RootChapter):NodeSeq =
<p>
  As described, there is no special magic in using Jackadocs.
  Because there are many ways to use the tools presented by the Jackadocs library, the best introduction is an example.
  The reader can then make up his or her own way of preference of using Jackadocs.
</p>
<p>
  The sub-module and code that generates this text serves as the example.
  It can be found in the <a href="https://github.com/jackadull/jackadocs/tree/master/docs-generator"><tt>docs-generator</tt></a> subfolder of the <tt>jackadocs</tt> project.
  In it, you will find:
</p>
<ul>
  <li>A separate <tt>pom.xml</tt> for the documentation-generating project.</li>
  <li>Scala souce code under <tt>src/main/scala</tt> that contains all the data for generating this documentation.</li>
</ul>

  override def subChapters:Seq[Chapter] = Seq(
    Chapter("How to Re-Generate this Documentation",
<p>
  The documentation of Jackadocs (i.e., the <tt>README.md</tt> in the <tt>jackadocs</tt> project root folder) is (re-)generated by choosing <tt>docs-generator</tt> as the current working directory and executing:
</p>
<pre><code class="language-bash">
mvn clean compile exec:java
</code></pre>
<p>
  Maven will then clean up the target folder, compile the project, and run the main class.
  The details of this execution are defined in <tt>docs-generator/pom.xml</tt>, in the configuration of the <tt>exec-maven-plugin</tt>:
  As can bee seen there, the main class (i.e., the class that contains the <tt>main</tt> method) is <tt>net.jackadull.jackadocs.docs.Main</tt>.
  Also, the first (and only) command-line argument for the execution is configured as <tt>${{project.basedir}}</tt>.
  This means that the path to the <tt>docs-generator</tt> project folder will be passed to the <tt>main</tt> method.
</p>
    ),
    Chapter("The Main Class",
<p>
  The source code of the main class can be found <a href="https://github.com/jackadull/jackadocs/blob/kickoff/docs-generator/src/main/scala/net/jackadull/jackadocs/docs/Main.scala">here</a>.
  The main class does very few things:
</p>
<ul>
  <li>
    <p>Create the main <tt>Jackadocs</tt> instance:</p>
<pre><code class="language-scala">
val jackadocs = Jackadocs fromArgs args
</code></pre>
    <p>
      The command-line arguments are passed to the factory method.
      This way, the <tt>Jackadocs</tt> instance knows the project root folder.
    </p>
  </li>
  <li>
    <p>
      Check if dependency for the main project has the right version:
    </p>
<pre><code class="language-scala">
jackadocs.requirePOMVersion("../pom.xml")(JackadocsInfo Version)
</code></pre>
    <p>
      Asserts that the nominal version of the parent library dependency is actually the same as the version declared in the <tt>pom.xml</tt> of the parent folder.
      Every time the version of the main Jackadocs <tt>pom.xml</tt> is changed, the dependency version in <tt>docs-generator/pom.xml</tt> must also be changed.
      This line of code will ensure that this has happened.
    </p>
    <p>
      <tt>requirePOMVersion</tt> reads the <tt>pom.xml</tt> at the given relative path.
      (That is, relative to the <tt>docs-generator</tt> project folder.)
      It then uses a simple XML lookup to find the declared project version.
      This must be a constant, which is also encouraged by Maven.
      After it found the POM's project version, it compares the string with the second given argument.
      If they do not match, an exception is thrown.
    </p>
    <p>
      In this case, the version number to compare against is given as <tt>JackadocsInfo.Version</tt>.
      This is possible because the parent project is configured in such a way that Maven will generate a static class called <tt>JackadocsInfo</tt>, which contains the Maven module version as the constant named <tt>Version</tt>.
    </p>
    <p>
      If you are interested in copying this behaviour, take a look at the Jackadocs main <tt>pom.xml</tt>.
      The <tt>templating-maven-plugin</tt> does the job of creating said static class.
      The template can be found under <tt>src/main/scala-templates</tt>.
    </p>
  </li>
  <li>
    <p>Generate the <tt>README.md</tt> file:</p>
<pre><code class="language-scala">
jackadocs generateAt "../README.md" markdownFor ReadmeRoot
</code></pre>
    <p>
      Tells Jackadocs to generate the Markdown for <tt>ReadmeRoot</tt> and write it into <tt>"../README.md"</tt>, relative to the project base directory that was passed in as a command-line argument.
    </p>
    <p>
      <tt>ReadmeRoot</tt> can be found <a href="https://github.com/jackadull/jackadocs/blob/master/docs-generator/src/main/scala/net/jackadull/jackadocs/docs/ReadmeRoot.scala">here</a>.
      The contents of this object follow the chapter structure, which gets described next.
    </p>
  </li>
</ul>
    )
  )
}
