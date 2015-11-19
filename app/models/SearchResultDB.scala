package models

import sorm._

/**
 * Created by Jake on 18/11/2015.
 */
object SearchResultDB extends Instance(entities = Seq(Entity[SearchResult]()), url= "jdbc:h2:mem:test")
