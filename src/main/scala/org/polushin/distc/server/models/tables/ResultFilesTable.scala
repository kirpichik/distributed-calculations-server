package org.polushin.distc.server.models.tables

import org.polushin.distc.server.models.{ResultFile, TaskResultId}
import slick.jdbc.MySQLProfile.api._

class ResultFilesTable(tag: Tag) extends Table[ResultFile](tag, "result_files") {

  def resultId = column[TaskResultId]("result_id", O.PrimaryKey)

  def filename = column[String]("filename", O.PrimaryKey)

  override def * = (resultId, filename) <> (ResultFile.tupled, ResultFile.unapply)

  def taskResult = foreignKey("task_result_fk", resultId, TableQuery[TaskResultsTable])(_.id)

}
