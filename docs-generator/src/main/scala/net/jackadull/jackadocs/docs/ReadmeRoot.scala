package net.jackadull.jackadocs.docs

import net.jackadull.jackadocs.docs.readme._
import net.jackadull.jackadocs.structure.badges.BadgeGenerators
import net.jackadull.jackadocs.structure.{Chapter, DocsMetaData, RootChapter}

import scala.xml.{NodeSeq, Text}

object ReadmeRoot extends RootChapter with BadgeGenerators {
  def title = Text("Jackadocs")

  override def contentsBeforeTOC(root:RootChapter):NodeSeq =
<p>{travisCIBadge} {mavenCentralBadge} {coverallsBadge} {codeFactorBadge} {snykBadge}</p>

  override def toc = true

  def contents(root:RootChapter):NodeSeq =
<p>
  Tool library for automated generation of tool documentation.
  Can be used for creating <tt>README.md</tt> files, but also for documentation books, with multiple files, in either HTML or <a href="https://github.github.com/gfm/">Github-Flavored Markdown</a>.
</p>

  override def subChapters:Seq[Chapter] = Seq(Motivation, IntentedUse, UsageExample, ChapterStructure,
    RenderingProcess, FurtherRemarks, Roadmap)

  def docsMetaData:DocsMetaData = Main
}
