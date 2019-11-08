import net.jackadull.build.dependencies.JackadullDependencies._

lazy val jackadull = net.jackadull.build.JackadullBuild.onTravis(name = "jackadocs", version = "0.5.1-SNAPSHOT",
  basePackage = "net.jackadull.jackadocs", capitalizedIdentifier = "Jackadocs")

lazy val jackadocsBuild:Project =
  project.in(file(".")).configure(jackadull.project.withDocs(docs), jackadull.dependencies(ScalaTest % Test, ScalaXML))

lazy val docs = project.in(file("docs")).configure(jackadull.docs)
