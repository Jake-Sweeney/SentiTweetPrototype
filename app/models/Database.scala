package models

/**
 * Created by Jake on 31/10/2015.
 */

import sorm._

object Database extends Instance(entities = Seq(Entity[Person]()), url= "jdbc:h2:mem:test")