/**
 * Copyright (c) 2002-2012 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.docgen.cookbook

import org.junit.Test
import org.junit.Assert._
import org.neo4j.cypher.docgen.DocumentingTestBase

class PrettyGraphsFriendshipGraphTest extends DocumentingTestBase {
  def graphDescription = List()
  def section = "cookbook"
  generateInitialGraphForConsole = false


  @Test def completeGraph() {
    testQuery(
      title = "Friendship graph",
      text =
"""This query first creates a center node, and then once per element in the range, creates a cycle graph and connects it to the center""",
      queryText = """CREATE center
foreach( x in range(1,3) : 
   CREATE leaf1, leaf2, center-[:X]->leaf1, center-[:X]->leaf2, leaf1-[:X]->leaf2
)
RETURN ID(center) as id""",
      returns =
"""The id of the center node is returned by the query.""",
      assertions = (p) => assertEquals(List(Map("id" -> 1)),p.toList))
  } 
}